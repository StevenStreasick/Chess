package Chess;
public class Rook extends ChessPiece {

	public Rook(Player player) {
		super(player);
	}

	
	/** 
	 * @return String
	 */
	public String type() {
		return "Rook";
	}
	
	
	/** 
	 * @return boolean
	 */
	/*private boolean isCleartest(Move move, ChessPiece[][] board) {
		int i = 0;
		/** vertical */
		/*if(move.fromRow == move.toRow) {
			i = move.fromColumn;
			if(move.toColumn > move.fromColumn) {
				while(i < move.toColumn) {
					if()
					i++;
				}
			}
			if(move.fromColumn > move.toColumn) {

			}
		}
		/** horizontal */
		/*if(move.fromColumn == move.toColumn) {

		}
		return false;
	} 
	*/
	
	private boolean isHorizontalPathClear(Move move, ChessPiece[][] board) {
		int spacesToCheck = Math.abs(move.toColumn - move.fromColumn) - 1;
		int i = 1; 
		while(spacesToCheck >= i
				&& spacesToCheck > 0) {
			if(move.fromColumn > move.toColumn 
					&& move.toRow == move.fromRow) { //Moving Left
				if(board[move.toRow][move.toColumn + i] != null) {
					return false;
				}
			} else if(move.fromColumn < move.toColumn 
						&& move.toRow == move.fromRow) { //Moving Right
				if(board[move.fromRow][move.fromColumn + i] != null) {
					return false;
				}
			}
			i ++;
		} 
		if(spacesToCheck >= 0) {
			return true;
		}
		return false;
	}
	
	
	/** 
	 * @param move
	 * @param board
	 * @return boolean
	 */
	//Copied from isHorizontalPathClear. Might be copied wrong
	private boolean isVerticalPathClear(Move move, ChessPiece[][] board) {
		int spacesToCheck = Math.abs(move.toRow - move.fromRow) - 1;
		int i = 1;
		while(spacesToCheck >= i  
				&& spacesToCheck > 0) {
			if(move.fromRow > move.toRow
					&& move.toColumn == move.fromColumn) { //Moving Downwards
				if(board[move.toRow + i][move.toColumn] != null) {
					return false;
				}
			} else if(move.fromRow < move.toRow 
					&& move.toColumn == move.fromColumn) { //Moving upwards
				if(board[move.fromRow + i][move.toColumn] != null) {
					return false;
				}
			}
			i ++;
		} 
		if(spacesToCheck >= 0) {
			return true;
		}
		return false;
	}


	
	/** 
	 * @param move
	 * @param board
	 * @return boolean
	 */
	private boolean isPathClear(Move move, ChessPiece[][] board) {
		//Add helper methods for horizontal 
		if(isHorizontalPathClear(move, board) || isVerticalPathClear(move, board)){
			return true;
		}
		return false;
	}

	
	/** 
	 * @param move
	 * @return boolean
	 */
	private boolean isStraightLine(Move move) {
		//if()
		if(Math.abs(move.fromColumn - move.toColumn) == 0 
				|| Math.abs(move.fromRow - move.toRow) == 0){
			return true;
		}
		return false;
			
	}

	
	/** 
	 * @param move
	 * @param board
	 * @return boolean
	 */
	// determines if the move is valid for a rook piece
	public boolean isValidMove(Move move, ChessPiece[][] board) {
		// TODO:  implement this method
		boolean validity = super.isValidMove(move, board);
		
		if(!isStraightLine(move)) {
			return false;
		}

		if(!isPathClear(move, board)) {
			return false;
		}

		return validity;	
	}
	
}
