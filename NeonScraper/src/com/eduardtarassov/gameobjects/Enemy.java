package com.eduardtarassov.gameobjects;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.Color;
import com.eduardtarassov.nshelpers.Constants;
import com.sun.javafx.css.Size;
import com.sun.prism.image.ViewPort;

/**
 * Created by Eduard on 28/03/14.
 */
public class Enemy extends Entity {
    private int timeUntilStart = 60;
    public boolean isActive(){
        return timeUntilStart <= 0;
    }

    public Enemy(TextureRegion image, Vector2 position){
        this.image = image;
        this.position = position;
        radius = image.getRegionWidth() / 2.0f;
        color = Color.CLEAR; //!!!!! I am not sure this is gonna be transparent one.
    }

    @Override
    public void update(){
        if (timeUntilStart <= 0)
        {
            //enemy behaviour logic goes here.
        }
        else
        {
            timeUntilStart--;
            //color = Color.WHITE * (1 - timeUntilStart / 60f);
        }

        position.add(velocity);
       // position.clamp(Constants.VIEWPORT / 2, );
            velocity.x *= 0.8f;
            velocity.y *= 0.8f;

    }

    public void wasShot()
    {
        isExpired = true;
    }
}

