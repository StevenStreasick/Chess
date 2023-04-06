/**************************************************************
 * Functionality for parent ChessPiece
 * 
 * @author Richard
 * @author Steven
 * @version 3/13/2022
 **************************************************************/
public abstract class ChessPiece {

	private Player owner;


	/*************************************************************
	* Constructor sets the piece's owner to the current player
	*************************************************************/
	protected ChessPiece(Player player) {
		this.owner = player;
	}

	public abstract String type();


	/*************************************************************
	* Returns the player that owns this piece
	*************************************************************/
	public Player player() {
		return owner;
	}


	/*************************************************************
	* Verifies that the ChessPiece has moved from the previous
	* position
	*************************************************************/
	private boolean hasPieceMoved(Move move) {
		if (move.toRow != move.fromRow || move.toColumn != move.fromColumn) {
			return true;
		}

		return false;
	}


	/*************************************************************
	* Verifies the piece is moving to a position on the board
	*************************************************************/
	private boolean isOnBoard(Move move, ChessPiece[][] board) {

		if (move.toRow >= 0 && move.toRow < board.length &&
				move.toColumn >= 0 && move.toColumn < board.length) {
			return true;
		}
		
		return false;
	}


	/*************************************************************
	* Verifies that the piece being moved is at the expected 
	* position
	*************************************************************/
	private boolean isInitialPositionAccurate(Move move, ChessPiece[][] board) {
		if (this == board[move.fromRow][move.fromColumn]) {
			return true;
		}

		return false;
	}



	/*************************************************************
	* Verifies that the piece is not being moved to a position
	* that contains a piece of the same color
	*************************************************************/
	private boolean positionNotSameColor(Move move, ChessPiece[][] board) {
		if (board[move.toRow][move.toColumn] == null) {
			return true;
		} 

		if (this.player() != board[move.toRow][move.toColumn].player()) {
			return true;
		}
		
		return false;
	}


	/*************************************************************
	* Verifies that the piece can move to the selected
	* position
	*************************************************************/
	public boolean isValidMove(Move move, ChessPiece[][] board) {
		if (!isOnBoard(move, board)) 
			return false;

		if (!(hasPieceMoved(move) &&
				isInitialPositionAccurate(move, board) && 
				positionNotSameColor(move, board))) 
			return false;

		return true;
	}
}