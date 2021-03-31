package com.test.autobusiness.entities.DTOs.DirectoryDTOs;

import lombok.Data;

import java.util.List;

@Data
public class DriveUnitDTO {

    private String name;

    private List<Long> carIdList;

    public DriveUnitDTO(String name) {
        this.name = name;
    }
}
