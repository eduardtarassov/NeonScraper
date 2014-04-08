package com.eduardtarassov.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.math.Vector2;
import com.eduardtarassov.gameworld.GameRenderer;
import com.eduardtarassov.nshelpers.AssetLoader;
import com.eduardtarassov.nshelpers.Constants;
import com.eduardtarassov.nshelpers.MathUtil;
import com.eduardtarassov.nshelpers.TouchInputHandler;

import java.util.Random;

/**
 * Created by Eduard on 3/10/14.
 * This class is responsible for playerShip object properties (position, velocity, ...).
 * Also for bullet creation and behaviour.
 */
public class PlayerShip extends Entity {

    public static PlayerShip instance;

    private int cooldownRemaining = 0;
    private int framesUntilRespawn = 0;
    // public static Vector2 position2;// don't forget to remove

    public PlayerShip() {
        image = AssetLoader.player;
        position = new Vector2(Constants.WIDTH / 2 - 20, Constants.HEIGHT / 2 - 20); //Maybe it should not be -20 !!!!
        radius = 10;
    }

    @Override
    public void update() {
        if (isDead()) {
            framesUntilRespawn--;
            return;
        }

        // Method that is responsible for player movement.
        motionMove();

    }

    public void motionMove() {
        direction = new Vector2(-Gdx.input.getAccelerometerY() / 4, Gdx.input.getAccelerometerX() / 4 - 1);

        bulletsMove();

        orientation = direction.angle();
        direction.scl(3);

        position.sub(direction.x, direction.y);
        // position2 = new Vector2(position); // Don't forget to remove.


        // Now we make it impossible for ship to move out of the corners.
        if (position.x > (Constants.WIDTH - 20))
            position.x = Constants.WIDTH - 20;
        if (position.x < 0)
            position.x = 0;
        if (position.y > (Constants.HEIGHT - 20))
            position.y = Constants.HEIGHT - 20;
        if (position.y < 0)
            position.y = 0;
    }

    public void keysMove(int moveToX, int moveToY) {
        position.x += moveToX;
        position.y += moveToY;
    }

    public void bulletsMove(){
        final int cooldownFrames = 24;
        Vector2 aim = new Vector2(-direction.x, -direction.y);
        // Waiting for the delay between previous and next bullet launched.
        if (cooldownRemaining <= 0) {
            //cool downFrames if final static value equal to 6.
            // So loop will go through 6 times before next bullet.
            cooldownRemaining = cooldownFrames;

            // Taking the current position of the ship and applying it to the start position of the bullet.
            Vector2 startPos = new Vector2(position.x + 20, position.y + 20);
            Vector2 startPos2 = new Vector2(startPos);

            // Normalizing the aim (bullet direction) vector, so the sum of scalars of vector = 1;
            aim.nor();
            Vector2 aim2 = new Vector2(aim);

            // Taking the angle where to shoot
            float aimAngle = aim.angle();


            // Multiplying this vector by a scalar and setting the movement 20 times faster.
            aim.setAngle(aimAngle - 25);
            aim2.setAngle(aimAngle + 25);
            aim.scl(30);
            aim2.scl(30);
            AssetLoader.playRandShootSound();

           /* */
            // Adding new entity with the bullet start position and direction where it has to move.
            startPos.add(aim);// allows us to set the radius around the ship, where the initial position of the bullet will be.
            startPos2.add(aim2);

            aim.setAngle(aimAngle);
            aim2.setAngle(aimAngle);

            float randomSpread =  (float) ((-0.05f + (Math.random() * ((0.05f + 0.05f) + 1))) + (-0.05f + (Math.random() * ((0.05f + 0.05f) + 1))));
            aim.rotate(randomSpread);
            randomSpread =  (float) ((-0.05f + (Math.random() * ((0.05f + 0.05f) + 1))) + (-0.05f + (Math.random() * ((0.05f + 0.05f) + 1))));
            aim2.rotate(randomSpread);
            //startPos.rotate((float) -Math.PI/2);
            //startPos2.rotate((float) Math.PI/4);
            EntityManager.addEntity(new Bullet(startPos, aim));
            EntityManager.addEntity(new Bullet(startPos2, aim2));
            // After bullet has been launched, set isAiming to false.
            // TouchInputHandler.isAiming = false;
        }
        // Decreasing cool down Remaining in every loop until it will be equal 0 and bullet will be ready again.
        if (cooldownRemaining > 0)
            cooldownRemaining--;
    }

    @Override
    public void draw(SpriteBatch spriteBatch) {
        if (!isDead())
            super.draw(spriteBatch);
    }

    public static PlayerShip getInstance() {
        if (instance == null)
            instance = new PlayerShip();

        return instance;
    }

    public void kill() {
        framesUntilRespawn = 60;
        EnemySpawner.reset();
    }

    /*
    * This getter method checks is player ship dead and waiting for respawn.
    */
    public boolean isDead() {
        return framesUntilRespawn > 0;
    }

   /* public static Vector2 getPosition2(){
        return position2;
    }   */

}
