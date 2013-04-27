package com.pushpop.server.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Answer extends DatastoreObject implements IHasVotes {


    @OneToOne
    private Person person;
    
    private Date createdDate;

    private String content;

    
    @OneToMany(cascade = CascadeType.ALL)
    private List<Vote> votes;
    
    @NotNull
    @OneToOne(optional = false)
    private Question question;
    
    public Answer() {
    }
    
    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String answer) {
        this.content = answer;
    }
    
    public Date getCreatedTimestamp() {
        return createdDate;
    }
    

    @Override
    public List<Vote> getVotes() {
        return votes;
    }
    
    public Integer getVoteCount() {
        return votes.size();
    }

    @Override
    public void castVote(Vote vote) {
        votes.add(vote);
    }    
}
