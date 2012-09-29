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

import java.util.Collection;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

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

    @Test
    public void canFindAllTweetsByAuthor() {
        DbUser elaine = userRepo.persist(new DbUser("elaine@bennis.com", "Elaine Benes"));
        DbUser george = userRepo.persist(new DbUser("george@castanza.com", "George Costanza"));

        DbTweet elainesTweet = tweetRepo.persist(new DbTweet(elaine, "I'm an awesome dancer."));

        tweetRepo.persist(new DbTweet(george, "George is getting angry."));
        tweetRepo.persist(new DbTweet(george, "I wish I was an architect."));
        tweetRepo.persist(new DbTweet(george, "I'm going bald."));

        Collection<DbTweet> tweets = tweetRepo.findAllByAuthor(elaine);
        assertEquals(1, tweets.size(), "Tweet count of tweets by author not accurate");
        assertTrue(tweets.contains(elainesTweet), "Did not find expected tweet by author");
    }
}
