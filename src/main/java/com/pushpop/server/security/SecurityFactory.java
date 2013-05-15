/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pushpop.server.security;

import com.pushpop.server.utils.ServerProperties;


/**
 * 
 * @author chinshaw
 */
public class SecurityFactory {
	
	static String SecurityProvider = ServerProperties.getProperty("com.pushpop.authentication.provider");

    private static ISecurity impl = null;
    
    public static ISecurity getSecurity() {
        if (impl == null) {
        	IAuthenticationProvider authProvider = null;
        	try {
        		Class<IAuthenticationProvider> authProviderClass = (Class<IAuthenticationProvider>) Class.forName("com.pushpop.server.security." + SecurityProvider);
        		authProvider = authProviderClass.newInstance();
        	} catch(Exception e) {
        		throw new RuntimeException("Unable to instantiate the authentication provider ", e);
        	}
        	
        	impl = new Security(authProvider);  
        }
        return impl;
    }
}
