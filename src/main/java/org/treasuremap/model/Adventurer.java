package org.treasuremap.model;

/**
 * Cette classe représente un Aventurier.
 *
 * @author Matthieu Dupas
 * @version 1.0
 * @since 2024
 */
public class Adventurer {
    private final String name;
    private final String movements; // Movement sequence as a string (e.g., "AADADA")
    private Position position;
    private Orientation orientation;
    private int currentMoveIndex;
    private int collectedTreasures;

    public Adventurer(String name, Position startPosition, Orientation startOrientation, String movements) {
        this.name = name;
        this.position = startPosition;
        this.orientation = startOrientation;
        this.movements = movements;
        this.currentMoveIndex = 0;
        this.collectedTreasures = 0;
    }

    public String getName() {
        return name;
    }

    public Position getPosition() {
        return position;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public int getCollectedTreasures() {
        return collectedTreasures;
    }

    public boolean hasNextMove() {
        return currentMoveIndex < movements.length();
    }

    public Movement getNextMove() {
        char moveChar = movements.charAt(currentMoveIndex);
        currentMoveIndex++;
        return Movement.fromCode(String.valueOf(moveChar));
    }

    public Position getNextPosition() {
        return position.move(orientation);
    }

    public void moveForward(Position newPosition) {
        this.position = newPosition;
    }

    public void turnLeft() {
        this.orientation = orientation.turnLeft();
    }

    public void turnRight() {
        this.orientation = orientation.turnRight();
    }

    public void collectTreasure() {
        this.collectedTreasures++;
    }

    @Override
    public String toString() {
        return "Adventurer{name='" + name + "', position=" + position + ", orientation=" + orientation +
                ", collectedTreasures=" + collectedTreasures + "}";
    }
}
