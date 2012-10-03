/*
 * Project Horizon
 *
 * (c) 2012 VMware, Inc. All rights reserved.
 * VMware Confidential.
 */
package com.jglitter.domain;

import com.jglitter.persistence.domain.DbUser;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@XmlRootElement
public class Users {

    private Collection<User> users = new ArrayList<User>();

    public boolean contains(final User user) {
        return users.contains(user);
    }

    public void add(final User user) {
        users.add(user);
    }

    public Collection<User> getUsers() {
        return users;
    }

    public void setUsers(final Collection<User> users) {
        this.users = users;
    }
}
