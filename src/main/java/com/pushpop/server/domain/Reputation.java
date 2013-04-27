package com.pushpop.server.domain;

import java.util.List;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

@Embeddable
public class Reputation {
    
    private Integer profileViews = 0;
    
    /**
     * Reputation score
     */
    private Integer reputationScore = 0;
    
    /**
     * List of badges
     */
    @OneToMany(fetch = FetchType.LAZY)
    private List<Badge> badges;

    public Integer getReputationScore() {
        return reputationScore;
    }

    public void setReputationScore(int reputationScore) {
        this.reputationScore = reputationScore;
    }

    public Integer getProfileViews() {
        return profileViews;
    }

    public void setProfileViews(Integer profileViews) {
        this.profileViews = profileViews;
    }

    public List<Badge> getBadges() {
        return badges;
    }

    public void setBadges(List<Badge> badges) {
        this.badges = badges;
    }
}
