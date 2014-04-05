package com.eduardtarassov.gameobjects;

/**
 * Created by Eduard on 04/04/14.
 */
public class Wanderer extends Entity{
    public void wasShot() {
        isExpired = true;
    }

   /* protected void moveInASquare() {
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
    }

    private void moveRandomly(){
        // We multiply this value by Math.PI * 2 and we get range 0.0 - 6.3.
        float direction = (float) (Math.random() * ((Math.PI * 2 - 0) + 1)) + 0;

        while (isActive())
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
               /* if ((position.x > (408 - 20)) || (position.x < 0) || (position.y > (272 - 20)) || (position.y < 0))
                    direction = (float) (Constants.SCREENSIZE.scl(0.5f).sub(position).angle() + (Math.random() * ((Math.PI*2 - -Math.PI*2) + 1)) - Math.PI*2);

                Thread.yield();
            }
        }
    }   */

}
