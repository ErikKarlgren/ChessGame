package game.chess.chesspieces;

import game.chess.movement.Square;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Abstract class thar represents a game piece.
 */
public abstract class Piece {
    protected final PiecesName piecesName;
    protected boolean hasMoved;

    /**
     * Creates a {@link Piece} given a board, a starting position and a color.
     *
     * @param piecesName
     */
    protected Piece(PiecesName piecesName) {
        this.piecesName = piecesName;
        this.hasMoved = false;
    }

    /**
     * @return <code>true</code> if this {@link Piece} has already moved, <code>false</code>
     * otherwise.
     */
    public boolean hasMoved() {
        return hasMoved;
    }

    /**
     * Checks if this {@link Piece} is on a certain {@link Square}.
     *
     * @param square {@link Square} where we want to check if this piece is on.
     * @return <code>true</code> if this piece's {@link Square} is equal to sq, <code>false</code>
     * if they're not equal.
     */
    /*public final boolean checkSquare(@NotNull Square square) {
        return this.square.equals(square);
    }*/

    /**
     * @return Returns a list of all the available squares where this piece can move to.
     */
    public abstract List<Square> availableMoves();

    /**
     * Creates a copy of this {@link Piece}'s {@link Square} and returns it.
     *
     * @return A copy of this piece's square.
     */
    /*public Square getSquare() {
        return new Square(square);
    }*/

    /**
     * Moves this {@link Piece} to a specific {@link Square}. If this piece cannot move there,
     * this method raises an {@link IllegalArgumentException}.
     *
     * @param destiny {@link Square} where we want to move this piece.
     * @throws IllegalArgumentException Thrown if this piece can't move to <code>destiny</code>.
     */
    public void move(@NotNull Square destiny) {
        if (availableMoves().contains(destiny)) {
            this.square = destiny;
            this.hasMoved = true;
            if (enemyPieceIsOn(destiny)) {
                var p = board.findChessPiece(destiny).get();
                board.killPiece(p);
            }
        } else
            throw new IllegalArgumentException("Moving to 'destiny' is an illegal move");
    }

    /**
     * @return Color of this piece.
     */
    public final COLOR getColor() {
        return color;
    }

    /**
     * Checks if there's an enemy {@link Piece} on a certain {@link Square}.
     *
     * @param square {@link Square} where an enemy piece might be.
     * @return <code>true</code> if an enemy piece is on 'square', <code>false</code> otherwise.
     */
    public final boolean enemyPieceIsOn(@NotNull Square square) {
        boolean result;
        if (this.color == COLOR.WHITE) {
            result = this.board.isThereABlackPieceOn(square);
        } else {
            result = this.board.isThereAWhitePieceOn(square);
        }
        return result;
    }

    /**
     * Checks if there's an ally {@link Piece} on a certain {@link Square}.
     *
     * @param square {@link Square} where an ally piece might be.
     * @return <code>true</code> if an ally piece is on 'square', <code>false</code> otherwise.
     */
    public final boolean allyPieceIsOn(@NotNull Square square) {
        boolean result;
        if (this.color == COLOR.BLACK) {
            result = this.board.isThereABlackPieceOn(square);
        } else {
            result = this.board.isThereAWhitePieceOn(square);
        }
        return result;
    }

    // TODO: might be better to use a different representation
    @Override
    public String toString() {
        return this.color == COLOR.BLACK ? textRepresentation.toUpperCase() : textRepresentation.toLowerCase();
    }

    /**
     * Specifies the color of a {@link Piece}. It can be either black or white.
     */
    public enum COLOR {
        WHITE, BLACK, NONE
    }
}
