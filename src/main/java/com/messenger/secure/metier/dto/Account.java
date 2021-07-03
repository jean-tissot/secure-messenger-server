package com.messenger.secure.metier.dto;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;

@Data
@Entity
@Table(name = "accounts")
public class Account implements UserDetails {
    
    @Id
    @Column(name = "username")
    protected String username;

    @Column(name = "password")
    protected String password;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    protected User user;

    @Column(name = "creation_date")
    protected Timestamp creationDate; 

    @Column(name = "enabled")
    protected boolean enabled;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority("authenticated"));
		return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

}
