package com.eduardtarassov.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.eduardtarassov.gameobjects.EntityManager;
import com.eduardtarassov.gameobjects.PlayerShip;
import com.eduardtarassov.gameworld.GameRenderer;
import com.eduardtarassov.gameworld.GameWorld;
import com.eduardtarassov.nshelpers.AndroidInput;

/**
 * Created by Eduard on 3/10/14.
 */
public class GameScreen implements Screen {

    private GameWorld world;
    private GameRenderer renderer;
    private float runTime = 0;

    public GameScreen() {
        float screenWidth = Gdx.graphics.getWidth();
        float screenHeight = Gdx.graphics.getHeight();
        /*float gameHeight = 136;
        float gameWidth = screenWidth / (screenHeight / gameHeight);*/
        int midPointX = (int) (screenWidth / 2);
        int midPointY = (int) (screenHeight / 2);




        world = new GameWorld();
        Gdx.input.setInputProcessor(new AndroidInput());
        renderer = new GameRenderer(world, midPointX, midPointY);


    }

    @Override
    public void render(float delta) {
        runTime += delta;
        world.update(delta);
        renderer.render(delta, runTime);
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void show() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }
}
