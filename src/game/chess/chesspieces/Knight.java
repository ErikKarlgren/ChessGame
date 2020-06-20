package game.chess.chesspieces;

import game.chess.movement.ChessMovementRules;
import game.chess.movement.Square;

import java.util.List;

/**
 * Class that represents a game knight.
 */
public class Knight extends Piece {
    /**
     * Creates a {@link Knight} given a board, a starting position and a color.
     *
     * @param isWhite
     */
    public Knight(boolean isWhite) {
        super(isWhite ? PiecesName.WhiteKnight : PiecesName.BlackKnight);
    }

    @Override
    public List<Square> availableMoves() {
        return ChessMovementRules.knightsMovement(this);
    }

}
