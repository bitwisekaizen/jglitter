/*
 * Project Horizon
 *
 * (c) 2012 VMware, Inc. All rights reserved.
 * VMware Confidential.
 */
package com.jglitter.test;

import com.jglitter.domain.Tweet;
import com.jglitter.domain.Tweets;
import com.jglitter.domain.User;
import com.jglitter.domain.Users;
import liquibase.precondition.Precondition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Collection;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

@Test
public class JGlitterRestTests extends AbstractTests {

    @Autowired
    private RestTemplate restTemplate;
    private User follower;
    private User userToFollow;

    @BeforeMethod
    void setup() {
        follower = createUser("gavin@vmware.com", "Gavin Gray");
        userToFollow = createUser("brad@vmware.com", "Brad");
    }

    @AfterMethod(alwaysRun = true)
    void teardown() {
        deleteUser(follower.getId());
        deleteUser(userToFollow.getId());
    }

    @Test
    void canCreateAUser() {
        Users allUsers = restTemplate.getForEntity(wsRoot() + "/user", Users.class).getBody();
        assertTrue(allUsers.contains(follower), "All users didn't include newly added user.");
    }

    private void deleteUser(String id) {
        restTemplate.delete(wsRoot() + "/user/" + id);
    }

    private User createUser(String username) {
        return createUser( username+"@example.com", username);
    }

    private User createUser(String email, String username) {
        User aUser = restTemplate.postForEntity(wsRoot() + "/user", new User(email, username), User.class).getBody();
        assertNotNull(aUser, "Create user failed " + username);
        return aUser;
    }

    private Tweets getTweetsForUser(User user) {
        return restTemplate.getForEntity(wsRoot() + "/user/" + user.getId() + "/tweets", Tweets.class).getBody();
    }

    @Test
    void userCanAuthorATweet() {
        User author = follower;
        Tweet tweet = createTweet(author, "This is my first tweet!");
        Tweets tweets = getTweetsForUser(author);
        assertTrue(tweets.contains(tweet), "All tweets by the author includes the new tweet.");
        deleteUser(author.getId());
        Tweets tweetsAfterDelete = getTweetsForUser(author);
        assertEquals(tweetsAfterDelete.getTweets().size(), 0, "Tweets where not deleted with user");
    }

    private Tweet createTweet(User author, String message) {
        return restTemplate.postForEntity(wsRoot() + "/tweet", new Tweet(author, message), Tweet.class).getBody();
    }

    @Test
    void unknownUserCannotAuthorATweet() {
        User unknownAuthor = new User("sneaky@bastard.com", "sneaky");
        try {
            createTweet(unknownAuthor, "This is my first tweet!");
            fail("Should have failed posting a tweet by an unknown author.");
        } catch (HttpClientErrorException exception) {
            assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode(), "Error code wasn't NOT_FOUND");
        }
    }

    @Test
    void canFollowAnotherUser() {
        User follower1 = createUser("iliketo@follow.com", "Follower");
        User userToFollow1 = createUser("ipostgoodtweets@me.com", "Followee");
        followUser(follower1, userToFollow1);

        Users followees = getFollowees(follower1);
        assertEquals(1, followees.getUsers().size());
        assertTrue(followees.contains(userToFollow1), "Expected followee not found");
    }

    @Test
    void canReadTweetsOfFollowees() {
        User follower    = createUser("follower");
        User followee1   = createUser("followee1");
        User followee2   = createUser("followee2");
        User nonfollowee = createUser("nonfollowee");
        followUser(follower, followee1);
        followUser(follower, followee2);

        Tweet followerTweet    = createTweet(follower   , "tweet by follower");
        Tweet followee1Tweet   = createTweet(followee1  , "tweet by followee1");
        Tweet followee2Tweet   = createTweet(followee2  , "tweet by followee2");
        Tweet nonfolloweeTweet = createTweet(nonfollowee, "tweet by nonfollowee");

        Tweets tweets = getUserFeed(follower);

        assertEquals( tweets.getTweets().size(), 2 );
        assertTrue( tweets.contains(followee1Tweet) );
        assertTrue( tweets.contains(followee2Tweet) );
    }

    private Tweets getUserFeed(User aUser) {
        return restTemplate.getForEntity(wsRoot() + "/feed/" + aUser.getId(), Tweets.class).getBody();
    }

    private Users getFollowees(User aUser) {
        return restTemplate.getForEntity(wsRoot() + "/followees/" + aUser.getId(), Users.class).getBody();
    }

    private void followUser(User aUser, User userToFollow) {
        restTemplate.postForEntity(wsRoot() + "/followers/" + aUser.getId() + "/" + userToFollow.getId(), null, null);
    }

    private String wsRoot() {
        // putting this here now because I removed the URL helper class
        return "http://localhost:8080/jglitter/ws";
    }

}
