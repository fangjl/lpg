package com.hyq.lpg.interceptor;







import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
public class DataSourceSpyInterceptor implements MethodInterceptor {

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		// TODO Auto-generated method stub
		return invocation.proceed(); 
		
	}

	/*@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		// TODO Auto-generated method stub
		 Object result = invocation.proceed();  
		 if(SpyLogFactory.getSpyLogDelegator().isJdbcLoggingEnabled()){
			 Connection conn = (Connection)result;  
             return new ConnectionSpy(conn);  
		 }
		 
	       return result;  
	}*/

	/*private RdbmsSpecifics rdbmsSpecifics = null;  
    
    private RdbmsSpecifics getRdbmsSpecifics(Connection conn) {  
        if(rdbmsSpecifics == null) {  
            rdbmsSpecifics = DriverSpy.getRdbmsSpecifics(conn);  
        }  
        return rdbmsSpecifics;  
    }  
      
    public Object invoke(MethodInvocation invocation) throws Throwable {  
        Object result = invocation.proceed();  
        if(SpyLogFactory.getSpyLogDelegator().isJdbcLoggingEnabled()) {  
            if(result instanceof Connection) {  
                Connection conn = (Connection)result;  
                return new ConnectionSpy(conn,getRdbmsSpecifics(conn));  
            }  
        }  
        return result;  
    }  */
  

}
