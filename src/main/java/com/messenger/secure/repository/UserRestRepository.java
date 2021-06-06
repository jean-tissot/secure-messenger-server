package com.messenger.secure.repository;

import com.messenger.secure.dto.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRestRepository extends CrudRepository<User, Integer> {


}
