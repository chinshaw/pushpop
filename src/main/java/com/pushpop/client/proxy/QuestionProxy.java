package com.pushpop.client.proxy;

import java.util.Date;
import java.util.List;

import com.google.web.bindery.requestfactory.shared.ProxyFor;
import com.pushpop.server.domain.DatastoreObjectLocator;
import com.pushpop.server.domain.Question;

@ProxyFor(value = Question.class, locator = DatastoreObjectLocator.class)
public interface QuestionProxy extends DatastoreObjectProxy {
    
    public static String[] PROPERTIES = {"owner"};

    public String getTitle();
    
    public void setTitle(String title);
    
    public String getDescription();
    
    public void setDescription(String description);
    
    public String getContent();
    
    public void setContent(String content);

    public Integer getVotesCount();

    public Integer getViewsCount();

    public PersonProxy getOwner();
    
    public List<TagProxy> getTags();

    public Integer getAnswersCount();

    public Date getOpenedTimeStamp();
}