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
        this.name = ChessName.JU;
    }

    @Override
    public boolean checkRule(Point currentP, Point nextP, ChessBoard cBoard) {
        if (nextP.getX() != currentP.getX() && nextP.getY() != currentP.getY()) return false;
        return super.checkRule(currentP, nextP, cBoard);
    }
}
