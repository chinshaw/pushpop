package com.pushpop.server;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.servlet.http.HttpSession;

import com.pushpop.server.IAuthenticationProvider.AuthenticationCallback;
import com.pushpop.server.dao.PersonDao;
import com.pushpop.server.domain.Person;
import com.pushpop.server.utils.HttpSessionUtils;
import com.pushpop.shared.AuthenticationException;
import com.pushpop.shared.AuthenticationProviderException;
import com.pushpop.shared.GroupMembership;

public class Security implements ISecurity {
    
    
    private class Authenticate implements Callable<AuthenticationCallback> {

        @Override
        public AuthenticationCallback call() throws Exception {
            // TODO Auto-generated method stub
            return null;
        }
        
        
        
    }
    
    
    static final int AUTH_THREAD_POOL_SIZE = 10;
    
    private ExecutorService authenticationThreadPool = Executors.newFixedThreadPool(AUTH_THREAD_POOL_SIZE);
    
    
    private IAuthenticationProvider authenticationProvider;
    
    public Security(IAuthenticationProvider authenticationProvider) {
        this.authenticationProvider = authenticationProvider;
    }
    
    @Override
    public Person createNewPerson(String email) throws Exception {
        return null;
        
    }

    /**
     * This method will find use the current authentication provider
     * to authenticate and store the authentication token in the current session.
     * 
     * A reference to the session id is returned.
     * @return String web session id for the user, can be used to find more information about user.
     * @throws AuthenticationProviderException 
     */
    @Override
    public String authenticate(String user, String password) throws AuthenticationException {
        String authToken = authenticationProvider.authenticate(user, password);
        if (authToken == null) {
            throw new AuthenticationException("We did not receive a valid auth token from auth provider");
        }
        
        String emailAddress;
        try {
            emailAddress = authenticationProvider.getEmail(authToken);
            
            if (emailAddress == null) {
                throw new AuthenticationException("We were unable to retrieve your email address from the authentication provider, you will not be able to" +
                        "autenticate using the current provider");
            }
        } catch (AuthenticationProviderException e) {
            throw new AuthenticationException("We were unable to retrieve yoru email address from the authentication provider, you may not be able to " +
                    "authenticate using the current provider", e);
        }
        
        Person person = PersonDao.findByEmail(emailAddress);
        
        if (person == null) {
            
            person = new Person();
            person.setEmailAddress(emailAddress);
            
            String commonName = null;
            try {
                commonName = authenticationProvider.getCommonName(authToken);
            } catch (AuthenticationProviderException e) {
                e.printStackTrace();
            }
            
            person.setCommonName(commonName);
            new PersonDao().save(person);
        } else {
            
            PersonDao.updateLoginDate(person);
            
        }
        
        HttpSessionUtils.createSession();
        HttpSessionUtils.setPerson(person);
        HttpSessionUtils.setAuthToken(authToken);
        return HttpSessionUtils.getSessionId();
    }

    @Override
    public void createSession() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void logout(Person user) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void logoutCurrentUser() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Person getCurrentPerson() {
        return HttpSessionUtils.getCurrentPerson();
    }

    @Override
    public boolean isUserAuthenticated() {
        // TODO Auto-generated method stub
        return false;
    }


    @Override
    public GroupMembership getSessionUserGroup() {
        // TODO Auto-generated method stub
        return null;
    }


    @Override
    public String getSessionId() {
        return HttpSessionUtils.getSessionId();
    }
    
    
    public boolean isSessionValid() {
        HttpSessionUtils.getRequest().isRequestedSessionIdValid();
        return HttpSessionUtils.isSessionValid();
    }
    
}
