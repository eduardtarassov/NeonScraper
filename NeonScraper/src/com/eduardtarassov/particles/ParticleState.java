package com.eduardtarassov.particles;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by Eduard on 10/04/14.
 */
public class ParticleState {
    public Vector2 velocity;
    public ParticleType type;
    public float lengthMultiplier;

    public ParticleState(Vector2 velocity, ParticleType type, float lengthMultiplier){
        this.velocity = velocity;
        this.type = type;
        this.lengthMultiplier = lengthMultiplier;
    }

    public static void updateParticle(Particle particle)
    {
        if(particle.state.velocity != null){
        Vector2 vel = particle.state.velocity;

        particle.position.add(vel);
        particle.orientation = vel.angle();

        // denormalized floats cause significant performance issues
        if (Math.abs(vel.x) + Math.abs(vel.y) < 0.00000000001f)
            vel = Vector2.Zero;

        vel.scl(0.97f);       // particles gradually slow down
        particle.state.velocity = vel;
        }
    }

}
