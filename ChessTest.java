package Chess;

import org.junit.Test;
import static org.junit.Assert.*;

//Updated this piece

public class ChessTest {

    //TODO: Test the movement of all black pieces initially...
    //TODO: Add check for checkmate
    //TODO: Add checks for turns.
    //TODO: Add a test for when a move is invalid/valid during a check...


    private static void movePiece(ChessModel c, int fromV, int  fromH, int  toV, int toH) {
        if(c.currentPlayer() != c.pieceAt(fromV, fromH).player()) {
            c.setNextPlayer();
        }

        Move move = new Move(fromV, fromH, toV, toH);
        c.move(move);
    }
   
    /**Testing the initial creation of ChessModel.*/
    @Test
    public void kingSetupTest() {
        ChessModel c = new ChessModel();

        assertEquals("King", c.pieceAt(7, 4).type());
        assertEquals(Player.WHITE, c.pieceAt(7, 4).player());

        assertEquals("King", c.pieceAt(0, 4).type());
        assertEquals(Player.BLACK, c.pieceAt(0, 4).player());
    }

    @Test
    public void queenSetupTest() {
        ChessModel c = new ChessModel();

        assertEquals("King", c.pieceAt(7, 4).type());
        assertEquals(Player.WHITE, c.pieceAt(7, 4).player());

        assertEquals("King", c.pieceAt(0, 4).type());
        assertEquals(Player.BLACK, c.pieceAt(0, 4).player());

        System.out.println(c.pieceAt(7, 4).type());
        System.out.println(c.pieceAt(7, 4).player());
    }

    @Test
    public void bishopSetupTest() {
        ChessModel c = new ChessModel();

        assertEquals("Bishop", c.pieceAt(7, 2).type());
        assertEquals(Player.WHITE, c.pieceAt(7, 2).player());
        assertEquals("Bishop", c.pieceAt(7, 5).type());
        assertEquals(Player.WHITE, c.pieceAt(7, 5).player());


        assertEquals("Bishop", c.pieceAt(0, 2).type());
        assertEquals(Player.BLACK, c.pieceAt(0, 2).player());
        assertEquals("Bishop", c.pieceAt(0, 5).type());
        assertEquals(Player.BLACK, c.pieceAt(0, 5).player());
    }

    @Test
    public void knightSetupTest() {
        ChessModel c = new ChessModel();

        assertEquals("Knight", c.pieceAt(7, 1).type());
        assertEquals(Player.WHITE, c.pieceAt(7, 1).player());
        assertEquals("Knight", c.pieceAt(7, 6).type());
        assertEquals(Player.WHITE, c.pieceAt(7, 6).player());

        assertEquals("Knight", c.pieceAt(0, 1).type());
        assertEquals(Player.BLACK, c.pieceAt(0, 1).player());
        assertEquals("Knight", c.pieceAt(0, 6).type());
        assertEquals(Player.BLACK, c.pieceAt(0, 6).player());

        System.out.println(c.pieceAt(7, 4).type());
        System.out.println(c.pieceAt(7, 4).player());
    }

    @Test
    public void rookSetupTest() {
        ChessModel c = new ChessModel();

        assertEquals("Rook", c.pieceAt(7, 0).type());
        assertEquals(Player.WHITE, c.pieceAt(7, 0).player());
        assertEquals("Rook", c.pieceAt(7, 7).type());
        assertEquals(Player.WHITE, c.pieceAt(7, 7).player());

        assertEquals("Rook", c.pieceAt(0, 0).type());
        assertEquals(Player.BLACK, c.pieceAt(0, 0).player());
        assertEquals("Rook", c.pieceAt(0, 7).type());
        assertEquals(Player.BLACK, c.pieceAt(0, 7).player());

        System.out.println(c.pieceAt(7, 4).type());
        System.out.println(c.pieceAt(7, 4).player());
    }

    @Test
    public void pawnSetupTest() {
        ChessModel c = new ChessModel();
        
        //White pawns
        for(int i = 0; i < 7; i++) {
            assertEquals("Pawn", c.pieceAt(6, i).type());
            assertEquals(Player.WHITE, c.pieceAt(7, i).player());
        }
        //Black pawns
        for(int i = 0; i < 7; i++) {
            assertEquals("Pawn", c.pieceAt(1, i).type());
            assertEquals(Player.BLACK, c.pieceAt(1, i).player());
        }
    }

    /**End of testing the initial creation of ChessModel.*/

    /**Start of testing the turn restrictions */
    @Test
    public void blackPlayer() {
        ChessModel c = new ChessModel();

        //Move move = new Move(1, 0, 2, 0); //Move pawn down
        movePiece(c, 1, 0, 2, 0);
        assertEquals("Pawn", c.pieceAt(2, 0).type());
        assertEquals(Player.BLACK, c.pieceAt(2, 0).player());
        
    }
    @Test
    public void whitePlayer() {
        ChessModel c = new ChessModel();

        Move move = new Move(6, 0, 5, 0); //Move pawn down
        c.move(move);
        assertEquals("Pawn", c.pieceAt(5, 0).type());
        assertEquals(Player.WHITE, c.pieceAt(5, 0).player());
        
    }
    @Test
    public void whitePlayerMovedTwice() {
        ChessModel c = new ChessModel();

        Move move = new Move(6, 0, 5, 0); //Move pawn up
        c.move(move);
        move = new Move(6, 1, 5, 1); //Move pawn up
        c.move(move);
        assertEquals("Pawn", c.pieceAt(6, 1).type());
        assertEquals(Player.WHITE, c.pieceAt(6, 1).player());
        
    }
    /**End of testing the turn restrictions */

    /**Start of testing the Pawn class. */

    @Test
    public void whitePawnTestForward1() {
        ChessModel c = new ChessModel();
        Move move = new Move(6, 0, 5, 0);

        assertTrue(c.isValidMove(move));
        c.move(move);
        assertNotNull(c.pieceAt(5,0));

    }
    @Test
    public void whitePawnTestForward2() {
        ChessModel c = new ChessModel();
        Move move = new Move(6, 3, 5, 3);

        assertTrue(c.isValidMove(move));
        c.move(move);
        assertNotNull(c.pieceAt(5,3));
          
    }
    // @Test
    // public void whitePawnTestForward2() {
    //     ChessModel c = new ChessModel();
    //     Move move = new Move(6, 0, 4, 0);

    //     assertTrue(c.isValidMove(move));
    //     c.move(move);
    //     assertNotNull(c.pieceAt(4, 0));

    // }
    @Test
    public void blackPawnTestForward1() {
        ChessModel c = new ChessModel();
        movePiece(c, 1, 0, 2 ,0);
        
        assertNotNull(c.pieceAt(2, 0));

    }
    @Test
    public void blackPawnTestForward2() {
        ChessModel c = new ChessModel();
        movePiece(c, 1, 5, 2 ,5);
        assertNotNull(c.pieceAt(2, 5));

    }
    @Test
    public void whitePawnInvalidTest1() {
        ChessModel c = new ChessModel();
        Move move = new Move(6, 0, 3, 0);

        assertTrue(!c.isValidMove(move));
    }
    @Test
    public void whitePawnInvalidTest2() {
        ChessModel c = new ChessModel();
        Move move = new Move(6, 0, 5, 1);

        assertTrue(!c.isValidMove(move));
    }
    @Test
    public void whitePawnInvalidTest3() {
        ChessModel c = new ChessModel();
        Move move = new Move(6, 0, 4, 1);

        assertTrue(!c.isValidMove(move));
    }
    @Test
    public void whitePawnInvalidTest4() {
        ChessModel c = new ChessModel();
        Move move = new Move(6, 0, 2, 5);

        assertTrue(!c.isValidMove(move));
    }
    @Test
    public void whitePawnInvalidTest5() {
        ChessModel c = new ChessModel();
        Move move = new Move(6, 0, 6, 0);

        assertTrue(!c.isValidMove(move));
    }
    
    @Test
    public void blackPawnInvalidTest1() {
        ChessModel c = new ChessModel();
        Move move = new Move(1, 0, 4, 0);

        assertTrue(!c.isValidMove(move));
    }
    @Test
    public void blackPawnInvalidTest2() {
        ChessModel c = new ChessModel();
        Move move = new Move(1, 0, 2, 1);

        assertTrue(!c.isValidMove(move));
    }
    @Test
    public void blackPawnInvalidTest3() {
        ChessModel c = new ChessModel();
        Move move = new Move(1, 0, 3, 1);

        assertTrue(!c.isValidMove(move));
    }
    @Test
    public void blackPawnInvalidTest4() {
        ChessModel c = new ChessModel();
        Move move = new Move(1, 0, 4, 5);

        assertTrue(!c.isValidMove(move));
    }
    @Test
    public void blackPawnInvalidTest5() {
        ChessModel c = new ChessModel();
        Move move = new Move(1, 0, 1, 0);

        assertTrue(!c.isValidMove(move));
    }

    /**End of testing the Pawn class. */

    /**Start of testing the Rook class. */
    @Test
    public void blackRookForwardTest1() {
        ChessModel c = new ChessModel();

        movePiece(c, 1, 0, 2, 0); //Move pawn
        movePiece(c, 2, 0, 3, 0); //Move same pawn
        movePiece(c, 0, 0, 2, 0); //Move the black rook
        
        assertEquals("Rook", c.pieceAt(2, 0).type());
        assertEquals(Player.BLACK, c.pieceAt(2, 0).player());
    }

    @Test
    public void blackRookForwardTest2() {
        ChessModel c = new ChessModel();

        movePiece(c, 1, 0, 2, 0); //Move pawn
        movePiece(c, 2, 0, 3, 0); //Move same pawn
        movePiece(c, 3, 0, 4, 0); //Move same pawn
        movePiece(c, 4, 0, 5, 0); //Move same pawn
        movePiece(c, 0, 0, 4, 0); //Move the black rook
        
        assertEquals("Rook", c.pieceAt(4, 0).type());
        assertEquals(Player.BLACK, c.pieceAt(4, 0).player());
    }
    @Test
    public void blackRookVerticalTest1() {
        ChessModel c = new ChessModel();

        movePiece(c, 1, 0, 2, 0); //Move pawn down
        movePiece(c, 2, 0, 3, 0); //Move same pawn down
        movePiece(c, 3, 0, 4, 0); //Move same pawn down
        movePiece(c, 4, 0, 5, 0); //Move same pawn down
        movePiece(c, 0, 0, 4, 0); //Move the black rook down, behind pawn
        movePiece(c, 4, 0, 4, 5); //Move the black rook right
        assertEquals("Rook", c.pieceAt(4, 5).type());
        assertEquals(Player.BLACK, c.pieceAt(4, 5).player());
    }
    @Test
    public void blackRookVerticalTest2() {
        ChessModel c = new ChessModel();

        movePiece(c, 1, 0, 2, 0); //Move pawn down
        movePiece(c, 2, 0, 3, 0); //Move same pawn down
        movePiece(c, 3, 0, 4, 0); //Move same pawn down
        movePiece(c, 4, 0, 5, 0); //Move same pawn down
        movePiece(c, 0, 0, 1, 0); //Move the black rook down, behind pawn
        assertEquals("Rook", c.pieceAt(1, 0).type());
        assertEquals(Player.BLACK, c.pieceAt(1, 0).player());
    }
    @Test
    public void blackRookVerticalTest3() {
        ChessModel c = new ChessModel();

        movePiece(c, 1, 0, 2, 0); //Move pawn down
        movePiece(c, 2, 0, 3, 0); //Move same pawn down
        movePiece(c, 3, 0, 4, 0); //Move same pawn down
        movePiece(c, 4, 0, 5, 0); //Move same pawn down
        movePiece(c, 0, 0, 4, 0); //Move the black rook down, behind pawn
        movePiece(c, 4, 0, 4, 1); //Move the black rook right
        movePiece(c, 4, 1, 5, 1);
        assertEquals("Rook", c.pieceAt(5, 1).type());
        assertEquals(Player.BLACK, c.pieceAt(5, 1).player());
    }
    /**End of testing the Rook class. */

    /**Start of testing the Queen class. */
    @Test
    public void whiteQueenVerticalMovement1() {
        ChessModel c = new ChessModel();

        movePiece(c, 6, 3, 5, 3); //Move pawn up
        movePiece(c, 5, 3, 4, 3); //Move same pawn up
        movePiece(c, 4, 3, 3, 3); //Move same pawn up
        movePiece(c, 3, 3, 2, 3); //Move same pawn up
        movePiece(c, 7, 3, 4, 3); //Move the white queen upward
        
        assertEquals("Queen", c.pieceAt(4, 3).type());
        assertEquals(Player.WHITE, c.pieceAt(4, 3).player());
    }
    @Test
    public void whiteQueenVerticalMovement2() {
        ChessModel c = new ChessModel();

        movePiece(c, 6, 3, 5, 3); //Move pawn up
        movePiece(c, 5, 3, 4, 3); //Move same pawn up
        movePiece(c, 4, 3, 3, 3); //Move same pawn up
        movePiece(c, 3, 3, 2, 3); //Move same pawn up
        movePiece(c, 7, 3, 6, 3); //Move the white queen upward
        
        assertEquals("Queen", c.pieceAt(6, 3).type());
        assertEquals(Player.WHITE, c.pieceAt(6, 3).player());
    }
    @Test
    public void whiteQueenVerticalMovement3() {
        ChessModel c = new ChessModel();

        movePiece(c, 6, 3, 5, 3); //Move pawn up
        movePiece(c, 5, 3, 4, 3); //Move same pawn up
        movePiece(c, 4, 3, 3, 3); //Move same pawn up
        movePiece(c, 3, 3, 2, 3); //Move same pawn up
        movePiece(c, 7, 3, 3, 3); //Move the white queen upward
        
        assertEquals("Queen", c.pieceAt(3, 3).type());
        assertEquals(Player.WHITE, c.pieceAt(3, 3).player());

        movePiece(c, 3, 3, 7, 3); //Move the white queen back into her starting spot.
        assertEquals("Queen", c.pieceAt(7, 3).type());
        assertEquals(Player.WHITE, c.pieceAt(7, 3).player());
    }
    @Test
    public void whiteQueenHorizontalMovement1() {
        ChessModel c = new ChessModel();

        movePiece(c, 6, 3, 5, 3); //Move pawn up
        movePiece(c, 5, 3, 4, 3); //Move same pawn up
        movePiece(c, 4, 3, 3, 3); //Move same pawn up
        movePiece(c, 3, 3, 2, 3); //Move same pawn up
        movePiece(c, 7, 3, 3, 3); //Move the white queen upward
        movePiece(c, 3, 3, 3, 0); //Move the white queen horizontally.
        assertEquals("Queen", c.pieceAt(3, 0).type());
        assertEquals(Player.WHITE, c.pieceAt(3, 0).player());
    }
    @Test
    public void whiteQueenHorizontalMovement2() {
        ChessModel c = new ChessModel();

        movePiece(c, 6, 3, 5, 3); //Move pawn up
        movePiece(c, 5, 3, 4, 3); //Move same pawn up
        movePiece(c, 4, 3, 3, 3); //Move same pawn up
        movePiece(c, 3, 3, 2, 3); //Move same pawn up
        movePiece(c, 7, 3, 3, 3); //Move the white queen upward
        movePiece(c, 3, 3, 3, 7); //Move the white queen horizontally.
        assertEquals("Queen", c.pieceAt(3, 7).type());
        assertEquals(Player.WHITE, c.pieceAt(3, 7).player());
    }
    @Test
    public void whiteQueenHorizontalMovement3() {
        ChessModel c = new ChessModel();

        movePiece(c, 6, 3, 5, 3); //Move pawn up
        movePiece(c, 5, 3, 4, 3); //Move same pawn up
        movePiece(c, 4, 3, 3, 3); //Move same pawn up
        movePiece(c, 3, 3, 2, 3); //Move same pawn up
        movePiece(c, 7, 3, 3, 3); //Move the white queen upward
        movePiece(c, 3, 3, 3, 7); //Move the white queen horizontally.
        movePiece(c, 3, 7, 3, 0);
        assertEquals("Queen", c.pieceAt(3, 0).type());
        assertEquals(Player.WHITE, c.pieceAt(3, 0).player());
    }
    @Test
    public void whiteQueenHorizontalMovement4() {
        ChessModel c = new ChessModel();

        movePiece(c, 6, 3, 5, 3); //Move pawn up
        movePiece(c, 5, 3, 4, 3); //Move same pawn up
        movePiece(c, 4, 3, 3, 3); //Move same pawn up
        movePiece(c, 3, 3, 2, 3); //Move same pawn up
        movePiece(c, 7, 3, 6, 3); //Move the white queen upward
       
        assertEquals("Queen", c.pieceAt(6, 3).type());
        assertEquals(Player.WHITE, c.pieceAt(6, 3).player());
    }

    @Test
    public void whiteQueenUpLeftMovement1() {
        ChessModel c = new ChessModel();

        movePiece(c, 6, 3, 5, 3); //Move pawn up
        movePiece(c, 5, 3, 4, 3); //Move same pawn up
        movePiece(c, 4, 3, 3, 3); //Move same pawn up
        movePiece(c, 3, 3, 2, 3); //Move same pawn up
        movePiece(c, 7, 3, 6, 3); //Move the white queen upward 
        //TODO: Fix the above test. Not working properly, somehow? 
        movePiece(c, 6, 3, 5, 2); //Move the white queen horizontally.
        assertEquals("Queen", c.pieceAt(5, 2).type());
        assertEquals(Player.WHITE, c.pieceAt(5, 2).player());
    }
    @Test
    public void whiteQueenUpLeftMovement2() {
        ChessModel c = new ChessModel();

        movePiece(c, 6, 3, 5, 3); //Move pawn up
        movePiece(c, 5, 3, 4, 3); //Move same pawn up
        movePiece(c, 4, 3, 3, 3); //Move same pawn up
        movePiece(c, 3, 3, 2, 3); //Move same pawn up
        movePiece(c, 7, 3, 6, 3); //Move the white queen upward 
        movePiece(c, 6, 3, 3, 0); //Move the white queen horizontally.

        assertEquals("Queen", c.pieceAt(3, 0).type());
        assertEquals(Player.WHITE, c.pieceAt(3, 0).player());
    }
    
    @Test
    public void whiteQueenUpRightMovement1() {
        ChessModel c = new ChessModel();

        movePiece(c, 6, 3, 5, 3); //Move pawn up
        movePiece(c, 5, 3, 4, 3); //Move same pawn up
        movePiece(c, 4, 3, 3, 3); //Move same pawn up
        movePiece(c, 3, 3, 2, 3); //Move same pawn up
        movePiece(c, 7, 3, 6, 3); //Move the white queen upward 
        movePiece(c, 6, 3, 5, 4); //Move the white queen horizontally.

        assertEquals("Queen", c.pieceAt(5, 4).type());
        assertEquals(Player.WHITE, c.pieceAt(5, 4).player());
    }
    @Test
    public void whiteQueenUpRightMovement2() {
        ChessModel c = new ChessModel();

        movePiece(c, 6, 3, 5, 3); //Move pawn up
        movePiece(c, 5, 3, 4, 3); //Move same pawn up
        movePiece(c, 4, 3, 3, 3); //Move same pawn up
        movePiece(c, 3, 3, 2, 3); //Move same pawn up
        movePiece(c, 7, 3, 6, 3); //Move the white queen upward 
        movePiece(c, 6, 3, 2, 7); //Move the white queen horizontally.

        assertEquals("Queen", c.pieceAt(2, 7).type());
        assertEquals(Player.WHITE, c.pieceAt(2, 7).player());
    }
    /**End of testing the Queen class. */

    /**Start of testing the Bishop class. */
    @Test
    public void whiteBishopUpLeftMovement1() {
        ChessModel c = new ChessModel();

        movePiece(c, 6, 1, 5, 1); //Move pawn up
        movePiece(c, 5, 1, 4, 1); //Move same pawn up
        movePiece(c, 7, 2, 6, 1); //Move the white bishop up/left.

        assertEquals("Bishop", c.pieceAt(6, 1).type());
        assertEquals(Player.WHITE, c.pieceAt(6, 1).player());
        movePiece(c, 6, 1, 5, 0); 
        assertEquals("Bishop", c.pieceAt(5, 0).type());
        assertEquals(Player.WHITE, c.pieceAt(5, 0).player());
    }
    @Test
    public void whiteBishopUpLeftMovement2() {
        ChessModel c = new ChessModel();

        movePiece(c, 6, 1, 5, 1); //Move pawn up
        movePiece(c, 5, 1, 4, 1); //Move same pawn up
        movePiece(c, 7, 2, 5, 0); //Move the white bishop up/left.

        assertEquals("Bishop", c.pieceAt(5, 0).type());
        assertEquals(Player.WHITE, c.pieceAt(5, 0).player());
    }
    @Test
    public void whiteBishopUpRightMovement1() {
        ChessModel c = new ChessModel();

        movePiece(c, 6, 3, 5, 3); //Move pawn up
        movePiece(c, 5, 3, 4, 3); //Move same pawn up
        movePiece(c, 7, 2, 6, 3); //Move the white bishop up/right.

        assertEquals("Bishop", c.pieceAt(6, 3).type());
        assertEquals(Player.WHITE, c.pieceAt(6, 3).player());
        movePiece(c, 6, 3, 5, 4); 
        assertEquals("Bishop", c.pieceAt(5, 4).type());
        assertEquals(Player.WHITE, c.pieceAt(5, 4).player());
    }
    @Test
    public void whiteBishopUpRightMovement2() {
        ChessModel c = new ChessModel();

        movePiece(c, 6, 3, 5, 3); //Move pawn up
        movePiece(c, 5, 3, 4, 3); //Move same pawn up
        movePiece(c, 7, 2, 2, 7); //Move the white bishop up/right.
        
        assertEquals("Bishop", c.pieceAt(2, 7).type());
        assertEquals(Player.WHITE, c.pieceAt(2, 7).player());
    }

    /**End of testing the Bishop class. */

    /**Start of testing the Knight class. */

    @Test
    public void whiteKnightUpLeftMovement1() {
        ChessModel c = new ChessModel();

        movePiece(c, 7, 1, 5, 0); //Move the white bishop up/right.
        
        assertEquals("Knight", c.pieceAt(5, 0).type());
        assertEquals(Player.WHITE, c.pieceAt(5, 0).player());
    }
    @Test
    public void whiteKnightUpLeftMovement2() {
        ChessModel c = new ChessModel();

        movePiece(c, 6, 4, 5, 4); //Move the pawn out of the way.
        movePiece(c, 7, 6, 6, 4); //Move the white bishop up/right.
        
        assertEquals("Knight", c.pieceAt(6, 4).type());
        assertEquals(Player.WHITE, c.pieceAt(6, 4).player());
    }

    @Test
    public void whiteKnightUpRightMovement1() {
        ChessModel c = new ChessModel();

        movePiece(c, 7, 1, 5, 2); //Move the white bishop up/right.
        
        assertEquals("Knight", c.pieceAt(5, 2).type());
        assertEquals(Player.WHITE, c.pieceAt(5, 2).player());
    }
    @Test
    public void whiteKnightUpRightMovement2() {
        ChessModel c = new ChessModel();

        movePiece(c, 6, 3, 5, 3); //Move the pawn out of the way.
        movePiece(c, 7, 1, 6, 3); //Move the white bishop up/right.
        
        assertEquals("Knight", c.pieceAt(6, 3).type());
        assertEquals(Player.WHITE, c.pieceAt(6, 3).player());
    }

    /**End of testing the Knight class. */

    /**Start of testing the King class. */
    @Test
    public void whiteKingUpMovement() {
        ChessModel c = new ChessModel();

        movePiece(c, 6, 4, 5, 4); //Move the pawn out of the way.
        movePiece(c, 7, 4, 6, 4); //Move the white king up.
        
        assertEquals("King", c.pieceAt(6, 4).type());
        assertEquals(Player.WHITE, c.pieceAt(6, 4).player());
    }
    @Test
    public void whiteKingLeftMovement() {
        ChessModel c = new ChessModel();

        movePiece(c, 6, 4, 5, 4); //Move the pawn out of the way.
        movePiece(c, 5, 4, 4, 4);
        movePiece(c, 7, 4, 6, 4); //Move the white king up.
        movePiece(c, 6, 4, 5, 4); //Move the white king up again
        movePiece(c, 5, 4, 5, 3); //Move the white king left
        
        assertEquals("King", c.pieceAt(5, 3).type());
        assertEquals(Player.WHITE, c.pieceAt(5, 3).player());
    }
    @Test
    public void whiteKingRightMovement() {
        ChessModel c = new ChessModel();

        movePiece(c, 6, 4, 5, 4); //Move the pawn out of the way.
        movePiece(c, 5, 4, 4, 4);
        movePiece(c, 7, 4, 6, 4); //Move the white king up.
        movePiece(c, 6, 4, 5, 4); //Move the white king up again
        movePiece(c, 5, 4, 5, 5); //Move the white king left
        
        assertEquals("King", c.pieceAt(5, 5).type());
        assertEquals(Player.WHITE, c.pieceAt(5, 5).player());
    }
    @Test
    public void whiteKingDownMovement() {
        ChessModel c = new ChessModel();

        movePiece(c, 6, 4, 5, 4); //Move the pawn out of the way.
        movePiece(c, 5, 4, 4, 4);
        movePiece(c, 7, 4, 6, 4); //Move the white king up.
        movePiece(c, 6, 4, 7, 4); //Move the white king down
        
        assertEquals("King", c.pieceAt(7, 4).type());
        assertEquals(Player.WHITE, c.pieceAt(7, 4).player());
    }

    /**End of testing the King class. */

    /**Start of invalid King testing. */
    @Test
    public void invalidKingMovement1() {
        ChessModel c = new ChessModel();

        Move move = new Move(7, 4, 8, 4);
        assertFalse(c.isValidMove(move));
    }
    @Test
    public void invalidKingMovement2() {
        ChessModel c = new ChessModel();

        Move move = new Move(7, 4, 7, -1);
        assertFalse(c.isValidMove(move));
    }
    @Test
    public void invalidKingMovement3() { //Bishop style movement
        ChessModel c = new ChessModel();
        movePiece(c, 6, 3, 5, 3);
        
        Move move = new Move(7, 4, 6, 3);
        assertFalse(c.isValidMove(move));
    }
    @Test
    public void invalidKingMovement4() { //Rook style movement
        ChessModel c = new ChessModel();
        movePiece(c, 6, 3, 5, 3);
        movePiece(c, 5, 3, 4, 3);

        Move move = new Move(7, 4, 5, 4);
        assertFalse(c.isValidMove(move));
    }
    @Test
    public void invalidKingMovement5() { //Knight style movement
        ChessModel c = new ChessModel();
        
        Move move = new Move(7, 4, 5, 3);
        assertFalse(c.isValidMove(move));
    }
    /**End of invalid King testing. */

    /**Start of invalid Rook testing */
    @Test
    public void invalidRookMovement1() {
        ChessModel c = new ChessModel();

        Move move = new Move(7, 0, 8, 0);
        assertFalse(c.isValidMove(move));
    }
    @Test
    public void invalidRookMovement2() {
        ChessModel c = new ChessModel();

        Move move = new Move(7, 0, 7, -1);
        assertFalse(c.isValidMove(move));
    }
    @Test
    public void invalidRookMovement3() { //Bishop style movement
        ChessModel c = new ChessModel();
        movePiece(c, 6, 0, 5, 0);
        movePiece(c, 5, 0, 4, 0);
        movePiece(c, 7, 0, 5, 0); //Moves the rook

        Move move = new Move(7, 0, 5, 2);

        assertFalse(c.isValidMove(move));
    }
    @Test
    public void invalidRookMovement4() { //Knight style movement
        ChessModel c = new ChessModel();
        
        Move move = new Move(7, 0, 5, 1);
        assertFalse(c.isValidMove(move));
    }
    /**End of invalid Rook movement */

    /**Start of invalid pawn testing. */
    @Test
    public void invalidPawnMovement1() {
        ChessModel c = new ChessModel();

        Move move = new Move(6, 4, 8, 4);
        assertFalse(c.isValidMove(move));
    }
    @Test
    public void invalidPawnMovement2() {
        ChessModel c = new ChessModel();

        Move move = new Move(6, 0, 6, -1);
        assertFalse(c.isValidMove(move));
    }
    @Test
    public void invalidPawnMovement3() { //Bishop style movement
        ChessModel c = new ChessModel();
        
        Move move = new Move(6, 0, 4, 2);
        assertFalse(c.isValidMove(move));
    }
    @Test
    public void invalidPawnMovement4() { //Rook style movement
        ChessModel c = new ChessModel();

        Move move = new Move(6, 0, 3, 0);
        assertFalse(c.isValidMove(move));
    }
    @Test
    public void invalidPawnMovement5() { //Knight style movement
        ChessModel c = new ChessModel();
        
        Move move = new Move(6, 0, 4, 1);
        assertFalse(c.isValidMove(move));
    }
    /**End of invalid Pawn testing. */

    /**Start of invalid Knight testing. */
    @Test
    public void invalidKnightMovement1() {
        ChessModel c = new ChessModel();

        Move move = new Move(7, 1, 9, 0);
        assertFalse(c.isValidMove(move));
    }
    @Test
    public void invalidKnightMovement2() {
        ChessModel c = new ChessModel();

        Move move = new Move(7, 1, 6, -2);
        assertFalse(c.isValidMove(move));
    }
    @Test
    public void invalidKnightMovement3() { //Bishop style movement
        ChessModel c = new ChessModel();
        movePiece(c, 6, 1, 5, 1);
        
        Move move = new Move(7, 1, 5, 3);
        assertFalse(c.isValidMove(move));
    }
    @Test
    public void invalidKnightMovement4() { //Rook style movement
        ChessModel c = new ChessModel();
        movePiece(c, 6, 1, 5, 1);
        movePiece(c, 5, 1, 4, 1);

        Move move = new Move(7, 1, 5, 1);
        assertFalse(c.isValidMove(move));
    }
    /**End of invalid Knight testing. */

    /**Start of invalid Bishop testing. */
    @Test
    public void invalidBishopMovement1() {
        ChessModel c = new ChessModel();

        Move move = new Move(7, 2, 8, 3);
        assertFalse(c.isValidMove(move));
    }
    @Test
    public void invalidBishopMovement2() {
        ChessModel c = new ChessModel();

        Move move = new Move(7, 2, 7, -1);
        assertFalse(c.isValidMove(move));
    }
    @Test
    public void invalidBishopMovement4() { //Rook style movement
        ChessModel c = new ChessModel();
        movePiece(c, 6, 2, 5, 2);
        movePiece(c, 5, 2, 4, 2);

        Move move = new Move(7, 2, 5, 2);
        assertFalse(c.isValidMove(move));
    }
    @Test
    public void invalidBishopMovement5() { //Knight style movement
        ChessModel c = new ChessModel();
        
        Move move = new Move(7, 2, 5, 1);
        assertFalse(c.isValidMove(move));
    }
    /**End of invalid Bishop testing. */

    /**Start of invalid Queen testing. */
    @Test
    public void invalidQueenMovement1() {
        ChessModel c = new ChessModel();

        Move move = new Move(7, 3, 8, 3);
        assertFalse(c.isValidMove(move));
    }
    @Test
    public void invalidQueenMovement2() {
        ChessModel c = new ChessModel();

        Move move = new Move(7, 3, 7, -1);
        assertFalse(c.isValidMove(move));
    }
    @Test
    public void invalidQueenMovement5() { //Knight style movement
        ChessModel c = new ChessModel();
        
        Move move = new Move(7, 3, 5, 2);
        assertFalse(c.isValidMove(move));
    }
    /**End of invalid Queen testing. */

    /**Start of inCheck testing */

    @Test
    public void inCheckTest() { 
        ChessModel c = new ChessModel();
        
        movePiece(c, 6, 4, 5, 4); //Move the white pawn out of the way
        movePiece(c, 1, 5, 2, 5); //Move the black pawn out of the way, allowing for an opening to occur.
        movePiece(c, 7, 3, 3, 7); //Moves the queen into a check position
        assertTrue(c.inCheck(Player.BLACK));
    }
    @Test
    public void inCheckTest2() { 
        ChessModel c = new ChessModel();
        
        movePiece(c, 6, 4, 4, 4); //Move the white pawn out of the way
        movePiece(c, 1, 5, 3, 5); //Move the black pawn out of the way, allowing for an opening to occur.
        movePiece(c, 4, 4, 3, 5);
        movePiece(c, 3, 5, 2, 5);
        movePiece(c, 2, 5, 1, 5);
        assertEquals("Pawn", c.pieceAt(1, 5).type());
        assertEquals(Player.WHITE, c.pieceAt(1, 5).player());

        //movePiece(c, 7, 3, 3, 7); //Moves the queen into a check position
        assertTrue(c.inCheck(Player.BLACK));
    }
    @Test
    public void notInCheckTest() { 
        ChessModel c = new ChessModel();
        
        movePiece(c, 6, 4, 5, 4); //Move the white pawn out of the way
        movePiece(c, 1, 5, 2, 5); //Move the black pawn out of the way, allowing for an opening to occur.
        assertFalse(c.inCheck(Player.BLACK));
    }

    /**End of inCheck testing */

    /**Start of undo testing */
    @Test
    public void undoTest() { 
        ChessModel c = new ChessModel();
        ChessPiece whitePawn = c.pieceAt(6, 4);
        movePiece(c, 6, 4, 5, 4); //Move the white pawn out of the way
        c.undo();
        assertNotNull(c.pieceAt(6, 4));
        assertNull(c.pieceAt(5, 4));
        assertEquals(whitePawn, c.pieceAt(6, 4));

        movePiece(c, 6, 4, 5, 4);
        ChessPiece whiteQueen = c.pieceAt(7, 3);
        movePiece(c, 7, 3, 3, 7);
        c.undo();
        assertNotNull(c.pieceAt(7, 3));
        assertNull(c.pieceAt(3,7));
        assertEquals(whiteQueen, c.pieceAt(7, 3));

    }
    @Test
    public void undoPieceCaptureTest() { 
        ChessModel c = new ChessModel();
        movePiece(c, 6, 7, 5, 7);
        movePiece(c, 5, 7, 4, 7);
        movePiece(c, 4, 7, 3, 7);
        movePiece(c, 3, 7, 2, 7);
        movePiece(c, 2, 7, 1, 6);
        c.undo();
        assertEquals(Player.BLACK, c.pieceAt(1, 6).player());
        assertEquals(Player.WHITE, c.pieceAt(2, 7).player());
    }
    @Test
    public void undoPromotionTest() { 
        ChessModel c = new ChessModel();
        movePiece(c, 6, 6, 5, 6);
        movePiece(c, 5, 6, 4, 6);
        movePiece(c, 4, 6, 3, 6);
        movePiece(c, 3, 6, 2, 6);
        movePiece(c, 2, 6, 1, 7);
        movePiece(c, 1, 7, 0, 6);
        assertEquals("Queen", c.pieceAt(0, 6).type());
        assertEquals(Player.WHITE, c.pieceAt(0, 6).player());
        c.undo();
        assertEquals(Player.BLACK, c.pieceAt(0, 6).player());
        assertEquals(Player.WHITE, c.pieceAt(1, 7).player());
        assertEquals("Pawn", c.pieceAt(1, 7).type());
    }
    @Test
    public void undoPromotionTestLonger() { 
        ChessModel c = new ChessModel();
        movePiece(c, 6, 6, 5, 6);
        movePiece(c, 5, 6, 4, 6);
        movePiece(c, 4, 6, 3, 6);
        movePiece(c, 3, 6, 2, 6);
        movePiece(c, 2, 6, 1, 7);
        movePiece(c, 1, 7, 0, 6);

        movePiece(c, 0, 6, 0, 7);
        c.undo();
        c.undo();
        assertEquals(Player.BLACK, c.pieceAt(0, 6).player());
        assertEquals(Player.WHITE, c.pieceAt(1, 7).player());
        assertEquals("Pawn", c.pieceAt(1, 7).type());
    }
    @Test
    public void multipleUndoTest() { 
        ChessModel c = new ChessModel();

        ChessPiece whitePawn = c.pieceAt(6, 4);
        movePiece(c, 6, 4, 5, 4); //Move the white pawn out of the way

        ChessPiece whiteQueen = c.pieceAt(7, 3);
        movePiece(c, 7, 3, 3, 7);

        c.undo();
        assertNotNull(c.pieceAt(7, 3));
        assertNull(c.pieceAt(3,7));
        assertEquals(whiteQueen, c.pieceAt(7, 3));

        c.undo();
        assertNotNull(c.pieceAt(6, 4));
        assertNull(c.pieceAt(5, 4));
        assertEquals(whitePawn, c.pieceAt(6, 4));

    }
    @Test
    public void undoPlayerTurnTest() { 
        ChessModel c = new ChessModel();
        Move move = new Move(6, 5, 5, 5);
        c.move(move);
        //c.setNextPlayer();
        assertEquals(Player.BLACK, c.currentPlayer());
        c.undo();
        assertEquals(Player.WHITE, c.currentPlayer());
    }
    @Test
    public void undoPlayerTurnTest2() { 
        ChessModel c = new ChessModel();
        Move move = new Move(6, 5, 5, 5); //Black turn
        c.move(move);
        //c.setNextPlayer();
        move = new Move(1, 3, 2, 3); //White
        c.move(move);
        //c.setNextPlayer();
        move = new Move(5, 5, 4, 5); //Black turn
        c.move(move);
        //c.setNextPlayer();
        assertEquals(Player.BLACK, c.currentPlayer());
        c.undo();
        assertEquals(Player.WHITE, c.currentPlayer());
    }
    /**End of undo testing */

    /**Start of promotion testing */
    @Test
    public void promotionTest() { 
        ChessModel c = new ChessModel();
        movePiece(c, 6, 2, 5, 2); //Move white pawn upward
        movePiece(c, 5, 2, 4, 2); //Move white pawn upward
        movePiece(c, 4, 2, 3, 2); //Move white pawn upward
        movePiece(c, 3, 2, 2, 2); //Move white pawn upward
        movePiece(c, 2, 2, 1, 1); //Move white pawn to capture
        movePiece(c, 1, 1, 0, 0); //Move white pawn to capture
        assertEquals(Player.BLACK, c.currentPlayer());
        assertEquals(Player.WHITE, c.pieceAt(0, 0).player());
        assertEquals("Queen", c.pieceAt(0, 0).type());

    }
    /**End of promotion testing */
    /**Start of isComplete() method */
    @Test
    public void isCompleteTest() { //Knight style movement
        ChessModel c = new ChessModel();
        
        movePiece(c, 6, 4, 5, 4); //Move the white pawn out of the way
        movePiece(c, 1, 5, 2, 5); //Move the black pawn out of the way, allowing for an opening to occur.
        movePiece(c, 1, 6, 2, 6);
        movePiece(c, 2, 6, 3, 6);
        movePiece(c, 1, 7, 2, 7);
        Move move = new Move(7, 3, 3, 7);
        c.move(move);//Moves the queen into a check position
        assertEquals(Player.WHITE, c.pieceAt(3, 7).player());
        assertTrue(c.inCheck(Player.BLACK));
        assertEquals(Player.BLACK, c.currentPlayer());
        assertTrue(c.isComplete());
    } 
    @Test
    public void isNotCompleteTest() { //Knight style movement
        ChessModel c = new ChessModel();
        
        movePiece(c, 6, 4, 5, 4); //Move the white pawn out of the way
       
        assertFalse(c.isComplete());
    } 
    /**End of isComplete() method */
 
    /**start of Chess Board isValidMove testing */
    public static void main(String args[]) {
        ChessModel c = new ChessModel();
        Move move = new Move(6, 1, 5, 1);
        c.move(move);



        // movePiece(c, 6, 4, 5, 4); //Move the white pawn out of the way
        // movePiece(c, 1, 5, 2, 5); //Move the black pawn out of the way, allowing for an opening to occur.
        // movePiece(c, 1, 6, 2, 6);
        // movePiece(c, 2, 6, 3, 6);
        // movePiece(c, 1, 7, 2, 7);
        // Move move = new Move(7, 3, 3, 7);
        // c.move(move);//Moves the queen into a check position
        // assertEquals(Player.WHITE, c.pieceAt(3, 7).player());
        // printBoard(c);
        // c.inCheck(Player.BLACK);
        // assertEquals(Player.BLACK, c.currentPlayer());
        //assertTrue(c.isComplete());
    }


}