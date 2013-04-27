package com.pushpop.client.proxy;

import com.google.web.bindery.requestfactory.shared.ProxyFor;
import com.google.web.bindery.requestfactory.shared.ValueProxy;
import com.pushpop.server.domain.Person;

@ProxyFor(Person.class)
public interface PersonProxy extends ValueProxy {
    
    public Long getId();
    
    public String getCommonName();
    
    public ReputationProxy getReputation();

}
