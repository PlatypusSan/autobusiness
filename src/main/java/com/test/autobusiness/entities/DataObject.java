package com.test.autobusiness.entities;

import lombok.Data;

import javax.persistence.Entity;

@Entity
@Data
public class DataObject extends AbstractEntity {

    private String name;
    private byte[] file;
}
