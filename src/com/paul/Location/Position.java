package com.paul.Location;

import java.util.concurrent.ThreadLocalRandom;

public class Position {
    private int x;
    private int y;

    public Position(){
        this.x = ThreadLocalRandom.current().nextInt(0,Location.islandMap.length);
        this.y = ThreadLocalRandom.current().nextInt(0,Location.islandMap[0].length);
    }

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
