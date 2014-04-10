package com.eduardtarassov.gameobjects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.eduardtarassov.gameworld.GameWorld;
import com.eduardtarassov.nshelpers.AssetLoader;

import com.eduardtarassov.particles.ParticleType;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import javax.swing.text.Position;
import java.util.Random;

/**
 * Created by Eduard on 3/10/14.
 * This is the base class for out entities.
 */
public class Entity {
    public TextureRegion image;
    // The tint of the image. This will also allow us to change the transparency.
    //  protected Color color = Color.WHITE;
    protected Vector2 position; // NOT FORGET TO MAKE IT PROTECTED
    protected Vector2 velocity, direction;
    protected float orientation;
    protected int timeUntilStart = 60;
    protected int killingPoints;
    protected int hp;
    private Random rand = new Random();

    // We will use radius for circular collision detection.
    protected float radius = 20;
    // True if the entity was destroyed and should be deleted.
    protected boolean isExpired;

   /* public Vector2 size() {
        return image == null ? Vector2.Zero : new Vector2(image.getRegionWidth(), image.getRegionHeight());
    }    */

    public void update(float delta) {
        position.add(velocity);
        EntityManager.handleGravity(delta);
    }

    public void draw(SpriteBatch spriteBatch) {
        //spriteBatch.draw(image, position.x, position.y, image.getRegionWidth(), image.getRegionHeight());     //NOT AS IN EXAMPLE

        spriteBatch.draw(image, position.x, position.y, image.getRegionWidth() / 2.0f, image.getRegionHeight() / 2.0f, image.getRegionWidth(), image.getRegionHeight(), 1f, 1f, orientation + 90, false);
    }

    public Vector2 getPosition() {
        return position;
    }

    /*
* This method will push the current enemy away from the other enemy.
* The closer they are, the harder it will be pushed, because the magnitude of (d / d.LengthSquared()) is just one over the distance.
*/
    public void handleCollision(Entity other) {
        Vector2 d = new Vector2(position).sub(other.position);
        //Vector2 d = new Vector2(position).sub(other.position).nor();

        position.add(d.scl(10).scl(1 / (d.len2() + 1)));
    }

    public void wasShot(int enemyNo) {
        if (hp > 0)
            hp--;
        else{
            AssetLoader.playRandExplosionSound();
            PlayerStatus.addPoints(EntityManager.enemies.get(enemyNo).killingPoints);
            isExpired = true;
            // Particles Explosion
            for (int i = 0; i < 3; i++)
            {
                float speed = 2f * (rand.nextFloat()*(10f + 10f) - 10f);
            float speed2 = 2f * (rand.nextFloat()*(10f + 10f) - 10f);
           //float speed2 = 0.7f * (1f - 1 / rand.nextFloat()*(10f - 1f) + 1f);
                System.out.println("Particle No" + i + " velocity is: " + speed + "    " + speed2);
                Vector2 partVelocity = new Vector2(speed, speed2);
            //System.out.println("This is your entity position: " + position.x + "       " + position.y);

                GameWorld.particleManager.createParticle(AssetLoader.lineParticle, position, partVelocity.angle(), new Vector2(1.5f, 1.5f), Color.GREEN, 190, 1f, partVelocity, ParticleType.Enemy, 1f, i);
            }
        }
    }

    public void destroyEnemy() {
        isExpired = true;
    }

    public boolean isActive() {
        return timeUntilStart <= 0;
    }

    public void applyGravity(Vector2 gravity){
        position.add(gravity);
    }
}

