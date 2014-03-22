package com.eduardtarassov.nshelpers;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by Eduard on 21/03/14.
 */
public class MathUtil {
    public MathUtil(){}

    public static Vector2 FromPolar(float angle, float magnitude)
    {
        return new Vector2((float)Math.cos(angle), (float)Math.sin(angle)).scl(magnitude); // Not sure that it really multiplies it
    }
}
