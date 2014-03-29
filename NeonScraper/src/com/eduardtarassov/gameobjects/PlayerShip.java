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
 *  This class is responsible for playerShip object properties (position, velocity, ...).
 *  Also for bullet creation and behaviour.
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
        // When player touches the screen, TouchInputHandler class sets the variable isAiming = true.
        if (TouchInputHandler.isAiming) {
            //Creating an Vector2 object which takes coordinates from user input on the screen.
            Vector2 aim = new Vector2(TouchInputHandler.getAimDirection().x, TouchInputHandler.getAimDirection().y);

            // Checking if aim is real value.
            // Waiting for the delay between previous and next bullet launched.
            if (aim.len2() > 0 && cooldownRemaining <= 0) {
                //cooldownFrames if final static value equal to 6.
                // So loop will go through 6 times before next bullet.
                cooldownRemaining = cooldownFrames;
                // Taking the current position of the ship and applying it to the start position of the bullet.
                Vector2 startPos = new Vector2(position.x + 7, position.y + 7);
                // Subtracting current bullet position from the aim position and getting direction vector.
                aim.sub(startPos);
                // Normalizing the aim (bullet direction) vector, so the sum of scalars of vector = 1;
                aim.nor();
                // Multiplying this vector by a scalar and setting the movement 20 times faster.
                aim.scl(20);

                // Adding new entity with the bullet start position and direction where it has to move.
                // startPos.add(aim) allows us to set the radius around the ship, where the initial position of the bullet will be.
                EntityManager.addEntity(new Bullet(startPos.add(aim), aim));

                // After bullet has been launched, set isAiming to false.
                TouchInputHandler.isAiming = false;
            }
        }
        // Decreasing cooldown Remaining in every loop until it will be equal 0 and bullet'll be ready again.
        if (cooldownRemaining > 0)
            cooldownRemaining--;

        // Method that is responsible for player movement.
        motionMove();

    }

    public void motionMove() {
        direction = new Vector2(-Gdx.input.getAccelerometerY() / 4, Gdx.input.getAccelerometerX() / 4 - 1);
        orientation = direction.angle();

        position.sub(direction.x, direction.y);

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
