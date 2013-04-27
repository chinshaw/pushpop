package com.pushpop.server.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.validation.constraints.NotNull;

import com.pushpop.server.SecurityFactory;
import com.pushpop.shared.QuestionStatus;

@Entity
@Access(AccessType.FIELD)
public class Question extends DatastoreObject implements IHasVotes {

    @NotNull
    private String title;
    
    @NotNull
    private String description;
    
    private String content;

    private Date openedTimeStamp;
    
    private Date modifiedTimestamp;
    
    /**
     * The owner of the question
     */
    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, optional = false)
    private Person owner;
    
    @Enumerated(EnumType.STRING)
    private QuestionStatus status = QuestionStatus.Open;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "question")
    private List<Answer> answers = new ArrayList<Answer>();
    
    @OneToMany(fetch = FetchType.EAGER, orphanRemoval= false)
    private List<Tag> tags = new ArrayList<Tag>();
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "question")
    private List<QuestionView> views = new ArrayList<QuestionView>();
    
    @OneToMany(cascade = CascadeType.ALL)
    private List<Vote> votes = new ArrayList<Vote>();
    
    /**
     * Default constructor.
     */
    public Question() {
    }
    
    public Question(Person owner) {
        this.owner = owner;
    }
    
    /**
     * Get the owner of the question.
     * @return
     */
    public Person getOwner() {
        return owner;
    }

    /**
     * Set the owner of the question.
     * @param owner
     */
    public void setOwner(Person owner) {
        this.owner = owner;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getDescription() {
        return description;    
    }
    
    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent() {
        return content;
    }
    
    public void setContent(String content) {
        this.content = content;
    }

    public List<QuestionView> getViews() {
        return views;
    }

    public void addView(QuestionView view) {
        views.add(view);
    }
    
    public Integer getViewsCount() {
        return views.size();
    }
    
    public Integer getAnswersCount() {
        return answers.size();
    }
    
    public List<Vote> getVotes() {
        return votes;
    }
    
    @Override
    public void castVote(Vote vote) {
        votes.add(vote);
    }
    
    public QuestionStatus getStatus() {
        return status;
    }
    
    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }
    
    public void addTag(Tag tag) {
        tags.add(tag);
    }
    
    public void removeTag(Tag tag) {
        tags.remove(tag);
    }

    public void setStatus(QuestionStatus status) {
        this.status = status;
    }
    public Integer getVotesCount() {
        if (votes == null) {
            return 0;
        }
        return votes.size();
    }
    

    public Date getOpenedTimeStamp() {
        return openedTimeStamp;
    }
    
    public Date getLastModifiedTimeStamp() {
        return modifiedTimestamp;
    }
    
    @PrePersist
    private void updateTimeStamps() {
        System.out.println("CALLING UPDATE TIME STAMPS");
        if (isNew()) {
            this.openedTimeStamp = new Date();
            
            // Set it to the current logged in user.
            if (owner == null) {
                Person currentPerson = SecurityFactory.getSecurity().getCurrentPerson();
                if (currentPerson == null) {
                    throw new RuntimeException("Cannot person a new question without a valid user");
                }
                
                this.owner = currentPerson;
            }
        } else {
            modifiedTimestamp = new Date();
        }
    }
}