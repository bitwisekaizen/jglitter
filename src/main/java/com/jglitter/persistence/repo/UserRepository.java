/*
 * Project Horizon
 *
 * (c) 2012 VMware, Inc. All rights reserved.
 * VMware Confidential.
 */
package com.jglitter.persistence.repo;

import com.jglitter.persistence.domain.DbUser;

import java.util.List;

public interface UserRepository {

    DbUser persist(DbUser user);

    DbUser findById(Integer id);

    List<DbUser> findAll();
}
