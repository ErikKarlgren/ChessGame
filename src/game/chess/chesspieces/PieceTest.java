package game.chess.chesspieces;

import org.junit.jupiter.api.Test;

import java.util.HashSet;

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
    void piecesName() {
        var p = new TestablePiece(PiecesName.WhiteRook);
        assertSame(p.name(), PiecesName.WhiteRook);
        assertNotSame(p.name(), PiecesName.WhiteKing);
    }

    @Test
    void equalsTest(){
        var p1 = new Pawn(true);
        var p2 = new Pawn(true);
        assertNotEquals(p1, p2);
        assertNotEquals(p1.hashCode(), p2.hashCode());
        assertNotEquals(p1, "string test");
        assertNotEquals(null, p1);
        assertEquals(p2, p2);
        assertEquals(p2.hashCode(), p2.hashCode());
    }

    @Test
    void equalsTest2(){
        final int n = 100;
        var set = new HashSet<Piece>();

        for (int i = 0; i < n; i++) {
            set.add(new Pawn(true));
            set.add(new Pawn(false));
            set.add(new King(true));
            set.add(new King(false));
            set.add(new Rook(true));
            set.add(new Rook(false));
            set.add(new Knight(true));
            set.add(new Knight(false));
            set.add(new Queen(true));
            set.add(new Queen(false));
            set.add(new Bishop(true));
            set.add(new Bishop(false));
            set.add(new Empty());
        }
        assertEquals(n * 13, set.size());
    }
}