package game.chess;

import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class SquareTest {

    @Test
    void testCreateSquare() {
        assertThrows(IllegalArgumentException.class, () -> new Square(-1, -1));
        assertThrows(IllegalArgumentException.class, () -> new Square(0, 0));
        assertThrows(IllegalArgumentException.class, () -> new Square(5, 300));
        assertDoesNotThrow(() -> new Square(2, 2));
        assertDoesNotThrow(() -> new Square(8, 1));

        var sq = new Square(1, 1);
        assertDoesNotThrow(() -> new Square(sq));
    }

    @Test
    void testEquals() {
        var s1 = new Square(1, 1);
        var s2 = new Square(1, 1);
        var s3 = new Square(1, 2);
        var s4 = new Square(2, 1);
        Square s5 = new Square(1, 1);
        String testString = "hi";

        assertEquals(s1, s2);
        assertEquals(s2, s1);
        assertEquals(s1, s5);
        assertEquals(s5, s1);
        assertEquals(s2, s5);
        assertEquals(s5, s2);

        assertNotEquals(s1, s3);
        assertNotEquals(s3, s1);
        assertNotEquals(s2, s4);
        assertNotEquals(s4, s2);
        assertNotEquals(s4, s1);
        assertNotEquals(s1, null);
        assertNotEquals(null, s1);
        assertNotEquals(s1, testString);
        assertNotEquals(testString, s1);
    }

    @Test
    void testHashCode() {
        var s1 = new Square(1, 1);
        var s2 = new Square(1, 1);
        var s3 = new Square(1, 2);
        var s4 = new Square(2, 1);
        Square s5 = new Square(1, 1);
        String testString = "hi";

        assertEquals(s1.hashCode(), s2.hashCode());
        assertEquals(s2.hashCode(), s1.hashCode());
        assertEquals(s1.hashCode(), s5.hashCode());
        assertEquals(s5.hashCode(), s1.hashCode());
        assertEquals(s2.hashCode(), s5.hashCode());
        assertEquals(s5.hashCode(), s2.hashCode());

        assertNotEquals(s1.hashCode(), s3.hashCode());
        assertNotEquals(s3.hashCode(), s1.hashCode());
        assertNotEquals(s2.hashCode(), s4.hashCode());
        assertNotEquals(s4.hashCode(), s2.hashCode());
        assertNotEquals(s4.hashCode(), s1.hashCode());
        assertNotEquals(s1.hashCode(), testString.hashCode());
        assertNotEquals(testString.hashCode(), s1.hashCode());
    }

    @Test
    void advancedHashCodeTest() {
        var hashSet = new HashSet<Square>();

        for (int i = ChessBoard.FIRST_ROW; i <= ChessBoard.LAST_ROW; i++) {
            for (int j = ChessBoard.FIRST_COLUMN; j <= ChessBoard.LAST_COLUMN; j++) {
                var sq = new Square(i, j);
                hashSet.add(sq);
            }
        }
        assertEquals(64, hashSet.size());
    }

    @Test
    void row() {
        var sq = new Square(1, 1);
        assertEquals(1, sq.row());
    }

    @Test
    void column() {
        var sq = new Square(1, 1);
        assertEquals(1, sq.column());
    }
}