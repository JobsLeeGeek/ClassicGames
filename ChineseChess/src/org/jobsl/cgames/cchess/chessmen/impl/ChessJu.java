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
public class ChessJu extends Chessman implements ChessRule {
    public ChessJu(ChessColor color) {
        super(color);
    }

    @Override
    protected void setNameAndColor(ChessColor color) {
        this.color = color;
        this.name = ChessName.JU.getName();
    }

    @Override
    public boolean checkRule(Point nextP, ChessBoard cBoard) {
        if (nextP.getX() != this.point.getX() && nextP.getY() != this.point.getY()) return false;
        return super.checkRule(nextP, cBoard);
    }
}
