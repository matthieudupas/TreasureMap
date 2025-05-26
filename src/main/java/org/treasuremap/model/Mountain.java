package org.treasuremap.model;

public class Mountain {
    int x;
    int y;

    public Mountain(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "Mountain{" + "x=" + x + ", y=" + y + '}';
    }
}
