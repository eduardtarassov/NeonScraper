package com.eduardtarassov.gameobjects;

import com.badlogic.gdx.math.Vector2;
import com.eduardtarassov.gameworld.GameRenderer;
import com.eduardtarassov.nshelpers.AssetLoader;

/**
 * Created by Eduard on 18/03/14.
 */
public class Bullet extends Entity {
    public Bullet(Vector2 position){
        image = AssetLoader.bullet;
        this.position = position;
        //this.velocity = velocity;
       // this.orientation = velocity.angle();
    }

    @Override
    public void update()
    {
       /* if ((velocity.x > 0) && (velocity.y > 0))
            orientation = velocity.angle();  */

        //position.sub(velocity.x, velocity.y);

        // delete bullets that go off-screen
        if ((position.x > 408 - 28) || (position.x < 0) || (position.y > 272 - 9) || position.y < 0){
            isExpired = true;

        }
    }

}
