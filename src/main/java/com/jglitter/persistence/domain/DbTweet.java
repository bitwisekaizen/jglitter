/*
 * Project Horizon
 *
 * (c) 2012 VMware, Inc. All rights reserved.
 * VMware Confidential.
 */
package com.jglitter.persistence.domain;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class DbTweet extends HasPrimaryKey {

    @ManyToOne
    private DbUser author;

    @Column
    private String message;

    public DbTweet(final DbUser author, final String message) {
        this();
        this.author = author;
        this.message = message;
    }

    protected DbTweet() {
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final DbTweet that = (DbTweet) o;

        return new EqualsBuilder().append(this.author, that.author).append(this.message, that.message).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(author).append(message).toHashCode();
    }

    public DbUser getAuthor() {
        return author;
    }

    public String getMessage() {
        return message;
    }
}
