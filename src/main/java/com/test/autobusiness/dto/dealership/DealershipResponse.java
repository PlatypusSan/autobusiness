package com.test.autobusiness.dto.dealership;

import com.test.autobusiness.dto.contact.ContactResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class DealershipResponse extends RepresentationModel<DealershipResponse> {

    private String name;
    private String city;
    private String time;
    private String property;
    private List<ContactResponse> contacts;

}
