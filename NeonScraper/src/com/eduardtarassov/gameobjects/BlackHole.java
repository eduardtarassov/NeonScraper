package com.eduardtarassov.gameobjects;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.eduardtarassov.nshelpers.AssetLoader;

/**
 * Created by Eduard on 08/04/14.
 */
public class BlackHole extends Entity {
    private final float blackHoleStart = System.currentTimeMillis();

    public BlackHole(TextureRegion image, Vector2 position) {
        this.image = image;
        this.position = position;
        AssetLoader.playRandSpawnSound();
    }


    @Override
    public void update() {
        if (System.currentTimeMillis() - blackHoleStart > 10000)
            isExpired = true;

    }
}


