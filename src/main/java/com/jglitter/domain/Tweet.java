/*
 * Project Horizon
 *
 * (c) 2012 VMware, Inc. All rights reserved.
 * VMware Confidential.
 */
package com.jglitter.domain;

import com.jglitter.persistence.domain.DbTweet;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Tweet {

    private User author;
    private String message;

    public Tweet(final User author, final String message) {
        this();
        this.author = author;
        this.message = message;
    }

    public Tweet(final DbTweet tweet) {
        this(new User(tweet.getAuthor()), tweet.getMessage());
    }

    public Tweet() {
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(final User author) {
        this.author = author;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(final String message) {
        this.message = message;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final Tweet tweet = (Tweet) o;

        return new EqualsBuilder().append(this.author, tweet.author).append(this.message, tweet.message).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(author).append(message).toHashCode();
    }
}
