package com.eduardtarassov.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.eduardtarassov.neonscraper.NSGame;
import com.eduardtarassov.nshelpers.AssetLoader;
import com.eduardtarassov.nshelpers.Constants;
import com.eduardtarassov.nshelpers.TouchInputHandler;

import java.util.Random;

/**
 * Created by Eduard on 3/10/14.
 */
public class PlayerShip extends Entity {

    private static PlayerShip instance;
     public boolean bulletIsActive = false;
    private final int cooldownFrames = 6;
    private int cooldownRemaining = 0;
    private static Random rand = new Random();

    public PlayerShip() {
        image = AssetLoader.player;
        position = new Vector2(Constants.WIDTH / 2 - 20, Constants.HEIGHT / 2 - 20);
        radius = 10;
    }

    @Override
    public void update() {
        // ship logic goes here
                 //System.out.println(TouchInputHandler.getAimDirection());
        if (bulletIsActive){
        Vector2 aim = TouchInputHandler.getAimDirection();
        //float aimAngle = aim.angle();

        //one line missing      Quaternion aimQuat = Quaternion.CreateFromYawPitchRoll(0, 0, aimAngle);
         /*float randomSpread = (rand.nextFloat() * (0.04f - (- 0.04f)) + (-0.04f)) + (rand.nextFloat() * (0.04f - (- 0.04f)) + (-0.04f)); //setting the spread of bullets
        aim.x += randomSpread;
        aim.y += randomSpread; */
        EntityManager.addEntity(new Bullet(position, aim));
        }
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
