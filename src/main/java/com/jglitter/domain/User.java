/*
 * Project Horizon
 *
 * (c) 2012 VMware, Inc. All rights reserved.
 * VMware Confidential.
 */
package com.jglitter.domain;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class User {

    protected User() {}

    public User(final String email, final String name) {
        this();
    }
}
