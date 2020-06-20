package game.chess;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import game.chess.chesspieces.*;
import game.chess.movement.Move;
import game.chess.movement.Square;

import java.util.*;

public final class ChessBoard implements Board {
    public static final int FIRST_ROW = 1;
    public static final int LAST_ROW = 8;
    public static final int FIRST_COLUMN = 1;
    public static final int LAST_COLUMN = 8;
    private static final int SIZE = 64;

    private final List<Square> squares;
    private final List<Piece> pieces;
    private final BiMap<Square, Piece> squarePieceBiMap;

    public ChessBoard() {
        squares = Arrays.asList(new Square[SIZE]);
        pieces = new LinkedList<>();
        squarePieceBiMap = HashBiMap.create(SIZE);
        initSquares();
        initPieces();
    }

    private static boolean legalRow(int row) {
        return row >= 1 && row <= ChessBoard.LAST_ROW;
    }

    private static boolean legalColumn(int column) {
        return column >= 1 && column <= ChessBoard.LAST_COLUMN;
    }

    /**
     * Specifies whether the given <code>row</code> and <code>column</code> are legal coordinates (so
     * a {@link Square} could be created with those) or not.
     *
     * @param row    Row on the board.
     * @param column Column on the board.
     * @return <code>true</code> if the coordinates are legal, <code>false</code> otherwise.
     */
    public static boolean illegalCoordinates(int row, int column) {
        return !legalRow(row) || !legalColumn(column);
    }

    private Square at(int row, int col) {
        if (illegalCoordinates(row, col))
            throw new IllegalArgumentException("Illegal coordinates");
        return squares.get(8 * (row - 1) + (col - 1));
    }

    private void initSquares() {
        for (int col = FIRST_COLUMN; col <= LAST_COLUMN; col++) {
            for (int row = FIRST_ROW; row <= LAST_ROW; row++) {
                var sq = new Square(row, col);
                squares.add(sq);
                squarePieceBiMap.put(sq, new Empty());
            }
        }
    }

    /**
     * Auxiliary method to add a {@link Piece} to the board.
     *
     * @param piece {@link Piece} to be added.
     * @param row   Row where <code>piece</code> will be placed.
     * @param col   Column where <code>piece</code> will be placed.
     */
    private void addPiece(Piece piece, int row, int col) {
        pieces.add(piece);
        squarePieceBiMap.put(at(row, col), piece);
    }

    /**
     * Creates a {@link Piece} array containing a row of pieces full of {@link Pawn}s.
     *
     * @param areWhite Specifies whether the {@link Piece}s are white (<code>true</code>)
     *                 or not (<code>false</code>).
     * @return An array full of {@link Pawn}s.
     */
    private Piece[] createPawns(boolean areWhite) {
        var pawns = new Pawn[8];
        Arrays.fill(pawns, new Pawn(areWhite));
        return pawns;
    }

    /**
     * Creates a {@link Piece} array containing the row of pieces that aren't
     * {@link Pawn}s.
     *
     * @param areWhite Specifies whether the {@link Piece}s are white (<code>true</code>)
     *                 or not (<code>false</code>).
     * @return An array with all {@link Piece}s that aren't {@link Pawn}s.
     */
    private Piece[] createNotPawns(boolean areWhite) {
        return new Piece[]{
                new Rook(areWhite),
                new Knight(areWhite),
                new Bishop(areWhite),
                new Queen(areWhite),
                new King(areWhite),
                new Bishop(areWhite),
                new Knight(areWhite),
                new Rook(areWhite)
        };
    }

    private void addArrayOfPieces(Piece[] pieces, int row) {
        Arrays.stream(pieces).forEach(p -> addPiece(p, row, Arrays.binarySearch(pieces, p)));
    }

    private void initPieces() {
        var whitePawns = createPawns(true);
        addArrayOfPieces(whitePawns, FIRST_ROW);
        var blackPawns = createPawns(false);
        addArrayOfPieces(blackPawns, LAST_ROW);
        var whitePieces = createNotPawns(true);
        addArrayOfPieces(whitePieces, FIRST_ROW + 1);
        var blackPieces = createNotPawns(false);
        addArrayOfPieces(blackPieces, LAST_ROW - 1);
    }

    @Override
    public Square positionOf(Piece piece) {
        return squarePieceBiMap.inverse().get(piece);
    }

    @Override
    public Piece at(Square square) {
        return squarePieceBiMap.get(square);
    }

    @Override
    public void make(Move move) {
        // TODO: I don't know, man
    }

    @Override
    public Iterator<Square> squaresIterator() {
        return new SquaresIterator();
    }

    @Override
    public Iterator<Piece> piecesIterator() {
        return new PiecesIterator();
    }

    /**
     * {@link Iterator} that wraps this board {@link ChessBoard#pieces} list's
     * iterator so removing a {@link Piece} also eliminates it from the {@link BiMap}.
     */
    private class PiecesIterator implements Iterator<Piece> {
        private final Iterator<Piece> iterator;
        private Piece piece;

        private PiecesIterator() {
            iterator = pieces.iterator();
        }

        @Override
        public boolean hasNext() {
            return iterator.hasNext();
        }

        @Override
        public Piece next() {
            if (!iterator.hasNext())
                throw new NoSuchElementException("There are no more pieces in the list");
            piece = iterator.next();
            return piece;
        }

        @Override
        public void remove() {
            Square square = squarePieceBiMap.inverse().get(piece);
            iterator.remove();
            squarePieceBiMap.put(square, new Empty());
        }
    }

    /**
     * {@link Iterator} that wraps this board {@link ChessBoard#squares} list's
     * iterator so no {@link Square} can be removed.
     */
    private class SquaresIterator implements Iterator<Square> {
        private final Iterator<Square> iterator;

        private SquaresIterator() {
            iterator = squares.iterator();
        }

        @Override
        public boolean hasNext() {
            return iterator.hasNext();
        }

        @Override
        public Square next() {
            return iterator.next();
        }

        /**
         * @throws UnsupportedOperationException This operation is not supported so no squares can
         *                                       be removed from the board.
         */
        @Override
        public void remove() {
            throw new UnsupportedOperationException("Cannot remove squares from the board");
        }

    }

}
