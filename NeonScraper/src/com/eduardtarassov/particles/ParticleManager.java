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


    public void createParticle(TextureRegion texture, Vector2 position, float orientation, Vector2 scale, Color color, float duration, float percentLife, Vector2 velocity, ParticleType type, float lengthMultiplier, int no)
    {
        Particle particle;
        /*if (particleList.getCount() == particleList.getCapacity())
        {
            System.out.println("ERROR1");
            // if the list is full, overwrite the oldest particle, and rotate the circular list
            particle = particleList.getElement(0);
            particleList.setStart(particleList.getStart() + 1);
        }
        else
        { */
            System.out.println("ERROR2");
            particle = new Particle(texture, position, orientation, scale, color, duration, percentLife, velocity, type, lengthMultiplier, no);
            particleList.setElement(particle, particleList.getCount());
        //}

        System.out.println("particlelist SIZE: " + particleList.getCount());

      /*  // Create the particle
        particle.texture = texture;
        particle.position = position;
        particle.color = color;
        particle.duration = duration;
        particle.percentLife = 1f;
        particle.scale = scale;
        particle.orientation = 0;  */

    }

    public void update(){
        int removalCount = 0;
        for (int i = 0; i < particleList.getCount(); i++)
        {
            Particle particle = particleList.getElement(i);
            particle.updateParticle();
            particle.percentLife -= 1f / particle.duration;

            // shift deleted particles to the end of the list
            //swap(particleList, i - removalCount, i);

            // if the particle has expired, delete this particle
           /* if (particle.percentLife < 0)
                removalCount++; */
        }
    }



    private void swap(CircularParticleArray list, int index1, int index2)
    {
        Particle temp = list.getElement(index1);
        list.setElement(list.getElement(index2), index1);
        list.setElement(temp, index2);
    }

    public void draw(SpriteBatch spriteBatch)
    {
        for (int i = 0; i < particleList.getCount(); i++)
        {
            Particle currentParticle = particleList.getElement(i);
            spriteBatch.draw(currentParticle.texture, currentParticle.position.x, currentParticle.position.y, currentParticle.texture.getRegionWidth() / 2.0f, currentParticle.texture.getRegionHeight() / 2.0f, currentParticle.texture.getRegionWidth(), currentParticle.texture.getRegionHeight(), 1f, 1f, currentParticle.orientation, false);
        }
    }
    }
