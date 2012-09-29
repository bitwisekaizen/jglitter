/*
 * Project Horizon
 *
 * (c) 2012 VMware, Inc. All rights reserved.
 * VMware Confidential.
 */
package com.jglitter.persistence.repo.jpa;

import com.jglitter.persistence.domain.DbTweet;
import com.jglitter.persistence.repo.TweetRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class JpaTweetRepository implements TweetRepository {

    @PersistenceContext
    private EntityManager em;

    public DbTweet persist(final DbTweet tweet) {
        em.persist(tweet);
        return tweet;
    }

    public DbTweet findById(final Integer id) {
        return em.getReference(DbTweet.class, id);
    }
}
