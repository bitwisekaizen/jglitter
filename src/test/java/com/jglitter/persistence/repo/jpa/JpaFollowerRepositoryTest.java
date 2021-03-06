/*
 * Project Horizon
 *
 * (c) 2012 VMware, Inc. All rights reserved.
 * VMware Confidential.
 */
package com.jglitter.persistence.repo.jpa;


import com.jglitter.persistence.domain.DbFollower;
import com.jglitter.persistence.domain.DbUser;
import com.jglitter.persistence.repo.FollowerRepository;
import com.jglitter.persistence.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

@Test
@ContextConfiguration({"classpath:application-config.xml"})
public class JpaFollowerRepositoryTest extends AbstractTransactionalTestNGSpringContextTests {

    @Autowired
    private FollowerRepository followerRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void canPersistAFollower() {
        DbUser follower = userRepository.persist(new DbUser("follower@doe.com", "Follower Doe"));
        DbUser followee = userRepository.persist(new DbUser("followee@doe.com", "Followee Doe"));

        DbFollower dbFollower = new DbFollower(follower, followee);
        DbFollower addedDbFollower = followerRepository.persist(dbFollower);

        assertNotNull(addedDbFollower.getId());

        DbFollower retrieved = followerRepository.findById(addedDbFollower.getId());
        assertEquals(retrieved, addedDbFollower);
    }


    @Test
    public void canFindAllFolloweesForAUser() {
        DbUser follower = userRepository.persist(new DbUser("follower@doe.com", "Follower Doe"));
        DbUser followee = userRepository.persist(new DbUser("followee@doe.com", "Followee Doe"));
        DbUser followee2 = userRepository.persist(new DbUser("followee2@doe.com", "Followee2 Doe"));

        DbFollower addedDbFollower = followerRepository.persist(new DbFollower(follower, followee));
        DbFollower addedDbFollower2 = followerRepository.persist(new DbFollower(follower, followee2));

        List<DbUser> followees = followerRepository.findFollowees(follower);
        assertEquals(followees.size(), 2);
        assertTrue(followees.contains(followee));
        assertTrue(followees.contains(followee2));
    }
}
