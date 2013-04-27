package com.pushpop.client.events;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.pushpop.client.proxy.PersonProxy;

public class AuthenticationEvent extends GwtEvent<AuthenticationEvent.Handler> {

    /**
     * Implemented by handlers of NotificationEvent.
     */
    public interface Handler extends EventHandler {
        void onUserAuthentication(AuthenticationEvent event);
    }

    /**
     * A singleton instance of Type&lt;Handler&gt;.
     */
    public static final Type<Handler> TYPE = new Type<Handler>();
    
    
    private final PersonProxy person;
    
    public AuthenticationEvent(PersonProxy person) {
        this.person = person;
    }

    @Override
    public Type<Handler> getAssociatedType() {
        return TYPE;
    }

    
    public PersonProxy getPerson() {
    	return person;
    }
    
    @Override
    protected void dispatch(Handler handler) {
        handler.onUserAuthentication(this);
    }
}
