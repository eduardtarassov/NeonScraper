package com.eduardtarassov.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.eduardtarassov.gameobjects.EntityManager;
import com.eduardtarassov.gameobjects.PlayerShip;
import com.eduardtarassov.nshelpers.AssetLoader;

import java.util.List;

/**
 * Created by Eduard on 2/25/14.
 * This class is responsible for rendering game objects into graphics task.
 */
public class GameRenderer {

    private GameWorld myWorld;
    private OrthographicCamera camera;
    private ShapeRenderer shapeRenderer;

    private SpriteBatch spriteBatch;
    private Texture texture;
    private Sprite sprite;

    private int midPointY, midPointX;

    // Game Objects
       private PlayerShip playerShip;
    // Game Assets
    private TextureRegion player;
    private Animation birdAnimation;

    // Tween stuff

    // Buttons

    public GameRenderer(GameWorld world, int midPointX, int midPointY) {
        myWorld = world;

        this.midPointX = midPointX;
        this.midPointY = midPointY;
        camera = new OrthographicCamera();
        camera.setToOrtho(true, midPointX*2, midPointY*2);

        spriteBatch = new SpriteBatch();
        spriteBatch.setProjectionMatrix(camera.combined);
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(camera.combined);

        initGameObjects();
        initAssets();
        //setupTweens();

    }

    private void initGameObjects() {
        playerShip = myWorld.getPlayerShip();
    }

    private void initAssets() {
       player = AssetLoader.player;
    }

private void drawPlayer(){
    spriteBatch.draw(player, midPointX, midPointY);
}

    public void render(float delta, float runTime) {

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);


        shapeRenderer.begin(ShapeType.Filled);

        // Draw Background color
        shapeRenderer.setColor(55 / 255.0f, 80 / 255.0f, 100 / 255.0f, 1);
        shapeRenderer.rect(0, 0, 136, midPointY + 66);
        shapeRenderer.end();
        // Supposed to be in the GameRoot draw() method
        spriteBatch.begin();
        spriteBatch.enableBlending();
         drawPlayer();
        EntityManager.draw(spriteBatch);
        spriteBatch.end();




        //
       // drawTransition(delta);

    }

  /*  private void drawTransition(float delta) {
        if (alpha.getValue() > 0) {
            manager.update(delta);
            Gdx.gl.glEnable(GL10.GL_BLEND);
            Gdx.gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
            shapeRenderer.begin(ShapeType.Filled);
            shapeRenderer.setColor(1, 1, 1, alpha.getValue());
            shapeRenderer.rect(0, 0, 136, 300);
            shapeRenderer.end();
            Gdx.gl.glDisable(GL10.GL_BLEND);

        }
    }    */

}
