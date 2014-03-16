package com.eduardtarassov.nshelpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;
import com.eduardtarassov.gameobjects.Pointer;

import java.util.ArrayList;

/**
 * Created by Eduard on 3/13/14.
 */
public class TouchInputHandler implements InputProcessor {
    //private SpriteBatch spriteBatch;

    public ArrayList<Pointer> touches = new ArrayList<Pointer>();
    public Pointer pointerObject;

    public TouchInputHandler(){

    }

    @Override
    public boolean keyDown(int keycode) {
        // TODO Auto-generated method stub
        switch(keycode){
            case Keys.A:
                // Ship moves left
                System.out.println("Ship moves left");
                break;
            case Keys.W:
                // Ship moves up
                System.out.println("Ship moves up");
                break;
            case Keys.D:
                // Ship moves right
                System.out.println("Ship moves right");
                break;
            case Keys.S:
                // Ship moves down
                System.out.println("Ship moves down");
                break;
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
          //bshow pointer image on the current position

        pointerObject = new Pointer(screenX, screenY, true);
        touches.add(pointerObject);
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        //dispose image of pointer from the screen
        pointerObject = new Pointer(screenX, screenY, false);
        touches.add(pointerObject);
        //stop aiming
        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        // Update pointer image while touch is dragged

        // Condition to the left mouse button dragged
        /*if (pointer == Input.Buttons.LEFT) {

            return true;
        } */

       pointerObject = new Pointer(screenX, screenY, true);
        touches.add(pointerObject);
        System.out.println(pointerObject.isPressed);

        //pointerObject.draw()
        //aiming
        return true;
    }


    @Override
    public boolean mouseMoved(int screenX, int screenY) {

        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        // TODO Auto-generated method stub
        return false;
    }
}
