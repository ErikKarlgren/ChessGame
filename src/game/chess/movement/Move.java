package game.chess.movement;

import game.chess.Square;
import game.chess.chesspieces.Piece;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

/**
 * DTO containing all the data needed for a move. Instances of {@link Move} cannot be
 * created with an explicit call to its constructor in order to avoid creating {@link Move}
 * instances that represent illegal moves.
 */
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
@Accessors(fluent = true)
@Getter
public abstract class Move {
    private final @NonNull Piece piece;
    private final @NonNull Square destiny;
}
