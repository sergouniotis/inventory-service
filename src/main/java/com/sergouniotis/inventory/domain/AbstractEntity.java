package com.sergouniotis.inventory.domain;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class AbstractEntity implements Serializable {

    @Id
    @Column(name = "id")
    private UUID id = UUID.randomUUID();

    
    public void setId(UUID id) {
        this.id = id;
    }


    public UUID getId() {
        return id;
    }


    
}
