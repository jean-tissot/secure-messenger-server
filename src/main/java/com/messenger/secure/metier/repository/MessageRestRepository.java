package com.messenger.secure.metier.repository;

import com.messenger.secure.metier.dto.Message;

import org.springframework.data.repository.CrudRepository;

public interface MessageRestRepository extends CrudRepository<Message, Integer> {
    
}
