package com.messenger.secure.metier.repository;

import com.messenger.secure.metier.dto.User;

import org.springframework.data.repository.CrudRepository;

public interface UserRestRepository extends CrudRepository<User, Integer> {


}
