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
    private Color color = Color.WHITE;
    protected Vector2 position, velocity, accelerometer, direction;
    protected float rotation;

    // We will use radius for circular collision detection.
    protected float radius = 20;
    // True if the entity was destroyed and should be deleted.
    protected boolean isExpired;

    public Vector2 size()
    {
            return image == null ? Vector2.Zero : new Vector2(image.getRegionWidth(), image.getRegionHeight());
    }

    public void update(){

    }

    public void draw(SpriteBatch spriteBatch)
    {
        //spriteBatch.draw(image, position.x, position.y, image.getRegionWidth(), image.getRegionHeight());     //NOT AS IN EXAMPLE

        spriteBatch.draw(image, position.x, position.y, image.getRegionWidth()/2.0f, image.getRegionHeight()/2.0f, image.getRegionWidth(), image.getRegionHeight(), 1f, 1f, rotation + 90, false);
    }
}

