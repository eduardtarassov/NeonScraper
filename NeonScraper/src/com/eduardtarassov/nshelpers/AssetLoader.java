package com.eduardtarassov.nshelpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by Eduard on 3/10/14.
 */
public class AssetLoader {

    private static Texture texture, logoTexture;
    public static TextureRegion player, seeker, wanderer, bullet, hole, logo, nslogo;
    public static BitmapFont font, shadow;
    private static Preferences prefs;

    public static void load() {
        logoTexture = new Texture(Gdx.files.internal("data/logo.png"));
        logoTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        logo = new TextureRegion(logoTexture, 0, 0, 512, 114);

        texture = new Texture(Gdx.files.internal("data/texture.png"));
        texture.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);


        seeker = new TextureRegion(texture, 0, 0, 40, 30);
        seeker.flip(false, true);
        player = new TextureRegion(texture, 41, 0, 39, 40);
        player.flip(false, true);
        wanderer = new TextureRegion(texture, 80, 0, 39, 40);
        wanderer.flip(false, true);
        hole = new TextureRegion(texture, 120, 0, 40, 40);
        hole.flip(false, true);

        bullet = new TextureRegion(texture, 0, 31, 9, 28);
        bullet.flip(false, true);
        nslogo = new TextureRegion(texture, 10, 41, 205, 32);
        nslogo.flip(false, true);

        font = new BitmapFont(Gdx.files.internal("data/text.fnt"));
        font.setScale(.25f, -.25f);
        shadow = new BitmapFont(Gdx.files.internal("data/shadow.fnt"));
        shadow.setScale(.25f, -.25f);

        // Create (or retrieve existing) preferences file
        prefs = Gdx.app.getPreferences("ZombieBird");

        if (!prefs.contains("highScore")) {
            prefs.putInteger("highScore", 0);
        }
    }

    public static void setHighScore(int val) {
        prefs.putInteger("highScore", val);
        prefs.flush();
    }

    public static int getHighScore() {
        return prefs.getInteger("highScore");
    }

    public static void dispose() {
        texture.dispose();
        logoTexture.dispose();
        font.dispose();
        shadow.dispose();
    }
}
