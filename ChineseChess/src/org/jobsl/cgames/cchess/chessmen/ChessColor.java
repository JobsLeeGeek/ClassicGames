package org.jobsl.cgames.cchess.chessmen;

/**
 * @author JobsLee
 */
public enum ChessColor {
    RED(), BLACK();

    public ChessColor getOpposed(ChessColor color) {
        return color.equals(RED) ? BLACK : RED;
    }
}
