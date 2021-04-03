package com.test.autobusiness.entities.dto.directorydto;

import lombok.Data;

@Data
public class DriveUnitDTO {

    private String name;

    private long size;

    public DriveUnitDTO(String name, long size) {
        this.name = name;
        this.size = size;
    }
}
