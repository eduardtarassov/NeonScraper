package com.eduardtarassov.gameobjects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Eduard on 3/10/14.
 * This is the base class for out entities.
 */
public class Entity {
       public TextureRegion image;
    // The tint of the image. This will also allow us to change the transparency.
    public Color color = Color.WHITE;
    public Vector2 position, velocity;
    public float orientation;
    // We will use radius for circular collision detection.
    public float radius = 20;
    // True if the entity was destroyed and should be deleted.
    public boolean isExpired;

    public Vector2 size()
    {
            return image == null ? Vector2.Zero : new Vector2(image.getRegionWidth(), image.getRegionHeight());
    }

    public void update(){

    }

    public void draw(SpriteBatch spriteBatch)
    {
        spriteBatch.draw(image, position.x, position.y, image.getRegionWidth(), image.getRegionHeight());     //NOT AS IN EXAMPLE
    }
}

