/*
 * Project Horizon
 *
 * (c) 2012 VMware, Inc. All rights reserved.
 * VMware Confidential.
 */
package com.jglitter.persistence.repo;

import com.jglitter.persistence.domain.DbTweet;
import com.jglitter.persistence.domain.DbUser;

import java.util.Collection;

public interface TweetRepository {

    DbTweet persist(DbTweet tweet);

    DbTweet findById(Integer id);

    Collection<DbTweet> findAllByAuthor(DbUser author);
}
