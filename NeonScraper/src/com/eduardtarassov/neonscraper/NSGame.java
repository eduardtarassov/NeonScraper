package com.eduardtarassov.neonscraper;

import com.badlogic.gdx.Game;
import com.eduardtarassov.nshelpers.AssetLoader;
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
