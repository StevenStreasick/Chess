package Chess;
public abstract class ChessPiece {

	private Player owner;

	protected ChessPiece(Player player) {
		this.owner = player;
	}

	public abstract String type();

	
	/** 
	 * @return Player
	 */
	public Player player() {
		return owner;
	}

	/** isValidMove helper methods */
	/** Verifies piece is not moving to the same position */
	private boolean hasPieceMoved(Move move) {
		if(move.toRow != move.fromRow || move.toColumn != move.fromColumn) {
			return true;
		}
		return false;
	}

	/** Verifies piece is moving to position on the board */
	private boolean isOnBoard(Move move, ChessPiece[][] board) {
	
		if(move.toRow >= 0 && move.toRow < board.length && 
			move.toColumn >= 0 && move.toColumn < board.length) {
			return true; //Need to Verify that this piece is at starting location in move
							 //Need to Verify that ending location does not contain a piece belonging to the same player as this piece
		}
		return false;
		//return false;		
	}

	/** Verifies that the piece being moved is at the expected position */
	//May or may not work... 
	private boolean isInitialPositionAccurate(Move move, ChessPiece[][] board) {
		if(this == board[move.fromRow][move.fromColumn]) {
			
			return true; 
		}
		return false;
	}

	/** Verifies that the piece is not being moved to a position that contains a piece of the same color */
	private boolean positionNotSameColor(Move move, ChessPiece[][] board) {
		if(board[move.toRow][move.toColumn] == null) {
			return true;
		} else {
			if(this.player() != board[move.toRow][move.toColumn].player()) {
				return true;
			}
		}
		return false;
	}
	
	
	/** 
	 * @param move
	 * @param board
	 * @return boolean
	 */
	public boolean isValidMove(Move move, ChessPiece[][] board) {
		//TODO: implement this method
		//testing each individual one
		if(isOnBoard(move, board) == true) {
			//System.out.println("is on board");
		} else {
			//System.out.println("not on board");
			return false;
		}
		if(hasPieceMoved(move) == true) {
			//System.out.println("has moved");
		} else {
			//System.out.println("hasn't moved");
		}
		if(isInitialPositionAccurate(move, board) == true) {
			//System.out.println("position accurate");
		} else {
			//System.out.println("position not accurate");
		}
		if(positionNotSameColor(move, board) == true) {
			//System.out.println("postition not same color");
		} else {
			//System.out.println("position same color(bad)");
		}
		if(isOnBoard(move, board)) {
			if(hasPieceMoved(move) && 
				isInitialPositionAccurate(move, board) && positionNotSameColor(move, board)) {

				return true;
			}
		}
		
		
		return false;
		//Method needs testing
	}
}
