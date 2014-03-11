package com.eduardtarassov.gameobjects;

import com.badlogic.gdx.Gdx;
import com.eduardtarassov.neonscraper.NSGame;
import com.eduardtarassov.nshelpers.AssetLoader;

/**
 * Created by Eduard on 3/10/14.
 */
public class PlayerShip extends Entity {

    private static PlayerShip instance;

    private PlayerShip()
    {
        image = AssetLoader.player;
        float screenWidth = Gdx.graphics.getWidth();
        float screenHeight = Gdx.graphics.getHeight();
        position.add(screenWidth/2, screenHeight/2);
        radius = 10;
    }

    @Override
    public void update(){
        // ship logic goes here
    }

    public static PlayerShip getInstance() {
        if (instance == null)
            instance = new PlayerShip();

        return instance;
    }

}
