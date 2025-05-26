package org.treasuremap.model;

public record Position(int x, int y) {

    public Position move(Orientation orientation) {
        return switch (orientation) {
            case NORTH -> new Position(x, y - 1);
            case SOUTH -> new Position(x, y + 1);
            case EAST -> new Position(x + 1, y);
            case WEST -> new Position(x - 1, y);
            default -> throw new IllegalArgumentException("Unknown orientation: " + orientation);
        };
    }
}
