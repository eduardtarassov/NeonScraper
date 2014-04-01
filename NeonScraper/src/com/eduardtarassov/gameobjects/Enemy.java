package com.eduardtarassov.gameobjects;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.Color;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Eduard on 28/03/14.
 */
public class Enemy extends Entity {
    private int timeUntilStart = 60;

    // We  will be adding Iterable<Enemy> methods with enemy behaviour into our Iterable<Enemy> type ArrayList
    private ArrayList<Iterator <Enemy>> behaviours = new ArrayList();

    public boolean isActive() {
        return timeUntilStart <= 0;
    }

    public Enemy(TextureRegion image, Vector2 position) {
        this.image = image;
        this.position = position;
        radius = image.getRegionWidth() / 2.0f;
        color = Color.CLEAR; //!!!!! I am not sure this is gonna be transparent one.

    }

    @Override
    public void update() {
        if (timeUntilStart <= 0) {
            // Enemy behaviour logic goes here.
             if (timeUntilStart <= 0){
                 applyBehaviours();
             }
        } else {
            timeUntilStart--;
            //color = Color.WHITE * (1 - timeUntilStart / 60f);
        }

        position.add(velocity);
        // position.clamp(Constants.VIEWPORT / 2, );   //WTF WAS CODE HERE!!!!
        velocity.scl(0.8f);

    }

    protected void wasShot() {
        isExpired = true;
    }

           /*
           * This method will add the different behaviour methods into our Arraylist behaviours
           */
    private void addBehaviour(Iterable<Enemy> behaviour){
        behaviours.add(behaviour.iterator());
    }

    private void applyBehaviours(){
        for (int i = 0; i < behaviours.size(); i++){
            if (behaviours.get(i).next() == null){
                   behaviours.remove(i--);
            }
        }
    }

    private Iterable<Enemy> followPlayer() {
        while (true) {
            velocity.add(PlayerShip.instance.position.sub(position).scl(acceleration));
            if (velocity != Vector2.Zero)
                orientation = velocity.angle();

            Thread.yield();

        }
    }

    protected Iterable<Enemy> moveInASquare(){
        final int framesPerSide = 30;
        while (true){
            // Move right for 30 frames
            for (int i = 0; i < framesPerSide; i++){
                velocity = new Vector2(1, 0);
                Thread.yield();
            }

            //Move down for 30 frames
            for (int i = 0; i < framesPerSide; i++)
            {
                velocity = new Vector2(0, -1);
                Thread.yield();
            }

            //Move left for 30 frames
            for (int i = 0; i < framesPerSide; i++)
            {
                velocity = new Vector2(-1, 0);
                Thread.yield();
            }

            //Move up for 30 frames
            for (int i = 0; i < framesPerSide; i++)
            {
                velocity = new Vector2(0, 1);
                Thread.yield();
            }
        }
    }

}

