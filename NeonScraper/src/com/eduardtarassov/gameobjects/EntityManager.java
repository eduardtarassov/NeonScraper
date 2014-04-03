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
    //private static ArrayList<Entity> entities = new ArrayList<Entity>();
    private static boolean isUpdating;
    private static ArrayList<Entity> addedEntities = new ArrayList<Entity>();
    private static ArrayList<Entity> enemies = new ArrayList<Entity>();
    private static ArrayList<Entity> bullets = new ArrayList<Entity>();
    private static Entity player = new Entity();



    /*public static int getEntitiesSize() {
        return entities.size();
    }   */

    public static void addEntity(Entity entity){
        if (!isUpdating)
            if (entity.getClass().equals(Bullet.class))
                bullets.add(entity);
            else if (entity.getClass().equals(Enemy.class))
            enemies.add(entity);
            else
                player = entity;
        else
            addedEntities.add(entity);

            Grid.setObjectAt(entity.position.x, entity.position.y);
    }

    public static void update(){
        isUpdating = true;

        for(Entity item : bullets)
            item.update();

        for(Entity item : enemies)
            item.update();

        player.update();

        isUpdating = false;

        // Add all the new arrival entities into the arraylist of entities
        for (Entity item : addedEntities){
            if (item.getClass().equals(Bullet.class))
                bullets.add(item);
            else if (item.getClass().equals(Enemy.class))
                enemies.add(item);
            else
                player = item;
        }

        addedEntities.clear();

        // Remove any expired bullet entities
        for (Iterator<Entity> it = bullets.iterator(); it.hasNext(); ) {
            Entity item = it.next();
            if (item.isExpired) {
                it.remove();
            }
        }

        // Remove any expired enemy entities
        for (Iterator<Entity> it = enemies.iterator(); it.hasNext(); ) {
            Entity item = it.next();
            if (item.isExpired) {
                it.remove();
            }
        }
    }

    public static void draw(SpriteBatch spriteBatch){
        for (Entity item : bullets)
            item.draw(spriteBatch);
        for (Entity item : enemies)
            item.draw(spriteBatch);
        player.draw(spriteBatch);
    }
}
