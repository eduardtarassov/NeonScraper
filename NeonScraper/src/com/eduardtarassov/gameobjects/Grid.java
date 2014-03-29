package com.eduardtarassov.gameobjects;

import com.badlogic.gdx.math.Vector2;
import com.eduardtarassov.nshelpers.GridUnit;

import java.util.Random;

/**
 * Created by Eduard on 29/03/14.
 */
public class Grid {
    private static GridUnit[][] gridUnits;
                  public static Grid instance;
    private static final int GRID_WIDTH = 12;
    private static final int GRID_HEIGHT = 12;
    private static int gridLeft;
    private static int gridTop;
    private static int gridBottom;
    private static int gridRight;

    private static int gridColumns;
    private static int gridRows;

    public static void setInstance(int playAreaLeft,int playAreaTop,int playAreaRight,int playAreaBottom){
        gridLeft=playAreaLeft;
        gridRight=playAreaRight;
        gridTop = playAreaTop;
        gridBottom=playAreaBottom;

        gridColumns=(Math.abs(playAreaRight-playAreaLeft)/GRID_WIDTH);
        gridRows=(Math.abs(playAreaBottom-playAreaTop)/GRID_HEIGHT);

        gridUnits=new GridUnit[gridRows][gridColumns];
        for(int i=0;i<gridRows;i++){
            for(int j=0;j<gridColumns;j++){
                gridUnits[i][j]=new GridUnit(GRID_WIDTH,GRID_HEIGHT, i, j, false);
            }

        }

    }

    public static void setObjectAt(float x, float y, boolean type){
        int yindex = (int)(x-gridLeft)/GRID_WIDTH;    //MISTAKES HERE!!!
        int xindex = (int)(y-gridTop)/GRID_HEIGHT;
        gridUnits[xindex][yindex].objectType = type;
    }

    public static void RemoveObjectAt(int x,int y){
        setObjectAt(x, y, false);
    }

    public static Vector2 GetRandomIndex(){
        Random r=new Random();
        while(true){
            int y=r.nextInt(gridColumns);
            int x=r.nextInt(gridRows);
            if(!gridUnits[x][y].objectType)
                return new Vector2(x*GRID_WIDTH+gridLeft,y*GRID_HEIGHT+gridTop);
        }
    }

    public static boolean getCellEmpty(int gx,int gy){
        return gridUnits[gx][gy].objectType;
    }


    public static Grid getInstance(){
        if (instance != null)
            return instance;
        else {System.out.println("YOUR GRID OBJECT IS NULL!!!!");
        return null;
        }
    }
}
