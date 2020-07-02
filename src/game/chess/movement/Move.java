package game.chess.movement;

import com.google.common.collect.BiMap;
import game.chess.Square;
import game.chess.chesspieces.Empty;
import game.chess.chesspieces.Piece;
import game.chess.chesspieces.PiecesName;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * Class that contains the data needed for moving a {@link Piece} on a {@link game.chess.Board} and
 * executes that move if given access to the internal structure of that same {@link game.chess.Board}
 * (see method {@link Move#execute(List, BiMap)}).
 * <p>
 * Instances of {@link Move} cannot be created with an explicit call to its constructor by default
 * in order to avoid creating {@link Move} instances that represent illegal moves.
 */
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
@Accessors(fluent = true)
@NonNull
public abstract class Move {
    private final Piece piece;
    private final Square destiny;

    /**
     * This method executes a move by modifying the state of a {@link game.chess.Board} internal
     * structure. This method is overridable.
     * <p>
     * The default implementation moves a {@link Move#piece} to its {@link Move#destiny}, and if
     * another piece is present on that destiny, this last piece is removed from the {@link List}
     * of {@link Piece}s given as a parameter.
     *
     * @param pieces           List of {@link Piece}s of a {@link game.chess.Board}.
     * @param squarePieceBiMap {@link BiMap} that maps each {@link Square} of a {@link game.chess.Board} to a
     *                         {@link Piece} (which could be an {@link game.chess.chesspieces.Empty}
     *                         piece).
     */
    public void execute(List<Piece> pieces, BiMap<Square, Piece> squarePieceBiMap) {
        var source = squarePieceBiMap.inverse().get(this.piece);
        var pieceOnDestiny = squarePieceBiMap.get(this.destiny);

        if (pieceOnDestiny.name() != PiecesName.Undefined) {
            pieces.remove(squarePieceBiMap.get(this.destiny));
        }
        squarePieceBiMap.put(source, new Empty());
        squarePieceBiMap.put(this.destiny, this.piece);
    }
}
