/*
 * Project Horizon
 *
 * (c) 2012 VMware, Inc. All rights reserved.
 * VMware Confidential.
 */
package com.jglitter.services;

import com.jglitter.domain.User;
import com.jglitter.domain.Users;
import com.jglitter.persistence.domain.DbUser;
import com.jglitter.persistence.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RepositoryPoweredUserService implements UserService {

    @Autowired
    private UserRepository userRepo;

    @Transactional
    public User createUser(final User user) {
        final DbUser persisted = userRepo.persist(new DbUser(user.getEmail(), user.getName()));
        return new User(persisted);
    }

    @Transactional(readOnly = true)
    public Users findAllUsers() {
        final Users users = new Users();
        final List<DbUser> all = userRepo.findAll();
        for (DbUser eachUser : all) {
            users.add(new User(eachUser));
        }
        return users;
    }

    public User findById(final String userId) {
        return new User(userRepo.findByUuid(userId));
    }
}
