package game.chess.chesspieces;

import game.chess.movement.ChessMovementRules;
import game.chess.movement.Square;

import java.util.List;

/**
 * Class that represents a game king.
 */
public class King extends Piece {
    /**
     * Creates a {@link King} given a board, a starting position and a color.
     *
     * @param isWhite
     */
    public King(boolean isWhite) {
        super(isWhite ? PiecesName.WhiteKing : PiecesName.BlackKing);
    }


    @Override
    public List<Square> availableMoves() {
        return ChessMovementRules.kingsMovement(board,this);
    }

}
