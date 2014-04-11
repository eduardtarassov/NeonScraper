package com.eduardtarassov.gameobjects;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.eduardtarassov.gameworld.GameRenderer;
import com.eduardtarassov.gameworld.GameWorld;
import com.eduardtarassov.nshelpers.AssetLoader;
import com.eduardtarassov.nshelpers.Constants;
import com.eduardtarassov.particles.ParticleType;

import java.util.Random;

/**
 * Created by Eduard on 18/03/14.
 */
public class Bullet extends Entity {

    private Random rand = new Random();

    public Bullet(Vector2 position, Vector2 velocity){
        image = AssetLoader.bullet;
        this.position = position;
        this.velocity = velocity;
        this.orientation = velocity.angle();
        radius = 8;
    }

    @Override
    public void update(float delta)
    {
         velocity.nor();
        velocity.scl(10);
       // if (velocity.len2() > 0)
           // orientation = velocity.angle();


                position.add(velocity);
        // delete bullets which go off-screen

        if ((position.x >= Constants.WIDTH) || (position.x <= 0) || (position.y >= Constants.HEIGHT) || position.y <= 0){
            isExpired = true;

            GameWorld.particleManager.explosionHandler(position, 20, ParticleType.Bullet);

        }
    }
}
