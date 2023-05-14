package com.example.apidemo.utils;

public enum Gender {
    MALE(1),
    FEMALE(2),
    OTHER(3);

    private final int value;

    private Gender(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
