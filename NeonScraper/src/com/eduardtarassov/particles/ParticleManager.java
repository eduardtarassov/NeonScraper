package com.eduardtarassov.particles;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.Color;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Eduard on 10/04/14.
 */
public class ParticleManager {
    private ArrayList<Particle> list;
    private Color[] particlesColor = new Color[4];
    private Random rand = new Random();

    public ParticleManager()
    {
        list = new ArrayList<Particle>();
        particlesColor[0] = Color.RED;
        particlesColor[1] = Color.WHITE;
        particlesColor[2] = Color.LIGHT_GRAY;
        particlesColor[3] = new Color(6f, 0.5f, 1f, 1f);
    }

    public void createParticle(TextureRegion texture, Vector2 position, float orientation, Vector2 scale, float duration, float percentLife, Vector2 velocity, ParticleType type, float lengthMultiplier, int no)
    {
       Color color = particlesColor[rand.nextInt(4)];
        Particle particle;
            particle = new Particle(texture, position, orientation, scale, color, duration, percentLife, velocity, type, lengthMultiplier, no);
            list.add(particle);
    }

    public void update(){
        for (int i = 0; i < list.size(); i++)
        {
            Particle particle = list.get(i);
            particle.updateParticle();
            if (particle.percentLife <= 0){
                list.remove(i);
                i--;
            }
        }
    }

    public void draw(SpriteBatch spriteBatch)
    {
        for (int i = 0; i < list.size(); i++)
        {
            Color defaultColor;
            defaultColor = spriteBatch.getColor();
            Particle particle = list.get(i);
            spriteBatch.setColor(particle.color);
            spriteBatch.draw(particle.texture, particle.position.x, particle.position.y, particle.texture.getRegionWidth() / 2.0f, particle.texture.getRegionHeight() / 2.0f, particle.texture.getRegionWidth(), particle.texture.getRegionHeight(), 1f, 1f, particle.orientation, false);
            spriteBatch.setColor(defaultColor);

        }
    }
    }
