
import java.util.ArrayList;
/**************************************************************
 * Functionality and interactions between ChessPieces
 * 
 * @author Richard
 * @author Steven
 * @version 3/13/2022
 **************************************************************/
public class ChessModel {	 
    private ChessPiece[][] board;
	private Player player;

	private ArrayList<Move> moves = new ArrayList<Move>();
	private ArrayList<ChessPiece> fromPieces = new ArrayList<ChessPiece>();
	private ArrayList<ChessPiece> toPieces = new ArrayList<ChessPiece>();
	// declare other instance variables as needed


	/************************************************************* 
	* Constructor that fills the chess board with chess pieces
	*************************************************************/
	public ChessModel() {
		board = new ChessPiece[8][8];
		player = Player.WHITE;

		// example of how to create and set a couple pieces on board initially
		board[0][3] = new Queen(Player.BLACK);
		board[0][4] = new King(Player.BLACK);
        board[7][3] = new Queen(Player.WHITE);
		board[7][4] = new King(Player.WHITE);
		/** New Pieces */
		board[0][0] = new Rook(Player.BLACK);
		board[0][7] = new Rook(Player.BLACK);
		board[7][0] = new Rook(Player.WHITE);
		board[7][7] = new Rook(Player.WHITE);
		board[0][1] = new Knight(Player.BLACK);
		board[0][6] = new Knight(Player.BLACK);
		board[7][1] = new Knight(Player.WHITE);
		board[7][6] = new Knight(Player.WHITE);
		board[0][2] = new Bishop(Player.BLACK);
		board[0][5] = new Bishop(Player.BLACK);
		board[7][2] = new Bishop(Player.WHITE);
		board[7][5] = new Bishop(Player.WHITE);

		for(int x = 0; x < numColumns(); x++) {
			board[1][x] = new Pawn(Player.BLACK);
		}
		for(int x = 0; x < numColumns(); x++) {
			board[6][x] = new Pawn(Player.WHITE);
		}
	}

	
	/************************************************************* 
	* Determines if the game has ended due to checkmate
	* or a lack of check free options
	*
	* @return boolean
	*************************************************************/
	public boolean isComplete() {
		if(!inCheck(currentPlayer())) {
			return false;
		}

		for(int row = 0; row < numRows(); row++) {
			for(int col = 0; col < numColumns(); col++) {
				if(pieceAt(row, col) != null) {
					if(pieceAt(row, col).player() == currentPlayer()) {
						for(int toRow = 0; toRow < numRows(); toRow++) {
							for(int toCol = 0; toCol < numRows(); toCol++) { 
								Move move = new Move(row, col, toRow, toCol);

								if(isValidMove(move)) {
									move(move);
									setNextPlayer();
									if(inCheck(currentPlayer())) {
										System.out.println("still in check");
										System.out.println(currentPlayer());
										undo(); 	
									} else {
										System.out.println("out of check");
										System.out.println(currentPlayer());
										undo();
										return false;
									}
									
								}
							}
						}
					}
				}
			}
		}

		return true;
	}

	
	/************************************************************* 
	* Determines if a determined move is possible for the 
	* specific chesspiece
	*
	* @param move
	* @return boolean
	*************************************************************/
	public boolean isValidMove(Move move) {
		if(move != null) {
			int fromRow = move.fromRow;
			int fromCol = move.fromColumn;
			if(fromRow < numRows() && fromRow >= 0) {
				if(fromCol < numColumns() && fromCol >= 0) {
					if(board[fromRow][fromCol] != null) {
						if(board[fromRow][fromCol]
								.isValidMove(move, board) == true) {
							if(board[fromRow][fromCol]
									.player() == this.player) {
						
								moves.add(move);
								fromPieces.add(board[fromRow][fromCol]);
								toPieces.add(board[move.toRow][move.toColumn]);
				
								setPiece(
									move.toRow, 
									move.toColumn, 
									board[fromRow][fromCol]
									);
								board[fromRow][fromCol] = null;
							
								if(!inCheck(currentPlayer())) {	
									setNextPlayer();
									undo();
									return true;
								} 

								setNextPlayer();
								undo();
								return false;
								
									
							}
						}
					}
				}
			}
		}
		
		return false;
	}


	/************************************************************* 
	* Moves the chesspiece when valid
	* 
	* @param move
	*************************************************************/
	public void move(Move move) {
		if(move != null) {
			if(isValidMove(move) == true) {
				if(pieceAt(move.fromRow, move.fromColumn) /** White player pawn promotion */
						.type().equals("Pawn")) {
							ChessPiece pawn = pieceAt(move.fromRow, move.fromColumn);
					if(move.toRow == 0 
							&& pawn.player() == Player.WHITE) {
						Queen queen = new Queen(Player.WHITE);

						moves.add(move);
						fromPieces.add(pawn);
						toPieces.add(board[move.toRow][move.toColumn]);						
						setPiece(0, move.toColumn, queen);
						board[move.fromRow][move.fromColumn] = null;
						setNextPlayer();
						return;
					}
					if(move.toRow == 7
							&& pawn.player() == Player.BLACK) {/** Black player pawn promotion */
						Queen queen = new Queen(Player.BLACK);

						moves.add(move);
						fromPieces.add(pawn);
						toPieces.add(board[move.toRow][move.toColumn]);
						setPiece(7, move.toColumn, queen);
						board[move.fromRow][move.fromColumn] = null;
						setNextPlayer();
						return;
					}
				}
				/**Adds the move to allow for undos */
				moves.add(move);
				fromPieces.add(board[move.fromRow][move.fromColumn]);
				toPieces.add(board[move.toRow][move.toColumn]);

				setPiece(
					move.toRow,
					move.toColumn, 
					board[move.fromRow][move.fromColumn]
					); /**Move the piece */
				board[move.fromRow][move.fromColumn] = null;
				setNextPlayer();
				return;
			}
		}
		if(move == null) {
			throw new NullPointerException();
		}
	}

	
	/************************************************************* 
	* Determines if the selected player is in check or not
	* 
	* @param p
	* @return boolean
	*************************************************************/
	public boolean inCheck(Player p) {
		if(p != null) {
			int toRow = -1;
			int toCol = -1;
			/** This loop finds the king */
			for (int row = 0; row < numRows(); row++) {
				for(int col = 0; col < numColumns(); col++) {
					if(board[row][col] != null) {
						if(board[row][col].type() == "King" 
								&& board[row][col].player() == p) {
							toRow = row;
							toCol = col;
						}
					}
				}
			}
			/** This loop determines if any move is capable of taking the king */
			if(toRow != -1 && toCol != -1) {
				for(int row = 0; row < numRows(); row++) {
					for(int col = 0; col < numColumns(); col++) {
						if(board[row][col] != null) {
							if(board[row][col].player() != p) {
								Move move = new Move(row, col, toRow, toCol);
								
								System.out.println(board[row][col].player());
								if(board[move.fromRow][move.fromColumn]
										.isValidMove(move, board)) {
									System.out.println(p.toString() + " in check");
									return true;
								}
							}
						}
					}
				}
			} else {
				System.out.println("error");
			}
		}
		System.out.println(p.toString() + " NOT in check");
		return false;
	}


	/************************************************************* 
	* Returns the current player
	*
	* @return Player
	*************************************************************/
	public Player currentPlayer() {
		return player;
	}

	
	/************************************************************* 
	* Gives the amount of rows on the chessboard
	*
	* @return int
	*************************************************************/
	public int numRows() {
		return 8;
	}

	
	/************************************************************* 
	* Gives the amount of columns on the chessboard
	*
	* @return int
	*************************************************************/
	public int numColumns() {
		return 8;
	}

	
	/************************************************************* 
	* Returns a ChessPiece at an inputted space on the chessboard
	*
	* @param row
	* @param column
	* @return ChessPiece
	*************************************************************/
	public ChessPiece pieceAt(int row, int column) {		
		return board[row][column];
	}


	/************************************************************* 
	* Switches the current player
	*************************************************************/
	public void setNextPlayer() {
		player = player.next();
	}

	
	/************************************************************* 
	* Sets a specific space on the board to a different ChessPiece
	*
	* @param row
	* @param column
	* @param piece
	*************************************************************/
	public void setPiece(int row, int column, ChessPiece piece) {
		board[row][column] = piece;
	}


	/************************************************************* 
	* Undoes the last move made on the board
	*************************************************************/
	public void undo() {
		if(moves.size() > 0) { 
			/**If more than one move has been made */
			Move move = moves.get(moves.size() - 1);
			ChessPiece fromPiece = fromPieces.get(fromPieces.size() - 1);
			ChessPiece toPiece = toPieces.get(toPieces.size() - 1);

			board[move.fromRow][move.fromColumn] = fromPiece;
			board[move.toRow][move.toColumn] = toPiece;
			moves.remove(moves.size() - 1);
			fromPieces.remove(fromPieces.size() - 1);
			toPieces.remove(toPieces.size() - 1);
			setNextPlayer();
		}

	}


	/************************************************************* 
	* Checks to see if any of the AI's pieces are in danger of
	* being captured
	*************************************************************/
	public boolean AIDanger() {
		int toRow = -1;
		int toCol = -1;
		for (int row1 = 0; row1 < numRows(); row1++) {
			for(int col1 = 0; col1 < numColumns(); col1++) { /**Find the king */
				if(board[row1][col1] != null) {
					if(board[row1][col1].type() != "King" 
							&& board[row1][col1].player() == Player.BLACK) {
						toRow = row1;
						toCol = col1;

						if(toRow != -1 && toCol != -1) {
							for(int row = 0; row < numRows(); row++) {
								for(int col = 0; col < numColumns(); col++) {
									if(board[row][col] != null) {
										if(board[row][col].player() != Player.WHITE) {
											Move move = new Move(row, col, toRow, toCol);

											if(board[move.fromRow][move.fromColumn]
													.isValidMove(move, board)) {
												System.out.println(Player.WHITE.toString() + " in check by AI");
												move(move);
											}
										}
									}
								}
							}
						} else {
							System.out.println("error");
						}
					}
				}
			}
		}
			
		
		
		return false;
	}


	/************************************************************* 
	* Functionality of an AI opponent
	*************************************************************/
	public void AI() {
		/* TODO: implement this method (manually graded)
		 * Write a simple AI set of rules in the following order. 
		 * a. Check to see if you are in check.
		 * 		i. If so, get out of check by moving the king or placing a piece to block the check 
		 * 
		 * b. Attempt to put opponent into check (or checkmate). 
		 * 		i. Attempt to put opponent into check without losing your piece
		 *		ii. Perhaps you have won the game. 
		 *
		 *c. Determine if any of your pieces are in danger, 
		 *		i. Move them if you can. 
		 *		ii. Attempt to protect that piece. 
		 *
		 *d. Move a piece (pawns first) forward toward opponent king 
		 *		i. check to see if that piece is in danger of being removed, if so, move a different piece.
		 */

		
		 /** AI == BLACK */

		if(currentPlayer() == Player.BLACK) {
		if(inCheck(currentPlayer())) {
		if(!isComplete()) {
			for(int row = 0; row < numRows(); row++) {
				for(int col = 0; col < numColumns(); col++) {
					if(pieceAt(row, col) != null) {
					if(pieceAt(row, col).player() == currentPlayer()) {
						for(int toRow = 0; toRow < numRows(); toRow++) {
							for(int toCol = 0; toCol < numRows(); toCol++) { 			
														Move move = new Move(row, col, toRow, toCol);
											if(isValidMove(move)) {
												move(move);
												setNextPlayer();
												if(inCheck(currentPlayer())) {
													System.out.println("AI still in check");
													undo();	
												} else {
													System.out.println("AI out of check");
												}
												
											}
										}
									}
								}
							}
						}
					}
				}
			}
			if(!inCheck(Player.WHITE)) {/**need to add capture protection */
				int toRow = -1;
				int toCol = -1;
				for (int row = 0; row < numRows(); row++) {
					for(int col = 0; col < numColumns(); col++) {
						if(board[row][col] != null) {
							if(board[row][col].type() == "King" && board[row][col].player() == Player.WHITE) {
								toRow = row;
								toCol = col;
							}
						}
					}
				}
				if(toRow != -1 && toCol != -1) {
					for(int row = 0; row < numRows(); row++) {
						for(int col = 0; col < numColumns(); col++) {
							if(board[row][col] != null) {
								if(board[row][col].player() != Player.WHITE) {
									Move move = new Move(row, col, toRow, toCol);
									if(board[move.fromRow][move.fromColumn].isValidMove(move, board)) {
										System.out.println(Player.WHITE.toString() + " in check by AI");
										move(move);
									}
								}
							}
						}
					}
				} else {
					System.out.println("error");
				}
			
			} else {

			}	
		}
	}
}
