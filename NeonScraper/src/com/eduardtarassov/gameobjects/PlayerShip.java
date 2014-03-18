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

    public PlayerShip() {
        image = AssetLoader.player;
        position = new Vector2(Constants.WIDTH / 2 - 20, Constants.HEIGHT / 2 - 20);
        radius = 10;
    }

    @Override
    public void update() {
        // ship logic goes here
        motionMove();

    }

    public void motionMove() {

        // System.out.println(deviceAccelerometerX  + "     " + deviceAccelerometerY);
        //accelerometer = new Vector2(deviceAccelerometerX, deviceAccelerometerY);
        direction = new Vector2(-Gdx.input.getAccelerometerY() / 4, Gdx.input.getAccelerometerX() / 4 - 1);
        rotation = direction.angle();
        System.out.println(rotation);
        position.sub(direction.x, direction.y);

        //System.out.println(deviceAccelerometerX + "     " + deviceAccelerometerY);

        // Now we make it impossible for ship to move out of the corners.
        if (position.x > (408 - 40))
            position.x = 408 - 40;
        if (position.x < 0)
            position.x = 0;
        if (position.y > (272 - 40))
            position.y = 272 - 40;
        if (position.y < 0)
            position.y = 0;
    }

    public void keysMove(int moveToX, int moveToY) {
        position.x += moveToX;
        position.y += moveToY;
    }

    public static PlayerShip getInstance() {
        if (instance == null)
            instance = new PlayerShip();

        return instance;
    }

}
