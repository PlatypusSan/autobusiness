package com.test.autobusiness.entities.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum SortingField {
    BRAND("brand"),
    MODEL("model"),
    GENERATION("generation"),
    ENGINE_VOLUME("engineVolume"),
    AGE("age"),
    MILE_AGE("mileAge"),
    PRICE("price");

    private String sortingFieldName;

    SortingField(String sortingField) {
        this.sortingFieldName = sortingField;
    }

    @JsonValue
    public String getSortingFieldName() {
        return sortingFieldName;
    }
}
