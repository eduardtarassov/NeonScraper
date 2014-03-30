package com.eduardtarassov.gameobjects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.eduardtarassov.nshelpers.Constants;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * This class tracks for our entities, updates and draws them. Also responsible for collision detection.
 * Created by Eduard on 3/10/14.
 */
public class EntityManager {
    private static ArrayList<Entity> entities = new ArrayList<Entity>();   // In example author uses List
    private static boolean isUpdating;
    private static ArrayList<Entity> addedEntities = new ArrayList<Entity>(); // In example author uses List



    public static int getEntitiesSize() {
        return entities.size();
    }

    public static void addEntity(Entity entity){
        if (!isUpdating)
            entities.add(entity);
        else
            addedEntities.add(entity);

            Grid.setObjectAt(entity.position.x, entity.position.y);
    }

    public static void update(){
        isUpdating = true;

        for(Entity item : entities)
            item.update();

        isUpdating = false;

        // Add all the new arrival entities into the arraylist of entities
        for (Entity item : addedEntities){
            entities.add(item);
        }

        addedEntities.clear();

        // Remove any expired entities
        /*for (Entity item : entities){
           if (item.isExpired)  {

                entities.remove(item);
           }
        }    */

        for (Iterator<Entity> it = entities.iterator(); it.hasNext(); ) {
            Entity item = it.next();
            if (item.isExpired) {
                it.remove();
            }
        }



        //System.out.println("SIZE " + entities.size());

    }

    public static void draw(SpriteBatch spriteBatch){
        for (Entity item : entities)
            item.draw(spriteBatch);
    }
}
