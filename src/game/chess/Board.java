package game.chess;

import game.chess.chesspieces.Piece;
import game.chess.movement.Move;

import java.util.Iterator;
import java.util.Optional;

public interface Board {
    Square positionOf(Piece piece);

    Optional<Piece> at(Square square);

    void make(Move move);

    Iterator<Square> squaresIterator();

    Iterator<Piece> piecesIterator();
}
