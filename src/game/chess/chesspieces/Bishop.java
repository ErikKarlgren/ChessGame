package game.chess.chesspieces;

/**
 * Class that represents a game bishop.
 */
public final class Bishop extends Piece {
    /**
     * Creates a {@link Bishop} given a board, a starting position and a color.
     *
     * @param isWhite Specifies the color of thi {@link Piece}.
     */
    public Bishop(boolean isWhite) {
        super(isWhite ? PiecesName.WhiteBishop : PiecesName.BlackBishop);
    }

}
