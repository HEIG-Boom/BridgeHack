package ch.heigvd.mcr.bridgehack.utils;

import lombok.Getter;
import lombok.Setter;

/**
 * A simple vector class for pairs of integers
 */
public class IntVector {
    @Getter
    @Setter
    private int x;
    @Getter
    @Setter
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