package org.treasuremap.model;

import java.util.HashMap;
import java.util.Map;

public class TreasureMap {
    private final int width;
    private final int height;
    private final Map<Position, Integer> treasures; // Position and count of treasures
    private final boolean[][] mountains; // True if there's a mountain

    public TreasureMap(int width, int height) {
        this.width = width;
        this.height = height;
        this.treasures = new HashMap<>();
        this.mountains = new boolean[width][height];
    }

    public void addMountain(Position position) {
        if (isPositionWithinBounds(position)) {
            mountains[position.x()][position.y()] = true;
        } else {
            throw new IllegalArgumentException("Mountain position out of bounds: " + position);
        }
    }

    public void addTreasure(Position position, int count) {
        if (isPositionWithinBounds(position)) {
            treasures.put(position, treasures.getOrDefault(position, 0) + count);
        } else {
            throw new IllegalArgumentException("Treasure position out of bounds: " + position);
        }
    }

    public boolean isAccessible(Position position) {
        return isPositionWithinBounds(position) && !mountains[position.x()][position.y()];
    }

    public void collectTreasure(Position position, Adventurer adventurer) {
        if (treasures.containsKey(position) && treasures.get(position) > 0) {
            int remainingTreasures = treasures.get(position) - 1;
            treasures.put(position, remainingTreasures);
            adventurer.collectTreasure();

            if (remainingTreasures == 0) {
                treasures.remove(position);
            }
        }
    }

    private boolean isPositionWithinBounds(Position position) {
        return position.x() >= 0 && position.x() < width &&
                position.y() >= 0 && position.y() < height;
    }

    public int getTreasureCount(Position position) {
        if (isPositionWithinBounds(position)) {
            return treasures.getOrDefault(position, 0);
        } else {
            throw new IllegalArgumentException("Position out of bounds: " + position);
        }
    }

    public void printMap() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Position pos = new Position(x, y);
                if (mountains[x][y]) {
                    System.out.print(" M ");
                } else if (treasures.containsKey(pos)) {
                    System.out.print(" T(" + treasures.get(pos) + ") ");
                } else {
                    System.out.print(" . ");
                }
            }
            System.out.println();
        }
    }
}
