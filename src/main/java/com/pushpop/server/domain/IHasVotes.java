package com.pushpop.server.domain;

import java.util.List;

public interface IHasVotes {

    public List<Vote> getVotes();
    
    public void castVote(Vote vote);
}
