package com.pushpop.client.service;

import java.util.List;

import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.RequestFactory;
import com.google.web.bindery.requestfactory.shared.Service;
import com.pushpop.client.proxy.DatastoreObjectProxy;
import com.pushpop.client.proxy.PersonProxy;
import com.pushpop.client.proxy.QuestionProxy;
import com.pushpop.server.dao.DaoServiceLocator;
import com.pushpop.server.dao.PersonDao;
import com.pushpop.server.dao.QuestionDao;

public interface DaoRequestFactory extends RequestFactory {

    public interface DaoRequest<T extends DatastoreObjectProxy> extends PaginationRequest<T>, SearchableRequest<T> {

    }

    @Service(value = PersonDao.class, locator = DaoServiceLocator.class)
    public interface PersonRequest extends RequestContext {
        
        Request<PersonProxy> find(Long id);

    }

    @Service(value = QuestionDao.class, locator = DaoServiceLocator.class)
    public interface QuestionRequest extends RequestContext {

        public Request<List<QuestionProxy>> findRange(int start, int max);

        public Request<List<QuestionProxy>> findSortedRange(int start, int max, String sortedColumn, String sortedBy);
        
        public Request<Long> save(QuestionProxy question);
    }
    
    
    public QuestionRequest createQuestionRequest();
}
