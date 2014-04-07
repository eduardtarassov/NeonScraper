package com.eduardtarassov.gameworld;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenEquations;
import aurelienribon.tweenengine.TweenManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.eduardtarassov.TweenAccessors.Value;
import com.eduardtarassov.TweenAccessors.ValueAccessor;
import com.eduardtarassov.gameobjects.EntityManager;
import com.eduardtarassov.gameobjects.PlayerShip;
import com.eduardtarassov.gameobjects.PlayerStatus;
import com.eduardtarassov.nshelpers.AssetLoader;
import com.eduardtarassov.nshelpers.Constants;
import com.eduardtarassov.nshelpers.TouchInputHandler;
import com.eduardtarassov.ui.SimpleButton;

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
    private TweenManager manager;
    private Value alpha = new Value();

    // Buttons
    private List<SimpleButton> menuButtons;

    public GameRenderer(GameWorld world) {
        myWorld = world;

        this.menuButtons = ((TouchInputHandler) Gdx.input.getInputProcessor())
                .getMenuButtons();
        camera = new OrthographicCamera(Constants.WIDTH, Constants.HEIGHT);
        //camera.setToOrtho(true, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
       camera.position.set(Constants.MIDPOINTX, Constants.MIDPOINTY, 0);
        camera.update();

        spriteBatch = new SpriteBatch();
        spriteBatch.setProjectionMatrix(camera.combined);

        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(camera.combined);

        initGameObjects();
        initAssets();
        setupTweens();

    }

    private void setupTweens() {
        Tween.registerAccessor(Value.class, new ValueAccessor());
        manager = new TweenManager();
        Tween.to(alpha, -1, .5f).target(0).ease(TweenEquations.easeOutQuad)
                .start(manager);
    }

    private void initGameObjects() {
        //playerShip = myWorld.getPlayerShip();
        EntityManager.addEntity(PlayerShip.getInstance());
    }

    private void initAssets() {
       player = new Sprite(AssetLoader.player);
    }

    private void drawMenuUI() {
        for (SimpleButton button : menuButtons) {
            button.draw(spriteBatch);
        }
    }

    private void drawScore() {
        int length = ("Score: " + PlayerStatus.getScore()).length();
        AssetLoader.font.draw(spriteBatch, "Score: " + PlayerStatus.getScore(),
                Constants.WIDTH - 200 - (28 * length), Constants.HEIGHT - 15);
    }

    private void drawLives() {
        int length = ("Lives: " + PlayerStatus.getLives()).length();
        AssetLoader.font.draw(spriteBatch, "Lives: " + PlayerStatus.getLives(),
                Constants.WIDTH - (24 * length), Constants.HEIGHT - 15);
    }

    public void render(float delta, float runTime) {
        // Sets the clear screen color to: Cornflower Blue
        Gdx.gl.glClearColor(0, 0, 0, 1); //The same in decimal notation Gdx.gl.glClearColor(  100/255.0f, 149/255.0f, 237/255.0f, 255/255.0f);
        // Clears the screen
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

     /* shapeRenderer.begin(ShapeRenderer.ShapeType.Point);
        shapeRenderer.setColor(Color.GREEN);
        shapeRenderer.point(PlayerShip.getPosition2().x + 20, PlayerShip.getPosition2().y + 20, 0);
        shapeRenderer.end();*/


       /* for (int x = 1; x <= Grid.GRID_COLUMNS; x++){
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
        if (myWorld.isRunning()) {
            EntityManager.draw(spriteBatch);
            drawScore();
            drawLives();
        } else if (myWorld.isReady()) {
            //drawScore();
        } else if (myWorld.isMenu()) {
            drawMenuUI();
        } else if (myWorld.isGameOver()) {
            //drawScore();
        } else if (myWorld.isHighScore()) {
            //drawScore();
        }

        spriteBatch.disableBlending();
        spriteBatch.end();

    }

}
