package com.eduardtarassov.gameobjects;

import com.badlogic.gdx.math.Vector2;
import com.eduardtarassov.nshelpers.AssetLoader;
import com.eduardtarassov.nshelpers.Constants;

/**
 * Created by Eduard on 28/03/14.
 * This class is responsible for creating enemies and setting rate to respawn new ones.
 * As during the game loop
 */
public class EnemySpawner {
    static float inverseSpawnChance = 60;
             static boolean created = false;
    public static void update()
    {
        if (!PlayerShip.instance.isDead() && EntityManager.enemies.size() < 200)
        {
           // if ((int)(Math.random() * (inverseSpawnChance + 1)) == 0) // Gives value in a range [0, inverseSpawnChance].
            if (!created){
                EntityManager.addEntity(new Seeker(AssetLoader.seeker, getSpawnPosition()));
                created = true;
            }

           /* if ((int)(Math.random() * (inverseSpawnChance + 1)) == 0)
                EntityManager.addEntity(new Wanderer(AssetLoader.wanderer, getSpawnPosition()));  */
        }

        // Increasing the spawn rate during the game to increase difficulty
        if (inverseSpawnChance > 20)
            inverseSpawnChance -= 0.005f;
    }

    private static Vector2 getSpawnPosition()
    {
        Vector2 pos;
        do
        {
            pos = new Vector2((float) (Math.random() * (Constants.WIDTH)), (float) Math.random() * (Constants.HEIGHT));
        }
        while (new Vector2(pos).sub(PlayerShip.instance.position).len2() < 20 * 20); // Enemy always at least 250 pixels away from player

        return pos;
    }

    public static void reset()
    {
        inverseSpawnChance = 60;
    }
}
