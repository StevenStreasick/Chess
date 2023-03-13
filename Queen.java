package Chess;
//Updated this piece
public class Queen extends ChessPiece {

	public Queen(Player player) {
		super(player);
	}

	
	/** 
	 * @return String
	 */
	public String type() {
		return "Queen";
	}

	
	/** 
	 * @param move
	 * @param board
	 * @return boolean
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
	private boolean isRookMovement(Move move, ChessPiece[][] board) {
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
	private boolean isRookMovementValid(Move move, ChessPiece[][] board) {
		if(isHorizontalPathClear(move, board) || isVerticalPathClear(move, board)){
			return true;
		}
		return false;
	}

	
	/** 
	 * @param move
	 * @param board
	 * @return boolean
	 */
	private boolean isBishopMovement(Move move, ChessPiece[][] board) {
		int columnMagnitude = Math.abs(move.fromColumn - move.toColumn);
		int rowMagnitude = Math.abs(move.fromRow - move.toRow);

		if(columnMagnitude == rowMagnitude) {
			return true;
		}
		
		return false;
	}

	
	/** 
	 * @param move
	 * @param board
	 * @return boolean
	 */
	private boolean isBishopMovementBlocked(Move move, ChessPiece[][] board) {
		
		int columnMagnitude = Math.abs(move.fromColumn - move.toColumn) - 1;
		int i = 1; //We do not need to check the position of this piece/piece at end position
		//we know because of isValidMove() method that this piece exists in this position
		while (i <= columnMagnitude 
				&&  0 < columnMagnitude){  
			int column;
			int row;
			if (move.fromColumn - move.toColumn > 0) { 
				column = move.fromColumn - i; //The piece is moving upwards.
			} else {
				column = move.fromColumn + i; //Moving downwards.
			}
			//Down leftwards.
			if(move.fromRow - move.toRow > 0) { 
				row = move.fromRow - i; //The piece is moving leftward.
			} else {
				row = move.fromRow + i; //Moving rightwards.
			}
			
			if(board[row][column] != null) {
				return true;
			}
			
			i++;
		}
		return false;
	}
	
	
	/** 
	 * @param move
	 * @param board
	 * @return boolean
	 */
	public boolean isValidMove(Move move, ChessPiece[][] board) {
		// TODO:  implement this method
		// Think carefully about how you could do this
		//Rook, bishop. Makes up the full queen's movement set.
		if(super.isValidMove(move, board)) {
			if(isRookMovement(move, board)) {
				if(isRookMovementValid(move, board)) {
					return true;
				}
			}
			if(isBishopMovement(move, board)) {
				if(!isBishopMovementBlocked(move, board)) {
					return true;
				}
			}
		}
		return false;
	}
}
