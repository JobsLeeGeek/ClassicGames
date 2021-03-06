package org.jobsl.cgames.cchess.chessmen.impl;

import org.jobsl.cgames.cchess.base.Point;
import org.jobsl.cgames.cchess.chessboard.ChessBoard;
import org.jobsl.cgames.cchess.chessmen.ChessColor;
import org.jobsl.cgames.cchess.chessmen.ChessName;
import org.jobsl.cgames.cchess.chessmen.ChessRule;
import org.jobsl.cgames.cchess.chessmen.Chessman;

/**
 * @author JobsLee
 */
public class ChessShuai extends Chessman implements ChessRule {
    public ChessShuai(ChessColor color) {
        super(color);
    }

    @Override
    protected void setNameAndColor(ChessColor color) {
        this.color = color;
        this.name = ChessColor.RED.equals(color) ? ChessName.SHUAI_RED : ChessName.JIANG_BLACK;
    }

    @Override
    public boolean checkRule(Point currentP, Point nextP, ChessBoard cBoard) {
        boolean res = false;
        int xx = Math.abs(nextP.getX() - currentP.getX());
        int yy = Math.abs(nextP.getY() - currentP.getY());
        if (!((xx == 1 && yy == 0) || (xx == 0 && yy == 1))) return res;
        // x 3~5 y 7~9
        if (ChessColor.RED.equals(color)) {
            if (nextP.getX() >= 3 && nextP.getX() <= 5 && nextP.getY() >= 7 && nextP.getY() <= 9) res = true;
        }
        // x 3~5 y 0~2
        if (ChessColor.BLACK.equals(color)) {
            if (nextP.getX() >= 3 && nextP.getX() <= 5 && nextP.getY() >= 0 && nextP.getY() <= 2) res = true;
        }
        return res ? super.checkRule(currentP, nextP, cBoard) : res;
    }
}
