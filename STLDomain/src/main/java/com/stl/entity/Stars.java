package com.stl.entity;

public enum Stars {
    ONE(0), TWO(1), THREE(2), FOUR(3), FIVE(4);

    private int value;

    public int getValue() {
        return value;
    }

    Stars(int value) {
        this.value = value;
    }
}
