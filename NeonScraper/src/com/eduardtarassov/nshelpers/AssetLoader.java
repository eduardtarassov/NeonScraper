package com.eduardtarassov.nshelpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Eduard on 3/10/14.
 */
public class AssetLoader {

    private static Texture texture, logoTexture;
    public static TextureRegion player, seeker, wanderer, bullet, greenhole, orangehole, logo, playButtonUp, playButtonDown;
    public static BitmapFont font, shadow;
    public static ArrayList<Sound> explosionS = new ArrayList();
    public static ArrayList<Sound> spawnS = new ArrayList();
    public static ArrayList<Sound> shootS = new ArrayList();
    public static Music music;
    private static Preferences prefs;
    private static Random random = new Random();


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
        greenhole = new TextureRegion(texture, 119, 0, 40, 40);
        greenhole.flip(false, true);
        orangehole = new TextureRegion(texture, 161, 0, 40, 40);
        orangehole.flip(false, true);
        playButtonUp = new TextureRegion(texture, 161, 0, 35, 16);
        playButtonUp.flip(false, true);
        playButtonDown = new TextureRegion(texture, 197, 0, 35, 16);
        playButtonDown.flip(false, true);

        bullet = new TextureRegion(texture, 0, 31, 9, 28);
        bullet.flip(false, true);

        font = new BitmapFont(Gdx.files.internal("data/text.fnt"));
        font.setScale(1f);
        shadow = new BitmapFont(Gdx.files.internal("data/shadow.fnt"));
        shadow.setScale(1f);

        for (int i = 1; i < 9; i++) {
            Sound s = Gdx.audio.newSound(Gdx.files.internal("data/sound/explosion-0" + i + ".wav"));
            explosionS.add(s);
            Sound s2 = Gdx.audio.newSound(Gdx.files.internal("data/sound/spawn-0" + i + ".wav"));
            spawnS.add(s2);
        }

        for (int i = 1; i < 5; i++) {
            Sound s = Gdx.audio.newSound(Gdx.files.internal("data/sound/shoot-0" + i + ".wav"));
            shootS.add(s);
        }

        music = Gdx.audio.newMusic(Gdx.files.internal("music.mp3"));

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

    public static void playRandExplosionSound(){
        Sound sound = randomSound(explosionS);

        long id = sound.play();
        sound.setVolume(id,0.2f);
    }

    public static void playRandSpawnSound(){
        Sound sound = randomSound(spawnS);

        long id = sound.play();
        sound.setVolume(id,0.2f);
    }

    public static void playRandShootSound(){
        Sound sound = randomSound(shootS);

        long id = sound.play();
        sound.setVolume(id,0.2f);
    }

    private static Sound randomSound(ArrayList<Sound> soundPar){

        int i = (int) (1 + Math.random() * (soundPar.size() - 1));
        return soundPar.get(i);
    }
}
