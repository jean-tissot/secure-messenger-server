package com.messenger.secure.metier.repository;

import com.messenger.secure.metier.dto.Group;

import org.springframework.data.repository.CrudRepository;

public interface GroupRestRepository extends CrudRepository<Group, Integer> {
    
}
