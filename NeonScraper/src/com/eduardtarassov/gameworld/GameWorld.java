package com.eduardtarassov.gameworld;

import com.eduardtarassov.gameobjects.EnemySpawner;
import com.eduardtarassov.gameobjects.EntityManager;
import com.eduardtarassov.gameobjects.PlayerShip;
import com.eduardtarassov.gameobjects.PlayerStatus;
import com.eduardtarassov.nshelpers.Constants;

/**
 * Created by Eduard on 3/10/2014.
 * This class is responsible for updating our game objects.
 */
public class GameWorld {

    private GameState currentState;
   /* private float runTime = 0;
    private int score = 0;  */
    private PlayerShip playerShip;


    enum GameState {
        MENU, READY, RUNNING, GAMEOVER, HIGHSCORE
    }

    public GameWorld() {
        //currentState = GameState.MENU;
        PlayerStatus.reset();
        currentState = GameState.RUNNING;

        playerShip = PlayerShip.getInstance();
    }

    public void update(float delta) {
        //runTime += delta;

        switch (currentState) {
            case READY:
            case MENU:
                updateReady(delta);
                break;

            case RUNNING:
                updateRunning(delta);
                break;
            default:
                break;
        }
    }

    private void updateReady(float delta) {

    }

    public void updateRunning(float delta) {
       /* if (delta > .15f)
            delta = .15f; */ // Maybe we will require this in the future. To set the same game speed working on all devices. Also see usage of delta in ZombieBird game.

        EntityManager.update();
        playerShip.update();
        EnemySpawner.update();
        PlayerStatus.update();
    }

   /* public void addScore(int increment) {
        score += increment;
    }   */

    public void start() {
        currentState = GameState.RUNNING;

    }

    public void ready() {
        currentState = GameState.READY;
    }

    public void restart() {
        currentState = GameState.READY;
       // score = 0;
    }

    public boolean isReady() {
        return currentState == GameState.READY;
    }

    public boolean isGameOver() {
        return currentState == GameState.GAMEOVER;
    }

    public boolean isHighScore() {
        return currentState == GameState.HIGHSCORE;
    }

    public boolean isMenu() {
        return currentState == GameState.MENU;
    }

    public boolean isRunning() {
        return currentState == GameState.RUNNING;
    }

    /*public PlayerShip getPlayerShip(){
        return playerShip;
    } */
}
