package com.messenger.secure.metier.repository;

import java.util.List;

import com.messenger.secure.metier.dto.Group;
import com.messenger.secure.metier.dto.User;
import com.messenger.secure.metier.dto.UserGroup;

import org.springframework.data.repository.CrudRepository;

public interface UserGroupRestRepository extends CrudRepository<UserGroup, Integer> {
    
    List<UserGroup> deleteByGroupAndUser(Group group, User user);

}
