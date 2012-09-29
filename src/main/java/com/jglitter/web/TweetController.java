/*
 * Project Horizon
 *
 * (c) 2012 VMware, Inc. All rights reserved.
 * VMware Confidential.
 */
package com.jglitter.web;

import com.jglitter.domain.Tweet;
import com.jglitter.domain.Tweets;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TweetController {

    private Tweets tweets = new Tweets();

    @RequestMapping(value = "/tweet", method = RequestMethod.POST)
    public Tweet createTweet(@RequestBody Tweet tweet) {
        tweets.add(tweet);
        return tweet;
    }

    @RequestMapping(value = "/user/{authorId}/tweets", method = RequestMethod.GET)
    public Tweets findTweetsAuthoredBy(@PathVariable String authorId) {
        return tweets;
    }
}
