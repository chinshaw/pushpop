package com.pushpop.server.dao;

import javax.persistence.EntityManager;

public class ThreadLocalPersistenceManager {

    private static final ThreadLocal<EntityManager> holder = new ThreadLocal<EntityManager>();

    /**
     * All methods in this class are static.
     */
    private ThreadLocalPersistenceManager() {
    }

    public static EntityManager getEntityManager() {
        return holder.get();
    }

    public static void setThreadLocakEntityManager(EntityManager em) {
        holder.set(em);
    }

    public static void removeThreadLocalEntityManager() {
        if (holder.get() != null) {
            holder.get().close();
        }
        holder.remove();
    }

}