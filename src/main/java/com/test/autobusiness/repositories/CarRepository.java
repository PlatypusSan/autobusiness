package com.test.autobusiness.repositories;

import com.test.autobusiness.entities.Car;
import com.test.autobusiness.entities.DirectoryElement;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends PagingAndSortingRepository<Car, Long> {

    @Query(nativeQuery = true, value = "select distinct vendor_name, c.drive_unit\n" +
            "from declaration\n" +
            "join car c on declaration.car_id = c.id")
    List<DirectoryElement> getAllDirectories();

    @Query(nativeQuery = true, value = "select * from car c left join declaration d on d.car_id = c.id where c.drive_unit = :drive_unit and d.vendor_name = :vendor_name")
    List<Car> findCarByDriveUnitAndDeclarationVendorName(
            @Param("drive_unit") String driveUnit,
            @Param("vendor_name") String vendorName);

}
