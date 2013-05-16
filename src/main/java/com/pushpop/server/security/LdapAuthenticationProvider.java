/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pushpop.server.security;

import java.net.URL;
import java.util.Hashtable;
import java.util.logging.Logger;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;

import com.pushpop.server.security.IAuthenticationProvider;
import com.pushpop.server.utils.LdapQuery;
import com.pushpop.server.utils.ServerProperties;
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

    private static Hashtable<String, Object> env = new Hashtable<String, Object>();

    private static final Logger logger = Logger.getLogger(LdapAuthenticationProvider.class.getName());
    
    private static final String LDAP_URL = ServerProperties.getProperty("com.pushpop.authentication.ldap.server.url");
    
    private static final String SSL_TRUSTSTORE = ServerProperties.getProperty("com.pushpop.ssl.truststore");
    
    private static final String SSL_TRUSTSTORE_PASSWORD = ServerProperties.getProperty("com.pushpop.ssl.truststore.password");

    static {
        // Set the location of our trust store, this equates to the location of
        // our war + /.truststore/
        URL truststoreUrl = LdapAuthenticationProvider.class.getResource(SSL_TRUSTSTORE);
        
        if (truststoreUrl == null) {
            throw new RuntimeException("Unable to find your trust store url, check the com.pushpop.ssl.truststore in Configuration.properties to verify the path is correct");
        }
        
        logger.info("Usuing truststore " + truststoreUrl.getPath());
        
        System.setProperty("javax.net.ssl.trustStore", truststoreUrl.getPath());
        System.setProperty("javax.net.ssl.trustStorePassword", SSL_TRUSTSTORE_PASSWORD);

        // Set up environment for creating initial context
        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        env.put(Context.PROVIDER_URL, LDAP_URL);

        // Specify SSL ldap.hp.com requires ssl
        env.put(Context.SECURITY_PROTOCOL, "ssl");
        // By default this needs to be anonymous to make queries.
        env.put(Context.SECURITY_AUTHENTICATION, "none");
    }


    @Override
    public String getCommonName(String token) throws AuthenticationProviderException {
        String fullName = null;
        try {
            fullName = LdapQuery.getFullName(token);
        } catch (NamingException e) {
            throw new AuthenticationProviderException("Unable to retrieve common name ", e);
        }
        return fullName;
    }

    @Override
    public String getEmail(String token) {
        if (token != null) {
            return token;
        }
        return null;
    }

    
    /**
     * This will bind to the ldap server url that is configured in the
     * Configuration.properties file server using simple email/password
     * authentication. It only creates an initial context as that is all that is
     * needed to test authentication.
     * 
     * @param email
     *            e-mail address of the user equates to ldap uid.
     * @param password
     *            domain password.
     * @throws AuthenticationException
     *             if there is a naming ldap authentication problem.
     * @throws NamingException
     *             if a generic authentication problem occurs.
     */
    public String authenticate(String username, String password) throws AuthenticationException {
        // Remove the anonymous security and set it to simple so we can test
        // login.
        env.remove(Context.SECURITY_AUTHENTICATION);
        env.put(Context.SECURITY_AUTHENTICATION, "simple");

        logger.info("Attempting to authenticate user email " + username);
        
        String securityPrinicipalFormat = ServerProperties.getProperty("com.pushpop.authentication.ldap.securityprincipal");
        String securityPrincipal = String.format(securityPrinicipalFormat, username);
        
        env.put(Context.SECURITY_PRINCIPAL, securityPrincipal);
        env.put(Context.SECURITY_CREDENTIALS, password);

        // Close the context when we're done
        
        try {
            DirContext ctx = new InitialDirContext(env);
            ctx.close();
        } catch (NamingException e) {
            // wrap the exception and chunk it up
            throw new AuthenticationException("LDAP authentication failed ", e);
        }
        
        return username;
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
