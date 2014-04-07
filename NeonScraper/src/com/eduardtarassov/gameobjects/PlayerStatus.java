package com.eduardtarassov.gameobjects;

/**
 * This class is responsible for tracking player score, high score and his lives.
 * Created by Eduard on 28/03/14.
 */
public class PlayerStatus {
   // Amount of time to expire the multiplier of score points.
    private static final float multiplierExpiryTime = 2000;
    private static final int maxMultiplier = 20;

    private static int lives;
    private static int score;
    private static int multiplier;

    // Time until the current multiplier expires
    private static float multiplierTimeLeft;
    private static long multiplierTimeStart = 0;
    private static float multiplierTimeElapsed = 0;
    // Score required to gain an extra life
    private static int scoreForExtraLife;

    public PlayerStatus(){
    }
    public static void multiplierTimeStart(){
        multiplierTimeStart = System.currentTimeMillis();
    }



    public static void reset(){
        score = 0;
        multiplier = 1;
        lives = 4;
        scoreForExtraLife = 2000;
        multiplierTimeLeft = 0;
    }

    public static void checkMultiplier(){
       if (multiplier > 1){
           // Updating timer of multiplier
           long currentTime = System.currentTimeMillis();
           long value = currentTime - multiplierTimeStart;

           System.out.println("This is your startTime: " + multiplierTimeStart);
           System.out.println("This is your currentTime: " + currentTime);
           System.out.println("This is your value: " + value);

           if (value >= multiplierTimeLeft){
              multiplierTimeLeft = multiplierExpiryTime;
               resetMultiplier();
           }

       }
       else{
           increaseMultiplier();
       }
    }

    public static void addPoints(int basePoints)
    {
        if (PlayerShip.instance.isDead())
            return;

        score += basePoints * multiplier;
        if (score >= scoreForExtraLife)
        {
            scoreForExtraLife += 2000;
            lives++;
        }
    }

    public static void increaseMultiplier()
    {
        if (PlayerShip.instance.isDead())
            return;

        multiplierTimeLeft = multiplierExpiryTime;
        if (multiplier < maxMultiplier)
            multiplier++;
    }

    public static void resetMultiplier()
    {
        multiplier = 1;
    }

    public static void removeLife()
    {
        lives--;
    }

    public static void setScore(int score) {
        PlayerStatus.score = score;
    }

    public static void setMultiplier(int multiplier) {
        PlayerStatus.multiplier = multiplier;
    }

    public void setLives(int lives){
        PlayerStatus.lives = lives;
    }

    public static int getScore() {
        return score;
    }
}
