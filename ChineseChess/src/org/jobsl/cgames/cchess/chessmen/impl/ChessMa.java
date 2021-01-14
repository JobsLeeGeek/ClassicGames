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
public class ChessMa extends Chessman implements ChessRule {
    public ChessMa(ChessColor color) {
        super(color);
    }

    @Override
    protected void setNameAndColor(ChessColor color) {
        this.color = color;
        this.name = ChessName.MA.getName();
    }

    @Override
    public boolean checkRule(Point nextP, ChessBoard cBoard) {
        boolean res = false;
        if (Math.abs(nextP.getX() - this.point.getX()) == 1) {
            if (Math.abs(nextP.getY() - this.point.getY()) == 2) res = true;
        }
        if (Math.abs(nextP.getY() - this.point.getY()) == 1) {
            if (Math.abs(nextP.getX() - this.point.getX()) == 2) res = true;
        }
        return res ? super.checkRule(nextP, cBoard) : res;
    }
}
