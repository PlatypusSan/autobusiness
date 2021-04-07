package com.test.autobusiness.entities.mappers;

import com.test.autobusiness.entities.Details;
import com.test.autobusiness.entities.dto.detailsdto.DetailsRequest;
import com.test.autobusiness.entities.dto.detailsdto.DetailsResponse;
import org.mapstruct.Mapper;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface DetailsMapper {

    DetailsResponse detailsRequestToResponse(Details details);

    Details detailsResponseToRequest(DetailsRequest details);

    Set<DetailsResponse> detailsToDetailsResponseSet(Set<Details> detailsSet);
}
