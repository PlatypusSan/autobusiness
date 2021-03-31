package com.test.autobusiness.entities.DTOs.DirectoryDTOs;

import lombok.Data;
import lombok.Value;

import java.util.List;

@Data
public class VendorDTO {

    private String name;

    private List<DriveUnitDTO> driveUnitDTOList;

    public VendorDTO(String name) {
        this.name = name;
    }
}
