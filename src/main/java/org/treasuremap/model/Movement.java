package org.treasuremap.model;

public enum Movement {
    FORWARD("A"),
    TURN_LEFT("G"),
    TURN_RIGHT("D");

    private final String code;

    Movement(String code) {
        this.code = code;
    }

    public static Movement fromCode(String code) {
        return switch (code) {
            case "A" -> FORWARD;
            case "G" -> TURN_LEFT;
            case "D" -> TURN_RIGHT;
            default -> throw new IllegalArgumentException("Unknown movement code: " + code);
        };
    }

    public String getCode() {
        return code;
    }
}
