package game.chess.chesspieces;

public enum PiecesName {
    WhitePawn(true), BlackPawn(false),
    WhiteRook(true), BlackRook(false),
    WhiteKnight(true), BlackKnight(false),
    WhiteBishop(true), BlackBishop(false),
    WhiteKing(true), BlackKing(false),
    WhiteQueen(true), BlackQueen(false),
    Undefined(true);

    final boolean isWhite;

    PiecesName(boolean isWhite) {
        this.isWhite = isWhite;
    }
}