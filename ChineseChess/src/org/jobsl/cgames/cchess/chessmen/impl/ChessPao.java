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
public class ChessPao extends Chessman implements ChessRule {
    public ChessPao(ChessColor color) {
        super(color);
    }

    @Override
    protected void setNameAndColor(ChessColor color) {
        this.color = color;
        this.name = ChessName.PAO;
    }

    @Override
    public boolean checkRule(Point currentP, Point nextP, ChessBoard cBoard) {
        if (nextP.getX() != currentP.getX() && nextP.getY() != currentP.getY()) return false;
        if (cBoard.checkCellNotEmptyNum(currentP, nextP) > 1) return false;
        if (cBoard.checkCellNotEmptyNum(currentP, nextP) == 1 && cBoard.checkCellEmpty(nextP, color.getOpposed(color))) return false;
        return super.checkRule(currentP, nextP, cBoard);
    }
}
