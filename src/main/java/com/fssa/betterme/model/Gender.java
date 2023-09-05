package com.fssa.betterme.model;

public enum Gender {
    MALE("Male"), FEMALE("Female"), OTHER("Other");

    private final String value;

    Gender(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static Gender fromString(String text) {
        for (Gender gender : Gender.values()) {
            if (gender.value.equalsIgnoreCase(text)) {
                return gender;
            }
        }
        throw new IllegalArgumentException("Invalid gender: " + text);
    }
}

