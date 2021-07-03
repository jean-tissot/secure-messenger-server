package com.messenger.secure.metier.services;

import java.util.Optional;

import javax.annotation.Resource;

import com.messenger.secure.metier.dto.Account;
import com.messenger.secure.metier.repository.AccountRestRepository;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class AccountService {

    @Resource
    private AccountRestRepository accountRestRepository;

    public Account getAccount(String username) throws UsernameNotFoundException {
        Optional<Account> account = accountRestRepository.findById(username);
        if (account.isPresent()) {
            return account.get();
        } else {
            log.warn(String.format("utilisateur %s inconnu", username));
            throw new UsernameNotFoundException("utilisateur inconnu");
        }
    }
    
}
