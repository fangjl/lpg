package com.hyq.lpg.cas;

import org.apache.shiro.SecurityUtils;
public class CasUtils {
	private CasUtils(){};
	public static RemotePrincipal getCurrentPrincipal(){
			return (RemotePrincipal)SecurityUtils.getSubject().getPrincipal();
		
	}
}
