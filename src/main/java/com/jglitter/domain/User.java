/*
 * Project Horizon
 *
 * (c) 2012 VMware, Inc. All rights reserved.
 * VMware Confidential.
 */
package com.jglitter.domain;

import com.jglitter.persistence.domain.DbUser;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class User {

    private String email;
    private String name;
    private String id;

    protected User() {
    }

    public User(final String email, final String name) {
        this();
        this.email = email;
        this.name = name;
    }

    public User(final DbUser user) {
        this(user.getEmail(), user.getName());
        this.id = user.getUuid();
    }

    @Override
    public int hashCode() {
        return email.hashCode();
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final User user = (User) o;

        return email.equals(user.email);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }
}
