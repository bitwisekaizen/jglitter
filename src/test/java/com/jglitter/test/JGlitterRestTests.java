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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.testng.annotations.Test;

import java.util.Collection;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

@Test
public class JGlitterRestTests extends AbstractTests {

    @Autowired
    private RestTemplate restTemplate;

    @Test
    void canCreateAUser() {
        User johnDoe = createUser("john@doe.com", "John Doe");
        Users allUsers = restTemplate.getForEntity(wsRoot() + "/user", Users.class).getBody();
        assertTrue(allUsers.contains(johnDoe), "All users didn't include newly added user.");
    }

    private User createUser(String email, String username) {
        return restTemplate.postForEntity(wsRoot() + "/user", new User(email, username), User.class).getBody();
    }

    @Test
    void userCanAuthorATweet() {
        User author = restTemplate.postForEntity(wsRoot() + "/user", new User("auth@or.com", "JohnDoe"), User.class).getBody();
        Tweet tweet = restTemplate.postForEntity(wsRoot() + "/tweet", new Tweet(author, "This is my first tweet!"), Tweet.class).getBody();
        Tweets tweets = restTemplate.getForEntity(wsRoot() + "/user/" + author.getId() + "/tweets", Tweets.class).getBody();
        assertTrue(tweets.contains(tweet), "All tweets by the author includes the new tweet.");
    }

    @Test
    void unknownUserCannotAuthorATweet() {
        User unknownAuthor = new User("sneaky@bastard.com", "sneaky");
        try {
            restTemplate.postForEntity(wsRoot() + "/tweet", new Tweet(unknownAuthor, "This is my first tweet!"), Tweet.class).getBody();
            fail("Should have failed posting a tweet by an unknown author.");
        } catch (HttpClientErrorException exception) {
            assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode(), "Error code wasn't NOT_FOUND");
        }
    }

    @Test
    void canFollowAnotherUser() {
        User aUser = createUser("gavin@vmware.com", "Gavin Gray");
        User userToFollow = createUser("brad@vmware.com", "Brad");
        followUser(aUser, userToFollow);

        Users followees = getFollowees(aUser);
        assertEquals(1, followees.getUsers().size());
        assertTrue(followees.contains(userToFollow), "Expected followee not found");
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
