package com.jglitter.services;

import com.jglitter.domain.User;
import com.jglitter.domain.Users;
import com.jglitter.persistence.domain.DbFollower;
import com.jglitter.persistence.domain.DbUser;
import com.jglitter.persistence.repo.FollowerRepository;
import com.jglitter.persistence.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: mchaudhary
 * Date: 10/3/12
 * Time: 2:03 PM
 * To change this template use File | Settings | File Templates.
 */
@Service

public class RepositoryPoweredFollowerService implements FollowerService {

    @Autowired
    FollowerRepository followerRep;

    @Autowired
    UserRepository userRepo;

    @Transactional
    public void createFollower(User follower, User followee) {
        DbUser dbFollower = userRepo.findByUuid(follower.getId());
        DbUser dbFollowee = userRepo.findByUuid(followee.getId());
        followerRep.persist(new DbFollower(dbFollower, dbFollowee));
    }

    public Users findFollowees(User follower) {
        DbUser dbFollower = userRepo.findByUuid(follower.getId());

        Users users = new Users();
        List<DbUser> followees = followerRep.findFollowees(dbFollower);
        for (DbUser eachFollowee : followees) {
            users.add(new User(eachFollowee));
        }

        return users;
    }
}
