package com.test.autobusiness.entities.dto.directory;

import lombok.Data;

import java.util.List;

@Data
public class DirectoryDTO {

    List<VendorDTO> vendorDTOList;

    private long size;


}
