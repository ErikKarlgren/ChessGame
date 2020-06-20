package game.chess;

import game.chess.chesspieces.Piece;
import game.chess.movement.Move;
import game.chess.movement.Square;

import java.util.Iterator;

public interface Board {
    Square positionOf(Piece piece);

    Piece at(Square square);

    void make(Move move);

    Iterator<Square> squaresIterator();

    Iterator<Piece> piecesIterator();
}
