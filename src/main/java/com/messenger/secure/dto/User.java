package com.messenger.secure.dto;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class User implements Serializable {

    public User() { super(); }
    
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "pseudo")
    private String pseudo;

    @Column(name = "last_time_online")
    private Timestamp lastTimeOnline;

}
