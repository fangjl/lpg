package com.hyq.lpg.cas;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cas.CasAuthenticationException;
import org.apache.shiro.cas.CasToken;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.CollectionUtils;
import org.apache.shiro.util.StringUtils;
import org.jasig.cas.client.authentication.AttributePrincipal;
import org.jasig.cas.client.validation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
public class MyCasRealms extends AuthorizingRealm{

	 // default name of the CAS attribute for remember me authentication (CAS 3.4.10+)
    public static final String DEFAULT_REMEMBER_ME_ATTRIBUTE_NAME = "longTermAuthenticationRequestTokenUsed";
    public static final String DEFAULT_VALIDATION_PROTOCOL = "CAS";
    
    public static Logger log = LoggerFactory.getLogger(MyCasRealms.class);
    
    // 认证服务器地址前缀   -->  http://192.168.0.108:8080/cas
    private String casServerUrlPrefix;
    
    // 票据回调地址(example : http://host:port/mycontextpath/shiro-cas)
    private String casService;
    
    /* CAS protocol to use for ticket validation : CAS (default) or SAML : d
       - CAS protocol can be used with CAS server version < 3.1 : in this case, no user attributes can be retrieved from the CAS ticket validation response (except if there are some customizations on CAS server side)
       - SAML protocol can be used with CAS server version >= 3.1 : in this case, user attributes can be extracted from the CAS ticket validation response
    */
    private String validationProtocol = DEFAULT_VALIDATION_PROTOCOL;
    
    // default name of the CAS attribute for remember me authentication (CAS 3.4.10+)
    private String rememberMeAttributeName = DEFAULT_REMEMBER_ME_ATTRIBUTE_NAME;
    
    // this class from the CAS client is used to validate a service ticket on CAS server
    private TicketValidator ticketValidator;
    
    // default roles to applied to authenticated user
    private String defaultRoles;
    
    // default permissions to applied to authenticated user
    private String defaultPermissions;
    
    // names of attributes containing roles
    private String roleAttributeNames;
    
    // names of attributes containing permissions
    private String permissionAttributeNames;
    
    public MyCasRealms() {
        setAuthenticationTokenClass(CasToken.class);
    }

    @Override
    protected void onInit() {
        super.onInit();
        
        
        ensureTicketValidator();
    }

    protected TicketValidator ensureTicketValidator() {
        if (this.ticketValidator == null) {
            this.ticketValidator = createTicketValidator();
        }
        return this.ticketValidator;
    }
    
    protected TicketValidator createTicketValidator() {
        String urlPrefix = getCasServerUrlPrefix();
        if ("saml".equalsIgnoreCase(getValidationProtocol())) {
             Saml11TicketValidator sam= new Saml11TicketValidator(urlPrefix);
             sam.setEncoding("UTF-8");
             
        }
        Cas20ServiceTicketValidator cas20 =   new Cas20ServiceTicketValidator(urlPrefix);
        cas20.setEncoding("UTF-8");
        return cas20;
        
    }
    
    /**
     * Authenticates a user and retrieves its information.
     * 
     * @param token the authentication token
     * @throws AuthenticationException if there is an error during authentication.
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        CasToken casToken = (CasToken) token;
        if (token == null) {
            return null;
        }
        
        String ticket = (String)casToken.getCredentials();
        if (!StringUtils.hasText(ticket)) {
            return null;
        }
        
        
        
        log.info("票据："  +"《"+ticket+"》");
        TicketValidator ticketValidator = ensureTicketValidator();
        
        try {
            // contact CAS server to validate service ticket
            Assertion casAssertion = ticketValidator.validate(ticket, getCasService());
            
            
            
            log.info("验证票据成功@   ---");
            
            
            
            
            // get principal, user id and attributes
            AttributePrincipal casPrincipal = casAssertion.getPrincipal();
            String userId = casPrincipal.getName();
           
            
            log.info("userId  :---  " +userId);
            log.debug("Validate ticket : {} in CAS server : {} to retrieve user : {}", new Object[]{
                    ticket, getCasServerUrlPrefix(), userId
            });

            Map<String, Object> attributes = casPrincipal.getAttributes();
            log.debug("attributes",attributes.toString());

            /*
            JsonMapper jm = new JsonMapper(Include.NON_NULL);
    		FillRecords rf = jm.fromJson(attributes.toString(), FillRecords.class);
            */
            
            log.info("attributes  :---  " +attributes);
            // refresh authentication token (user id + remember me)
            casToken.setUserId(userId);
            String rememberMeAttributeName = getRememberMeAttributeName();
            
            log.info("rememberMeAttributeName  :---  " +rememberMeAttributeName);
            String rememberMeStringValue = (String)attributes.get(rememberMeAttributeName);
            
            log.info("rememberMeAttributeName  :---  " +rememberMeAttributeName);
            boolean isRemembered = rememberMeStringValue != null && Boolean.parseBoolean(rememberMeStringValue);
            
            log.info("isRemembered  :---  " +isRemembered);
            if (isRemembered) {
                casToken.setRememberMe(true);
            }
            // create simple authentication info
            List<Object> principals = CollectionUtils.asList(userId, attributes);
            
            log.info("principals  :---  " +principals);
            
            //return new SimpleAuthenticationInfo(principalCollection, ticket);

            return new SimpleAuthenticationInfo(new RemotePrincipal(casPrincipal), ticket,getName());
            
            
        } catch (TicketValidationException e) { 
        	
            throw new CasAuthenticationException("Unable to validate ticket [" + ticket + "]", e);
        }
    }
    
    /**
     * Retrieves the AuthorizationInfo for the given principals (the CAS previously authenticated user : id + attributes).
     * 
     * @param principals the primary identifying principals of the AuthorizationInfo that should be retrieved.
     * @return the AuthorizationInfo associated with this principals.
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
    	
    	
    	RemotePrincipal casPrincipal = (RemotePrincipal)principals.getPrimaryPrincipal();
    	
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.addRole(casPrincipal.getRolename());
        addPermissions(simpleAuthorizationInfo, split(casPrincipal.getPermissions()));
        return simpleAuthorizationInfo;
    }
    
    /**
     * Split a string into a list of not empty and trimmed strings, delimiter is a comma.
     * 
     * @param s the input string
     * @return the list of not empty and trimmed strings
     */
    private List<String> split(String s) {
        List<String> list = new ArrayList<String>();
        String[] elements = StringUtils.split(s, ',');
        if (elements != null && elements.length > 0) {
            for (String element : elements) {
                if (StringUtils.hasText(element)) {
                    list.add(element.trim());
                }
            }
        }
        return list;
    }
    
    
    
    /**
     * Add permissions to the simple authorization info.
     * 
     * @param simpleAuthorizationInfo
     * @param permissions the list of permissions to add
     */
    private void addPermissions(SimpleAuthorizationInfo simpleAuthorizationInfo,List<String> permissions) {
        for (String permission : permissions) {
            simpleAuthorizationInfo.addStringPermission(permission);
        }
    }

    public String getCasServerUrlPrefix() {
        return casServerUrlPrefix;
    }

    public void setCasServerUrlPrefix(String casServerUrlPrefix) {
        this.casServerUrlPrefix = casServerUrlPrefix;
    }

    public String getCasService() {
        return casService;
    }

    public void setCasService(String casService) {
        this.casService = casService;
    }

    public String getValidationProtocol() {
        return validationProtocol;
    }

    public void setValidationProtocol(String validationProtocol) {
        this.validationProtocol = validationProtocol;
    }

    public String getRememberMeAttributeName() {
        return rememberMeAttributeName;
    }

    public void setRememberMeAttributeName(String rememberMeAttributeName) {
        this.rememberMeAttributeName = rememberMeAttributeName;
    }

    public String getDefaultRoles() {
        return defaultRoles;
    }

    public void setDefaultRoles(String defaultRoles) {
        this.defaultRoles = defaultRoles;
    }

    public String getDefaultPermissions() {
        return defaultPermissions;
    }

    public void setDefaultPermissions(String defaultPermissions) {
        this.defaultPermissions = defaultPermissions;
    }

    public String getRoleAttributeNames() {
        return roleAttributeNames;
    }

    public void setRoleAttributeNames(String roleAttributeNames) {
        this.roleAttributeNames = roleAttributeNames;
    }

    public String getPermissionAttributeNames() {
        return permissionAttributeNames;
    }

    public void setPermissionAttributeNames(String permissionAttributeNames) {
        this.permissionAttributeNames = permissionAttributeNames;
    }

	
}
