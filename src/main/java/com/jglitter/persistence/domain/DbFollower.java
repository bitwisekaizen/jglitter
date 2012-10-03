package com.jglitter.persistence.domain;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class DbFollower extends HasPrimaryKey {

    @ManyToOne
    private DbUser follower;

    @ManyToOne
    private DbUser followee;

    public DbFollower(DbUser follower, DbUser followee) {
        this();
        this.follower = follower;
        this.followee = followee;
    }

    /** Necessary for persistence framework. */
    protected DbFollower() {
    }
}
