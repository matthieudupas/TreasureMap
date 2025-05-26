package org.treasuremap.model;

public enum Orientation {
    NORTH("N"),
    SOUTH("S"),
    EAST("E"),
    WEST("W");

    private final String code;

    Orientation(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public Orientation turnLeft() {
        return switch (this) {
            case NORTH -> WEST;
            case SOUTH -> EAST;
            case EAST -> NORTH;
            case WEST -> SOUTH;
            default -> throw new IllegalArgumentException("Unknown orientation: " + this);
        };
    }

    public Orientation turnRight() {
        return switch (this) {
            case NORTH -> EAST;
            case SOUTH -> WEST;
            case EAST -> SOUTH;
            case WEST -> NORTH;
            default -> throw new IllegalArgumentException("Unknown orientation: " + this);
        };
    }
}
