package com.jglitter.persistence.repo;

import com.jglitter.persistence.domain.DbFollower;
import com.jglitter.persistence.domain.DbUser;

import java.util.List;

public interface FollowerRepository {
    DbFollower persist(DbFollower dbFollower);

    DbFollower findById(final Integer id);

    List<DbUser> findFollowees(DbUser follower);
}