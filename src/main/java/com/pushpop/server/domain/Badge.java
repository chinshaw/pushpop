package com.pushpop.server.domain;

import javax.persistence.Entity;

@Entity
public class Badge {

    public enum Metal {
        GOLD, SILVER, BRONZE
    }

    private String name;

    private Metal metal;

    public Badge() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Metal getMetal() {
        return metal;
    }

    public void setMetal(Metal metal) {
        this.metal = metal;
    }
}
