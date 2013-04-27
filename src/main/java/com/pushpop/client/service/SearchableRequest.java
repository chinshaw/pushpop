package com.pushpop.client.service;

import java.util.List;

import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;

public interface SearchableRequest<T extends EntityProxy> extends RequestContext {
    Request<List<T>> search(String search);

  
}

