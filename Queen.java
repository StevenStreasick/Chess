/**************************************************************
 * Functionality for each Queen Piece
 * 
 * @author Richard
 * @author Steven
 * @version 3/13/2022
 **************************************************************/
public class Queen extends ChessPiece {

	/************************************************************* 
	* Default constructor calls the ChessPiece constructor
	*************************************************************/
	public Queen(Player player) {
		super(player);
	}


	/************************************************************* 
	* Returns the type of chesspiece
	*
	* @return String
	*************************************************************/
	public String type() {
		return "Queen";
	}


	/************************************************************* 
	* Verifies that horizontal path is not blocked by another
	* piece
	*
	* @param move
	* @param board
	* @return boolean
	*************************************************************/
	private boolean isHorizontalPathClear(Move move, ChessPiece[][] board) {
		int spacesToCheck = 
			Math.abs(move.toColumn - move.fromColumn) - 1;
		int i = 1;

		while (spacesToCheck >= i
				&& spacesToCheck > 0) {

			/** moving left */
			if (move.fromColumn > move.toColumn
					&& move.toRow == move.fromRow) { 
				if (board[move.toRow][move.toColumn + i] != null) {
					return false;
				}

			/** moving right */
			} else if (move.fromColumn < move.toColumn
					&& move.toRow == move.fromRow) { 
				if (board[move.fromRow][move.fromColumn + i] != null) {
					return false;
				}
			}
			i++;
		}

		if (spacesToCheck >= 0) {
			return true;
		}

		return false;
	}


	/************************************************************* 
	* Verifies that the vertical path is not blocked by any other
	* pieces
	*
	* @param move
	* @param board
	* @return boolean
	*************************************************************/
	private boolean isVerticalPathClear(Move move, ChessPiece[][] board) {
		int spacesToCheck = 
			Math.abs(move.toRow - move.fromRow) - 1;
		int i = 1;
		while (spacesToCheck >= i
				&& spacesToCheck > 0) {

			/** moving downwards */
			if (move.fromRow > move.toRow
					&& move.toColumn == move.fromColumn) {
				if (board[move.toRow + i][move.toColumn] != null) {
					return false;
				}

			/** moving upwards */
			} else if (move.fromRow < move.toRow
					&& move.toColumn == move.fromColumn) {
				if (board[move.fromRow + i][move.toColumn] != null) {
					return false;
				}
			}
			i++;
		}
		if (spacesToCheck >= 0) {
			return true;
		}
		return false;
	}


	/************************************************************* 
	* Utilizes the functionality of the rook
	*
	* @param move
	* @param board
	* @return boolean
	*************************************************************/
	private boolean isRookMovement(Move move, ChessPiece[][] board) {
		if (Math.abs(move.fromColumn - move.toColumn) == 0
				|| Math.abs(move.fromRow - move.toRow) == 0) {
			return true;
		}

		return false;
	}


	/************************************************************* 
	* Confirms the rook pathing is clear
	*
	* @param move
	* @param board
	* @return boolean
	*************************************************************/
	private boolean isRookMovementValid(Move move, ChessPiece[][] board) {
		if (isHorizontalPathClear(move, board) || 
		isVerticalPathClear(move, board)) {
			return true;
		}
		return false;
	}


	/************************************************************* 
	* Utilizes the functionality of the bishop
	*
	* @param move
	* @param board
	* @return boolean
	*************************************************************/
	private boolean isBishopMovement(Move move, ChessPiece[][] board) {
		int columnMagnitude = 
			Math.abs(move.fromColumn - move.toColumn);
		int rowMagnitude = Math.abs(move.fromRow - move.toRow);

		if (columnMagnitude == rowMagnitude) {
			return true;
		}

		return false;
	}


	/************************************************************* 
	* Checks the functionality of the bishop movement
	*
	* @param move
	* @param board
	* @return boolean
	*************************************************************/
	private boolean isBishopMovementBlocked(Move move, ChessPiece[][] board) {

		int columnMagnitude = 
			Math.abs(move.fromColumn - move.toColumn) - 1;
		int i = 1;
		while (i <= columnMagnitude
				&& 0 < columnMagnitude) {
			int column;
			int row;

			/** the piece is moving upwards */
			if (move.fromColumn - move.toColumn > 0) {
				column = move.fromColumn - i;

			/** the piece is moving downwards */
			} else {
				column = move.fromColumn + i;
			}

			/** leftwards */
			if (move.fromRow - move.toRow > 0) {
				row = move.fromRow - i; 

			/** rightwards */
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
	* Verifies that the queen is able to move to the selected
	* position
	*
	* @param move
	* @param board
	* @return boolean
	*************************************************************/
	public boolean isValidMove(Move move, ChessPiece[][] board) {
		if (!super.isValidMove(move, board)) 
			return false;

		if (!(isRookMovement(move, board) || isBishopMovement(move, board))) 
			return false;

		if (!(isRookMovementValid(move, board) || isBishopMovementBlocked(move, board))) {
			return false;
		}
		
		return true;
		
	
	}
}
