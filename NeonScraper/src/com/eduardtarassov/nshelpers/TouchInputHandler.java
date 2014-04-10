package com.eduardtarassov.nshelpers;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.eduardtarassov.gameobjects.PlayerShip;
import com.eduardtarassov.gameworld.GameRenderer;
import com.eduardtarassov.ui.SimpleButton;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Eduard on 3/13/14.
 */
public class TouchInputHandler implements InputProcessor {
    //private SpriteBatch spriteBatch;
     private PlayerShip playerShip;
    private static Vector3 aimDirection;
    public static boolean isAiming = false;


    private List<SimpleButton> menuButtons;
    private SimpleButton playButton;

    public TouchInputHandler(){
        playerShip = PlayerShip.getInstance();

        menuButtons = new ArrayList<SimpleButton>();
        playButton = new SimpleButton(
                136 / 2 - (AssetLoader.playButtonUp.getRegionWidth() / 2),
                Constants.MIDPOINTY + 50, 29, 16, AssetLoader.playButtonUp,
                AssetLoader.playButtonDown);
        menuButtons.add(playButton);
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
        // playerShip.bulletIsActive = true;
       /* System.out.println(screenX + "     " + screenY);    //For some reasons this gives completely absurd numbers for the positions on the screen.
          aimDirection = new Vector2(screenX, screenY);
        playerShip.bulletIsActive = true; */
 /*       isAiming = true;
        //playerShip.bulletIsActive = true;



        aimDirection = new Vector3(screenX, screenY, 0);
        GameRenderer.camera.unproject(aimDirection);   */
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        //bshow pointer image on the current position
        // playerShip.bulletIsActive = true;
       /* System.out.println(screenX + "     " + screenY);    //For some reasons this gives completely absurd numbers for the positions on the screen.
          aimDirection = new Vector2(screenX, screenY);
        playerShip.bulletIsActive = true; */
       /* isAiming = true;
        //playerShip.bulletIsActive = true;



        aimDirection = new Vector3(screenX, screenY, 0);
        GameRenderer.camera.unproject(aimDirection);  */
        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        //bshow pointer image on the current position
        // playerShip.bulletIsActive = true;
       /* System.out.println(screenX + "     " + screenY);    //For some reasons this gives completely absurd numbers for the positions on the screen.
          aimDirection = new Vector2(screenX, screenY);
        playerShip.bulletIsActive = true; */
       /* isAiming = true;
        //playerShip.bulletIsActive = true;



        aimDirection = new Vector3(screenX, screenY, 0);
        GameRenderer.camera.unproject(aimDirection);  */
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

    public List<SimpleButton> getMenuButtons() {
        return menuButtons;
    }
}
