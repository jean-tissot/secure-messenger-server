package com.messenger.secure.metier.services;
 
import javax.annotation.Resource;

import com.messenger.secure.metier.dto.Account;
import com.messenger.secure.metier.dto.User;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.log4j.Log4j2;
 
@Log4j2
@Service("authUserDetailsService")
public class AuthenticationService implements UserDetailsService {

	@Resource
	private AccountService accountService;

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		if (username.trim().isEmpty()) {
			throw new UsernameNotFoundException("username is empty");
		}

		return accountService.getAccount(username);
	}

	public User getUserConnected() {
        User user;
        try {
            Account account = (Account) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            user = account.getUser();
        } catch (ClassCastException e) {
            log.warn("getCurrentUser: aucun utilisateur connecté ? Exception : {}", e);
            user = null;
        }
        return user;
    }

}