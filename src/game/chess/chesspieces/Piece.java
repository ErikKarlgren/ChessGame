package game.chess.chesspieces;

/**
 * Abstract class thar represents a game piece.
 */
public abstract class Piece {
    protected final PiecesName piecesName;
    protected boolean hasMoved;

    /**
     * Creates a {@link Piece} given a name. This name should be specified by subclasses, not by
     * the client, who should only specify its color.
     *
     * @param piecesName Name of this {@link Piece}.
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
     * Sets this {@link Piece}'s attribute {@link Piece#hasMoved} to <code>true</code>.
     */
    public void move() {
        this.hasMoved = true;
    }

    /**
     * @return Color of this piece.
     */
    public final COLOR getColor() {
        if (this.piecesName == PiecesName.Undefined)
            return COLOR.NONE;
        else
            return this.piecesName.isWhite() ? COLOR.WHITE : COLOR.BLACK;
    }

    public boolean canBeEatenEnPassant(){
        return false;
    }
    /**
     * Specifies the color of a {@link Piece}. It can be either black or white.
     */
    public enum COLOR {
        WHITE, BLACK, NONE
    }
}
