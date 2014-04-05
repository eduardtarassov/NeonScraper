package com.eduardtarassov.gameobjects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * This class tracks for our entities, updates and draws them. Also responsible for collision detection.
 * Created by Eduard on 3/10/14.
 */
public class EntityManager {
    //private static ArrayList<Entity> entities = new ArrayList<Entity>();
    private static boolean isUpdating;
    private static ArrayList<Entity> addedEntities = new ArrayList<Entity>();
    public static ArrayList<Entity> enemies = new ArrayList<Entity>();
    private static ArrayList<Entity> bullets = new ArrayList<Entity>();
    private static Entity player = new Entity();



    /*public static int getEntitiesSize() {
        return entities.size();
    }   */

    public static void addEntity(Entity entity){
        if (!isUpdating)
            if (entity.getClass().equals(Bullet.class))
                bullets.add(entity);
            else if ((entity instanceof Seeker) || (entity instanceof Wanderer))
            enemies.add(entity);
            else
                player = entity;
        else
            addedEntities.add(entity);
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
            if (item instanceof Bullet)
                bullets.add(item);
            else if ((item instanceof Seeker) || (item instanceof Wanderer))
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

    private static boolean isColliding(Entity a, Entity b){
      float radius = a.radius + b.radius;
        return !a.isExpired && !b.isExpired && new Vector2(a.position).sub(b.position).len2() < radius * radius;
    }

    private static void handleCollisions(){
        // Handles collisions between enemies
        for (int i = 0; i < enemies.size(); i++){
            for (int j = i + 1; j < enemies.size(); j++){
                if (isColliding(enemies.get(i), enemies.get(j))){
                    enemies.get(i).handleCollision(enemies.get(j));
                    enemies.get(j).handleCollision(enemies.get(i));
                }
            }
        }

        // Handles collisions between bullets and enemies
        for (int i = 0; i < enemies.size(); i++){
            for (int j = 0; j < bullets.size(); j++){
                if (isColliding(enemies.get(i), bullets.get(j))){
                    enemies.get(i).wasShot();
                    ((Bullet) bullets.get(j)).isExpired = true;
                }
            }
        }

        // Handles collisions between the player and enemies
        for (int i = 0; i < enemies.size(); i++){
            if (((enemies.get(i)).isActive()) && (isColliding(PlayerShip.instance, enemies.get(i)))){
                PlayerShip.instance.kill();
                for (Entity item : enemies)
                    item.wasShot();
                break;
            }
        }
    }




}
