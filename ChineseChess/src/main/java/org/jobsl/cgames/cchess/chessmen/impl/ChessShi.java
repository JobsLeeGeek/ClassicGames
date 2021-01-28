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
public class ChessShi extends Chessman implements ChessRule {
    public ChessShi(ChessColor color) {
        super(color);
    }

    @Override
    protected void setNameAndColor(ChessColor color) {
        this.color = color;
        this.name = ChessColor.RED.equals(color) ? ChessName.SHI_RED : ChessName.SHI_BLACK;
    }

    @Override
    public boolean checkRule(Point currentP, Point nextP, ChessBoard cBoard) {
        boolean res = false;
        int xx = Math.abs(nextP.getX() - currentP.getX());
        int yy = Math.abs(nextP.getY() - currentP.getY());
        if (ChessColor.RED.equals(color)) {
            // (3,9) (3,7) (4,8) (5,9) (5,7)
            if (nextP.getX() == 3 && (nextP.getY() == 9 || nextP.getX() == 7) && xx == 1 && yy == 1) res = true;
            if (nextP.getX() == 4 && nextP.getY() == 8 && xx == 1 && yy == 1) res = true;
            if (nextP.getX() == 5 && (nextP.getY() == 9 || nextP.getX() == 7) && xx == 1 && yy == 1) res = true;
        }
        if (ChessColor.BLACK.equals(color)) {
            // (3,0) (3,2) (4,1) (5,0) (5,2)
            if (nextP.getX() == 3 && (nextP.getY() == 0 || nextP.getX() == 2) && xx == 1 && yy == 1) res = true;
            if (nextP.getX() == 4 && nextP.getY() == 1 && xx == 1 && yy == 1) res = true;
            if (nextP.getX() == 5 && (nextP.getY() == 0 || nextP.getX() == 2) && xx == 1 && yy == 1) res = true;
        }
        return res ? super.checkRule(currentP, nextP, cBoard) : res;
    }
}
