package com.eduardtarassov.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.eduardtarassov.neonscraper.NSGame;
import com.eduardtarassov.nshelpers.AssetLoader;
import com.eduardtarassov.nshelpers.Constants;

/**
 * Created by Eduard on 3/10/14.
 */
public class PlayerShip extends Entity {

    private static PlayerShip instance;
    public PlayerShip()
    {
        image = AssetLoader.player;
        position = new Vector2(Constants.WIDTH/2 - 20, Constants.HEIGHT/2 - 20);
        radius = 10;
    }

    @Override
    public void update(){
        // ship logic goes here
    }

    public static PlayerShip getInstance(){
        if (instance == null)
            instance = new PlayerShip();

        return instance;
    }

}
