package com.eduardtarassov.nshelpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by Eduard on 3/10/14.
 */
public class AssetLoader {

    private static Texture texture;
    public static TextureRegion player, seeker, wanderer, bullet, pointer;


    public static void load(){

        texture = new Texture(Gdx.files.internal("data/texture.png"));
        texture.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);


        seeker = new TextureRegion(texture,0, 0, 20, 15);
        seeker.flip(false, true);
        pointer = new TextureRegion(texture, 20, 0, 31, 15);
        pointer.flip(false, true);
        player = new TextureRegion(texture, 31, 0, 51, 20);
        player.flip(false, true);

        wanderer = new TextureRegion(texture,121, 0, 70, 20);
        wanderer.flip(false, true);
        bullet = new TextureRegion(texture,0, 20, 14, 15);
        bullet.flip(false, true);


    }

    public static void dispose(){
     texture.dispose();
    }
}
