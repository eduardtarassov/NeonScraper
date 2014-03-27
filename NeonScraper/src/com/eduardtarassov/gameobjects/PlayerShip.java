package com.eduardtarassov.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.math.Vector2;
import com.eduardtarassov.nshelpers.AssetLoader;
import com.eduardtarassov.nshelpers.Constants;
import com.eduardtarassov.nshelpers.MathUtil;
import com.eduardtarassov.nshelpers.TouchInputHandler;

import java.util.Random;

/**
 * Created by Eduard on 3/10/14.
 */
public class PlayerShip extends Entity {

    private static PlayerShip instance;
    private final int cooldownFrames = 12;
    private int cooldownRemaining = 0;

    public boolean bulletIsActive = false;

    //Creating an object of structure responsible for rotation in three dimensions.
    private static Quaternion aimQuat = new Quaternion();
    private static Random rand = new Random();

    public PlayerShip() {
        image = AssetLoader.player;
        position = new Vector2(Constants.WIDTH / 2 - 20, Constants.HEIGHT / 2 - 20);
        radius = 10;
    }

    @Override
    public void update() {
        if (TouchInputHandler.isAiming) {
            Vector2 aim = new Vector2(TouchInputHandler.getAimDirection().x, TouchInputHandler.getAimDirection().y);

            if (aim.len2() > 0 && cooldownRemaining <= 0) {

                cooldownRemaining = cooldownFrames;

                Vector2 startPos = new Vector2(position.x + 7, position.y + 7);
                aim.sub(startPos);
                aim.nor();
                aim.scl(20);
                 // if (TouchInputHandler.isAiming){
                EntityManager.addEntity(new Bullet(startPos.add(aim), aim));


                      TouchInputHandler.isAiming = false;
                  }
        }

        if (cooldownRemaining > 0)
            cooldownRemaining--;

        motionMove();

    }

    public void motionMove() {

        // System.out.println(deviceAccelerometerX  + "     " + deviceAccelerometerY);
        //accelerometer = new Vector2(deviceAccelerometerX, deviceAccelerometerY);
        direction = new Vector2(-Gdx.input.getAccelerometerY() / 4, Gdx.input.getAccelerometerX() / 4 - 1);
        orientation = direction.angle();

        //System.out.println(orientation);
        position.sub(direction.x, direction.y);

        //System.out.println(deviceAccelerometerX + "     " + deviceAccelerometerY);

        // Now we make it impossible for ship to move out of the corners.
        if (position.x > (408 - 20))
            position.x = 408 - 20;
        if (position.x < 0)
            position.x = 0;
        if (position.y > (272 - 20))
            position.y = 272 - 20;
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
