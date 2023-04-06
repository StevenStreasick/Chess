/**************************************************************
 * Functionality for each Rook Piece
 * 
 * @author Richard
 * @author Steven
 * @version 3/13/2022
 **************************************************************/
public class Rook extends ChessPiece {


	/************************************************************* 
	* Default constructor calls ChessPiece constructor
	*************************************************************/
	public Rook(Player player) {
		super(player);
	}


	/************************************************************* 
	* Returns the type of chesspiece
	*************************************************************/
	public String type() {
		return "Rook";
	}


	/************************************************************* 
	* Confirms that the horizontal path is clear
	*************************************************************/
	private boolean isHorizontalPathClear(Move move, ChessPiece[][] board) {
		int spacesToCheck = 
			Math.abs(move.toColumn - move.fromColumn) - 1;
		int i = 1;

		while (spacesToCheck >= i
				&& spacesToCheck > 0) {
			if (move.fromColumn > move.toColumn
					&& move.toRow == move.fromRow) { 
				if (board[move.toRow][move.toColumn + i] != null) {
					return false;
				}
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
	* Confirms that the vertical path is clear
	*************************************************************/
	private boolean isVerticalPathClear(Move move, ChessPiece[][] board) {
		int spacesToCheck = Math.abs(move.toRow - move.fromRow) - 1;
		int i = 1;

		while (spacesToCheck >= i
				&& spacesToCheck > 0) {
			if (move.fromRow > move.toRow
					&& move.toColumn == move.fromColumn) { 
				if (board[move.toRow + i][move.toColumn] != null) {
					return false;
				}
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
	* Verifies that the path is clear
	*************************************************************/
	private boolean isPathClear(Move move, ChessPiece[][] board) {
		if (isHorizontalPathClear(move, board) || 
		isVerticalPathClear(move, board)) {
			return true;
		}
		return false;
	}


	/************************************************************* 
	* Verifies that the selected position is in a straight line
	* from the rook
	*************************************************************/
	private boolean isStraightLine(Move move) {
		if (Math.abs(move.fromColumn - move.toColumn) == 0
				|| Math.abs(move.fromRow - move.toRow) == 0) {
			return true;
		}
		return false;

	}


	/************************************************************* 
	* Determines if the move is valid for the rook
	*************************************************************/
	public boolean isValidMove(Move move, ChessPiece[][] board) {
		boolean validity = super.isValidMove(move, board);

		if (!isStraightLine(move)) {
			return false;
		}

		if (!isPathClear(move, board)) {
			return false;
		}

		return validity;
	}

}