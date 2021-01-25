package org.jobsl.cgames.cchess;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import org.jobsl.cgames.cchess.base.Constants;
import org.jobsl.cgames.cchess.chessboard.ChessBoard;
import org.jobsl.cgames.cchess.chessboard.ChessBoardCell;
import org.jobsl.cgames.cchess.chessmen.ChessColor;
import org.jobsl.cgames.cchess.chessmen.Chessman;

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
                cell.reDraw();
                cell.setOnAction(event -> {
                    // red first and black second ... one by one
//                    if (cell.getChessman() != null && !chessColorShould.equals(cell.getChessman().getColor())) return;
                    // choose new one
                    if (chooseCell == null) {
                        cell.setChoose(true);
                        cell.reDraw();
                        chooseCell = cell;
                    }
                    // choose the same one
                    if (chooseCell == cell) return;
                    // choose or move or override
                    Chessman lastC = chooseCell.getChessman();
                    Chessman nextC = cell.getChessman();
                    if (lastC == null) return;
                    if (lastC != null) {
                        if (nextC != null) {
                            if (lastC.getColor().equals(nextC.getColor())) {
                                // the same color choose new one
                                cell.setChoose(true);
                                cell.reDraw();
                                chooseCell.setChoose(false);
                                chooseCell.reDraw();
                                chooseCell = cell;
                                return;
                            }
                            // override
//                            move(cell);
                        }
                        /*if (nextC == null) {
                            // move
//                            move(cell);
                        }*/
                        move(cell);
                    }
                });
                mainPane.getChildren().add(cell);
            }
        }
    }

    private void move(ChessBoardCell cell) {
        if (chooseCell.getChessman().checkRule(chooseCell.getPoint(), cell.getPoint(), chessBoard)) {
            cell.setChessman(chooseCell.getChessman());
            cell.setChoose(true);
            cell.reDraw();
            chooseCell.setChoose(false);
            chooseCell.setChessman(null);
            chooseCell.reDraw();
            chooseCell = cell;
        }
    }
}
