package game.chess.chesspieces;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
@Accessors(fluent = true)
@Getter
public
enum PiecesName {
    WhitePawn(true), BlackPawn(false),
    WhiteRook(true), BlackRook(false),
    WhiteKnight(true), BlackKnight(false),
    WhiteBishop(true), BlackBishop(false),
    WhiteKing(true), BlackKing(false),
    WhiteQueen(true), BlackQueen(false),
    Undefined(true);

    final boolean isWhite;

}