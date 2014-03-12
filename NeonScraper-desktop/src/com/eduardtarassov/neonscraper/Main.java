package com.eduardtarassov.neonscraper;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "NeonScraper";
		cfg.useGL20 = false;
		cfg.width = 408;
		cfg.height = 272;
		
		new LwjglApplication(new NSGame(), cfg);
	}
}
