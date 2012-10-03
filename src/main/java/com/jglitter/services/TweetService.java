/*
 * Project Horizon
 *
 * (c) 2012 VMware, Inc. All rights reserved.
 * VMware Confidential.
 */
package com.jglitter.services;

import com.jglitter.domain.Tweet;
import com.jglitter.domain.Tweets;
import com.jglitter.domain.User;

public interface TweetService {

    Tweet create(Tweet tweet);

    Tweets findAllBy(User author);

    Tweets findAllForFollower(User user);
}
