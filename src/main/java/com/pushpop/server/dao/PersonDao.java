package com.pushpop.server.dao;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import com.pushpop.server.domain.Person;

public class PersonDao extends DaoBase<Person> {

    public static Person findByEmail(String emailAddress) {
        EntityManager em = getEntityManager();
        TypedQuery<Person> query = em.createQuery("SELECT person FROM " + Person.class.getName() + " AS person where emailAddress = :emailAddress" , Person.class);
        
        query.setParameter("emailAddress", emailAddress);
        
        try {
            return query.getSingleResult();
        } catch (NoResultException ignored) {
            return null;
        }
    }

    public static void updateLoginDate(Person person) {
        if (person == null) {
            throw new IllegalArgumentException("Cannot update data on person that does not exist");
        }
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        person.setLastLogin(new Date());
        em.getTransaction().commit();
    }
}
