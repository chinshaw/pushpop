package com.pushpop.server.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.web.bindery.requestfactory.server.RequestFactoryServlet;
import com.pushpop.server.domain.Person;

public class HttpSessionUtils  {

    public static final String USER_ID = "uid";
    
    public static final String CURRENT_PERSON = "currentperson";
    
    public static final String AUTHENTICATED = "authenticated";
    
    public static final String AUTH_TOKEN = "authtoken";
    
    public static final String USER_ROLE = "userrole";
    
    public static final String USER_GROUP = "usergroup";

    public static final String COMET_SESSION_UUID = "cometuuid";
    
    
    public static String getSessionUserId() {
        return (String) getSession().getAttribute(USER_ID);
    }

    public static HttpServletRequest getRequest() {
        return RequestFactoryServlet.getThreadLocalRequest();
    }

    public static HttpSession createSession() {
        HttpSession session = getSession();
        if (session == null) {
            session = getRequest().getSession();
        }
        return session;
    }
    
    public static HttpSession getSession() {
        return getRequest().getSession(false);
    }
    
    public static String getSessionId() {
        return getSession().getId();
    }

    public static HttpServletResponse getResponse() {
        return RequestFactoryServlet.getThreadLocalResponse();
    }
    
    public static void setEmailAddress(String email) {
        getSession().setAttribute(USER_ID, email);
    }
    
    public static void setAuthenticated(boolean authenticated) {
        getSession().setAttribute(AUTHENTICATED, Boolean.valueOf(authenticated));
    }
    
    public static boolean isSessionValid() {
        return getRequest().isRequestedSessionIdValid();
    }

    public static boolean isAuthenticated() {
        boolean isValid = false;
        if (getSession() != null) {
            Object value = HttpSessionUtils.getSession().getAttribute(AUTHENTICATED);
            if (value != null) {
                isValid = (Boolean) value;
            }
        }
        return isValid;
    }
    
    public static String getCometServiceUUID() {
        return (String) getSession().getAttribute(COMET_SESSION_UUID);
    }
    
    public static void setCometServiceUUID(String cometUUID) {
        getSession().setAttribute(COMET_SESSION_UUID, cometUUID);
    }
    
    public static void removeCometSessionUUID() {
        getSession().removeAttribute(COMET_SESSION_UUID);
    }

    public static void setPerson(Person currentPerson) {
        getSession().setAttribute(CURRENT_PERSON, currentPerson);
    }
    
    public static Person getCurrentPerson() {
        return (Person) getSession().getAttribute(CURRENT_PERSON);
    }

    public static void setAuthToken(String authToken) {
        getSession().setAttribute(AUTH_TOKEN, authToken);
    }
    
    public static void getAuthToken(String authToken) {
        getSession().getAttribute(AUTH_TOKEN);
    }
}
