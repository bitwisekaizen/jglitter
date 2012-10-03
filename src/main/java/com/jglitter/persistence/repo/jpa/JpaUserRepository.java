/*
 * Project Horizon
 *
 * (c) 2012 VMware, Inc. All rights reserved.
 * VMware Confidential.
 */
package com.jglitter.persistence.repo.jpa;

import com.jglitter.persistence.domain.DbUser;
import com.jglitter.persistence.repo.UserRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class JpaUserRepository implements UserRepository {

    @PersistenceContext
    private EntityManager em;

    public DbUser persist(final DbUser user) {
        em.persist(user);
        return user;
    }

    public List<DbUser> findAll() {
        return em.createQuery("select d from DbUser d", DbUser.class).getResultList();
    }

    public DbUser findByEmail(final String id) {
        final TypedQuery<DbUser> query = em.createQuery("select d from DbUser d where d.uuid = :user_uuid", DbUser.class);
        final List<DbUser> users = query.setParameter("user_uuid", id).getResultList();
        return users.isEmpty() ? null : users.get(0);
    }

    public void delete(DbUser user) {
        em.remove(user);
    }
}
