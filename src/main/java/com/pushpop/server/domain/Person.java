package com.pushpop.server.domain;

import java.util.Date;

import javax.jdo.annotations.Embedded;
import javax.jdo.annotations.Unique;
import javax.persistence.Entity;

import org.datanucleus.api.jpa.annotations.Index;

@Entity
public class Person extends DatastoreObject {
    
    public static final String USER_CONSTANT = "Person";
    
    private Date createdDate;
    
    private Date lastLogin;
    
    private String commonName;
    
    private String aboutMe;
    
    @Unique
    @Index(name = "email_idx", unique = "true")
    private String emailAddress;
    
    @Embedded
    private Reputation reputation;

    public Person() {
        reputation = new Reputation();
        createdDate = new Date();
    }
    
    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public Reputation getReputation() {
        return reputation;
    }
    
    public String getCommonName() {
        if (commonName == null) {
            return USER_CONSTANT + "" + getId().toString();
        }
        return commonName;
    }
    
    public void setCommonName(String commonName) {
        this.commonName = commonName;
    }

    public String getAboutMe() {
        return aboutMe;
    }

    public void setAboutMe(String aboutMe) {
        this.aboutMe = aboutMe;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }
}
