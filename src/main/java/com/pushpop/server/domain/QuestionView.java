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
    
    public void setQuestionId(Long questionId) {
        
    }
}
