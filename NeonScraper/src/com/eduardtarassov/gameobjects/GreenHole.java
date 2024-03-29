package com.eduardtarassov.gameobjects;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.eduardtarassov.nshelpers.AssetLoader;

/**
 * Created by Eduard on 08/04/14.
 */
public class GreenHole extends Entity {
    private final long greenHoleStart = System.currentTimeMillis();

    public GreenHole(TextureRegion image, Vector2 position) {
        this.image = image;
        this.position = position;
        AssetLoader.playRandSpawnSound();
        hp = 1000;
    }


    @Override
    public void update(float delta) {
        if (System.currentTimeMillis() - greenHoleStart > 35000)
            isExpired = true;

    }
}


