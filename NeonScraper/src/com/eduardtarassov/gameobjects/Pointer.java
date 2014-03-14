package com.eduardtarassov.gameobjects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.eduardtarassov.nshelpers.AssetLoader;
import com.eduardtarassov.nshelpers.Constants;

/**
 * Created by Eduard on 3/14/14.
 */
public class Pointer extends Entity {

    public boolean isPressed = false;

   // private static PlayerShip instance;
    public Pointer(int x, int y, boolean isPressed)
    {
        image = AssetLoader.pointer;
        position = new Vector2(x, y);
        radius = 10;
        this.isPressed = isPressed;
    }

    @Override
    public void update(){
        // ship logic goes here
    }

    public void draw(SpriteBatch spriteBatch){
        if (isPressed)
            spriteBatch.draw(image, position.x, position.y);
    }

    /*public static PlayerShip getInstance(){
        if (instance == null)
            instance = new PlayerShip();

        return instance;
    } */

}
