package com.pushpop.client.proxy;

import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyFor;
import com.pushpop.server.domain.DatastoreObject;
import com.pushpop.server.domain.DatastoreObjectLocator;

@ProxyFor(value = DatastoreObject.class, locator = DatastoreObjectLocator.class)
public interface DatastoreObjectProxy extends EntityProxy {

    public Long getId();

    public Integer getVersion();
}
