package com.test.autobusiness.mappers;

import com.test.autobusiness.dto.details.DetailsRequest;
import com.test.autobusiness.dto.details.DetailsResponse;
import com.test.autobusiness.entities.Details;
import org.mapstruct.Mapper;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface DetailsMapper {

    DetailsResponse detailsToDetailsResponse(Details details);

    Details detailsRequestToDetails(DetailsRequest details);

    Set<DetailsResponse> detailsToDetailsResponseSet(Set<Details> detailsSet);


}
