package game.chess.chesspieces;

import game.chess.movement.ChessMovementRules;
import game.chess.movement.Square;

import java.util.List;

/**
 * Class that represents a game queen.
 */
public class Queen extends Piece {

    /**
     * Creates a {@link Queen} given a board, a starting position and a color.
     *
     * @param isWhite
     */
    public Queen(boolean isWhite) {
        super(isWhite ? PiecesName.WhiteQueen : PiecesName.BlackQueen);
    }

    @Override
    public List<Square> availableMoves() {
        return ChessMovementRules.queensMovement(this);
    }

}
