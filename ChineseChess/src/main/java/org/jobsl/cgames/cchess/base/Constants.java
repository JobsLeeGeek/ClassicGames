package org.jobsl.cgames.cchess.base;

/**
 * @author JobsLee
 */
public class Constants {
    public static final String RESOURCE_PATH = Constants.class.getResource("/").toString();
    public static final String RESOURCE_BOARD_PATH = RESOURCE_PATH + "image/";
    public static final String RESOURCE_CHESS_PATH = RESOURCE_PATH + "image/chess/";

    public static final int BOARD_BACKGROUND_WIDTH = 521;
    public static final int BOARD_BACKGROUND_HEIGHT = 577;
    public static final String BOARD_BACKGROUND_IMAGE = RESOURCE_BOARD_PATH + "BOARD.JPG";

    public static final int CHESSMAN_WIDTH = 57;
    public static final int CHESSMAN_HEIGHT = 57;
    public static final String CHESSMAN_BLANK_IMAGE = RESOURCE_CHESS_PATH + "BLANK";
    public static final String CHESSMAN_IMAGE_FRONT_RED = "R_";
    public static final String CHESSMAN_IMAGE_FRONT_BALCK = "B_";
    public static final String CHESSMAN_CHOOSE_IMAGE_BACK = "_C.GIF";
    public static final String CHESSMAN_UNCHOOSE_IMAGE_BACK = ".GIF";
}
