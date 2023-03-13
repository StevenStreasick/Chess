package Chess;
public class Pawn extends ChessPiece {

	public Pawn(Player player) {
		super(player);
	}

	
	/** 
	 * @return String
	 */
	public String type() {
		return "Pawn";
	}

	/** Verifies there is no piece in the way */
	private boolean isForwardPositionEmpty(Move move, ChessPiece[][] board) {
		if(board[move.toRow][move.toColumn] == null) {
			return true;
		}
		return false;
	}
	/**Checks if the pawn is moving up/down depending on the color. */
	private boolean isPieceMovingForward(Move move, ChessPiece[][] board) {
		if(super.player() == Player.BLACK  
				&& move.toRow - move.fromRow == 1 
				&& move.toColumn - move.fromColumn == 0) {
			return true;
		}
		if(super.player() == Player.WHITE  
				&& move.toRow - move.fromRow == -1 
				&& move.toColumn - move.fromColumn == 0) {
			return true;
		}
		return false;
	}
	/** Checks moving 2 spaces for first move */
	private boolean isFirstMove2Spaces(Move move, ChessPiece[][] board) {
		if(super.player() == Player.BLACK
		&& move.toRow - move.fromRow == 2
		&& move.toColumn - move.fromColumn == 0
		&& move.fromRow == 1) {
			if(board[move.toRow - 1][move.toColumn] == null) {
				return true;
			}
		}
		if(super.player() == Player.WHITE
		&& move.toRow - move.fromRow == -2
		&& move.toColumn - move.fromColumn == 0
		&& move.fromRow == 6) {
			if(board[move.toRow + 1][move.toColumn] == null) {
				return true;
			}
		}
		return false;
	}
	/**Checks if the pawn is moving diagonally depending on the color. */
	private boolean isPieceMovingDiagonally(Move move, ChessPiece[][] board) {
		if(super.player() == Player.BLACK
				&& move.toRow - move.fromRow == 1 
				&& Math.abs(move.toColumn - move.fromColumn) == 1) {
			return true;
		}
		if(super.player() == Player.WHITE
				&& move.toRow - move.fromRow == -1
				&& Math.abs(move.toColumn - move.fromColumn) == 1) {
			return true;
		}
		return false;
	}
	/** Verifies the To position is valid */

	/**  
	private boolean isOpener(Move move, ChessPiece[][] board) {
		boolean validity = true;
		
		if(move.fromRow == 1 && board[move.fromRow][move.fromColumn].player() == Player.BLACK) {
			return validity;
		}
		if(move.fromRow == 6 && board[move.fromRow][move.fromColumn].player() == Player.WHITE) {
			return validity;
		}
		return !validity;
	}
	*/
	

	private boolean isDiagonalEmpty(Move move, ChessPiece[][] board) {
		if(board[move.toRow][move.toColumn] == null) {
			return true;
		}
		if(board[move.fromRow][move.fromColumn].player() != board[move.toRow][move.toColumn].player() ) {
			if((board[move.toRow][move.toColumn].type() != "King")) { //TODO: Fix this. toColumn can be 1 or -1
				return(false);
			}
		}
		if(board[move.fromRow][move.fromColumn].player() == board[move.toRow][move.toColumn].player() ) {
			return false;
		}
		return true;
	}

	
	/** 
	 * @param move
	 * @param board
	 * @return boolean
	 */
	// determines if the move is valid for a pawn piece
	public boolean isValidMove(Move move, ChessPiece[][] board) {
		boolean validity = super.isValidMove(move, board);
		if(validity) {
			//Moving forward a space
			//TODO: Create a helper method for these few checks. Looks ugly if I don't
			if(isFirstMove2Spaces(move, board) == true) {
				return true;
			}
			if(isPieceMovingForward(move, board)) {
				if(isForwardPositionEmpty(move, board)) {
					return true;
				}
			//
			} else if (isPieceMovingDiagonally(move, board)) {
				if(isDiagonalEmpty(move, board) == false) {
					return true;
				}
			}
		}
		return false;
	}

}
