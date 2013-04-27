package com.pushpop.server.domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;

@MappedSuperclass
@Access(AccessType.FIELD)
public abstract class DatastoreObject implements IDatastoreObject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer version = 0;

    public Long getId() {
        return id;
    }

    public boolean isNew() {
        return (id == null ? true : false);
    }

    public Integer getVersion() {
        return version;
    }

    @PrePersist
    private void incrementVersion() {
        version += 1;
    }
}
