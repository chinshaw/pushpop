package com.pushpop.client.proxy;

import com.google.web.bindery.requestfactory.shared.ProxyFor;
import com.pushpop.server.domain.DatastoreObjectLocator;
import com.pushpop.server.domain.Tag;


@ProxyFor(value = Tag.class, locator = DatastoreObjectLocator.class)
public interface TagProxy extends DatastoreObjectProxy {

    public String getName();
    
    public void setName(String name);
    
    public String getDescription();
    
    public void setDescription(String description);
   
}
