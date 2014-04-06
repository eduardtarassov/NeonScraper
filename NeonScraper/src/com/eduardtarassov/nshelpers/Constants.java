package com.eduardtarassov.nshelpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

import java.util.HashMap;

/**
 * Created by Eduard on 3/13/14.
 */
public class Constants {
    // This value shows us that our camera will see 5 metres tall and width radius from center
    public static final float VIEWPORT = 0.5f;
    public static final int WIDTH = Gdx.graphics.getWidth();
     public static final int HEIGHT = Gdx.graphics.getHeight();
    public static final Vector2 SCREENSIZE = new Vector2(WIDTH, HEIGHT);
    public static final int ANDROID_WIDTH = 1280;
    public static final int ANDROID_HEIGHT = 720;
    public static final int MIDPOINTX = Gdx.graphics.getWidth()/2;
    public static final int MIDPOINTY = Gdx.graphics.getHeight()/2;

}
