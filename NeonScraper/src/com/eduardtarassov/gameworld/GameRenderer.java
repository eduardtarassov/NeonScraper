package com.eduardtarassov.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
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
    public static OrthographicCamera camera;
    private ShapeRenderer shapeRenderer;
    private SpriteBatch spriteBatch;
    private Texture texture;
    private Sprite player;




    // Game Objects
    // Game Assets

    // Tween stuff

    // Buttons

    public GameRenderer(GameWorld world) {
        myWorld = world;


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
       player = new Sprite(AssetLoader.player);

    }

    public void render(float delta, float runTime) {
        // Sets the clear screen color to: Cornflower Blue
        Gdx.gl.glClearColor(0, 0, 0, 1); //The same in decimal notation Gdx.gl.glClearColor(  100/255.0f, 149/255.0f, 237/255.0f, 255/255.0f);
        // Clears the screen
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

      /*shapeRenderer.begin(ShapeType.Line);
        for (int x = 1; x <= Grid.GRID_COLUMNS; x++){
            shapeRenderer.line(x*Grid.gridDividedColumns, 0, x*Grid.gridDividedColumns, Constants.HEIGHT, Color.GREEN, Color.GREEN);
        }

        for (int x = 1; x <= Grid.GRID_ROWS; x++){
            shapeRenderer.line(0, x*Grid.gridDividedRows, Constants.WIDTH, x*Grid.gridDividedRows, Color.GREEN, Color.GREEN);
        }
        shapeRenderer.end();  */
        //shapeRenderer.begin(ShapeType.Filled);

                      // Draw Background color
                      // shapeRenderer.setColor(55 / 255.0f, 80 / 255.0f, 100 / 255.0f, 1);
                      //shapeRenderer.rect(0, 0, midPointX*2, midPointY*2);
                      //shapeRenderer.end();
                      // Supposed to be in the GameRoot draw() method
                      spriteBatch.enableBlending();
        spriteBatch.begin();


        //spriteBatch.disableBlending();

        EntityManager.draw(spriteBatch);

                 //drawPlayer();
        spriteBatch.disableBlending();
        //manager.draw(spriteBatch);
        spriteBatch.end();

    }

}
