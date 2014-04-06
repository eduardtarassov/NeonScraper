package com.eduardtarassov.screens;

import aurelienribon.tweenengine.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.eduardtarassov.TweenAccessors.SpriteAccessor;
import com.eduardtarassov.neonscraper.NSGame;
import com.eduardtarassov.nshelpers.AssetLoader;
import com.eduardtarassov.nshelpers.Constants;

/**
 * This class is responsible for taking our logo sprite,
 * start it at 0 opacity , increase it to 1 (100%) and bring it back down to zero on this Screen.
 * Created by Eduard on 05/04/14.
 */
public class SplashScreen implements Screen {
    private TweenManager manager;
    private SpriteBatch batcher;
    private Sprite sprite;
    private NSGame game;

    public SplashScreen(NSGame game) {
        this.game = game;
    }

    @Override
    public void show() {
        sprite = new Sprite(AssetLoader.logo);
        sprite.setColor(0, 0, 0, 0);

        float desiredHeight = Constants.HEIGHT * .3f;
        float scale = desiredHeight / sprite.getHeight();


        sprite.setSize(sprite.getWidth() * scale, sprite.getHeight() * scale);
        sprite.setPosition((Constants.WIDTH / 2) - (sprite.getWidth() / 2), (Constants.HEIGHT / 2) - (sprite.getHeight() / 2));
        setupTween();
        batcher = new SpriteBatch();
    }

    private void setupTween() {
        Tween.registerAccessor(Sprite.class, new SpriteAccessor()); //this line registers a new accessor. We will be able to modify Sprite objects using the Tween Engine.
        manager = new TweenManager(); //The Tween Engine requires a TweenManager to work, which is updated in our render method with the delta value. This manager will then handle the interpolation for us using our SpriteAccessor.

        TweenCallback cb = new TweenCallback() {   // object whose methods are called when Tweening is complete.
            @Override
            public void onEvent(int type, BaseTween<?> source) {   //cb's onEvent method will be called when our tweening will be completed and will send us to GameScreen.
                game.setScreen(new GameScreen());
            }
        };

        Tween.to(sprite, SpriteAccessor.ALPHA, .8f).target(1) //This says: We are going to tween the sprite object using the SpriteAccessor's ALPHA tweenType. We want this to take .8 seconds. We want you to modify the starting alpha value (this is specified in the SpriteAccessor class) to the desired target value of 1.
                .ease(TweenEquations.easeInOutQuad).repeatYoyo(1, .4f) //We want this to use a quadratic interpolation (you will see what this does), and repeat once as a Yoyo (with .4 seconds between the repetition). This accomplishes the desired effect of bringing our opacity back down to zero once it hits one.
                .setCallback(cb).setCallbackTriggers(TweenCallback.COMPLETE) //This then says, use the callback that we have created above called cb, and notify it when tweening is complete.
                .start(manager);  //this specifies which manager will be doing the work.
    }

    @Override
    public void render(float delta) {
        manager.update(delta);
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        batcher.begin();
        sprite.draw(batcher);
        batcher.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void hide() {
        // TODO Auto-generated method stub

    }

    @Override
    public void pause() {
        // TODO Auto-generated method stub

    }

    @Override
    public void resume() {
        // TODO Auto-generated method stub

    }

    @Override
    public void dispose() {
        // TODO Auto-generated method stub

    }
}
