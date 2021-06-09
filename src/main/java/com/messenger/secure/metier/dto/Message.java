package com.messenger.secure.metier.dto;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "messages")
public class Message implements Serializable {

    @Id
    @Column(name = "id")
    protected int id;

    @ManyToOne
    @JoinColumn(name = "group_id", nullable = false)
    protected Group group;

    @ManyToOne
    @JoinColumn(name = "sender", nullable = false)
    protected User sender;

    @Column(name = "when_sent")
    protected Timestamp whenSent;

    @Column(name = "text")
    protected String text;

    @OneToOne(cascade = CascadeType.ALL)  // si on supprime un message, supprimer les fichiers associés
    @JoinColumn(name = "binary_id")
    protected BinaryContent binaryContent;

}
