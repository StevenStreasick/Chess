/**************************************************************
 * Functionality for each King Piece
 * 
 * @author Richard
 * @author Steven
 * @version 3/13/2022
 **************************************************************/
public class King extends ChessPiece {


    /*************************************************************
    * Default constructor calls the ChessPiece constructor
    *************************************************************/
    public King(Player player) {
        super(player);
    }


    /************************************************************* 
	* Returns the type of chesspiece
	*************************************************************/
    public String type() {
        return "King";
    }


    /************************************************************* 
	* Verifies that the selected space is within the king's
    * ability to go to
	*************************************************************/
    private boolean isMovementASingleUnit(Move move, ChessPiece[][] board) {
        if (Math.abs(move.fromColumn - move.toColumn) < 2
                && Math.abs(move.fromRow - move.toRow) < 2) {
            return true;
        }
        return false;
    }


    /************************************************************* 
	* Verifies that the king can move to selected space
	*************************************************************/
    public boolean isValidMove(Move move, ChessPiece[][] board) {
        if (!super.isValidMove(move, board)) 
            return false;
            
        if (!isMovementASingleUnit(move, board)) 
            return false;
        
    
        return true;
    }
}