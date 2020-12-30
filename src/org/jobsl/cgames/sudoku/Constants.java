package org.jobsl.cgames.sudoku;

import javafx.scene.paint.Color;

/**
 * @author JobsLee
 */
public class Constants {
    enum State {
        start, won, running, over
    }

    enum Level {
        LOW(1, 20), MID(2, 40), HIGH(3, 60);

        int level;
        int empty;

        Level(int level, int empty) {
            this.level = level;
            this.empty = empty;
        }
    }

    public static final Color background = Color.rgb(250, 248, 239);
    public static final Color emptyColor = Color.rgb(205, 193, 180);
    public static final Color textColor = Color.rgb(0, 0, 0);
    public static final Color tipColor = Color.rgb(190, 94, 86);

    public static final String buttonDefaultHex = "#E6E6FA";
    public static final String buttonRedHex = "#FF0000";
    public static final String buttonEmptyHex = "#E6E6FA";
    public static final String buttonNotEmptyHex = "#FAF8EF";
    public static final String buttonChooseHex = "#FFC0CB";
}
