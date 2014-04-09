package com.eduardtarassov.gameobjects;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.eduardtarassov.nshelpers.AssetLoader;

/**
 * Created by Eduard on 09/04/14.
 */
public class OrangeHole extends Entity {
    private final long orangeHoleStart = System.currentTimeMillis();

    public OrangeHole(TextureRegion image, Vector2 position) {
        this.image = image;
        this.position = position;
        AssetLoader.playRandSpawnSound();
        hp = 10;
    }


    @Override
    public void update(float delta) {
        if (System.currentTimeMillis() - orangeHoleStart > 35000)
            isExpired = true;
    }
}
