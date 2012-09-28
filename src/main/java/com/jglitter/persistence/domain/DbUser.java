/*
 * Project Horizon
 *
 * (c) 2012 VMware, Inc. All rights reserved.
 * VMware Confidential.
 */
package com.jglitter.persistence.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class DbUser {

    @Id
    @GeneratedValue
    private Integer id;

    @Column
    private String email;

    @Column
    private String name;

    public DbUser(final String email, final String name) {
        this();
        this.email = email;
        this.name = name;
    }

    protected DbUser() {
    }

    public Integer getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
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
