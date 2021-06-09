package com.test.autobusiness.entities.mappers;

import com.test.autobusiness.entities.Dealership;
import com.test.autobusiness.entities.dto.dealership.DealershipResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = ContactMapper.class)
public interface DealershipMapper {

    DealershipResponse dealershipToDealershipResponse(Dealership dealership);

    List<DealershipResponse> dealershipListToDealershipResponseList(List<Dealership> dealershipList);
}
