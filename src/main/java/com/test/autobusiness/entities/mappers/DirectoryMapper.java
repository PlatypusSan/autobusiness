package com.test.autobusiness.entities.mappers;

import com.test.autobusiness.entities.DirectoryElement;
import com.test.autobusiness.entities.dto.directorydto.DriveUnitDTO;
import com.test.autobusiness.entities.dto.directorydto.VendorDTO;
import org.mapstruct.Mapper;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface DirectoryMapper {

    default List<VendorDTO> dirElementToVendorDTOList(List<DirectoryElement> directoryElementList) {

        List<VendorDTO> vendorDTOList = new ArrayList<>();

        directoryElementList
                .stream()
                .map(DirectoryElement::getVendor_name)
                .distinct()
                .forEach(p -> vendorDTOList.add(new VendorDTO(p)));

        vendorDTOList.forEach(vendorDTO -> {
            List<DriveUnitDTO> driveUnitDTOList = new ArrayList<>();
            directoryElementList
                    .stream()
                    .filter(directoryElement -> directoryElement.getVendor_name().equals(vendorDTO.getName()))
                    .forEach(p -> driveUnitDTOList.add(new DriveUnitDTO(p.getDrive_unit(), p.getSize())));

            vendorDTO.setDriveUnitDTOList(driveUnitDTOList);
            long vendorCarListSize = 0;
            for (int i = 0; i < vendorDTO.getDriveUnitDTOList().size(); i++) {
                vendorCarListSize += vendorDTO.getDriveUnitDTOList().get(i).getSize();
            }
            vendorDTO.setSize(vendorCarListSize);
        });


        return vendorDTOList;
    }
}