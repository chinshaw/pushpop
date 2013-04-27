package com.pushpop.server.dao;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PersistenceManagerFactory {

    private static final EntityManagerFactory PMF = Persistence.createEntityManagerFactory("datanucleus-pu");
    
    public static EntityManagerFactory getEntityManagerFactory() {
        return PMF;
    }
}
