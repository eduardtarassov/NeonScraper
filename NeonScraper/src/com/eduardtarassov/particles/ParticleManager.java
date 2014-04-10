package com.eduardtarassov.particles;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.Color;

/**
 * Created by Eduard on 10/04/14.
 */
public class ParticleManager {
  /*  // This delegate will be called for each particle.
   private void updateParticle(Particle particle){

   }  */

    private CircularParticleArray particleList;



    public ParticleManager(int capacity)
    {
        //this.updateParticle = updateParticle;
        particleList = new CircularParticleArray(capacity);

        // Populate the list with empty particle objects, for reuse.
       /* for (int i = 0; i < capacity; i++)
            particleList.setElement(new Particle(), i);    */
    }


    public void createParticle(TextureRegion texture, Vector2 position, Color color, float duration, Vector2 scale, ParticleState state)
    {
        Particle particle;
        if (particleList.getCount() == particleList.getCapacity())
        {
            // if the list is full, overwrite the oldest particle, and rotate the circular list
            particle = particleList.getElement(0);
            particleList.setStart(particleList.getStart() + 1);
        }
        else
        {
            particleList.setElement(new Particle(), particleList.getCount());
            particle = particleList.getElement(particleList.getCount() - 1);
        }

        // Create the particle
        particle.texture = texture;
        particle.position = position;
        particle.color = color;

        particle.duration = duration;
        particle.percentLife = 1f;
        particle.scale = scale;
        particle.orientation = 0;
       particle.state = state;
    }

    public void update(){
        int removalCount = 0;
        for (int i = 0; i < particleList.getCount(); i++)
        {
            Particle particle = particleList.getElement(i);
            ParticleState.updateParticle(particle);
            particle.percentLife -= 1f / particle.duration;

            // shift deleted particles to the end of the list
            swap(particleList, i - removalCount, i);

            // if the particle has expired, delete this particle
            if (particle.percentLife < 0)
                removalCount++;
        }
    }

    private static void swap(CircularParticleArray list, int index1, int index2)
    {
        Particle temp = list.getElement(index1);
        list.setElement(list.getElement(index2), index1);
        list.setElement(temp, index2);
    }

    public void draw(SpriteBatch spriteBatch)
    {
        for (int i = 0; i < particleList.getCount(); i++)
        {
            Particle particle = particleList.getElement(i);
            spriteBatch.draw(particle.texture, particle.position.x, particle.position.y, particle.texture.getRegionWidth() / 2.0f, particle.texture.getRegionHeight() / 2.0f, particle.texture.getRegionWidth(), particle.texture.getRegionHeight(), 1f, 1f, particle.orientation, false);
        }
    }
    }
