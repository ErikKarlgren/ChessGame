package game.chess;

import game.chess.chesspieces.Piece;
import game.chess.movement.Move;
import lombok.NonNull;

import java.util.Iterator;
import java.util.Optional;

public interface Board {
    @NonNull Square positionOf(@NonNull Piece piece);

    @NonNull Optional<Piece> at(@NonNull Square square);

    void make(@NonNull Move move);

    @NonNull Iterator<Square> squaresIterator();

    @NonNull Iterator<Piece> piecesIterator();
}
