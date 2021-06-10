package com.messenger.secure.metier.dto.idclasses;

import java.io.Serializable;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UserGroupId implements Serializable {
    int group;
    int user;
}
