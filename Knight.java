/**************************************************************
 * Functionality for each Knight Piece
 * 
 * @author Richard
 * @author Steven
 * @version 3/13/2022
 **************************************************************/
public class Knight extends ChessPiece {


	/************************************************************* 
	* Default constructor calls ChessPiece constructor
	*************************************************************/
	public Knight(Player player) {
		super(player);
	}


	/************************************************************* 
	* Returns type of chesspiece
	*************************************************************/
	public String type() {
		return "Knight";
	}


	/************************************************************* 
	* Checks to see if the selected space is a possible move for
	* a knight piece
	*************************************************************/
	private boolean isKnightMovement(Move move, ChessPiece[][] board) {
		int columnMagnitude = Math.abs(move.fromColumn - move.toColumn);
		int rowMagnitude = Math.abs(move.fromRow - move.toRow);

		if ((columnMagnitude == 2 || rowMagnitude == 2) && 
		columnMagnitude + rowMagnitude == 3) {
			return true;
		}
		return false;
	}


	/************************************************************* 
	* Verifies that the knight can move to the selected space
	*************************************************************/
	public boolean isValidMove(Move move, ChessPiece[][] board) {
		if (!super.isValidMove(move, board)) 
			return false;
			
		if (!isKnightMovement(move, board)) 
			return false;

		return true;
	}

}