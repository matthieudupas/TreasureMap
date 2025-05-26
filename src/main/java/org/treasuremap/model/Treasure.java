package org.treasuremap.model;

public class Treasure {
    int x;
    int y;
    int count;

    public Treasure(int x, int y, int count) {
        this.x = x;
        this.y = y;
        this.count = count;
    }

    @Override
    public String toString() {
        return "Treasure{" + "x=" + x + ", y=" + y + ", count=" + count + '}';
    }
}
