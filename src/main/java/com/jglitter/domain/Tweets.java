/*
 * Project Horizon
 *
 * (c) 2012 VMware, Inc. All rights reserved.
 * VMware Confidential.
 */
package com.jglitter.domain;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.Collection;

@XmlRootElement
public class Tweets {

    private Collection<Tweet> tweets = new ArrayList<Tweet>();

    public boolean contains(final Tweet tweet) {
        return tweets.contains(tweet);
    }

    public Collection<Tweet> getTweets() {
        return tweets;
    }

    public void setTweets(final Collection<Tweet> tweets) {
        this.tweets = tweets;
    }

    public void add(final Tweet tweet) {
        tweets.add(tweet);
    }
}
