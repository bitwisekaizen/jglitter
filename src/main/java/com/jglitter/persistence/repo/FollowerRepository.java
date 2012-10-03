package com.jglitter.persistence.repo;

import com.jglitter.persistence.domain.DbFollower;

public interface FollowerRepository {
    DbFollower persist(DbFollower dbFollower);

    DbFollower findById(final Integer id);
}