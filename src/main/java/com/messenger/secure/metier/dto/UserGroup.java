package com.messenger.secure.metier.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.messenger.secure.metier.dto.idclasses.UserGroupId;

import lombok.Data;

@Data
@Entity
@Table(name = "user_group")
@IdClass(UserGroupId.class)
public class UserGroup implements Serializable {
    
    @Id
    @ManyToOne
    @JoinColumn(name = "group_id")
    protected Group group;

    @Id
    @ManyToOne
    @JoinColumn(name = "user_id")
    protected User user;

    @Column(name = "role_id")
    protected int roleId;

    @OneToOne
    @JoinColumn(name = "last_received_id")
    protected Message lastReceivedMessage;

    @OneToOne
    @JoinColumn(name = "last_read_id")
    protected Message lastReadMessage;
    
}
