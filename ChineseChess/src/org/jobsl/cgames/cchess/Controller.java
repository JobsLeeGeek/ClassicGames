package org.jobsl.cgames.cchess;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import org.jobsl.cgames.cchess.base.Constants;
import org.jobsl.cgames.cchess.chessboard.ChessBoard;
import org.jobsl.cgames.cchess.chessboard.ChessBoardCell;
import org.jobsl.cgames.cchess.chessmen.ChessColor;

/**
 * @author JobsLee
 */
public class Controller {
    @FXML
    private AnchorPane mainPane;
    @FXML
    private ImageView mainBoard;

    private ChessBoard chessBoard;
    private ChessBoardCell chooseCell;
    private ChessColor chessColorShould = ChessColor.RED;

    public void init() {
        // init chessBoard and chessmen data
        chessBoard = new ChessBoard();
        chessBoard.init();
        // init background display
        mainBoard.setImage(new Image(Constants.BOARD_BACKGROUND_IMAGE));
        // init chessmen display
        ChessBoardCell[][] boardCells = chessBoard.getBoardCells();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 9; j++) {
                ChessBoardCell cell = boardCells[j][i];
                cell.setOnAction(event -> {
                    if (cell.getChessman() != null && !chessColorShould.equals(cell.getChessman().getColor())) {
                        return;
                    }
                    // move or override
                    if (chooseCell != null) chooseCell.unchooseBackground();
                    if (chooseCell != null && chooseCell.getChessman().checkRule(chooseCell.getPoint(), cell.getPoint(), chessBoard)) {
                        cell.moveFrom(chooseCell);
                        // change chesscolor should
                        chessColorShould = chessColorShould.getOpposed(chessColorShould);
                    }
                    if (cell.getChessman() != null) {
                        cell.chooseBackground();
                        chooseCell = cell;
                    }
                });
                mainPane.getChildren().add(cell);
            }
        }
    }


}
