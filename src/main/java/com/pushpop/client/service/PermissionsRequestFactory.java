package com.pushpop.client.service;

import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.RequestFactory;
import com.google.web.bindery.requestfactory.shared.Service;
import com.pushpop.client.proxy.PersonProxy;
import com.pushpop.server.service.AuthenticationService;

public interface PermissionsRequestFactory extends RequestFactory {

    
    @Service(value = AuthenticationService.class)
    public interface AuthenticationRequest extends RequestContext {
        
        public abstract Request<PersonProxy> authenticate(String username, String password);
        
        /**
         * Returns the session id of the current authenticated user or 
         * will throw an AuthenticationException if not authenticated
         * @return
         */
        public abstract Request<String> isAuthenticated();
        
        public abstract Request<PersonProxy> getCurrentPersionFromSession();
        
        public abstract Request<Void> logoutCurrentUser();
        
        
    }
    
    
    public AuthenticationRequest createAuthenticationRequest();
}
