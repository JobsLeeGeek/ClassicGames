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
public class ChessBing extends Chessman implements ChessRule {
    public ChessBing(ChessColor color) {
        super(color);
    }

    @Override
    protected void setNameAndColor(ChessColor color) {
        this.color = color;
        this.name = ChessColor.RED.equals(color) ? ChessName.BING_RED : ChessName.ZU_BLACK;
    }

    @Override
    public boolean checkRule(Point lastP, Point nextP, ChessBoard cBoard) {
        if ((Math.abs(nextP.getX() - lastP.getX()) > 1) || (Math.abs(nextP.getY() - lastP.getY()) > 1)) return false;
        return super.checkRule(lastP, nextP, cBoard);
    }
}
