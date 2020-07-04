package game.chess.chesspieces;

import lombok.Getter;
import lombok.experimental.Accessors;

/**
 * Abstract class thar represents a game piece.
 */
@Accessors(fluent = true)
public abstract class Piece {
    /**
     * Static attribute to generate unique ids for each created {@link Piece}.
     * Each {@link Piece}'s id is used as its hash code.
     */
    private static int nextID;

    @Getter
    private final PiecesName name;

    /**
     * Each {@link Piece}'s id is used as its hash code.
     */
    private final int id;

    @Getter
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
        this.id = nextID;
        nextID++;
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

    @Override
    public boolean equals(Object obj) {
        boolean ok = super.equals(obj);
        if (ok) {
            var p = (Piece) obj;
            return this.id == p.id;
        } else
            return false;
    }

    @Override
    public int hashCode() {
        return this.id;
    }

    /**
     * Specifies the color of a {@link Piece}. It can be either black or white.
     */
    public enum COLOR {
        WHITE, BLACK, NONE
    }
}
