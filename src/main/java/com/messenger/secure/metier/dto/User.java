package com.messenger.secure.metier.dto;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;


@Getter
@Entity
@Table(name = "users")
public class User implements Serializable {

    @Id
    @Column(name = "id")
    protected int id;

    @Setter
    @Column(name = "pseudo")
    protected String pseudo;

    @Setter
    @Column(name = "last_time_online")
    protected Timestamp lastTimeOnline;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
        name="friends",
        joinColumns=@JoinColumn(name="user1_id"),
        inverseJoinColumns=@JoinColumn(name="user2_id")
    )
    protected List<User> friends = new ArrayList<>();

    @JsonIgnore
    @ManyToMany
    @JoinTable(
        name="friends",
        joinColumns=@JoinColumn(name="user2_id"),
        inverseJoinColumns=@JoinColumn(name="user1_id")
    )
    protected List<User> friendOf = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "group")
    protected List<UserGroup> userGroups = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "sender")
    protected List<Message> messages = new ArrayList<>();


}
