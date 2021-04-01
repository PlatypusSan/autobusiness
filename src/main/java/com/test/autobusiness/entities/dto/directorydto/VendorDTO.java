package com.test.autobusiness.entities.dto.directorydto;

import lombok.Data;

import java.util.List;

@Data
public class VendorDTO {

    private String name;

    private long size;

    private List<DriveUnitDTO> driveUnitDTOList;

    public VendorDTO(String name) {
        this.name = name;
    }
}
