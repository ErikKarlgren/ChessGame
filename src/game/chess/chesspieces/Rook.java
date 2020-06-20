package game.chess.chesspieces;

/**
 * Class that represents a game rook.
 */
public final class Rook extends Piece {
    /**
     * Creates a {@link Rook} given a board, a starting position and a color.
     *
     * @param isWhite Specifies the color of thi {@link Piece}.
     */
    public Rook(boolean isWhite) {
        super(isWhite ? PiecesName.WhiteRook : PiecesName.BlackRook);
    }


}
