package com.pushpop.server.service;

import com.pushpop.server.domain.Person;
import com.pushpop.server.security.ISecurity;
import com.pushpop.server.security.SecurityFactory;
import com.pushpop.shared.AuthenticationException;


public class AuthenticationService {

    private static ISecurity securityProvider = SecurityFactory.getSecurity();
    
    public static final Person authenticate(String username, String password) throws AuthenticationException {
        Person person = null;        
        String sessionId = securityProvider.authenticate(username, password);
        
        if (sessionId != null) {
            person = securityProvider.getCurrentPerson();
        }
        return person; 
    }

    public static final String isAuthenticated() {
        return securityProvider.getSessionId();
    }
    
    public static final void logoutCurrentUser() {
        securityProvider.logoutCurrentUser();
    }
    
    
    public static Person getCurrentPersionFromSession() {
        Person person = null;
        if (securityProvider.isSessionValid()) {
            person = securityProvider.getCurrentPerson();
        }
        return person;
    }
}
