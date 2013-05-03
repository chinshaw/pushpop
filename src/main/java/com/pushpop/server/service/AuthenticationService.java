package com.pushpop.server.service;

<<<<<<< HEAD
=======
import com.pushpop.client.proxy.PersonProxy;
>>>>>>> 9629e6d9c7b32e65882d3bcb6996414b8ba5ea22
import com.pushpop.server.ISecurity;
import com.pushpop.server.SecurityFactory;
import com.pushpop.server.domain.Person;
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
    
<<<<<<< HEAD
=======
    
>>>>>>> 9629e6d9c7b32e65882d3bcb6996414b8ba5ea22
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