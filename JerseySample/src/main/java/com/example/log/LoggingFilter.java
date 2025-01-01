package com.example.log;

import java.io.IOException;

import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerResponseContext;
import jakarta.ws.rs.container.ContainerResponseFilter;
import jakarta.ws.rs.ext.Provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

@Provider
public class LoggingFilter implements ContainerResponseFilter {
	
	private static final Logger logger = LoggerFactory.getLogger(LoggingFilter.class);

	//filter
	@Override
	public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext)
			throws IOException {
		
		// レスポンスステータスとメッセージをMDCに設定 
		MDC.put("status", String.valueOf(responseContext.getStatus()));
		if(String.valueOf(responseContext.getStatus()).equals("200")) {
			MDC.put("message", "SUCCESS");
		}else {
			MDC.put("message", "ERROR");
		}
		
		// ログ出力 
		logger.info(MDC.get("status"));
		logger.info(MDC.get("message"));
		
		// MDCをクリア 
		MDC.clear();
		
	}

	

}
