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

package com.stl.security.entity;

import org.springframework.data.mongodb.core.index.Indexed;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Token implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Indexed(unique = true)
    private long id;

    public Token() {}

    public Token(long id) {
        this.id = id;
    }

    public void setId(long id) {
        this.id = id;

    }

    public long getId() {
        return id;
    }
}