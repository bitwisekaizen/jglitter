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

    public DbUser findById(final Integer id) {
        return em.getReference(DbUser.class, id);
    }

    public List<DbUser> findAll() {
        return em.createQuery("select d from DbUser d", DbUser.class).getResultList();
    }

    public DbUser findByEmail(final String email) {
        final TypedQuery<DbUser> query = em.createQuery("select d from DbUser d where d.email = :email", DbUser.class);
        final List<DbUser> users = query.setParameter("email", email).getResultList();
        return users.isEmpty() ? null : users.get(0);
    }
}
