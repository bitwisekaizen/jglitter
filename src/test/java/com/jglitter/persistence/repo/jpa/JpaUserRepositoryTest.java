/*
 * Project Horizon
 *
 * (c) 2012 VMware, Inc. All rights reserved.
 * VMware Confidential.
 */
package com.jglitter.persistence.repo.jpa;


import com.jglitter.persistence.domain.DbUser;
import com.jglitter.persistence.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

@Test
@ContextConfiguration({"classpath:application-config.xml"})
public class JpaUserRepositoryTest extends AbstractTransactionalTestNGSpringContextTests {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void canPersistAUser() {
        DbUser johnDoe = userRepository.persist(new DbUser("john@doe.com", "John Doe"));
        assertNotNull(johnDoe.getId(), "Newly persisted user did not have primary key.");

        DbUser retrieved = userRepository.findByUuid(johnDoe.getUuid());
        assertEquals(retrieved, johnDoe, "Retrieved user did not match newly inserted user.");
    }

    @Test
    public void canFindAllUsers() {
        final DbUser johnDoe = userRepository.persist(new DbUser("john@doe.com", "John Doe"));
        final DbUser janeDoe = userRepository.persist(new DbUser("jane@doe.com", "Jane Doe"));
        final List<DbUser> all = userRepository.findAll();
        assertTrue(all.contains(johnDoe));
        assertTrue(all.contains(janeDoe));
    }

    @Test
    public void canFindUserByEmail() {
        final DbUser johnDoe = userRepository.persist(new DbUser("john@doe.com", "John Doe"));
        final DbUser janeDoe = userRepository.persist(new DbUser("jane@doe.com", "Jane Doe"));
        assertTrue(johnDoe.equals(userRepository.findByUuid(johnDoe.getUuid())));
        assertTrue(janeDoe.equals(userRepository.findByUuid(janeDoe.getUuid())));
        assertNull(userRepository.findByUuid("NotFound"), "Mistakenly found a user by uuid not in database");
    }

    @Test
    public void cannotPersistMoreThanOneUserWithSameEmailAddress() {
        final DbUser johnDoe = userRepository.persist(new DbUser("john@doe.com", "John Doe"));
        assertNotNull(johnDoe.getId(), "Newly persisted user did not have primary key.");

        try {
            userRepository.persist(new DbUser("john@doe.com", "Jim Doe"));
            fail("Should have disallowed persisting second user with duplicated email");
        } catch (Exception e){

        }
    }

    @Test
    public void canDeleteUser() {
        final DbUser johnDoe = userRepository.persist(new DbUser("john@doe.com", "John Doe"));
        assertNotNull(johnDoe.getId(), "Newly persisted user did not have primary key.");

        userRepository.delete(johnDoe);
        DbUser dbuser = userRepository.findByUuid(johnDoe.getUuid());
        assertNull(dbuser, "User did not get deleted");
        
    }

}
