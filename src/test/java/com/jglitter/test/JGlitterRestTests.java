/*
 * Project Horizon
 *
 * (c) 2012 VMware, Inc. All rights reserved.
 * VMware Confidential.
 */
package com.jglitter.test;

import com.jglitter.domain.User;
import com.jglitter.domain.Users;
import harvard.AbstractTests;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

@Test
public class JGlitterRestTests extends AbstractTests {

    @Autowired
    private RestTemplate restTemplate;

    @Test
    void canCreateAUser() {
        User johnDoe = restTemplate.postForEntity(urls.wsRoot() + "/user", new User("john@doe.com", "JohnDoe"), User.class).getBody();
        Users allUsers = restTemplate.getForEntity(urls.wsRoot() + "/user", Users.class).getBody();
        assertTrue(allUsers.contains(johnDoe), "All users didn't include newly added user.");
    }
}
