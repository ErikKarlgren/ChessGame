package game.chess.chesspieces;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PieceTest {

    private static class TestablePiece extends Piece {

        /**
         * Creates a {@link Piece} given a name. This name should be specified by subclasses, not by
         * the client, who should only specify its color. However, since {@link TestablePiece} is
         * for test purposes, it doesn't really matter.
         *
         * @param piecesName Name of this {@link Piece}.
         */
        protected TestablePiece(PiecesName piecesName) {
            super(piecesName);
        }
    }

    @Test
    void move() {
        var p = new TestablePiece(PiecesName.BlackRook);
        assertFalse(p.hasMoved());
        p.move();
        assertTrue(p.hasMoved());
        p.move();
        assertTrue(p.hasMoved());
    }

    @Test
    void getColor() {
        var p = new TestablePiece(PiecesName.BlackRook);
        assertSame(p.getColor(), Piece.COLOR.BLACK);
        assertNotSame(p.getColor(), Piece.COLOR.WHITE);
        assertNotSame(p.getColor(), Piece.COLOR.NONE);

        var q = new TestablePiece(PiecesName.WhiteRook);
        assertNotSame(q.getColor(), Piece.COLOR.BLACK);
        assertSame(q.getColor(), Piece.COLOR.WHITE);
        assertNotSame(q.getColor(), Piece.COLOR.NONE);

        var r = new TestablePiece(PiecesName.Undefined);
        assertNotSame(r.getColor(), Piece.COLOR.BLACK);
        assertNotSame(r.getColor(), Piece.COLOR.WHITE);
        assertSame(r.getColor(), Piece.COLOR.NONE);
    }

    @Test
    void canBeEatenEnPassant() {
        var p = new TestablePiece(PiecesName.WhiteRook);
        assertFalse(p.canBeEatenEnPassant());
    }

    @Test
    void piecesName() {
        var p = new TestablePiece(PiecesName.WhiteRook);
        assertSame(p.name(), PiecesName.WhiteRook);
        assertNotSame(p.name(), PiecesName.WhiteKing);
    }

}