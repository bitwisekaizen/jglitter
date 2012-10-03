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
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class JpaFollowerRepository implements FollowerRepository {

    @PersistenceContext
    private EntityManager em;

    public DbFollower persist(final DbFollower dbFollower) {
        em.persist(dbFollower);
        return dbFollower;
    }

    public DbFollower findById(final Integer id) {
        return em.getReference(DbFollower.class, id);
    }

    public List<DbUser> findFollowees(DbUser follower) {
        final TypedQuery<DbUser> query = em.createQuery("select d.followee from DbFollower d where d.follower = :follower", DbUser.class);
        return query.setParameter("follower", follower).getResultList();
    }
}
