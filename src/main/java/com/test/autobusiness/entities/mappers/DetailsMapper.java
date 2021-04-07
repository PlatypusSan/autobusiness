package com.test.autobusiness.entities.mappers;

import com.test.autobusiness.entities.Details;
import com.test.autobusiness.entities.dto.detailsdto.DetailsRequest;
import com.test.autobusiness.entities.dto.detailsdto.DetailsResponse;
import org.mapstruct.Mapper;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface DetailsMapper {

    DetailsResponse detailsToDetailsResponse(Details details);

    Details detailsRequestToDetails(DetailsRequest details);

    Set<DetailsResponse> detailsToDetailsResponseSet(Set<Details> detailsSet);


}
