package com.eduardtarassov.neonscraper;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.eduardtarassov.nshelpers.AssetLoader;
import com.eduardtarassov.screens.GameScreen;
import com.eduardtarassov.screens.SplashScreen;

import javax.swing.*;

public class NSGame extends Game {
  //  private static NSGame instance;



    @Override
	public void create() {
		System.out.println("Game Created!");
        AssetLoader.load();
        setScreen(new SplashScreen(this));
	}

	@Override
	public void dispose() {
		//batch.dispose();
		//texture.dispose();
	}
}
