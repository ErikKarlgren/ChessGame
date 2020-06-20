package game.chess.chesspieces;

import game.chess.ChessBoard;
import game.chess.movement.ChessMovementRules;
import game.chess.movement.Square;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Class that represents a game pawn.
 */
public class Pawn extends Piece {
    /**
     * Creates a {@link Pawn} given a board, a starting position and a color.
     *
     * @param isWhite
     */
    public Pawn(boolean isWhite) {
        super(isWhite? PiecesName.WhitePawn : PiecesName.BlackPawn);
    }

    @Override
    public void move(@NotNull Square destiny) {
        super.move(destiny);
        if (square.getRow() == ChessBoard.LAST_ROW)
            this.promote();
    }

    /**
     * TODO: 'Promotion' not supported yet.
     */
    private void promote() {
        throw new UnsupportedOperationException("Pawn's promotion not supported yet");
    }


    @Override
    public List<Square> availableMoves() {
        return ChessMovementRules.pawnsMovement(board,this);
    }

}
