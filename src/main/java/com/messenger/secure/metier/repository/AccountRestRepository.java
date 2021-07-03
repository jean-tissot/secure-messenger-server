package com.messenger.secure.metier.repository;

import com.messenger.secure.metier.dto.Account;

import org.springframework.data.repository.CrudRepository;

public interface AccountRestRepository extends CrudRepository<Account, String> {
    
}
