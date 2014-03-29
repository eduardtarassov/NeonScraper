package com.eduardtarassov.nshelpers;

/**
 * Created by Eduard on 29/03/14.
 * This class will allow us to create grid to add game objects automatically in random positions
 * and at the same time make it impossible to intersect them. So this class is a single grid cell.
 */
public class GridUnit {
    public int xIndex;
    public int YIndex;
    public boolean objectType;
    int width;
    int height;

    public GridUnit(int width, int height, int xIndex, int yIndex, boolean objectType) {
        this.width = width;
        this.height = height;
        this.xIndex = xIndex;
        this.YIndex = yIndex;
        this.objectType = objectType;
    }
}
