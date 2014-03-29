package com.eduardtarassov.gameobjects;

import com.badlogic.gdx.math.Vector2;
import com.eduardtarassov.nshelpers.GridUnit;

import java.util.Random;

/**
 * Created by Eduard on 29/03/14.
 */
public class Grid {
    public GridUnit[][] gridUnits;
    static final int GRID_WIDTH = 12;
    static final int GRID_HEIGHT = 12;
    int gridLeft;
    int gridTop;
    int gridBottom;
    int gridRight;

    int gridColumns;
    int gridRows;
    public Grid(int playAreaLeft,int playAreaTop,int playAreaRight,int playAreaBottom){
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

    public void SetObjectAt(int x,int y, boolean type){
        int yindex = (x-gridLeft)/GRID_WIDTH;
        int xindex = (y-gridTop)/GRID_HEIGHT;
        gridUnits[xindex][yindex].objectType = type;
    }

    public void RemoveObjectAt(int x,int y){
        SetObjectAt(x, y, false);
    }

    public Vector2 GetRandomIndex(){
        Random r=new Random();
        while(true){
            int y=r.nextInt(gridColumns);
            int x=r.nextInt(gridRows);
            if(!gridUnits[x][y].objectType)
                return new Vector2(x*GRID_WIDTH+gridLeft,y*GRID_HEIGHT+gridTop);
        }
    }

    public boolean getCellEmpty(int gx,int gy){
        return gridUnits[gx][gy].objectType;
    }
}
