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

    /**
     * Calculates the manhattan distance between the current object's coordinates and
     * the destination point
     *
     * @param destPoint destination point
     * @return Direction vector
     */
    public IntVector manhattan(IntVector destPoint) {
        int distanceX = Math.abs(destPoint.getX() - x);
        int distanceY = Math.abs(destPoint.getY() - y);
        return new IntVector(distanceX, distanceY);

    }

}