package com.eduardtarassov.gameobjects;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.eduardtarassov.gameworld.GameRenderer;
import com.eduardtarassov.nshelpers.AssetLoader;

/**
 * Created by Eduard on 18/03/14.
 */
public class Bullet extends Entity {
    public Bullet(Vector2 position, Vector2 velocity){
        image = AssetLoader.bullet;
        this.position = position;
        this.velocity = velocity;
        this.orientation = velocity.angle();
        radius = 8;
    }

    @Override
    public void update()
    {

        if (velocity.len2() > 0)
            orientation = velocity.angle();
          //System.out.println("This is velocity: " + velocity.x + "      " + velocity.y);
       // System.out.println("This is position: " + position.x + "     " + position.y);
                position.x += velocity.x;
        position.y += velocity.y;
        // position.sub(velocity.x, velocity.y);
       // System.out.println("This is new position: " + position.x + "     " + position.y);
        //System.out.println("THis is bullet position: " + position.x + "      " + position.y);
        // delete bullets that go off-screen
        if ((position.x > 408 - 28) || (position.x < 0) || (position.y > 272 - 9) || position.y < 0){
            isExpired = true;

        }
    }

}
