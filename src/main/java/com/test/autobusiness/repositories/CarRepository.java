package com.test.autobusiness.repositories;

import com.test.autobusiness.entities.Car;
import com.test.autobusiness.entities.DirectoryElement;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends PagingAndSortingRepository<Car, Long>,
        JpaSpecificationExecutor<Car>,
        CarFilterRepository,
        QuerydslPredicateExecutor<Car> {

    @Query(nativeQuery = true, value = "select distinct vendor_name, drive_unit, count(c) as size\n" +
            "from declaration\n" +
            "join car c on declaration.id = c.declaration_id\n" +
            "group by  (vendor_name, drive_unit)")
    List<DirectoryElement> getAllDirectories();

    @Query(nativeQuery = true, value = "select * from declaration d right join car c on d.id = c.declaration_id where drive_unit = :drive_unit and vendor_name = :vendor_name")
    List<Car> findCarByDriveUnitAndDeclarationVendorName(
            @Param("drive_unit") String driveUnit,
            @Param("vendor_name") String vendorName);

    List<Car> findAll();
}
