/*
 * Copyright (c) 2015 by Marfeel Solutions (http://www.marfeel.com)
 * All Rights Reserved.
 *
 * NOTICE:  All information contained herein is, and remains
 * the property of Marfeel Solutions S.L and its suppliers,
 * if any.  The intellectual and technical concepts contained
 * herein are proprietary to Marfeel Solutions S.L and its
 * suppliers and are protected by trade secret or copyright law.
 * Dissemination of this information or reproduction of this material
 * is strictly forbidden unless prior written permission is obtained
 * from Marfeel Solutions SL.
 */

package com.stl.security;

import com.stl.security.entity.Securable;
import com.stl.security.entity.Token;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.acls.model.Permission;
import org.springframework.security.core.userdetails.UserDetails;

public interface PermissionRepository {

    @PreAuthorize("hasRole('" + StlRole.TRAVELER + "')")
    Token createToken();

    @PreAuthorize("hasRole('" + StlRole.TRAVELER + "')")
    Securable createPermission(UserDetails user, Securable securable);

    @PostAuthorize("hasPermission(returnObject.token, 'WRITE')")
    Securable addPermission(UserDetails user, Securable securable, Permission permission);

    @PostAuthorize("hasPermission(returnObject.token, 'WRITE')")
    Securable removePermission(UserDetails user, Securable securable, Permission permission);
}