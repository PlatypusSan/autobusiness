package com.test.autobusiness.entities.mappers;

import com.test.autobusiness.entities.DTOs.DetailsDTOs.DetailsRequest;
import com.test.autobusiness.entities.DTOs.DetailsDTOs.DetailsResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DetailsMapper {

    DetailsResponse detailsRequestToResponse(DetailsRequest detailsRequest);

    DetailsRequest detailsResponseToRequest(DetailsResponse detailsResponse);
}
