package com.pushpop.server;

import com.pushpop.shared.AuthenticationException;
import com.pushpop.shared.AuthenticationProviderException;


public interface IAuthenticationProvider {

    
    public interface AuthenticationCallback {
        
        public void onSuccess();
        
        public void onSuccess(String token);
        
        public void onFailure(String message);
        
    }
    
    public abstract String authenticate(String user, String password) throws AuthenticationException;
    
    public abstract void authenticate(String user, String password, AuthenticationCallback callback) throws AuthenticationException;
    
    public abstract String getCommonName(String authToken) throws AuthenticationProviderException;
    
    public abstract String getEmail(String authToken) throws AuthenticationProviderException;

    public abstract void logout(String authToken) throws AuthenticationProviderException;
    
}
