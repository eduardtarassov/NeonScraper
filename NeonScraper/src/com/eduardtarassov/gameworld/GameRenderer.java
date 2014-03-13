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
import com.eduardtarassov.nshelpers.Constants;

import java.util.List;

/**
 * Created by Eduard on 2/25/14.
 * This class is responsible for rendering game objects into graphics task.
 */
public class GameRenderer {
    //Game objects
    private GameWorld myWorld;
   // private EntityManager manager;
    //Game configuration objects
    private OrthographicCamera camera;
    private ShapeRenderer shapeRenderer;
    private SpriteBatch spriteBatch;
    private Texture texture;
    private Sprite sprite;


    // Simple game fields
    private int midPointY, midPointX;



    // Game Objects
    // Game Assets
    private TextureRegion player;
    private Animation birdAnimation;

    // Tween stuff

    // Buttons

    public GameRenderer(GameWorld world, int midPointX, int midPointY) {
        myWorld = world;

        this.midPointX = midPointX;
        this.midPointY = midPointY;

        camera = new OrthographicCamera(Constants.WIDTH, Constants.HEIGHT);
        //camera.setToOrtho(true, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
       camera.position.set(Constants.WIDTH/2, Constants.HEIGHT/2, 0);
        camera.update();

        spriteBatch = new SpriteBatch();
        spriteBatch.setProjectionMatrix(camera.combined);

        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(camera.combined);

        initGameObjects();
        initAssets();
        //setupTweens();

    }

    private void initGameObjects() {
        //playerShip = myWorld.getPlayerShip();
        EntityManager.addEntity(PlayerShip.getInstance());

    }

    private void initAssets() {
       player = AssetLoader.player;
    }

private void drawPlayer(){
    spriteBatch.draw(player, midPointX-20, midPointY-20);
}

    public void render(float delta, float runTime) {
        // Sets the clear screen color to: Cornflower Blue
        Gdx.gl.glClearColor(0x64 / 255.0f, 0x95 / 255.0f, 0xed / 255.0f, 0xff / 255.0f); //The same in decimal notation Gdx.gl.glClearColor(  100/255.0f, 149/255.0f, 237/255.0f, 255/255.0f);
        // Clears the screen
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

        //shapeRenderer.begin(ShapeType.Filled);

        // Draw Background color
       // shapeRenderer.setColor(55 / 255.0f, 80 / 255.0f, 100 / 255.0f, 1);
        //shapeRenderer.rect(0, 0, midPointX*2, midPointY*2);
        //shapeRenderer.end();
        // Supposed to be in the GameRoot draw() method
        spriteBatch.begin();
        spriteBatch.enableBlending();
        EntityManager.draw(spriteBatch);

                 //drawPlayer();
        //manager.draw(spriteBatch);
        spriteBatch.end();

    }

}
