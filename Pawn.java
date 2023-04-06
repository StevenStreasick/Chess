/**************************************************************
 * Functionality for each Pawn Piece
 * 
 * @author Richard
 * @author Steven
 * @version 3/13/2022
 **************************************************************/
public class Pawn extends ChessPiece {


	/*************************************************************
	* Default constructor calls the ChessPiece Constructor
	*************************************************************/
	public Pawn(Player player) {
		super(player);
	}


	/************************************************************* 
	* Returns the type of chesspiece
	*************************************************************/
	public String type() {
		return "Pawn";
	}


	/************************************************************* 
	* Verifies there is no piece in the way
	*************************************************************/
	private boolean isForwardPositionEmpty(Move move, ChessPiece[][] board) {
		if (board[move.toRow][move.toColumn] == null) {
			return true;
		}
		return false;
	}


	/************************************************************* 
	* Checks if the pawn is moving up/down depending on the color
	*************************************************************/
	private boolean isPieceMovingForward(Move move, ChessPiece[][] board) {
		if (super.player() == Player.BLACK
				&& move.toRow - move.fromRow == 1
				&& move.toColumn - move.fromColumn == 0) {
			return true;
		}
		if (super.player() == Player.WHITE
				&& move.toRow - move.fromRow == -1
				&& move.toColumn - move.fromColumn == 0) {
			return true;
		}
		return false;
	}


	/************************************************************* 
	* Checks if the pawn can move two spaces for an opener
	*************************************************************/
	private boolean isFirstMove2Spaces(Move move, ChessPiece[][] board) {
		if (super.player() == Player.BLACK
				&& move.toRow - move.fromRow == 2
				&& move.toColumn - move.fromColumn == 0
				&& move.fromRow == 1) {
			if (board[move.toRow - 1][move.toColumn] == null) {
				return true;
			}
		}
		if (super.player() == Player.WHITE
				&& move.toRow - move.fromRow == -2
				&& move.toColumn - move.fromColumn == 0
				&& move.fromRow == 6) {
			if (board[move.toRow + 1][move.toColumn] == null) {
				return true;
			}
		}
		return false;
	}


	/************************************************************* 
	* Checks if the pawn is moving diagonally depending on the color
	*************************************************************/
	private boolean isPieceMovingDiagonally(Move move, ChessPiece[][] board) {
		if (super.player() == Player.BLACK
				&& move.toRow - move.fromRow == 1
				&& Math.abs(move.toColumn - move.fromColumn) == 1) {
			return true;
		}
		if (super.player() == Player.WHITE
				&& move.toRow - move.fromRow == -1
				&& Math.abs(move.toColumn - move.fromColumn) == 1) {
			return true;
		}
		return false;
	}


	/************************************************************* 
	* Checks if selected diagonal space is empty and thus invalid
	*************************************************************/
	private boolean isDiagonalEmpty(Move move, ChessPiece[][] board) {
		if (board[move.toRow][move.toColumn] == null) {
			return true;
		}
		if (board[move.fromRow][move.fromColumn].player() != 
		board[move.toRow][move.toColumn].player()) {
			return (false);

		}
		if (board[move.fromRow][move.fromColumn].player() == 
		board[move.toRow][move.toColumn].player()) {
			return false;
		}
		return true;
	}


	/************************************************************* 
	* Determines if the move is valid for a pawn piece
	*************************************************************/
	public boolean isValidMove(Move move, ChessPiece[][] board) {
		
		if (!super.isValidMove(move, board)) 
			return false;

		if (isFirstMove2Spaces(move, board) == true) 
			return true;
		
		if (isPieceMovingForward(move, board)) {
			if (isForwardPositionEmpty(move, board)) 
				return true;
		} else if (isPieceMovingDiagonally(move, board)) 
			if (isDiagonalEmpty(move, board) == false)
				return true;
			
		

		return false;
	}

}