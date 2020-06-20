package game.chess.chesspieces;

import game.chess.movement.ChessMovementRules;
import game.chess.movement.Square;

import java.util.List;

/**
 * Class that represents a game rook.
 */
public class Rook extends Piece {
    /**
     * Creates a {@link Rook} given a board, a starting position and a color.
     *
     * @param isWhite
     */
    public Rook(boolean isWhite) {
        super(isWhite ? PiecesName.WhiteRook : PiecesName.BlackRook);
    }


    @Override
    public List<Square> availableMoves() {
        return ChessMovementRules.rooksMovement(this);
    }

}
