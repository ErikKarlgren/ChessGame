package game.chess.chesspieces;

/**
 * Class that represents a game knight.
 */
public final class Knight extends Piece {
    /**
     * Creates a {@link Knight} given a board, a starting position and a color.
     *
     * @param isWhite Specifies the color of thi {@link Piece}.
     */
    public Knight(boolean isWhite) {
        super(isWhite ? PiecesName.WhiteKnight : PiecesName.BlackKnight);
    }

}
