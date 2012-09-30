/*
 * Project Horizon
 *
 * (c) 2012 VMware, Inc. All rights reserved.
 * VMware Confidential.
 */
package com.jglitter.web;

import com.jglitter.domain.Tweet;
import com.jglitter.domain.Tweets;
import com.jglitter.domain.User;
import com.jglitter.domain.UserNotFoundException;
import com.jglitter.services.TweetService;
import com.jglitter.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TweetController {

    @Autowired
    private TweetService tweetService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/tweet", method = RequestMethod.POST)
    public Tweet createTweet(@RequestBody Tweet tweet) {
        return tweetService.create(tweet);
    }

    @RequestMapping(value = "/user/{authorId}/tweets", method = RequestMethod.GET)
    public Tweets findTweetsAuthoredBy(@PathVariable String authorId) {
        final User user = userService.findById(authorId);
        if (user == null) {
            throw new UserNotFoundException(authorId);
        }
        return tweetService.findAllBy(user);
    }
}
