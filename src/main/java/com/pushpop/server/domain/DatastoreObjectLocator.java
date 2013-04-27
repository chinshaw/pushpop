package com.pushpop.server.domain;


import com.google.web.bindery.requestfactory.shared.Locator;
import com.pushpop.server.dao.DaoBase;

/**
 * This class is the locator for all things that reside in the datastore.
 * It handles looking up objects by their id for RequestFactory. See the 
 * @see Locator
 * @author chinshaw
 */
public class DatastoreObjectLocator extends Locator<IDatastoreObject, Long> {

    @Override
    public IDatastoreObject create(Class<? extends IDatastoreObject> clazz) {
        try {
            return clazz.newInstance();
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public IDatastoreObject find(Class<? extends IDatastoreObject> clazz, Long id) {
        return DaoBase.findByClass(clazz, id);
    }

    @Override
    public Class<IDatastoreObject> getDomainType() {
        return IDatastoreObject.class;
    }

    @Override
    public Long getId(IDatastoreObject domainObject) {
        return domainObject.getId();
    }

    @Override
    public Class<Long> getIdType() {
        return Long.class;
    }

    @Override
    public Integer getVersion(IDatastoreObject domainObject) {
        return domainObject.getVersion();
    }
}
