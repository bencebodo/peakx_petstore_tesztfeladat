package org.peakx.models;

import com.fasterxml.jackson.annotation.JsonValue;

@SuppressWarnings("unused")
public enum PetStatus {
    AVAILABLE("available"),
    PENDING("pending"),
    SOLD("sold");

    private final String value;

    PetStatus(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }
}
