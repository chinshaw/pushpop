package com.pushpop.client.proxy;

import java.util.Date;

import com.google.web.bindery.requestfactory.shared.ProxyFor;
import com.pushpop.server.domain.Answer;
import com.pushpop.server.domain.DatastoreObjectLocator;


@ProxyFor(value = Answer.class, locator = DatastoreObjectLocator.class)
public interface AnswerProxy extends DatastoreObjectProxy {
    
    
    
    public PersonProxy getPerson();

    public String getContent();

    public void setContent(String answer);
    
    public Integer getVoteCount();
    
    public Date getCreatedTimestamp();

    
}
