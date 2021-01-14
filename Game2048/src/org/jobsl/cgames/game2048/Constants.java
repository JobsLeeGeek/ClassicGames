package org.jobsl.cgames.game2048;


import javafx.scene.paint.Color;

/**
 * @author JobsLee
 */
public class Constants {
    enum State {
        start, won, running, over
    }

    public static final Color[] colorTable = {
            Color.rgb(112, 23, 16), Color.rgb(255, 228, 195), Color.rgb(255, 244, 211),
            Color.rgb(255, 218, 195), Color.rgb(231, 176, 142), Color.rgb(231, 191, 142),
            Color.rgb(255, 196, 195), Color.rgb(231, 148, 142), Color.rgb(190, 126, 86),
            Color.rgb(190, 94, 86), Color.rgb(156, 57, 49), Color.rgb(112, 23, 16)};

    public static final Color background = Color.rgb(250, 248, 239);
    public static final Color gridColor = Color.rgb(187, 173, 160);
    public static final Color emptyColor = Color.rgb(205, 193, 180);
    public static final Color startColor = Color.rgb(255, 235, 205);
}
