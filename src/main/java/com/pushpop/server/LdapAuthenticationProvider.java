/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pushpop.server;


import com.pushpop.shared.AuthenticationException;
import com.pushpop.shared.AuthenticationProviderException;
import com.pushpop.shared.NotImplementedException;

/**
 * This is the security implementation for GAE. It is used to implement security
 * for the App engine.
 * 
 * @author chinshaw
 */
public class LdapAuthenticationProvider implements IAuthenticationProvider {
    // private static final Logger logger =
    // Logger.getLogger(SecurityImpl.class.getName());

    @Override
    public String getCommonName(String token) throws AuthenticationProviderException {
        /*
        String fullName = null;
        try {
            fullName = LdapQuery.getFullName(token);
        } catch (NamingException e) {
            throw new AuthenticationProviderException("Unable to retrieve common name ", e);
        }
        */
        String fullName = "guest";
        return fullName;
    }

    @Override
    public String getEmail(String token) {
        if (token != null) {
            return token;
        }
        return null;
    }

    @Override
    public String authenticate(String email, String password) throws AuthenticationException {
        if (email == null) {
            throw new AuthenticationException("Must supply a valid HP email address");
        }
        
        /*
        try {
            LdapQuery.authenticate(email, password);
        } catch (javax.naming.AuthenticationException authException) {
            throw new AuthenticationException("Unable to authenticate user " + email);
        } catch (NamingException e) {
            throw new AuthenticationException("LDAP Naming exception occurred while communicating with ldap.hp.com", e);
        }
        */
        return email;
    }

    @Override
    public void logout(String token) {
        // Not needed.
    }

    @Override
    public void authenticate(String user, String password, AuthenticationCallback callback) throws AuthenticationException {
        throw new NotImplementedException("Use the synchronous authentication method");
    }
}
