package com.eduardtarassov.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.eduardtarassov.gameworld.GameRenderer;
import com.eduardtarassov.gameworld.GameWorld;
import com.eduardtarassov.nshelpers.AssetLoader;
import com.eduardtarassov.nshelpers.Constants;
import com.eduardtarassov.nshelpers.MathUtil;
import com.eduardtarassov.nshelpers.TouchInputHandler;
import com.eduardtarassov.particles.ParticleType;

import java.util.Random;

/**
 * Created by Eduard on 3/10/14.
 * This class is responsible for playerShip object properties (position, velocity, ...).
 * Also for bullet creation and behaviour.
 */
public class PlayerShip extends Entity {

    public static PlayerShip instance;
     Random rand = new Random();
    private int cooldownRemaining = 0;
    private int framesUntilRespawn = 0;
    // public static Vector2 position2;// don't forget to remove

    public PlayerShip() {
        image = AssetLoader.player;
        position = new Vector2(Constants.WIDTH / 2 - 20, Constants.HEIGHT / 2 - 20); //Maybe it should not be -20 !!!!
        radius = 10;
    }

    @Override
    public void update(float delta) {
        if (isDead()) {
            framesUntilRespawn--;
            return;
        }

        // Method that is responsible for player movement.
        motionMove();
       // exhaustFire();
    }

    public void motionMove() {
        velocity = new Vector2(-Gdx.input.getAccelerometerY() / 4, Gdx.input.getAccelerometerX() / 4 - 1);

        bulletsMove();

        orientation = velocity.angle();
        velocity.scl(3);

        position.sub(velocity.x, velocity.y);
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
        Vector2 aim = new Vector2(-velocity.x, -velocity.y);
        // Waiting for the delay between previous and next bullet launched.
        if (cooldownRemaining <= 0) {
            //cool downFrames if final static value equal to 6.
            // So loop will go through 6 times before next bullet.
            cooldownRemaining = cooldownFrames;

            // Taking the current position of the ship and applying it to the start position of the bullet.
            Vector2 startPos = new Vector2(position.x + 20, position.y + 20);
            Vector2 startPos2 = new Vector2(startPos);

            // Normalizing the aim (bullet velocity) vector, so the sum of scalars of vector = 1;
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
            // Adding new entity with the bullet start position and velocity where it has to move.
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

    /*private void exhaustFire(){
        if (velocity.len2() > 0.1f){
            // Set up some variables
            orientation = velocity.angle();
            Quaternion rot = new Quaternion().setEulerAngles(0f, 0f, orientation);
            long t =  GameWorld.getGameDuration();
            // The primary velocity of the particles is 3 pixels/frame in the direction opposite to which the ship is travelling.
            Vector2 baseVel = velocity.scl(-3);
            // Calculate the sideways velocity for the two side streams. The direction is perpendicular to the ship's velocity and the magnitude varies sinusoidally.
            Vector2 perpVel = new Vector2(baseVel.y, -baseVel.x).scl((0.6f * (float) Math.sin((t * 10))));
            Color sideColor = new Color(200f, 38f, 9f, 1f);    // deep red
            Color midColor = new Color(255f, 187f, 30f, 1f);   // orange-yellow

            // Position of the ship's exhaust pipe.
            Vector3 prePos = new Vector3(-25, 0, 0).mul(rot);
            Vector2 pos = new Vector2(prePos.x, prePos.y).add(position);

            final float alpha = 0.7f;

            // Middle particle stream contains Vector(random between 0 and 1) + baseVel
            Vector2 velMid = new Vector2(0, rand.nextFloat()).add(baseVel);

            GameWorld.particleManager.createParticle(AssetLoader.lineParticle, pos, new Color(216f, 229f, 0f, 1f), orientation, new Vector2(0.5f, 1), 60f, 1f, velMid, ParticleType.Enemy, 1f);
            GameWorld.particleManager.createParticle(AssetLoader.glow, pos, new Color(216f, 229f, 0f, 1f), orientation, new Vector2(0.5f, 1), 60f, 1f, velMid, ParticleType.Enemy, 1f);

            // side particle streams
            Vector2 vel1 =  new Vector2(0, rand.nextFloat() * 0.3f).add(baseVel.add(perpVel));
            Vector2 vel2 = new Vector2(0, rand.nextFloat() * 0.3f).add(baseVel.sub(perpVel));
            GameWorld.particleManager.createParticle(AssetLoader.lineParticle, pos, new Color(254f, 167f, 0f, 1f), orientation, new Vector2(0.5f, 1), 60f, 1f, vel1, ParticleType.Enemy, 1f);
            GameWorld.particleManager.createParticle(AssetLoader.lineParticle, pos, new Color(254f, 167f, 0f, 1f), orientation, new Vector2(0.5f, 1), 60f, 1f, vel2, ParticleType.Enemy, 1f);

            GameWorld.particleManager.createParticle(AssetLoader.glow, pos, new Color(255f, 204f, 49f, 1f), orientation, new Vector2(0.5f, 1), 60f, 1f, vel1, ParticleType.Enemy, 1f);
            GameWorld.particleManager.createParticle(AssetLoader.glow, pos, new Color(255f, 204f, 49f, 1f), orientation, new Vector2(0.5f, 1), 60f, 1f, vel2, ParticleType.Enemy, 1f);
        }
    }       */

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
