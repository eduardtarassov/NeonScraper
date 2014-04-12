package com.eduardtarassov.gameobjects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.eduardtarassov.gameworld.GameWorld;
import com.eduardtarassov.nshelpers.AssetLoader;
import com.eduardtarassov.particles.Particle;
import com.eduardtarassov.particles.ParticleManager;
import com.eduardtarassov.particles.ParticleType;

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


    //public static ArrayList<Entity> blackHoles = new ArrayList<Entity>();
    private static Entity player = new Entity();


    public static void addEntity(Entity entity) {
        if (!isUpdating)
            if (entity.getClass().equals(Bullet.class))
                bullets.add(entity);
            else if (entity instanceof PlayerShip)
                player = entity;
            else
                enemies.add(entity);
        else
            addedEntities.add(entity);
    }

    public static void update(float delta) {
        isUpdating = true;

        for (Entity item : bullets)
            item.update(delta);

        for (Entity item : enemies)
            item.update(delta);

        player.update(delta);

        isUpdating = false;

        // Add all the new arrival entities into the arraylist of entities
        for (Entity item : addedEntities) {
            if (item instanceof Bullet)
                bullets.add(item);
            else if ((item instanceof Seeker) || (item instanceof Wanderer) || (item instanceof GreenHole))
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
        handleCollisions();
        handleGravity(delta);

    }

    public static void draw(SpriteBatch spriteBatch) {
        for (Entity item : bullets)
            item.draw(spriteBatch);
        for (Entity item : enemies)
            item.draw(spriteBatch);
        player.draw(spriteBatch);
    }

    private static boolean isColliding(Entity a, Entity b) {
        float radius = a.radius + b.radius;
        return !a.isExpired && !b.isExpired && new Vector2(a.position).sub(b.position).len2() < radius * radius;
    }

    private static void handleCollisions() {
        // Handles collisions between enemies
        for (int i = 0; i < enemies.size(); i++) {
            for (int j = i + 1; j < enemies.size(); j++) {
                if (isColliding(enemies.get(i), enemies.get(j))) {
                   if ((! (enemies.get(i) instanceof OrangeHole)) && (! (enemies.get(i) instanceof OrangeHole))){
                       enemies.get(i).handleCollision(enemies.get(j));
                    enemies.get(j).handleCollision(enemies.get(i));
                   }
                    else {
                       enemies.get(i).wasShot(i);
                    enemies.get(j).wasShot(j);
                   }
                }
            }
        }

        // Handles collisions between bullets and enemies
        for (int i = 0; i < enemies.size(); i++) {
            for (int j = 0; j < bullets.size(); j++) {
                if (isColliding(enemies.get(i), bullets.get(j))) {

                    PlayerStatus.checkMultiplier();
                    PlayerStatus.multiplierTimeStart();
                    enemies.get(i).wasShot(i);
                    AssetLoader.playRandExplosionSound();
                    ((Bullet) bullets.get(j)).isExpired = true;
                }
            }
        }

        // Handles collisions between the player and enemies
        for (int i = 0; i < enemies.size(); i++) {
            if (isColliding(PlayerShip.instance, enemies.get(i))) {
                PlayerShip.instance.kill();
                EnemySpawner.reset();
                AssetLoader.playRandExplosionSound();
                PlayerStatus.removeLife();
                GameWorld.particleManager.explosionHandler(PlayerShip.getInstance().position, 50, ParticleType.Player);
                for (Entity item : enemies)
                    item.destroyEnemy();
                if (PlayerStatus.getLives() <= 0) {
                    /*if (PlayerStatus.getScore() > PlayerStatus.loadHighScore())
                        PlayerStatus.saveHighScore();*/
                    GameWorld.setCurrentStateGameOver();
                }
                break;
            }
        }
    }

    public static void handleGravity(float delta) {
        for (int i = 0; i < enemies.size(); i++) {
            if ((enemies.get(i) instanceof GreenHole) || (enemies.get(i) instanceof OrangeHole)) {
                int radius = 300;
                // Checking the player
                if (isNearby(player, enemies.get(i), radius)) {
                    applyGravity(enemies.get(i), player, delta);
                }

                // Checking bullets
                for (int j = 0; j < bullets.size(); j++) {
                    if (isNearby(bullets.get(j), enemies.get(i), radius)) {
                        applyGravity(enemies.get(i), bullets.get(j), delta);
                    }
                }

                // Checking enemies
                for (int j = 0; j < enemies.size(); j++) {
                    if (!(enemies.get(j) instanceof GreenHole) || !(enemies.get(j) instanceof OrangeHole))
                        if (isNearby(enemies.get(j), enemies.get(i), radius)) {
                            applyGravity(enemies.get(i), enemies.get(j), delta);
                        }
                }

                for (int j = 0; j < GameWorld.particleManager.list.size(); j++){
                    Vector2 posP = new Vector2(GameWorld.particleManager.list.get(j).position);
                    Vector2 posH = new Vector2(enemies.get(i).getPosition());
                    if (posP.sub(posH).len2() <= radius * radius)
                        applyParticlesGravity(enemies.get(i), GameWorld.particleManager.list.get(j), delta);
                }

            }
        }
    }

    private static boolean isNearby(Entity a, Entity b, float distance) {
        Vector2 posA = new Vector2(a.position);
        Vector2 posB = new Vector2(b.position);
        return posA.sub(posB).len2() <= distance * distance;
    }

    private static void applyGravity(Entity hole, Entity target, float delta) {
        Vector2 difference = new Vector2(hole.position).sub(target.position);

        Vector2 gravity = new Vector2(difference).nor().scl(delta);
        float distance = difference.len();
                int pressureType = (hole instanceof OrangeHole) ? 1 : -1;

        if (target instanceof PlayerShip) {
            gravity.scl(250f/distance);

            target.applyGravity(new Vector2(gravity).scl(80f * pressureType));  // We can set the gravity multiplied internally, I think.
        } else if (target instanceof Bullet) {
            gravity.scl(250f/distance);
            target.applyGravity(new Vector2(gravity).scl(80f * pressureType));
        } else if (target instanceof Seeker) {
            target.applyGravity(new Vector2(gravity).scl(100f * pressureType));
        } else if (target instanceof Wanderer) {
            target.applyGravity(new Vector2(gravity).scl(80f * pressureType));
        }
    }

    private static void applyParticlesGravity(Entity hole, Particle target, float delta){
        Vector2 difference = new Vector2(hole.position).sub(target.position);
        Vector2 gravity = new Vector2(difference).nor().scl(delta);
        float distance = difference.len();
        int pressureType = (hole instanceof OrangeHole) ? 1 : -1;
        gravity.scl(250f/distance);

        target.applyGravity(new Vector2(gravity).scl(80f * pressureType));
    }

    /*private static void applyParticlesGravity(Entity hole, Particle target, float delta){
        Vector2 difference = new Vector2(hole.position).sub(target.position);
        float distance = difference.len();
        Vector2 n = new Vector2(difference.x / distance, difference.y / distance);
        target.velocity.add(new Vector2(n.x * 10000 / (distance * distance + 10000), n.y * 10000 / (distance * distance + 10000)));

        if (distance < 20){
            target.duration = 1000;
            target.velocity.add(new Vector2 (n.y / (distance +100), -n.x / (distance +100)).scl(70));
        }
    } */


}
