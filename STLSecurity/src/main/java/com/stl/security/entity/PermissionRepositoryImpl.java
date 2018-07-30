/*
 * Copyright (c) 2017 by Marfeel Solutions (http://www.marfeel.com)
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

package com.stl.security.entity;

import com.stl.security.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.acls.domain.BasePermission;
import org.springframework.security.acls.domain.ObjectIdentityImpl;
import org.springframework.security.acls.domain.PrincipalSid;
import org.springframework.security.acls.jdbc.JdbcMutableAclService;
import org.springframework.security.acls.model.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class PermissionRepositoryImpl implements PermissionRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private JdbcMutableAclService aclService;

    @Override
    @Transactional
    public Token createToken() {
        Token token = new Token();
        entityManager.persist(token);
        return token;
    }

    @Override
    @Transactional
    public Securable createPermission(UserDetails user, Securable securable) {
       return addPermission(user, securable, BasePermission.WRITE);
    };

    @Override
    @Transactional
    public Securable addPermission(UserDetails user, Securable securable, Permission permission) {
        MutableAcl accessControlList = getAccessControlList(securable);
        Sid securityIdentity = new PrincipalSid(user.getUsername());

        accessControlList.insertAce(accessControlList.getEntries().size(), permission, securityIdentity, true);
        aclService.updateAcl(accessControlList);

        return securable;
    };

    @Override
    @Transactional
    public Securable removePermission(UserDetails user, Securable securable, Permission permission) {
        MutableAcl accessControlList = getAccessControlList(securable);
        Sid securityIdentity = new PrincipalSid(user.getUsername());
        List<AccessControlEntry> entries = accessControlList.getEntries();

        for (int i = 0; i < entries.size(); i++) {
            AccessControlEntry ace = entries.get(i);
            if (ace.getSid().equals(securityIdentity) && ace.getPermission().equals(permission)) {
                try {
                    accessControlList.deleteAce(i);
                } catch (NotFoundException e) {
                    throw new AccessDeniedException(e.getMessage());
                }
            }
        }

        aclService.updateAcl(accessControlList);

        return securable;
    }

    private MutableAcl getAccessControlList(Securable securable) {
        ObjectIdentity objectIdentity = new ObjectIdentityImpl(Token.class, securable.getToken().getId());
        MutableAcl acl;

        try {
            acl = (MutableAcl) aclService.readAclById(objectIdentity);
        } catch (NotFoundException nfe) {
            acl = aclService.createAcl(objectIdentity);
        }

        return acl;
    }
}