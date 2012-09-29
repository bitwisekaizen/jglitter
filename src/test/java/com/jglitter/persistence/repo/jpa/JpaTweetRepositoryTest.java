/*
 * Project Horizon
 *
 * (c) 2012 VMware, Inc. All rights reserved.
 * VMware Confidential.
 */
package com.jglitter.persistence.repo.jpa;

import com.jglitter.persistence.domain.DbTweet;
import com.jglitter.persistence.domain.DbUser;
import com.jglitter.persistence.repo.TweetRepository;
import com.jglitter.persistence.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

@Test
@ContextConfiguration({"classpath:application-config.xml"})
public class JpaTweetRepositoryTest extends AbstractTransactionalTestNGSpringContextTests {

    @Autowired
    private TweetRepository tweetRepo;

    @Autowired
    private UserRepository userRepo;

    @Test
    public void canPersistATweet() {
        DbUser author = userRepo.persist(new DbUser("jane@doe.com", "Jane Doe"));

        DbTweet tweet = tweetRepo.persist(new DbTweet(author, "I have a common name"));
        assertNotNull(tweet.getId(), "Persisted tweet was not assigned primary key.");

        DbTweet retrieved = tweetRepo.findById(tweet.getId());
        assertEquals(retrieved, tweet, "Tweet retrieved by primary key not same as persisted tweet");
    }
}
