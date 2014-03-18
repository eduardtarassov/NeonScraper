package com.eduardtarassov.nshelpers;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;
import com.eduardtarassov.gameobjects.PlayerShip;

import java.util.ArrayList;

/**
 * Created by Eduard on 3/13/14.
 */
public class TouchInputHandler implements InputProcessor {
    //private SpriteBatch spriteBatch;
     private PlayerShip playerShip;
    private static Vector2 aimDirection;
    public TouchInputHandler(){
        playerShip = PlayerShip.getInstance();
    }
                                         //
    @Override
    public boolean keyDown(int keycode) {
        // TODO Auto-generated method stub
        switch(keycode){
            case Keys.A:
                // Ship moves left
                playerShip.keysMove(-1, 0);
                System.out.println("Ship moves left");
                break;
            case Keys.W:
                // Ship moves up
                playerShip.keysMove(0, -1);
                System.out.println("Ship moves up");
                break;
            case Keys.D:
                // Ship moves right
                playerShip.keysMove(1, 0);
                System.out.println("Ship moves right");
                break;
            case Keys.S:
                // Ship moves down
                playerShip.keysMove(0, 1);
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
        playerShip.bulletIsActive = true;
        System.out.println(screenX + "     " + screenY);    //For some reasons this gives completely absurd numbers for the positions on the screen.
          aimDirection = new Vector2(playerShip.getPosition()).sub(screenX, screenY);

        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        //dispose image of pointer from the screen
        /*pointerObject = new Pointer(screenX, screenY, false);
        touches.add(pointerObject);                             //do we really need to add objects into touches or we can have only one object*/

        //stop aiming
        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        // Update pointer image while touch is dragged
        playerShip.bulletIsActive = true;
        // Condition to the left mouse button dragged
        /*if (pointer == Input.Buttons.LEFT) {

            return true;
        } */
        System.out.println(screenX + "     " + screenY);    //For some reasons this gives completely absurd numbers for the positions on the screen.
        aimDirection = new Vector2(playerShip.getPosition()).sub(screenX, screenY);
        //System.out.println(aimDirection.x + "     " + aimDirection.y);


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

    public static Vector2 getAimDirection(){
        return aimDirection;
    }
}
