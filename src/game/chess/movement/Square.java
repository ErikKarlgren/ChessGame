package game.chess.movement;

import game.chess.ChessBoard;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * Class that represents a square in a game board. This class also has static methods
 * to create squares from other squares.
 */
public class Square {
    private final int row;
    private final int column;

    /**
     * Creates a {@link Square} with the given coordinates. If those are illegal an
     * {@link IllegalArgumentException} is raised. You can check if those would be
     * legal or not using the {@link ChessBoard#illegalCoordinates(int, int)} method.
     *
     * @param row    Row of the new {@link Square}.
     * @param column Column of the new {@link Square}.
     * @throws IllegalArgumentException When either <code>row</code> or <code>column</code>
     *                                  have illegal values.
     */
    public Square(int row, int column) {
        if (ChessBoard.illegalCoordinates(row, column))
            throw new IllegalArgumentException("Illegal coordinates");
        this.row = row;
        this.column = column;
    }

    /**
     * Creates a new {@link Square} using the values of the one given as a
     * parameter.
     *
     * @param other {@link Square} from which a copy is built.
     */
    public Square(@NotNull Square other) {
        this.row = other.row;
        this.column = other.column;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    @Override
    public String toString() {
        var str = "";
        char colChar = (char) (this.column + 'a' - 1);
        char rowChar = (char) (this.row + '0');
        str += colChar + rowChar;
        return str;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        else if (this == obj)
            return true;
        else if (obj instanceof Square) {
            Square other = (Square) obj;
            return this.row == other.row && this.column == other.column;
        } else
            return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, column);
    }
}
