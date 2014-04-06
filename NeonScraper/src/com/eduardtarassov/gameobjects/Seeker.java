package com.eduardtarassov.gameobjects;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.Color;
import com.eduardtarassov.nshelpers.AssetLoader;
import com.eduardtarassov.nshelpers.Constants;
import com.eduardtarassov.nshelpers.MathUtil;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

/**
 * Created by Eduard on 28/03/14.
 * This class is responsible for every enemy in the game creation.
 */
public class Seeker extends Entity {


    public Seeker(TextureRegion image, Vector2 position) {
        this.image = image;
        this.position = position;
        radius = image.getRegionWidth() / 2.0f;
       // color = Color.CLEAR;
         this.velocity = new Vector2(0, 0);
    }




    @Override
    public void update() {
        if (timeUntilStart <= 0) {
            followPlayer();
        } else {
            timeUntilStart--;
            //color = Color.WHITE * (1 - timeUntilStart / 60f);
        }
        velocity.scl(3f);
        position.add(velocity);
        //velocity.scl(0.8f);
    }



    private void followPlayer() {
        velocity.add(new Vector2(PlayerShip.instance.position).sub(position)).nor();
        //if (velocity != Vector2.Zero)
            orientation = velocity.angle();
    }
}