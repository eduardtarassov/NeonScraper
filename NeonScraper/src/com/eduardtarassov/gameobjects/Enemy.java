package com.eduardtarassov.gameobjects;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.Color;

/**
 * Created by Eduard on 28/03/14.
 */
public class Enemy extends Entity {
    private int timeUntilStart = 60;
    public boolean IsActive(){
        return timeUntilStart <= 0;
    }

    public Enemy(TextureRegion image, Vector2 position){
        this.image = image;
        this.position = position;
        radius = image.getRegionWidth() / 2.0f;
    }
}

