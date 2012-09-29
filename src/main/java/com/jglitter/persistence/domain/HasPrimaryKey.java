/*
 * Project Horizon
 *
 * (c) 2012 VMware, Inc. All rights reserved.
 * VMware Confidential.
 */
package com.jglitter.persistence.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class HasPrimaryKey {

    @Id
    @GeneratedValue
    private Integer id;

    public Integer getId() {
        return id;
    }
}
