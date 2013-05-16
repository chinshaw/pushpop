package com.pushpop.server.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class QuestionView extends DatastoreObject {

    public Date dateViewed;
    
    /**
     * Viewer
     */
    @OneToOne
    public Person person;
    
    @OneToOne
    public Question question;

    /**
     * Calls {@link #QuestionView(Question, Person)} so with a null
     * person so that the date is initialized correctly.
     * @param question
     */
    public QuestionView(Question question) {
    	this(question, null);
    }
    
    /**
     * Initializes the 
     * @param question
     * @param person
     */
    public QuestionView(Question question, Person person) {
    	this.question = question;
    	this.person = person;
    	this.dateViewed = new Date();
    }
    
    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Date getDateViewed() {
        return dateViewed;
    }

    public void setDateViewed(Date dateViewed) {
        this.dateViewed = dateViewed;
    }
    
    public Question getQuestion() {
        return question;
    }
    
    public void setQuestion(Question question) {
        this.question = question;
    }
}
