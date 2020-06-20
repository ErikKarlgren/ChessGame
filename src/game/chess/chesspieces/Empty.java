package game.chess.chesspieces;

import game.chess.movement.Square;

import java.util.LinkedList;
import java.util.List;

public class Empty extends Piece {

    /**
     * Creates a {@link Empty} piece. It represents a lack of a {@link Piece} on a {@link Square}.
     */
    public Empty() {
        super(PiecesName.Undefined);
    }

    @Override
    public List<Square> availableMoves() {
        return new LinkedList<>();
    }
}
