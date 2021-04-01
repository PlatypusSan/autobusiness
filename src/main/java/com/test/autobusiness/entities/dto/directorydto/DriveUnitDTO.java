package com.test.autobusiness.entities.dto.directorydto;

import lombok.Data;

@Data
public class DriveUnitDTO {

    private String name;

    //private List<Long> carIdList;

    private long size;

    public DriveUnitDTO(String name) {
        this.name = name;
    }
}
