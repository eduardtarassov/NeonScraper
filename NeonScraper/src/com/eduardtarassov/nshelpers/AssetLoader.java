package com.eduardtarassov.nshelpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by Eduard on 3/10/14.
 */
public class AssetLoader {

    private static Texture texture, logoTexture;
    public static TextureRegion player, seeker, wanderer, bullet, hole, logo;


    public static void load(){
        logoTexture = new Texture(Gdx.files.internal("data/logo.png"));
        logoTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        logo = new TextureRegion(logoTexture, 0, 0, 512, 114);

        texture = new Texture(Gdx.files.internal("data/texture.png"));
        texture.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);


        seeker = new TextureRegion(texture, 0, 0, 40, 30);
        seeker.flip(false, true);
        player = new TextureRegion(texture, 41, 0, 39, 40);
        player.flip(false, true);
        bullet = new TextureRegion(texture, 0, 31, 9, 28);
        bullet.flip(false, true);
        wanderer = new TextureRegion(texture, 80, 0, 39, 40);
        wanderer.flip(false, true);
        hole = new TextureRegion(texture, 120, 0, 40, 40);
        hole.flip(false, true);
    }

    public static void dispose(){
     texture.dispose();
    }
}
