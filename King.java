package Chess;
public class King extends ChessPiece {

	public King(Player player) {
		super(player);
	}

	
    /** 
     * @return String
     */
    public String type() {
		return "King";
	}
	

	
    /** 
     * @param move
     * @param board
     * @return boolean
     */
    private boolean isMovementASingleUnit(Move move, ChessPiece[][] board) {
		if(Math.abs(move.fromColumn - move.toColumn) < 2
		 		&& Math.abs(move.fromRow - move.toRow) < 2) {
			return true;
		}
		return false;
	}


	
	
    /** 
     * @param move
     * @param board
     * @return boolean
     */
    public boolean isValidMove(Move move, ChessPiece[][] board) {
		if(super.isValidMove(move, board)) {
			if(isMovementASingleUnit(move, board)) {
				return true;
			}	
		}
		return false;
	}
}
						/** save conflict so below was the other code */
/*public class King extends ChessPiece {

    public King(Player player) {
        super(player);
    }

    public String type() {
        return "King";
    }
    

    private boolean isMovementASingleUnit(Move move, ChessPiece[][] board) {
        if(Math.abs(move.fromColumn - move.toColumn) < 2
                && Math.abs(move.fromColumn - move.fromRow) < 2) {
            return true;
        }
        return false;
    }

    private boolean isFriendlyFireOccuring(Move move, ChessPiece[][] board) {
        if(board[move.toRow][move.toColumn] != null) {
            if(board[move.toRow][move.toColumn].player() != board[move.fromRow][move.fromColumn].player()) {
                return true;
            }
        }
        return false;
    }
    private boolean isPieceQueen(Move move, ChessPiece[][] board) {
        if(board[move.toRow][move.toColumn].type() == "Queen") {
            return true;
        }
        return false;
    }
    
    public boolean isValidMove(Move move, ChessPiece[][] board) {
        // TODO:  implement this method
        if(super.isValidMove(move, board)) {
            if(isMovementASingleUnit(move, board)) {
                if(!isFriendlyFireOccuring(move, board)) {
                    if(!isPieceQueen(move, board)) {
                        return true;
                    }           
                }
            }   
        }
        return false;
    }
}
*/
