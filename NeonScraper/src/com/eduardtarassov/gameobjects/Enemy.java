package com.eduardtarassov.gameobjects;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.Color;
import com.eduardtarassov.nshelpers.AssetLoader;
import com.eduardtarassov.nshelpers.Constants;
import com.eduardtarassov.nshelpers.MathUtil;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

/**
 * Created by Eduard on 28/03/14.
 * This class is responsible for every enemy in the game creation.
 */
public class Enemy extends Entity {
    private int timeUntilStart = 60;

    // We  will be adding Iterable<Enemy> methods with enemy behaviour into our Iterable<Enemy> type ArrayList
    private ArrayList<Iterator<Enemy>> behaviours = new ArrayList();
    Random rand = new Random();

    public boolean isActive() {
        return timeUntilStart <= 0;
    }

    public Enemy(TextureRegion image, Vector2 position) {
        this.image = image;
        this.position = position;
        radius = image.getRegionWidth() / 2.0f;
        color = Color.CLEAR; //!!!!! I am not sure this is gonna be transparent one.

    }

    @Override
    public void update() {
        if (timeUntilStart <= 0) {
            // Enemy behaviour logic goes here.
            if (timeUntilStart <= 0) {
                applyBehaviours();
            }
        } else {
            timeUntilStart--;
            //color = Color.WHITE * (1 - timeUntilStart / 60f);
        }

        position.add(velocity);
        // position.clamp(Constants.VIEWPORT / 2, );   //WTF WAS CODE HERE!!!!
        velocity.scl(0.8f);

    }

    protected void wasShot() {
        isExpired = true;
    }

    /*
    * This static method will allow to create seeking enemies.
    */
    public static Enemy createSeeker(Vector2 position){
        Enemy enemy = new Enemy(AssetLoader.seeker, position);
        enemy.addBehaviour(enemy.followPlayer());
        return enemy;
    }

    /*
    * This static method will allow to create wandering enemies.
    */
    public static Enemy createWanderer(Vector2 position){
        Enemy enemy = new Enemy(AssetLoader.wanderer, position);
        enemy.addBehaviour(enemy.moveRandomly());
        return enemy;
    }

    /*
    * This method will add the different behaviour methods into our Arraylist behaviours
    */
    private void addBehaviour(Iterable<Enemy> behaviour) {
        behaviours.add(behaviour.iterator());
    }

    private void applyBehaviours() {
        for (int i = 0; i < behaviours.size(); i++) {
            if (behaviours.get(i).next() == null) {
                behaviours.remove(i--);
            }
        }
    }

    private Iterable<Enemy> followPlayer() {
        while (true) {
            velocity.add(PlayerShip.instance.position.sub(position).scl(acceleration));
            if (velocity != Vector2.Zero)
                orientation = velocity.angle();

            Thread.yield();

        }
    }

    protected Iterable<Enemy> moveInASquare() {
        final int framesPerSide = 30;
        while (true) {
            // Move right for 30 frames
            for (int i = 0; i < framesPerSide; i++) {
                velocity = new Vector2(1, 0);
                Thread.yield();
            }

            //Move down for 30 frames
            for (int i = 0; i < framesPerSide; i++) {
                velocity = new Vector2(0, -1);
                Thread.yield();
            }

            //Move left for 30 frames
            for (int i = 0; i < framesPerSide; i++) {
                velocity = new Vector2(-1, 0);
                Thread.yield();
            }

            //Move up for 30 frames
            for (int i = 0; i < framesPerSide; i++) {
                velocity = new Vector2(0, 1);
                Thread.yield();
            }
        }
    }

    private Iterable<Enemy> moveRandomly(){
        // We multiply this value by Math.PI * 2 and we get range 0.0 - 6.3.
          float direction = (float) (Math.random() * ((Math.PI * 2 - 0) + 1)) + 0;

        while (true)
        {
            // Increases direction by the random value from -0.1f to 0.1f.
            direction += (float) (Math.random() * ((0.1f - -0.1f) + 1)) - 0.1f;

            direction = MathUtil.wrapAngle(direction);

            for (int i = 0; i < 6; i++)
            {
                velocity.add(MathUtil.FromPolar(direction, 0.4f));
                orientation -= 0.05f;

                /*
                * If position of enemy is going out of bounds of the screen, then...
                * direction will be equal to the direction given by central point of the screen to the current position of the enemy.
                * This gives us the certain direction where the enemy came off the screen. And we simple add a random angle from -360 degrees to 360 degrees.
                */
                if ((position.x > (408 - 20)) || (position.x < 0) || (position.y > (272 - 20)) || (position.y < 0))
                    direction = (float) (Constants.SCREENSIZE.scl(0.5f).sub(position).angle() + (Math.random() * ((Math.PI*2 - -Math.PI*2) + 1)) - Math.PI*2);

                Thread.yield();
                          }
        }
    }

    /*
  * This method will push the current enemy away from the other enemy.
  * The closer they are, the harder it will be pushed, because the magnitude of (d / d.LengthSquared()) is just one over the distance.
  */
    public void handleCollision(Entity other){
        Vector2 d = position.sub(other.position);

        velocity.add(d.scl(10).scl(1 / (d.len2() + 1)));
    }



}

