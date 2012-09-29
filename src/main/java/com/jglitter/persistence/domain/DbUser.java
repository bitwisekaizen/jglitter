/*
 * Project Horizon
 *
 * (c) 2012 VMware, Inc. All rights reserved.
 * VMware Confidential.
 */
package com.jglitter.persistence.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.UUID;

@Entity
public class DbUser extends HasPrimaryKey {

    @Column
    private String email;

    @Column
    private String name;

    @Column
    private String uuid;

    public DbUser(final String email, final String name) {
        this();
        this.email = email;
        this.name = name;
        uuid = UUID.randomUUID().toString();
    }

    protected DbUser() {
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getUuid() {
        return uuid;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final DbUser dbUser = (DbUser) o;
        return email.equals(dbUser.email);
    }

    @Override
    public int hashCode() {
        return email.hashCode();
    }
}
