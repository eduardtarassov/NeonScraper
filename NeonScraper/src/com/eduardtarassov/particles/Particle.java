package com.eduardtarassov.particles;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

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
public int no;

    public Particle(TextureRegion texture, Vector2 position, float orientation, Vector2 scale, Color color, float duration, float percentLife, Vector2 velocity, ParticleType type, float lengthMultiplier, int no){
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
        this.no = no;
    }

    public void updateParticle()
    {
        if(velocity != null){

            Vector2 vel = velocity;

            position = new Vector2(position.x + vel.x, position.y + vel.y);
            orientation = vel.angle();
            System.out.println("This is particle no " + no + " position: " + position.x + "       " + position.y);
            System.out.println("This is particle no " + no + " velocity: " + velocity.x + "       " + velocity.y);
            // denormalized floats cause significant performance issues
            if (Math.abs(vel.x) + Math.abs(vel.y) < 0.00000000001f)
                vel = Vector2.Zero;

            vel.scl(0.97f);       // particles gradually slow down
            velocity = vel;
        }
    }
}
