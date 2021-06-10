package com.messenger.secure.metier.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name = "groups")
public class Group implements Serializable {

    @Id
    @Column(name = "id")
    protected int id;

    @Column(name = "name", nullable = false)
    protected String name;

    @JsonIgnore
    @OneToMany(mappedBy = "group")
    protected List<Message> messages = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "group")
    protected List<UserGroup> userGroups = new ArrayList<>();

}

