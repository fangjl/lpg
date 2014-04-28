package com.hyq.lpg.filters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.opensymphony.sitemesh.webapp.SiteMeshFilter;
public class MyAjaxSiteMeshFilter extends SiteMeshFilter {
	Logger log = LoggerFactory.getLogger(MyAjaxSiteMeshFilter.class);

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filterchain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest httprequest = (HttpServletRequest)request;
		if(!StringUtils.isEmpty(httprequest.getHeader("X-PJAX"))){
			//log.info("处理  Pajax 请求:" + httprequest.getRequestURL().toString() );
			filterchain.doFilter(request, response);
			return;
		};
		
		if(!StringUtils.isEmpty(httprequest.getHeader("X-Requested-With"))){
			log.info("处理 普通 ajax 请求:" + httprequest.getRequestURL().toString() );
			filterchain.doFilter(request, response);
			return;
		}
		
		//log.info("处理  装配 请求:" + httprequest.getRequestURL().toString() );
		super.doFilter(request, response,filterchain);
	}

	
	

	
	
}
