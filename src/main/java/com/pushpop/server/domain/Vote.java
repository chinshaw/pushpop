package com.pushpop.server.domain;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Vote extends DatastoreObject {

    @NotNull
    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, optional = false)
    public Person owner;
    
    public Date castDate;


    public Vote() {
        
    }
    
    
    public Person getOwner() {
        return owner;
    }


    public void setOwner(Person owner) {
        this.owner = owner;
    }


    public Date getCastDate() {
        return castDate;
    }


    public void setCastDate(Date castDate) {
        this.castDate = castDate;
    }
}
