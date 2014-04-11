package com.eduardtarassov.particles;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.Color;
import com.eduardtarassov.gameworld.GameWorld;
import com.eduardtarassov.nshelpers.AssetLoader;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Eduard on 10/04/14.
 */
public class ParticleManager {
    public ArrayList<Particle> list;
    private Color[] particlesColor;
    private Random rand = new Random();

    public ParticleManager() {
        list = new ArrayList<Particle>();

        particlesColor = new Color[6];
        particlesColor[0] = Color.RED;
        particlesColor[1] = new Color(6f, 0.5f, 1f, 1f);
        particlesColor[2] = Color.LIGHT_GRAY;

        particlesColor[3] = Color.WHITE;
        particlesColor[4] = new Color(0f, 226f, 218f, 1f);
        //particlesColor[5] = new Color(0f, 38f, 180f, 1f);

    }

    public void createParticle(TextureRegion texture, Vector2 position, float orientation, Vector2 scale, float duration, float percentLife, Vector2 velocity, ParticleType type, float lengthMultiplier) {

        // Deciding which color will particles be, for enemy they are red, light gray, violet and white. For bullets and player: white, green, bl
        Color color;
        if (type == ParticleType.Enemy)
            color = particlesColor[rand.nextInt(4)];
        else
            color = particlesColor[rand.nextInt((4 - 3) + 1) + 3];

        Particle particle;
        particle = new Particle(texture, position, orientation, scale, color, duration, percentLife, velocity, type, lengthMultiplier);
        list.add(particle);
    }

    public void update() {
        for (int i = 0; i < list.size(); i++) {
            Particle particle = list.get(i);
            particle.updateParticle();
            if (particle.percentLife <= 0) {
                list.remove(i);
                i--;
            }
        }
    }

    public void draw(SpriteBatch spriteBatch) {
        for (int i = 0; i < list.size(); i++) {
            Color defaultColor;
            defaultColor = spriteBatch.getColor();
            Particle particle = list.get(i);
            spriteBatch.setColor(particle.color);
            spriteBatch.draw(particle.texture, particle.position.x, particle.position.y, particle.texture.getRegionWidth() / 2.0f, particle.texture.getRegionHeight() / 2.0f, particle.texture.getRegionWidth(), particle.texture.getRegionHeight(), 1f, 1f, particle.orientation, false);
            spriteBatch.setColor(defaultColor);

        }
    }

    public void explosionHandler(Vector2 position, float duration, ParticleType type){
        // Particles Explosion
        for (int i = 0; i < 120; i++) {
            float speed = 2f * (rand.nextFloat() * (10f + 10f) - 10f);
            float speed2 = 2f * (rand.nextFloat() * (10f + 10f) - 10f);
            Vector2 partVelocity = new Vector2(speed, speed2);

            GameWorld.particleManager.createParticle(AssetLoader.lineParticle, position, partVelocity.angle(), new Vector2(1.5f, 1.5f), duration, 1f, partVelocity, type, 1f);
        }
    }
}
