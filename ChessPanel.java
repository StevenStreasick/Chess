import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ChessPanel extends JPanel {
    public enum GUIcodes {
        NoMessage, NotYourTurn, inCheck, Checkmate
    
    }

	private JButton[][] board;
	private ChessModel model;
	private ImageIcon bRook;
	private ImageIcon bBishop;
	private ImageIcon bQueen;
	private ImageIcon bKing;
	private ImageIcon bPawn;
	private ImageIcon bKnight;
	private ImageIcon wRook;
	private ImageIcon wBishop;
	private ImageIcon wQueen;
	private ImageIcon wKing;
	private ImageIcon wPawn;
	private ImageIcon wKnight;
	private GUIcodes messageCode;
	// you can declare other instance variables as needed

	private listener listener;

	public ChessPanel() {
		messageCode = GUIcodes.NoMessage;
		model = new ChessModel();
		board = new JButton[model.numRows()][model.numColumns()];
		listener = new listener();
		createIcons();

		JPanel boardpanel = new JPanel();
		boardpanel.setLayout(new GridLayout(model.numRows(), model.numColumns(), 1, 1));

		for (int r = 0; r < model.numRows(); r++) {
			for (int c = 0; c < model.numColumns(); c++) {
				if (model.pieceAt(r, c) == null) {
					board[r][c] = new JButton("", null);
					board[r][c].addActionListener(listener);
				} else if (model.pieceAt(r, c).player() == Player.WHITE)
					placeWhitePieces(r, c);
				else if (model.pieceAt(r, c).player() == Player.BLACK) {
					placeBlackPieces(r, c);
				}
				setBackGroundColor(r, c);
				boardpanel.add(board[r][c]);
			}
		}
		add(boardpanel, BorderLayout.WEST);
		boardpanel.setPreferredSize(new Dimension(600, 600));
	}

	private void setBackGroundColor(int r, int c) {
		if ((c % 2 == 1 && r % 2 == 0) || (c % 2 == 0 && r % 2 == 1)) {
			board[r][c].setBackground(Color.LIGHT_GRAY);
		} else if ((c % 2 == 0 && r % 2 == 0) || (c % 2 == 1 && r % 2 == 1)) {
			board[r][c].setBackground(Color.WHITE);
		}
	}

	private void placeBlackPieces(int r, int c) {
		if (model.pieceAt(r, c).type().equals("Pawn")) {
			board[r][c] = new JButton(null, bPawn);
			board[r][c].addActionListener(listener);
		}
		if (model.pieceAt(r, c).type().equals("Rook")) {
			board[r][c] = new JButton(null, bRook);
			board[r][c].addActionListener(listener);
		}
		if (model.pieceAt(r, c).type().equals("Knight")) {
			board[r][c] = new JButton(null, bKnight);
			board[r][c].addActionListener(listener);
		}
		if (model.pieceAt(r, c).type().equals("Bishop")) {
			board[r][c] = new JButton(null, bBishop);
			board[r][c].addActionListener(listener);
		}
		if (model.pieceAt(r, c).type().equals("Queen")) {
			board[r][c] = new JButton(null, bQueen);
			board[r][c].addActionListener(listener);
		}
		if (model.pieceAt(r, c).type().equals("King")) {
			board[r][c] = new JButton(null, bKing);
			board[r][c].addActionListener(listener);
		}
	}

	private void placeWhitePieces(int r, int c) {
		if (model.pieceAt(r, c).type().equals("Pawn")) {
			board[r][c] = new JButton(null, wPawn);
			board[r][c].addActionListener(listener);
		}
		if (model.pieceAt(r, c).type().equals("Rook")) {
			board[r][c] = new JButton(null, wRook);
			board[r][c].addActionListener(listener);
		}
		if (model.pieceAt(r, c).type().equals("Knight")) {
			board[r][c] = new JButton(null, wKnight);
			board[r][c].addActionListener(listener);
		}
		if (model.pieceAt(r, c).type().equals("Bishop")) {
			board[r][c] = new JButton(null, wBishop);
			board[r][c].addActionListener(listener);
		}
		if (model.pieceAt(r, c).type().equals("Queen")) {
			board[r][c] = new JButton(null, wQueen);
			board[r][c].addActionListener(listener);
		}
		if (model.pieceAt(r, c).type().equals("King")) {
			board[r][c] = new JButton(null, wKing);
			board[r][c].addActionListener(listener);
		}
	}

	private void createIcons() {
		// Sets the ImageIcon for the Black Player Pieces
		bRook = new ImageIcon("./src/bRook.png");  
		bBishop = new ImageIcon("./src/bBishop.png"); 
		bQueen = new ImageIcon("./src/bQueen.png");  
		bKing = new ImageIcon("./src/bKing.png");
		bPawn = new ImageIcon("./src/bPawn.png");
		bKnight = new ImageIcon("./src/bKnight.png");

		// Sets the Image for white player pieces
		wRook = new ImageIcon("./src/wRook.png");
		wBishop = new ImageIcon("./src/wBishop.png");
		wQueen = new ImageIcon("./src/wQueen.png");
		wKing = new ImageIcon("./src/wKing.png");
		wPawn = new ImageIcon("./src/wPawn.png");
		wKnight = new ImageIcon("./src/wKnight.png");
	}

	// method that updates the board
	private void displayBoard() {
		for (int r = 0; r < 8; r++) {
			for (int c = 0; c < 8; c++) 
				if (model.pieceAt(r, c) == null) 
					board[r][c].setIcon(null);
				else 
					if (model.pieceAt(r, c).player() == Player.WHITE) {
						if (model.pieceAt(r, c).type().equals("Pawn")) 
							board[r][c].setIcon(wPawn);

						if (model.pieceAt(r, c).type().equals("Rook")) 
							board[r][c].setIcon(wRook);

						if (model.pieceAt(r, c).type().equals("Knight")) 
							board[r][c].setIcon(wKnight);

						if (model.pieceAt(r, c).type().equals("Bishop")) 
							board[r][c].setIcon(wBishop);

						if (model.pieceAt(r, c).type().equals("Queen")) 
							board[r][c].setIcon(wQueen);

						if (model.pieceAt(r, c).type().equals("King")) 
							board[r][c].setIcon(wKing);

					} else 
						if (model.pieceAt(r, c).player() == Player.BLACK) {
							if (model.pieceAt(r, c).type().equals("Pawn")) 
								board[r][c].setIcon(bPawn);

							if (model.pieceAt(r, c).type().equals("Rook")) 
								board[r][c].setIcon(bRook);

							if (model.pieceAt(r, c).type().equals("Knight")) 
								board[r][c].setIcon(bKnight);

							if (model.pieceAt(r, c).type().equals("Bishop")) 
								board[r][c].setIcon(bBishop);

							if (model.pieceAt(r, c).type().equals("Queen")) 
								board[r][c].setIcon(bQueen);

							if (model.pieceAt(r, c).type().equals("King")) 
								board[r][c].setIcon(bKing);
						}
		}
		repaint();
	}

	// inner class that represents action listener for buttons
	private class listener implements ActionListener {
        boolean pieceChosen = false;
		int fromRow = -1;
		int fromColumn = -1;
		int toRow = -1;
		int toColumn = -1;
		Move tempMove;
		Color tempColor;

		public void actionPerformed(ActionEvent event) {
			for (int r = 0; r < model.numRows(); r++) {
				for (int c = 0; c < model.numColumns(); c++) {
					if (board[r][c] == event.getSource()) {
						if (!pieceChosen) {
							if (model.pieceAt(r, c) != null) 
								if (model.pieceAt(r, c).player() == model.currentPlayer()) {
									selectPiece(r, c);
								} else {
									messageCode = GUIcodes.NotYourTurn;
									displayMessage();
								}
						}
						else 
							if (pieceChosen) {
								tryMoveTo(r, c);
							}
					}
                }
            }
		}

		private void tryMoveTo(int r, int c) {
			board[fromRow][fromColumn].setBackground(tempColor);
			toRow = r;
			toColumn = c;
			tempMove = new Move(fromRow, fromColumn, toRow, toColumn);
			if (model.isValidMove(tempMove)) {
				model.move(tempMove);
				//model.setNextPlayer();//NEED TO DISABLE

				if (model.inCheck(model.currentPlayer())) {
					messageCode = GUIcodes.inCheck;
					if (model.isComplete()) 
						messageCode = GUIcodes.Checkmate;
				}
				displayBoard();
			}
			pieceChosen = false;
			displayMessage();

			// After implementing AI method in ChessModel.java, uncomment to turn on AI
            //AITurn();
		}
		
        public void AITurn() {
			if (model.currentPlayer() == Player.BLACK) {
				model.AI();
    		    messageCode = GUIcodes.NoMessage;
				model.setNextPlayer();
				displayBoard();

				if (model.inCheck(model.currentPlayer())) {
					messageCode = GUIcodes.inCheck;
					if (model.isComplete())
						messageCode = GUIcodes.Checkmate;
				}
				displayBoard();
				pieceChosen = false;
				displayMessage();
			}
        }

		private void selectPiece(int r, int c) {
			tempColor = board[r][c].getBackground();
			board[r][c].setBackground(Color.CYAN);
			fromRow = r;
			fromColumn = c;
			messageCode = GUIcodes.NoMessage;
			pieceChosen = true;
		}

		private void displayMessage() {
			if (messageCode == GUIcodes.NotYourTurn) {
				JOptionPane.showConfirmDialog(null, "It is not your turn.", 
						"Warning", JOptionPane.CLOSED_OPTION);
			}
            // TODO:  add other code here to display other dialogs
			//if (messageCode == GUIcodes.Checkmate) {
			//	JOptionPane.showConfirmDialog(null, "Checkmate", "Game Over", JOptionPane.CLOSED_OPTION);
			//}
			//if (messageCode ==  GUIcodes.inCheck) {
			//	JOptionPane.showConfirmDialog(null, "You're in Check", "Warning", JOptionPane.CLOSED_OPTION);
			//}
			//if (messageCode == GUIcodes.NoMessage) {
			//	JOptionPane.showConfirmDialog(null, "Invalid move", "Warning", JOptionPane.CLOSED_OPTION);
			//}

		}

	}
}