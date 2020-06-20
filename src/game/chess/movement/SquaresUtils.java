package game.chess.movement;

import game.chess.chesspieces.Piece;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

class SquaresUtils {
    /**
     * Private constructor. This class has only static methods.
     */
    private SquaresUtils() {
    }

    /**
     * Uses the function {@link SquaresUtils#createSquare(Square, int, int)} to create a
     * {@link List} containing all the available moves of <code>piece</code> if it moved
     * in the direction specified by <code>rowsDirection</code> and <code>columnsDirection</code>.
     * Those last two parameters work in the same way as they do in {@link SquaresUtils#createSquare(Square, int, int)},
     * which is explained below.
     *
     * @param piece            {@link Piece} from whose {@link Square} we create the {@link List}.
     * @param rowsDirection    Number of rows from the caller to the new {@link Square}. A positive
     *                         amount means how many rows we have to go up, whereas a negative amount
     *                         is the number of rows we have to go down.
     * @param columnsDirection Number of columns from the caller to the new {@link Square}. A positive
     *                         amount means how many columns we have to go right, whereas a negative
     *                         amount means how many columns we have to go left.
     * @return A {@link List} with all the squares in the specified direction.
     */
    private static List<Square> createDirectedSquaresList(@NotNull Piece piece, int rowsDirection, int columnsDirection) {
        var squareList = new ArrayList<Square>();
        loopForListOfSquares(piece, rowsDirection, columnsDirection, squareList);
        return squareList;
    }

    /**
     * Creates a list of all the available {@link Square}s the given {@link Piece} can move to in
     * the same row.
     *
     * @param piece {@link Piece} than can move horizontally.
     * @return List of {@link Square}s <code>piece</code> can move to.
     */
    public static Stream<Square> horizontalMoves(@NotNull Piece piece) {
        var squaresOnLeft = SquaresUtils.createDirectedSquaresList(piece, 0, +1);
        var squaresOnRight = SquaresUtils.createDirectedSquaresList(piece, 0, -1);
        return Stream.of(squaresOnLeft, squaresOnRight)
                .flatMap(Collection::stream);
    }

    /**
     * Creates a list of all the available {@link Square}s the given {@link Piece} can move to in
     * the same column.
     *
     * @param piece {@link Piece} than can move vertically.
     * @return List of {@link Square}s <code>piece</code> can move to.
     */
    public static Stream<Square> verticalMoves(@NotNull Piece piece) {
        var squaresUp = SquaresUtils.createDirectedSquaresList(piece, +1, 0);
        var squaresBottom = SquaresUtils.createDirectedSquaresList(piece, -1, 0);
        return Stream.of(squaresUp, squaresBottom)
                .flatMap(Collection::stream);
    }

    /**
     * Creates a list of all the available {@link Square}s the given {@link Piece} can move to.
     *
     * @param piece {@link Piece} than can move diagonally.
     * @return List of {@link Square}s <code>piece</code> can move to.
     */
    public static Stream<Square> diagonalMoves(@NotNull Piece piece) {
        var squaresUpRight = SquaresUtils.createDirectedSquaresList(piece, +1, +1);
        var squaresUpLeft = SquaresUtils.createDirectedSquaresList(piece, +1, -1);
        var squaresDownRight = SquaresUtils.createDirectedSquaresList(piece, -1, +1);
        var squaresDownLeft = SquaresUtils.createDirectedSquaresList(piece, -1, -1);
        return Stream.of(squaresUpRight, squaresUpLeft, squaresDownRight, squaresDownLeft)
                .flatMap(Collection::stream);
    }

    /**
     * Auxiliary method for {@link SquaresUtils#createDirectedSquaresList(Piece, int, int)}. It's just a loop.
     *
     * @param piece            {@link Piece} from whose {@link Square} we create the {@link List}.
     * @param rowsDirection    Number of rows from the caller to the new {@link Square}. A positive
     *                         amount means how many rows we have to go up, whereas a negative amount
     *                         is the number of rows we have to go down.
     * @param columnsDirection Number of columns from the caller to the new {@link Square}. A positive
     *                         amount means how many columns we have to go right, whereas a negative
     *                         amount means how many columns we have to go left.
     * @param squareList       The {@link List} that will be returned by the main method
     *                         {@link SquaresUtils#createDirectedSquaresList(Piece, int, int)}.
     */
    private static void loopForListOfSquares(Piece piece, int rowsDirection, int columnsDirection, ArrayList<Square> squareList) {
        var newOptionalSquare = SquaresUtils.createSquare(piece.getSquare(), rowsDirection, columnsDirection);

        while (newOptionalSquare.isPresent()) {
            var newSquare = newOptionalSquare.get();
            if (piece.allyPieceIsOn(newSquare))
                return;
            squareList.add(newSquare);
            if (piece.enemyPieceIsOn(newSquare))
                return;
            newOptionalSquare = SquaresUtils.createSquare(newSquare, rowsDirection, columnsDirection);
        }
    }

    /**
     * Creates a {@link Square} from the coordinates of the one passed as a parameter.
     * The returned {@link Square} is placed at a specified distance by the parameters
     * <code>rowsDifference</code> and <code>columnsDifference</code> (see below how it
     * works). The resulting {@link Square} is encapsulated in an {@link Optional} since
     * it could have illegal coordinates, in which case that {@link Optional} will be empty.
     *
     * @param square            {@link Square} from whose coordinates we create another {@link Square}.
     * @param rowsDifference    Number of rows from the caller to the new {@link Square}. A positive
     *                          amount means how many rows we have to go up, whereas a negative amount
     *                          is the number of rows we have to go down.
     * @param columnsDifference Number of columns from the caller to the new {@link Square}. A positive
     *                          amount means how many columns we have to go right, whereas a negative
     *                          amount means how many columns we have to go left.
     * @return An {@link Optional} containing a {@link Square} if it has legal coordinates. It will be
     * empty otherwise.
     */
    public static Optional<Square> createSquare(Square square, int rowsDifference, int columnsDifference) {
        Optional<Square> result;
        try {
            var sq = new Square(square.getRow() + rowsDifference, square.getColumn() + columnsDifference);
            result = Optional.of(sq);
        } catch (IllegalArgumentException e) {
            result = Optional.empty();
        }
        return result;
    }

}
