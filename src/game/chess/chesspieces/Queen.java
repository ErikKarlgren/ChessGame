package game.chess.chesspieces;

/**
 * Class that represents a game queen.
 */
public final class Queen extends Piece {

    /**
     * Creates a {@link Queen} given a board, a starting position and a color.
     *
     * @param isWhite Specifies the color of thi {@link Piece}.
     */
    public Queen(boolean isWhite) {
        super(isWhite ? PiecesName.WhiteQueen : PiecesName.BlackQueen);
    }

}
