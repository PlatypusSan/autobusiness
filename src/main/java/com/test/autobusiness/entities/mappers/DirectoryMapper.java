package com.test.autobusiness.entities.mappers;

import com.test.autobusiness.entities.AbstractEntity;
import com.test.autobusiness.entities.DTOs.DirectoryDTOs.DriveUnitDTO;
import com.test.autobusiness.entities.DTOs.DirectoryDTOs.VendorDTO;
import com.test.autobusiness.entities.DirectoryElement;
import com.test.autobusiness.entities.columnEnums.DriveUnit;
import com.test.autobusiness.repositories.CarRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class DirectoryMapper {

    private final CarRepository carRepository;

    public DirectoryMapper(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public List<VendorDTO> dirElementToVendorDTOList(List<DirectoryElement> directoryElementList) {

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
                    .map(DirectoryElement::getDrive_unit)
                    .forEach(p -> driveUnitDTOList.add(new DriveUnitDTO(p)));
            driveUnitDTOList.forEach(driveUnitDTO -> {
                driveUnitDTO.setCarIdList(carRepository.findCarByDriveUnitAndDeclarationVendorName(driveUnitDTO.getName(), vendorDTO.getName())
                .stream()
                .map(AbstractEntity::getId)
                .collect(Collectors.toList()));

            });
            vendorDTO.setDriveUnitDTOList(driveUnitDTOList);
        });



        return vendorDTOList;
    }
}
