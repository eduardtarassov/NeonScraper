package com.eduardtarassov.gameobjects;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.eduardtarassov.nshelpers.Constants;
import com.eduardtarassov.nshelpers.MathUtil;

/**
 * Created by Eduard on 04/04/14.
 */
public class Wanderer extends Entity {
    // We multiply this value by Math.PI * 2 and we get range 0.0 - 6.3.
    private float direction = (float) (Math.random() * ((Math.PI * 2 - 0) + 1)) + 0;

    public Wanderer(TextureRegion image, Vector2 position) {
        this.image = image;
        this.position = position;
        radius = image.getRegionWidth() / 2.0f;
        // color = Color.CLEAR;
        this.velocity = new Vector2(0, 0);
    }


    @Override
    public void update() {
        if (timeUntilStart <= 0) {
            moveRandomly();
        } else {
            timeUntilStart--;
            //color = Color.WHITE * (1 - timeUntilStart / 60f);
        }

        position.add(velocity);
        velocity.scl(0.8f);
    }

   /* private void moveInASquare() {
        final int framesPerSide = 30;
        while (isActive()) {
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
    } */

    private void moveRandomly() {

        // Increases direction by the random value from -0.1f to 0.1f.
        direction += (float) (Math.random() * ((10f - -10f) + 1)) - 10f;

        direction = MathUtil.wrapAngle(direction);


                /*
                * If position of enemy is going out of bounds of the screen, then...
                * direction will be equal to the direction given by central point of the screen to the current position of the enemy.
                * This gives us the certain direction where the enemy came off the screen. And we simple add a random angle from -360 degrees to 360 degrees.
                */
            if ((position.x > (408 - 20)) || (position.x < 0) || (position.y > (272 - 20)) || (position.y < 0))
                direction = (float) (Constants.SCREENSIZE.scl(0.5f).sub(position).angle() + (Math.random() * ((Math.PI * 2 - -Math.PI * 2) + 1)) - Math.PI * 2);

            velocity.add(MathUtil.FromPolar(direction, 1f));
            orientation -= 5f;

    }
}


