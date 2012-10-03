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
import com.jglitter.domain.UserNotFoundException;
import com.jglitter.persistence.domain.DbTweet;
import com.jglitter.persistence.domain.DbUser;
import com.jglitter.persistence.repo.TweetRepository;
import com.jglitter.persistence.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service
public class RepositoryPoweredTweetService implements TweetService {

    @Autowired
    private TweetRepository tweetRepo;

    @Autowired
    private UserRepository userRepo;

    @Transactional
    public Tweet create(final Tweet tweet) {
        final DbUser author = userRepo.findByEmail(tweet.getAuthor().getId());
        if (author == null) {
            throw new UserNotFoundException(tweet.getAuthor().getId());
        }
        final DbTweet createdTweet = tweetRepo.persist(new DbTweet(author, tweet.getMessage()));
        return new Tweet(createdTweet);
    }

    @Transactional(readOnly = true)
    public Tweets findAllBy(final User author) {
        final DbUser user = userRepo.findByEmail(author.getId());
        final Collection<DbTweet> allByAuthor = tweetRepo.findAllByAuthor(user);
        final Tweets tweets = new Tweets();
        for (DbTweet eachTweet : allByAuthor) {
            tweets.add(new Tweet(eachTweet));
        }
        return tweets;
    }
}
