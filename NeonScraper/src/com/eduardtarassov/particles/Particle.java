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

        public Vector2 scale = new Vector2(1, 1);

        public Color color;
        public float duration;
        public float percentLife = 1f;
        // This field will give us any additional information about our particle. It can be of any type, velocity, acceleration, rotation speed...
        public ParticleState state;
}
