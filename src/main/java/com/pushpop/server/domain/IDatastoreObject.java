package com.pushpop.server.domain;


public interface IDatastoreObject {

    public abstract Long getId();
    
    public abstract Integer getVersion();
}
