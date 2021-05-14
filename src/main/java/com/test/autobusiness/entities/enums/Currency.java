package com.test.autobusiness.entities.enums;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public enum Currency {
    BYN,
    EUR,
    USD,
    UNKNOWN;

    public static Currency fromString(String value) {
        for (Currency grade : values()) {
            if (grade.name().equalsIgnoreCase(value)) {
                return grade;
            }
        }

        return UNKNOWN;
    }
}
