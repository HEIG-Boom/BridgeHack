package ch.heigvd.mcr.bridgehack.utils;

import lombok.Getter;

/**
 * A simple vector class for pairs of integers
 */
public class IntVector {
    @Getter
    private int x;
    @Getter
    private int y;

    /**
     * Vector constructor
     *
     * @param x The x component of the vector
     * @param y The y component of the vector
     */
    public IntVector(int x, int y) {
        this.x = x;
        this.y = y;
    }
}