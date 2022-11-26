import chess.*;
import chess.board.Board;
import chess.game.Game;
import chess.game.GameMode;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
import pieces.Piece;
import pieces.Pieces;
import validation.Validator;
import validation.restrictions.BoardLimitRestriction;

import static org.junit.Assert.*;

public class ChessTest {

    static Board board;

    static Game game;
    static Validator validator;
    static BoardLimitRestriction boardLimitRestriction;



    @BeforeClass
    public static void setupBeforeClass() throws Exception {
        game = new Game(GameMode.CLASSIC);
        board = game.getBoard();
        validator = new Validator(GameMode.CLASSIC);
        boardLimitRestriction = new BoardLimitRestriction();
    }

    @After
    public void tearDown() throws Exception {
        game = new Game(GameMode.CLASSIC);
        board = game.getBoard();
    }

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
        assertFalse(boardLimitRestriction.validate(board, new Move(new Square(0,0), new Square(9,9), true)));
        assertFalse(boardLimitRestriction.validate(board, new Move(new Square(0,0), new Square(-1,2), true)));
        assertTrue(boardLimitRestriction.validate(board, new Move(new Square(4,7), new Square(1,3), true)));
    }


    @Test
    public void testPawnMovements(){
        Piece whitePawn = new Piece(Color.WHITE, Pieces.PAWN);
        Piece blackPawn = new Piece(Color.BLACK, Pieces.PAWN);
        board.addPieceInPosition(new Square(1,2),whitePawn);
        board.addPieceInPosition(new Square(6,3),blackPawn);

        //Two step first move
        assertTrue(validator.validate(board, new Move(new Square(1,2), new Square(3,2), true)));
        game.move(3,2,1,2);
        assertTrue(validator.validate(board, new Move(new Square(6,3), new Square(4,3), false)));
        game.move(4,3,6,3);

        //Two step second move
        assertFalse(validator.validate(board, new Move(new Square(3,2), new Square(5,2), true)));
        assertFalse(validator.validate(board, new Move(new Square(4,3), new Square(2,3), false)));

        //Back step
        assertFalse(validator.validate(board, new Move(new Square(3,2), new Square(2,2), true)));
        assertFalse(validator.validate(board, new Move(new Square(4,3), new Square(5,3), false)));

        // Diagonal without enemies
        assertFalse(validator.validate(board, new Move(new Square(3,2), new Square(4,1), true)));
        assertFalse(validator.validate(board, new Move(new Square(4,3), new Square(3,4), false)));

        // Diagonal with enemies
        assertTrue(validator.validate(board, new Move(new Square(3,2), new Square(4,3), true)));
        game.move(4,3,3,2);

        //one step
        assertTrue(validator.validate(board, new Move(new Square(4,3), new Square(5,3), true)));

        // Invalid move, partner in front
        board.addPieceInPosition(new Square(6,3), new Piece(Color.WHITE, Pieces.QUEEN));
        assertFalse(validator.validate(board, new Move(new Square(5,3), new Square(6,3), true)));

    }

    /**
     * Test to make sure rook moves properly.
     */
    @Test
    public void testRookMovements(){
        board.addPieceInPosition(new Square(4,4), new Piece(Color.WHITE, Pieces.ROOK));
        assertTrue(validator.validate(board, new Move(new Square(4,4), new Square(6,4), true)));
        assertTrue(validator.validate(board, new Move(new Square(4,4), new Square(2,4), true)));
        assertTrue(validator.validate(board, new Move(new Square(4,4), new Square(4,6), true)));
        assertTrue(validator.validate(board, new Move(new Square(4,4), new Square(4,2), true)));

        //piece in the way
        board.addPieceInPosition(new Square(4,5), new Piece(Color.BLACK, Pieces.QUEEN));
        assertFalse(validator.validate(board, new Move(new Square(4,4), new Square(4,7), true)));

    }

    /**
     * Test to make sure bishop moves properly.
     */
    @Test
    public void testBishopMovements(){
        board.addPieceInPosition(new Square(4,4), new Piece(Color.WHITE, Pieces.BISHOP));
        assertTrue(validator.validate(board, new Move(new Square(4,4), new Square(6,6), true)));
        assertTrue(validator.validate(board, new Move(new Square(4,4), new Square(2,2), true)));
        assertTrue(validator.validate(board, new Move(new Square(4,4), new Square(6,2), true)));
        assertTrue(validator.validate(board, new Move(new Square(4,4), new Square(2,6), true)));
    }

    /**
     * Test to verify the Queen moves properly
     */
    @Test
    public void testQueenMovements(){
        board.addPieceInPosition(new Square(4,4), new Piece(Color.WHITE, Pieces.QUEEN));
        assertTrue(validator.validate(board, new Move(new Square(4,4), new Square(6,4), true)));
        assertTrue(validator.validate(board, new Move(new Square(4,4), new Square(2,4), true)));
        assertTrue(validator.validate(board, new Move(new Square(4,4), new Square(4,6), true)));
        assertTrue(validator.validate(board, new Move(new Square(4,4), new Square(4,2), true)));
        assertTrue(validator.validate(board, new Move(new Square(4,4), new Square(6,6), true)));
        assertTrue(validator.validate(board, new Move(new Square(4,4), new Square(2,2), true)));
        assertTrue(validator.validate(board, new Move(new Square(4,4), new Square(6,2), true)));
        assertTrue(validator.validate(board, new Move(new Square(4,4), new Square(2,6), true)));

        assertFalse(validator.validate(board, new Move(new Square(4,4), new Square(6,3), true)));
        assertFalse(validator.validate(board, new Move(new Square(4,4), new Square(3,6), true)));
    }

    /**
     * Test to verify the King moves properly
     */
    @Test
    public void testKingMovements(){
        board.addPieceInPosition(new Square(4,4), new Piece(Color.WHITE, Pieces.KING));
        // Test all 8 valid spots
        assertTrue(validator.validate(board, new Move(new Square(4,4), new Square(5,4), true)));
        assertTrue(validator.validate(board, new Move(new Square(4,4), new Square(3,4), true)));
        assertTrue(validator.validate(board, new Move(new Square(4,4), new Square(3,5), true)));
        assertTrue(validator.validate(board, new Move(new Square(4,4), new Square(4,5), true)));
        assertTrue(validator.validate(board, new Move(new Square(4,4), new Square(5,5), true)));
        assertTrue(validator.validate(board, new Move(new Square(4,4), new Square(3,3), true)));
        assertTrue(validator.validate(board, new Move(new Square(4,4), new Square(4,3), true)));
        assertTrue(validator.validate(board, new Move(new Square(4,4), new Square(5,3), true)));

        assertFalse(validator.validate(board, new Move(new Square(4,4), new Square(6,3), true)));
        assertFalse(validator.validate(board, new Move(new Square(4,4), new Square(3,6), true)));
    }

}