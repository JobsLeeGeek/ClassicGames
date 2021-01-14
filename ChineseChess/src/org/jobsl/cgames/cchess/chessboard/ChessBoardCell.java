package org.jobsl.cgames.cchess.chessboard;

import org.jobsl.cgames.cchess.chessmen.Chessman;

/**
 * @author JobsLee
 */
public class ChessBoardCell {
    private Chessman chessman;

    public Chessman getChessman() {
        return chessman;
    }

    public ChessBoardCell setChessman(Chessman chessman) {
        this.chessman = chessman;
        return this;
    }
}
