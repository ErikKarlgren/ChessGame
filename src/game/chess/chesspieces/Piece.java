package game.chess.chesspieces;

import lombok.Getter;
import lombok.experimental.Accessors;

/**
 * Abstract class thar represents a game piece.
 */
@Accessors(fluent = true)
@Getter
public abstract class Piece {
    private final PiecesName name;
    private boolean hasMoved;

    /**
     * Creates a {@link Piece} given a name. This name should be specified by subclasses, not by
     * the client, who should only specify its color.
     *
     * @param piecesName Name of this {@link Piece}.
     */
    protected Piece(PiecesName piecesName) {
        this.name = piecesName;
        this.hasMoved = false;
    }

    /**
     * Sets this {@link Piece}'s attribute {@link Piece#hasMoved} to <code>true</code>.
     */
    public void move() {
        this.hasMoved = true;
    }

    /**
     * @return Color of this piece.
     */
    public final COLOR getColor() {
        if (this.name == PiecesName.Undefined)
            return COLOR.NONE;
        else
            return this.name.isWhite() ? COLOR.WHITE : COLOR.BLACK;
    }

    public boolean canBeEatenEnPassant() {
        return false;
    }

    /**
     * Specifies the color of a {@link Piece}. It can be either black or white.
     */
    public enum COLOR {
        WHITE, BLACK, NONE
    }
}
