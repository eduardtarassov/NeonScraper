package com.eduardtarassov.nshelpers;

//import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Eduard on 3/16/14.
 */
public class MotionInputHandler {

    private static SpriteBatch batch;
   // private BitmapFont font;
    private static String mesage = "Do something already!";
    private static float highestY = 0.0f;

    public static void motion(){
    float deviceAngle = Gdx.input.getAzimuth();
        System.out.println(deviceAngle);
    }



}
