package org.jobsl.cgames.cchess.chessmen;

import org.jobsl.cgames.cchess.base.Point;
import org.jobsl.cgames.cchess.chessboard.ChessBoard;

/**
 * @author JobsLee
 */
public interface ChessRule {
    boolean checkRule(Point currentP, Point nextP, ChessBoard cBoard);
}
