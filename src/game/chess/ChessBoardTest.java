package game.chess;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ChessBoardTest {

    @Test
    void creatingBoard(){
        assertDoesNotThrow(ChessBoard::new);
    }

    @Test
    void assertingSquaresOrder(){
        Board board = new ChessBoard();
        var it = board.squaresIterator();
        var strBuilder = new StringBuilder();
        var expectedStrBuilder = new StringBuilder();

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                var sq = it.next();
                strBuilder.append(sq.row())
                        .append(sq.column())
                        .append(' ');
                expectedStrBuilder.append(i)
                        .append(j)
                        .append(' ');
            }
            strBuilder.append('\n');
            expectedStrBuilder.append(' ');
        }
        assertEquals(expectedStrBuilder, strBuilder);
    }

    @Test
    void assertPositionOfPieces(){
        var board = new ChessBoard();
        var it = board.squaresIterator();
        var strBuilder = new StringBuilder();
        var expectedStrBuilder = new StringBuilder();
        expectedStrBuilder
                .append("1:WhiteRook\tWhiteKnight\tWhiteBishop\tWhiteQueen\tWhiteKing\tWhiteBishop\tWhiteKnight\tWhiteRook\t\n")
                .append("2:WhitePawn\tWhitePawn\tWhitePawn\tWhitePawn\tWhitePawn\tWhitePawn\tWhitePawn\tWhitePawn\t\n")
                .append("3:\t\t\t\t\t\t\t\t\n")
                .append("4:\t\t\t\t\t\t\t\t\n")
                .append("5:\t\t\t\t\t\t\t\t\n")
                .append("6:\t\t\t\t\t\t\t\t\n")
                .append("7:BlackPawn\tBlackPawn\tBlackPawn\tBlackPawn\tBlackPawn\tBlackPawn\tBlackPawn\tBlackPawn\t\n")
                .append("8:BlackRook\tBlackKnight\tBlackBishop\tBlackQueen\tBlackKing\tBlackBishop\tBlackKnight\tBlackRook\t");


        for (int i = 0; i < 8; i++) {
            strBuilder.append(i + 1)
                    .append(":");
            for (int j = 0; j < 8; j++) {
                var sq = it.next();
                var p = board.at(sq);
                var name = p.isPresent() ? p.get().name() : "";
                strBuilder.append(name)
                        .append("\t");
            }
            strBuilder.append('\n');
        }
        assertEquals(expectedStrBuilder.toString().trim(), strBuilder.toString().trim());
    }
}