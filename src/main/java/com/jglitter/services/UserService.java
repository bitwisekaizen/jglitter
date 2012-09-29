/*
 * Project Horizon
 *
 * (c) 2012 VMware, Inc. All rights reserved.
 * VMware Confidential.
 */
package com.jglitter.services;

import com.jglitter.domain.User;
import com.jglitter.domain.Users;

public interface UserService {

    User createUser(User user);

    Users findAllUsers();
}
