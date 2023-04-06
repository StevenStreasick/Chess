/**************************************************************
 * Functionality for each Bishop Piece
 * 
 * @author Richard
 * @author Steven
 * @version 3/13/2022
 **************************************************************/
public class Bishop extends ChessPiece {


	/*************************************************************
	 * Default constructor call the ChessPiece constructor
	 *************************************************************/
	public Bishop(Player player) {
		super(player);
	}


	/************************************************************* 
	* Returns the type of chesspiece
	*
	* @return String
	*************************************************************/
	public String type() {
		return "Bishop";
	}


	/************************************************************* 
	* Check to see if the selected position is diagonal from the
	* bishop
	*
	* @param move
	* @param board
	* @return boolean
	*************************************************************/
	public boolean isDiagonalMove(Move move, ChessPiece[][] board) {
		int columnMagnitude = 
			Math.abs(move.fromColumn - move.toColumn);
		int rowMagnitude = Math.abs(move.fromRow - move.toRow);

		if (columnMagnitude == rowMagnitude) {
			return true;
		}
		return false;
	}


	/************************************************************* 
	* Checks to see if the bishop's path is clear
	* 
	* @param move
	* @param board
	* @return boolean
	*************************************************************/
	public boolean isPieceInWay(Move move, ChessPiece[][] board) {
		int columnMagnitude = 
			Math.abs(move.fromColumn - move.toColumn) - 1;
		int i = 1; 

		while (i <= columnMagnitude
				&& 0 < columnMagnitude) {
			int column;
			int row;
			
			if (move.fromColumn - move.toColumn > 0) {
				column = move.fromColumn - i; 
			} else {
				column = move.fromColumn + i; 
			}

			if (move.fromRow - move.toRow > 0) {
				row = move.fromRow - i; 
			} else {
				row = move.fromRow + i; 
			}

			if (board[row][column] != null) {
				return true;
			}

			i++;
		}

		return false;
	}


	/************************************************************* 
	* Verifies that the bishop can move to the selected postition
	*
	* @param move
	* @param board
	* @return boolean
	*************************************************************/
	public boolean isValidMove(Move move, ChessPiece[][] board) {
		if (!super.isValidMove(move, board)) 
			return false;

		if (!isDiagonalMove(move, board)) 
			return false;
		
		if (isPieceInWay(move, board)) 
			return false;
				
		return true;
	}
}
