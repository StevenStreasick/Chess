package Chess;
public class Knight extends ChessPiece {

	public Knight(Player player) {
		super(player);
	}

	
	/** 
	 * @return String
	 */
	public String type() {
		return "Knight";
	}

	
	/** 
	 * @param move
	 * @param board
	 * @return boolean
	 */
	private boolean isKnightMovement(Move move, ChessPiece[][] board) {
		int columnMagnitude = Math.abs(move.fromColumn - move.toColumn);
		int rowMagnitude = Math.abs(move.fromRow - move.toRow);
		if ((columnMagnitude == 2 || rowMagnitude == 2) && columnMagnitude + rowMagnitude == 3) {
			return true;
		}
		return false;
	}

	
	/** 
	 * @param move
	 * @param board
	 * @return boolean
	 */
	public boolean isValidMove(Move move, ChessPiece[][] board){
		// TODO:  implement this method
		if(super.isValidMove(move, board)) {
			if(isKnightMovement(move, board)) {
				return true;
			}
		}

		return false;	
	}

}
