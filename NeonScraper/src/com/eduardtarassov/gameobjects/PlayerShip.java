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
 *  This class is responsible for playerShip object properties (position, velocity, ...).
 *  Also for bullet creation and behaviour.
 */
public class PlayerShip extends Entity {

    public static PlayerShip instance;

    private int cooldownRemaining = 0;
    private int framesUntilRespawn = 0;

    public PlayerShip() {
        image = AssetLoader.player;
        position = new Vector2(Constants.WIDTH / 2 - 20, Constants.HEIGHT / 2 - 20);
        radius = 10;
    }

    @Override
    public void update() {
        System.out.println(System.nanoTime());
        if (isDead()){
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

    public void bulletsMove(){
        final int cooldownFrames = 12;
        Vector2 aim = new Vector2(-direction.x, -direction.y);
        // Waiting for the delay between previous and next bullet launched.
        if (cooldownRemaining <= 0) {
            //cool downFrames if final static value equal to 6.
            // So loop will go through 6 times before next bullet.
            cooldownRemaining = cooldownFrames;

            // Taking the current position of the ship and applying it to the start position of the bullet.
            Vector2 startPos = new Vector2(position);
            // Normalizing the aim (bullet direction) vector, so the sum of scalars of vector = 1;
            aim.nor();
            // Multiplying this vector by a scalar and setting the movement 20 times faster.
            aim.scl(20);

            // Adding new entity with the bullet start position and direction where it has to move.
             startPos.add(aim);// allows us to set the radius around the ship, where the initial position of the bullet will be.

            EntityManager.addEntity(new Bullet(startPos, aim));
            EntityManager.addEntity(new Bullet(new Vector2(startPos), new Vector2(aim.x, aim.y)));
            // After bullet has been launched, set isAiming to false.
            // TouchInputHandler.isAiming = false;
        }
        // Decreasing cool down Remaining in every loop until it will be equal 0 and bullet will be ready again.
        if (cooldownRemaining > 0)
            cooldownRemaining--;
    }

    @Override
    public void draw(SpriteBatch spriteBatch){
        if (!isDead())
            super.draw(spriteBatch);
    }

    public static PlayerShip getInstance() {
        if (instance == null)
            instance = new PlayerShip();

        return instance;
    }

    public void kill()
    {
        framesUntilRespawn = 60;
        EnemySpawner.reset();
    }

    /*
    * This getter method checks is player ship dead and waiting for respawn.
    */
    public boolean isDead(){
        return framesUntilRespawn > 0;
    }

}
