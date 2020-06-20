package game.chess.chesspieces;

import game.chess.movement.Square;

public final class Empty extends Piece {

    /**
     * Creates a {@link Empty} piece. It represents a lack of a {@link Piece} on a {@link Square}.
     */
    public Empty() {
        super(PiecesName.Undefined);
    }

}
