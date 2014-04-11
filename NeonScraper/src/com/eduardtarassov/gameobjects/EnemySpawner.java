package com.eduardtarassov.gameobjects;

import com.badlogic.gdx.math.Vector2;
import com.eduardtarassov.nshelpers.AssetLoader;
import com.eduardtarassov.nshelpers.Constants;

import java.util.Random;

/**
 * Created by Eduard on 28/03/14.
 * This class is responsible for creating enemies and setting rate to respawn new ones.
 * As during the game loop
 */
public class EnemySpawner {
    private static int inverseSpawnChance = 400;
    private static long startTimeHole = 0;

    public static void update() {
        if (!PlayerShip.instance.isDead() && EntityManager.enemies.size() < 200) {
            if (new Random().nextInt(inverseSpawnChance) == 0) // Gives value in a range [0, inverseSpawnChance].
                EntityManager.addEntity(new Seeker(AssetLoader.seeker, getSpawnPosition()));

            if (new Random().nextInt(inverseSpawnChance) == 0)
                EntityManager.addEntity(new Wanderer(AssetLoader.wanderer, getSpawnPosition()));


            // System.out.println("This is the change in your time: " + (currentTime - startTimeHole));
            if (System.currentTimeMillis() - startTimeHole > 15000) {
                if (new Random().nextInt(400) == 0){
                    EntityManager.addEntity(new GreenHole(AssetLoader.greenhole, getSpawnPosition()));
                startTimeHole = System.currentTimeMillis();
            }
            if (new Random().nextInt(400) == 0) {
                EntityManager.addEntity(new OrangeHole(AssetLoader.orangehole, getSpawnPosition()));
                startTimeHole = System.currentTimeMillis();
            }


        }
        // Increasing the spawn rate during the game to increase difficulty
        if (inverseSpawnChance > 100)
            inverseSpawnChance -= 0.01f;
    }
    }


    private static Vector2 getSpawnPosition() {
        Vector2 pos;
        do {
            pos = new Vector2((float) (Math.random() * (Constants.WIDTH)), (float) Math.random() * (Constants.HEIGHT));
        }
        while (new Vector2(pos).sub(PlayerShip.instance.position).len2() < Constants.WIDTH / 5 * Constants.WIDTH / 5); // Enemy always at least "Constants.WIDTH / 5" pixels away from player

        return pos;
    }

    public static void reset() {
        inverseSpawnChance = 400;
    }
}
