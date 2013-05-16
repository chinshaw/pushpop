package com.pushpop.server.security;

import com.pushpop.server.domain.Person;
import com.pushpop.shared.AuthenticationException;
import com.pushpop.shared.GroupMembership;

public interface ISecurity {

    public abstract Person createNewPerson(String email) throws Exception;
    
    public abstract String authenticate(String user, String password) throws AuthenticationException;
    
    public abstract void createSession();

    public String getSessionId();
    
    /**
     * This is an administrative function that will log out a user, this
     * call will be restricted to administrative users.
     * 
     * @param user
     */
    public abstract void logout(Person user);

    /**
     * Clear the session an log out the user.
     */
    public abstract void logoutCurrentUser();

    /**
     * Retrieves the current logged in person, this should be trustable
     * since it is based on the authenticated session. We store the person in
     * an environment variable when logged in, this is the current user.
     * @return Current user logged in.
     */
    public abstract Person getCurrentPerson();

    public abstract boolean isUserAuthenticated();

    public abstract GroupMembership getSessionUserGroup();

    public abstract boolean isSessionValid();
}
