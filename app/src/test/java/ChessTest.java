import chess.*;
import chess.board.Board;
import chess.game.Game;
import chess.game.GameMode;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
import pieces.Piece;
import pieces.Pieces;
import validators.Validator;
import validators.movementValidators.MovementValidator;

import static org.junit.Assert.*;

public class ChessTest {

    static Board board;

    static Game game;
    static Validator validator;
    static Piece genericPieceOnBoard;
    static MovementValidator movementValidator;


    @BeforeClass
    public static void setupBeforeClass() throws Exception {
        game = new Game(GameMode.CLASSIC);
        board = game.getBoard();
        validator = new Validator();
        movementValidator = new MovementValidator();
    }

    @After
    public void tearDown() throws Exception {
        game = new Game(GameMode.CLASSIC);
        board = game.getBoard();
    }

    /**
     * This test checks that the Chess Board is properly initialized.
     * Assuming rectangular chess board
     */
    @Test
    public void instantiateBoard() {
        assertEquals(board.getAmountOfRows(), 8);
        assertEquals(board.getAmountOfColumns(), 8);
    }


    /**
     * Makes sure to check that any out-of-bounds locations are rejected.
     */
    @Test
    public void testBoundsOfBoard() {
        assertFalse(validator.isInsideBoardLimit(new Move(new Square(9,9), new Square(0,0), true), board));
        assertFalse(validator.isInsideBoardLimit(new Move(new Square(0,0), new Square(-1,2), true), board));
        assertTrue(validator.isInsideBoardLimit(new Move(new Square(4,7), new Square(1,3), true), board));
    }

    /**
     * Checks that the board can return pieces on the board
     */
    @Test
    public void checkForPieceOnBoard() {
        board.addPieceInPosition(2,2, new Piece(Color.WHITE, Pieces.QUEEN));
        assertFalse(board.getBoard()[2][2].isEmpty());
        assertTrue(board.getBoard()[2][3].isEmpty());
        assertEquals(board.getBoard()[2][2].getPiece().getType(), Pieces.QUEEN);
    }

    /**
     * Test to make sure pawns move properly.
     *
     * Note: White pawns move up, Black pawns move down.
     */
    @Test
    public void testPawnMovements(){
        Piece whitePawn = new Piece(Color.WHITE, Pieces.PAWN);
        Piece blackPawn = new Piece(Color.BLACK, Pieces.PAWN);
        board.addPieceInPosition(1,2,whitePawn);
        board.addPieceInPosition(6,3,new Piece(Color.BLACK, Pieces.PAWN));

        //Two step first move
        assertTrue(validator.isMoveValid(board, new Move(new Square(1,2), new Square(3,2), true)));
        assertTrue(validator.isMoveValid(board, new Move(new Square(6,3), new Square(4,3), false)));
        whitePawn.addMove();
        blackPawn.addMove();
        board.addPieceInPosition(3,2, whitePawn);
        board.deletePieceInPosition(1,2);
        board.addPieceInPosition(4,3, blackPawn);
        board.deletePieceInPosition(6,3);
        //Two step second move
        assertFalse(validator.isMoveValid(board, new Move(new Square(3,2), new Square(5,2), true)));
        assertFalse(validator.isMoveValid(board, new Move(new Square(4,3), new Square(2,3), false)));
        //Back step
        assertFalse(validator.isMoveValid(board, new Move(new Square(3,2), new Square(2,2), true)));
        assertFalse(validator.isMoveValid(board, new Move(new Square(4,3), new Square(5,3), false)));

        // Diagonal without enemies
        assertFalse(validator.isMoveValid(board, new Move(new Square(3,2), new Square(4,1), true)));
        assertFalse(validator.isMoveValid(board, new Move(new Square(4,3), new Square(3,4), false)));

        // Diagonal with enemies
        assertTrue(validator.isMoveValid(board, new Move(new Square(3,2), new Square(4,3), true)));
        board.addPieceInPosition(4,3, whitePawn);
        board.deletePieceInPosition(3,2);

        //one step
        assertTrue(validator.isMoveValid(board, new Move(new Square(4,3), new Square(5,3), true)));

        // Invalid move, partner in front
        board.addPieceInPosition(6,3, new Piece(Color.WHITE, Pieces.QUEEN));
        assertFalse(validator.isMoveValid(board, new Move(new Square(5,3), new Square(6,3), true)));

    }

    /**
     * Test to make sure rook moves properly.
     */
    @Test
    public void testRookMovements(){
        board.addPieceInPosition(4,4, new Piece(Color.WHITE, Pieces.ROOK));
        assertTrue(validator.isMoveValid(board, new Move(new Square(4,4), new Square(6,4), true)));
        assertTrue(validator.isMoveValid(board, new Move(new Square(4,4), new Square(2,4), true)));
        assertTrue(validator.isMoveValid(board, new Move(new Square(4,4), new Square(4,6), true)));
        assertTrue(validator.isMoveValid(board, new Move(new Square(4,4), new Square(4,2), true)));

        //piece in the way
        board.addPieceInPosition(4,5, new Piece(Color.BLACK, Pieces.QUEEN));
        assertFalse(validator.isMoveValid(board, new Move(new Square(4,4), new Square(4,7), true)));

    }

    /**
     * Test to make sure bishop moves properly.
     */
    @Test
    public void testBishopMovements(){
        board.addPieceInPosition(4,4, new Piece(Color.WHITE, Pieces.BISHOP));
        assertTrue(validator.isMoveValid(board, new Move(new Square(4,4), new Square(6,6), true)));
        assertTrue(validator.isMoveValid(board, new Move(new Square(4,4), new Square(2,2), true)));
        assertTrue(validator.isMoveValid(board, new Move(new Square(4,4), new Square(6,2), true)));
        assertTrue(validator.isMoveValid(board, new Move(new Square(4,4), new Square(2,6), true)));
    }

    /**
     * Test to verify the Queen moves properly
     */
    @Test
    public void testQueenMovements(){
        board.addPieceInPosition(4,4, new Piece(Color.WHITE, Pieces.QUEEN));
        assertTrue(validator.isMoveValid(board, new Move(new Square(4,4), new Square(6,4), true)));
        assertTrue(validator.isMoveValid(board, new Move(new Square(4,4), new Square(2,4), true)));
        assertTrue(validator.isMoveValid(board, new Move(new Square(4,4), new Square(4,6), true)));
        assertTrue(validator.isMoveValid(board, new Move(new Square(4,4), new Square(4,2), true)));
        assertTrue(validator.isMoveValid(board, new Move(new Square(4,4), new Square(6,6), true)));
        assertTrue(validator.isMoveValid(board, new Move(new Square(4,4), new Square(2,2), true)));
        assertTrue(validator.isMoveValid(board, new Move(new Square(4,4), new Square(6,2), true)));
        assertTrue(validator.isMoveValid(board, new Move(new Square(4,4), new Square(2,6), true)));

        assertFalse(validator.isMoveValid(board, new Move(new Square(4,4), new Square(6,3), true)));
        assertFalse(validator.isMoveValid(board, new Move(new Square(4,4), new Square(3,6), true)));
    }

    /**
     * Test to verify the King moves properly
     */
    @Test
    public void testKingMovements(){
        board.addPieceInPosition(4,4, new Piece(Color.WHITE, Pieces.KING));
        // Test all 8 valid spots
        assertTrue(validator.isMoveValid(board, new Move(new Square(4,4), new Square(5,4), true)));
        assertTrue(validator.isMoveValid(board, new Move(new Square(4,4), new Square(3,4), true)));
        assertTrue(validator.isMoveValid(board, new Move(new Square(4,4), new Square(3,5), true)));
        assertTrue(validator.isMoveValid(board, new Move(new Square(4,4), new Square(4,5), true)));
        assertTrue(validator.isMoveValid(board, new Move(new Square(4,4), new Square(5,5), true)));
        assertTrue(validator.isMoveValid(board, new Move(new Square(4,4), new Square(3,3), true)));
        assertTrue(validator.isMoveValid(board, new Move(new Square(4,4), new Square(4,3), true)));
        assertTrue(validator.isMoveValid(board, new Move(new Square(4,4), new Square(5,3), true)));

        assertFalse(validator.isMoveValid(board, new Move(new Square(4,4), new Square(6,3), true)));
        assertFalse(validator.isMoveValid(board, new Move(new Square(4,4), new Square(3,6), true)));
    }

    /**
     * Test to find checkmates
     */
//    @Test
//    public void checkmateFound() {
//        King blackKing = gameLogic.getBlackKing();
//        gameLogic.moveTo(blackKing, 4, 4);
//        Queen queen1 = gameLogic.addQueen(WHITE,3,3);
//        Queen queen2 = gameLogic.addQueen(WHITE,5,5);
//
//        assertTrue(gameLogic.isCheckmate(BLACK));
//
//        gameLogic.removePiece(queen1);
//        assertFalse(gameLogic.isCheckmate(BLACK));
//    }
//
//    /**
//     * Test that a player has a legal move available.
//     */
//    @Test
//    public void legalMoves() {
//        King blackKing = gameLogic.getBlackKing();
//        King whiteKing = gameLogic.getWhiteKing();
//        Queen queen = gameLogic.addQueen(BLACK, 2, 6);
//
//        gameLogic.moveTo(blackKing, 1, 5);
//        gameLogic.moveTo(whiteKing, 0, 7);
//
//        // Stalemate if white moves
//        assertFalse(gameLogic.canMove(WHITE));
//
//        // Still valid moves if black moves
//        assertTrue(gameLogic.canMove(BLACK));
//    }
//
//    /**
//     * Test whether a king has been checked.
//     */
//    @Test
//    public void kingChecked() {
//        King blackKing = gameLogic.getBlackKing();
//        gameLogic.moveTo(blackKing, 0, 0);
//
//        // Test checkmate
//        Queen queen = gameLogic.addQueen(WHITE, 3, 0);
//        assertTrue(gameLogic.isKingInCheck(BLACK));
//
//        // Test non-checkmate
//        gameLogic.removePiece(queen);
//        assertFalse(gameLogic.isKingInCheck(BLACK));
//    }
//
//    /**
//     * Verify that turns can be switched properly.
//     */
//    @Test
//    public void switchingTurns() {
//        gameLogic.setTurn(BLACK);
//
//        assertEquals(gameLogic.getCurrentPlayer(), BLACK);
//        gameLogic.switchPlayerTurn();
//        assertEquals(gameLogic.getCurrentPlayer(), WHITE);
//    }

}