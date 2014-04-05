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
         velocity.nor();
        velocity.scl(10);
       // if (velocity.len2() > 0)
           // orientation = velocity.angle();

        orientation = PlayerShip.getInstance().orientation;

                position.add(velocity);
        // delete bullets which go off-screen

        if ((position.x > 408) || (position.x < 0) || (position.y > 272) || position.y < 0){
            isExpired = true;

        }
    }

}
