package com.messenger.secure.metier.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "binary_content")
public class BinaryContent implements Serializable {

    @Id
    @Column(name = "id")
    protected int id;

    @Column(name = "type", nullable = false)
    protected String type;

    @Column(name = "content", nullable = false)
    protected byte[] content;

    @OneToOne(mappedBy = "binaryContent")
    protected Message message;

}
