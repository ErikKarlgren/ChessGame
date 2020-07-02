package game.chess.chesspieces;

/**
 * Class that represents a game pawn.
 */
public final class Pawn extends Piece {
    /**
     * Creates a {@link Pawn} given a board, a starting position and a color.
     *
     * @param isWhite @param isWhite Specifies the color of thi {@link Piece}.
     */
    public Pawn(boolean isWhite) {
        super(isWhite ? PiecesName.WhitePawn : PiecesName.BlackPawn);
    }
    
}
