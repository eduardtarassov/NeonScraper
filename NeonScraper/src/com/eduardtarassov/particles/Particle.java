package com.eduardtarassov.particles;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.eduardtarassov.nshelpers.Constants;

/**
 * Created by Eduard on 10/04/14.
 */
public class Particle {
        public TextureRegion texture;
        public Vector2 position;
        public float orientation;

        public Vector2 scale;

        public Color color;
        public float duration;
        public float percentLife;
    public Vector2 velocity;
    public ParticleType type;
    public float lengthMultiplier;
public boolean enemy;

    public Particle(TextureRegion texture, Vector2 position, float orientation, Vector2 scale, Color color, float duration, float percentLife, Vector2 velocity, ParticleType type, float lengthMultiplier, boolean enemy){
        this.texture = texture;
        this.position = position;
        this.orientation = orientation;
        this.scale = scale;
        this.color = color;
        this.duration = duration;
        this.percentLife = percentLife;
        this.velocity = velocity;
        this.type = type;
        this.lengthMultiplier = lengthMultiplier;
        this.enemy = enemy;
    }

    public void updateParticle()
    {
        if(velocity != null){

            Vector2 vel = velocity;
            position = new Vector2(position.x + vel.x, position.y + vel.y);

            if ((position.x >= Constants.WIDTH) || (position.x <= 0))
                velocity.x *= -1;
            else if ((position.y >= Constants.HEIGHT) || (position.y <= 0))
                velocity.y *= -1;

            orientation = vel.angle();
            // denormalized floats cause significant performance issues
            if (Math.abs(vel.x) + Math.abs(vel.y) < 0.00000000001f)
                vel = Vector2.Zero;

            vel.scl(0.97f);       // particles gradually slow down
            velocity = vel;
            percentLife -= 1f / duration;
        }
    }
}
