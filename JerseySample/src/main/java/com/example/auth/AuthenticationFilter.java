package com.example.auth;

import java.io.IOException;
import java.util.Base64;
import java.util.ResourceBundle;
import java.util.StringTokenizer;

import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.ext.Provider;

import com.example.exception.UnAuthorizedException;

@Provider
public class AuthenticationFilter implements ContainerRequestFilter {
	
	//application.propertiesからユーザー名/パスワードを取得
	ResourceBundle rb = ResourceBundle.getBundle("application");
	String username = rb.getString("username");
	String password = rb.getString("password");
	
	//filter
	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		
		try {
			// Authorizationヘッダーから認証情報を取得 
			String authHeader = requestContext.getHeaderString("Authorization");
			
			// ベーシック認証情報をデコード 
			String encodedCredentials = authHeader.substring("Basic ".length()).trim(); 
			String credentials = new String(Base64.getDecoder().decode(encodedCredentials)); 
			
			// ユーザー名とパスワードを解析 
			StringTokenizer tokenizer = new StringTokenizer(credentials, ":"); 
			String getUsername = tokenizer.nextToken(); 
			String getPassword = tokenizer.nextToken();
			
			//認証処理
			if(!(username.equals(getUsername) && password.equals(getPassword))) {
				//認証失敗 の場合はUnAuthorizedExceptionをthrow
				throw new UnAuthorizedException(); 
			}
			
		}catch(Exception e){
			throw new UnAuthorizedException(); 
		}
		
	}

}
