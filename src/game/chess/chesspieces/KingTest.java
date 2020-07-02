package game.chess.chesspieces;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KingTest {

    @Test
    void testKing(){
        var k = new King(true);
        assertSame(k.name(), PiecesName.WhiteKing);
        assertNotSame(k.name(), PiecesName.BlackKing);
        assertNotSame(k.name(), PiecesName.WhiteBishop);
    }
}