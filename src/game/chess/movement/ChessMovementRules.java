package game.chess.movement;

import game.chess.ChessBoard;
import game.chess.chesspieces.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Utility class containing methods to calculate each kind of {@link Piece} available
 * movements according to the rules of game.
 */
public class ChessMovementRules {
    private ChessMovementRules() {
        // This class can only have static methods.
    }

    /**
     * TODO: Castling not supported yet
     * A {@link King} can move to every one of the 8 squares surrounding it if
     * they're not occupied by a {@link Piece} of the same color. Also, if
     * it hasn't moved yet it can move in a special way called <i>castling</i>,
     * which allows the {@link King} and a {@link Rook} to move at the same time
     * if neither of those has moved yet.
     *
     * @return Returns all the movements available to a {@link King}.
     */
    public static List<Square> kingsMovement(ChessBoard board, King king) {
        var squares = new ArrayList<Optional<Square>>();

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i != 0 && j != 0)
                    squares.add(SquaresUtils.createSquare(king.getSquare(), i, j));
            }
        }

        return squares.stream()
                .filter(Optional::isPresent)
                .map(Optional::get)
                .filter(sq -> !king.allyPieceIsOn(sq) && !board.wouldLeadToCheck(sq))
                .collect(Collectors.toUnmodifiableList());
    }

    /**
     * TODO: 'En passant' not supported yet
     * <p>
     * A {@link Pawn} can always move to the square in front of it if it isn't already
     * occupied by a piece of the same color. If it hasn't moved yet it can move two
     * squares in that same direction.
     * <p>
     * A {@link Pawn} cannot "eat" a piece of the opposite color by moving as described
     * before, but it can do so moving to one of the two squares that lay diagonally in
     * front of it as long as they have a piece of the opposite color.
     * <p>
     * Also... TODO: explain 'en passant'
     *
     * @param board {@link ChessBoard} where the {@link Pawn} is.
     * @param pawn  {@link Pawn} whose available movements we want to know.
     * @return Returns all the movements available to a {@link Pawn}.
     */
    public static List<Square> pawnsMovement(ChessBoard board, Pawn pawn) {
        var squares = new ArrayList<Square>();

        var upperSquare = SquaresUtils.createSquare(pawn.getSquare(), +1, 0);
        var upperLeftSquare = SquaresUtils.createSquare(pawn.getSquare(), +1, -1);
        var upperRightSquare = SquaresUtils.createSquare(pawn.getSquare(), +1, +1);
        var twoSquaresUp = SquaresUtils.createSquare(pawn.getSquare(), +2, 0);

        if (upperSquare.isPresent() && board.isEmpty(upperSquare.get()))
            squares.add(upperSquare.get());

        if (!pawn.hasMoved() && twoSquaresUp.isPresent() && board.isEmpty(twoSquaresUp.get()))
            squares.add(twoSquaresUp.get());

        if (upperLeftSquare.isPresent() && pawn.enemyPieceIsOn(upperLeftSquare.get()))
            squares.add(upperLeftSquare.get());

        if (upperRightSquare.isPresent() && pawn.enemyPieceIsOn(upperRightSquare.get()))
            squares.add(upperRightSquare.get());

        return squares;
    }

    /**
     * @param knight
     * @return
     */
    public static List<Square> knightsMovement(Knight knight) {
        var squares = new ArrayList<Optional<Square>>();
        // Highest squares
        squares.add(SquaresUtils.createSquare(knight.getSquare(), +2, +1));
        squares.add(SquaresUtils.createSquare(knight.getSquare(), +2, -1));
        // 2nd highest squares
        squares.add(SquaresUtils.createSquare(knight.getSquare(), +1, +2));
        squares.add(SquaresUtils.createSquare(knight.getSquare(), +1, -2));
        // 2nd lowest squares
        squares.add(SquaresUtils.createSquare(knight.getSquare(), -1, +2));
        squares.add(SquaresUtils.createSquare(knight.getSquare(), -1, -2));
        // Lowest squares
        squares.add(SquaresUtils.createSquare(knight.getSquare(), -2, +1));
        squares.add(SquaresUtils.createSquare(knight.getSquare(), -2, -1));

        return squares.stream()
                .filter(Optional::isPresent)
                .map(Optional::get)
                .filter(sq -> !knight.allyPieceIsOn(sq))
                .collect(Collectors.toUnmodifiableList());
    }

    /**
     * @param queen
     * @return
     */
    public static List<Square> queensMovement(Queen queen) {
        var vertical = SquaresUtils.verticalMoves(queen);
        var horizontal = SquaresUtils.horizontalMoves(queen);
        var diagonal = SquaresUtils.diagonalMoves(queen);

        return Stream.of(vertical, horizontal, diagonal)
                .flatMap(i -> i)
                .collect(Collectors.toUnmodifiableList());

    }

    /**
     * TODO: Castling not supported yet
     *
     * @param rook
     * @return
     */
    public static List<Square> rooksMovement(Rook rook) {
        var vertical = SquaresUtils.verticalMoves(rook);
        var horizontal = SquaresUtils.horizontalMoves(rook);
        return Stream.of(vertical, horizontal)
                .flatMap(i -> i)
                .collect(Collectors.toUnmodifiableList());
    }

    /**
     * @param bishop
     * @return
     */
    public static List<Square> bishopsMovement(Bishop bishop) {
        return SquaresUtils.diagonalMoves(bishop)
                .collect(Collectors.toUnmodifiableList());
    }
}
