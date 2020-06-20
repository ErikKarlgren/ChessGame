package game.chess.chesspieces;

import game.chess.movement.ChessMovementRules;
import game.chess.movement.Square;

import java.util.List;

/**
 * Class that represents a game bishop.
 */
public class Bishop extends Piece {
    /**
     * Creates a {@link Bishop} given a board, a starting position and a color.
     *
     * @param isWhite
     */
    public Bishop(boolean isWhite) {
        super(isWhite ? PiecesName.WhiteBishop : PiecesName.BlackBishop);
    }

    @Override
    public List<Square> availableMoves() {
       return ChessMovementRules.bishopsMovement(this);
    }

}
