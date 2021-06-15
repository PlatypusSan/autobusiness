package com.test.autobusiness.mappers;

import com.test.autobusiness.dto.dealership.DealershipResponse;
import com.test.autobusiness.entities.Dealership;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = ContactMapper.class)
public interface DealershipMapper {

    DealershipResponse dealershipToDealershipResponse(Dealership dealership);

    List<DealershipResponse> dealershipListToDealershipResponseList(List<Dealership> dealershipList);
}
