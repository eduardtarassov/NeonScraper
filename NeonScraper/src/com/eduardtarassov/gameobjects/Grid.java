package com.eduardtarassov.gameobjects;

import com.badlogic.gdx.math.Vector2;
import com.eduardtarassov.nshelpers.GridUnit;

import java.util.Random;

/**
 * Created by Eduard on 29/03/14.
 * This class is responsible for creation of grid of GridUnits, and setting the size of it.
 */
public class Grid {
    private static GridUnit[][] gridUnits;
    public static int gridDividedColumns;
    public static int gridDividedRows;
    private static int gridWidth;
    private static int gridHeight;
    public static final int GRID_COLUMNS = 12;
    public static final int GRID_ROWS = 8;


    public static void setInstance(int playAreaLeft, int playAreaTop, int playAreaRight, int playAreaBottom) {

        gridWidth = playAreaRight - playAreaLeft;
        gridHeight = playAreaBottom - playAreaTop;
        gridDividedColumns = gridWidth / GRID_COLUMNS;
        gridDividedRows = gridHeight / GRID_ROWS;

        gridUnits = new GridUnit[GRID_COLUMNS][GRID_ROWS];
        for (int i = 0; i < GRID_COLUMNS; i++) {
            for (int j = 0; j < GRID_ROWS; j++) {

                gridUnits[i][j] = new GridUnit(gridWidth, gridHeight, i*gridDividedColumns, j*gridDividedRows, false);
            }

        }

    }

    public static void setObjectAt(float x, float y) {
        int yindex = (int) x / gridDividedColumns;
        int xindex = (int) y / gridDividedRows;
         if (!gridUnits[xindex][yindex].objectType)
        gridUnits[xindex][yindex].objectType = true;
    }

    public static void RemoveObjectAt(int x, int y) {
        int yindex = (int) x / gridDividedColumns;
        int xindex = (int) y / gridDividedRows;
        if (gridUnits[xindex][yindex].objectType)
            gridUnits[xindex][yindex].objectType = false;
    }

    public static Vector2 setRandomIndex() {
        Random r = new Random();
        while (true) {
            int x = r.nextInt(gridWidth + 1);
            int y = r.nextInt(gridHeight + 1);
            setObjectAt(x, y);
        }
    }

/*    public static Grid getInstance() {
        if (instance != null)
            return instance;
        else {
            System.out.println("YOUR GRID OBJECT IS NULL!!!!");
            return null;
        }
    }*/
}
