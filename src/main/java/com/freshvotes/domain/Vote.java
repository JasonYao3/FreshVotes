package com.freshvotes.domain;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;


@Entity
public class Vote {

    @EmbeddedId
    private VoteId pk;
    private Boolean upvote;

    public Boolean getUpvote() {
        return upvote;
    }

    public void setUpvote(Boolean upvote) {
        this.upvote = upvote;
    }

    public VoteId getPk() {
        return pk;
    }

    public void setPk(VoteId pk) {
        this.pk = pk;
    }
}
