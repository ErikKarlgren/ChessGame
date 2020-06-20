package game.chess.chesspieces;

/**
 * Class that represents a game king.
 */
public final class King extends Piece {
    /**
     * Creates a {@link King} given a board, a starting position and a color.
     *
     * @param isWhite Specifies the color of thi {@link Piece}.
     */
    public King(boolean isWhite) {
        super(isWhite ? PiecesName.WhiteKing : PiecesName.BlackKing);
    }


}
