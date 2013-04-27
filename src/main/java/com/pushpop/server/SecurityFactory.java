/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pushpop.server;


/**
 * 
 * @author chinshaw
 */
public class SecurityFactory {

    private static ISecurity impl = null;
    
    
    public static ISecurity getSecurity() {
        if (impl == null) {
         impl = new Security(new LdapAuthenticationProvider());  
        }
        return impl;
    }
}
