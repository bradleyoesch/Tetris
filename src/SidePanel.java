import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.Random;

/**
 * The side panel. It contains the score, level, next piece, pause button,
 * save button, and load button.
 * 
 * @author Bradley Oesch
 * @version 1.0
 */

public class SidePanel extends JPanel {
	
	private int score, lastScore, level;
	private boolean isPaused, changeDelay;
	private PlayingField board;
	private JLabel scoreLabel, levelLabel;
	private JButton pauseButton, saveButton, loadButton;
	private static NextPiecePanel nextPiecePanel;
	private Piece thisPiece, nextPiece;
	private static Random rand = new Random();
	
	public SidePanel() {
		setBackground(new Color(200,200,200));
		board = new PlayingField();
		setPreferredSize(new Dimension(120, board.getHeight()));
		
		level = 1;
		score = 0;
		isPaused = false;
		changeDelay = false;
		lastScore = 0;
		
		levelLabel = new JLabel("Level: " + board.getLevel());
		add(levelLabel);
		
		scoreLabel = new JLabel("Points: " + board.getScore());
		add(scoreLabel);
		
		nextPiecePanel = new NextPiecePanel();
		add(nextPiecePanel);
		thisPiece = createPiece();
		
		pauseButton = new JButton("Pause Game");
		pauseButton.addActionListener(new PauseListener());
		add(pauseButton);
		pauseButton.setFocusable(false);
		
		saveButton = new JButton("Save Game");
		saveButton.addActionListener(new SaveListener());
		add(saveButton);
		saveButton.setFocusable(false);
		saveButton.setEnabled(false);
		
		loadButton = new JButton("Load Game");
		loadButton.addActionListener(new LoadListener());
		add(loadButton);
		loadButton.setFocusable(false);
		loadButton.setEnabled(false);
	}
	
	/**
	 * Updates the buttons and repaints the other panel.
	 */
	
	public void updateGUI() {
		changeDelay = false;
		score = board.getScore();
		if (score > lastScore) {
			changeDelay = true;
			lastScore = score;
		}
		if (isPaused) {
			pauseButton.setText("Play Game");
			saveButton.setEnabled(true);
			loadButton.setEnabled(true);
		} else {
			pauseButton.setText("Pause Game");
			saveButton.setEnabled(false);
			loadButton.setEnabled(false);
		}
		levelLabel.setText("Level: " + board.getLevel());
		scoreLabel.setText("Points: " + board.getScore());
		nextPiecePanel.repaint();
	}
	
	/**
	 * Sets current piece to old piece and gets a new old piece.
	 * 
	 * @return The current piece.
	 */
	
	public Piece createPiece() {
		thisPiece = nextPiece;
		
		int pieceNum = rand.nextInt(7);
		if (pieceNum == 0)
			nextPiece = new LinePiece();
		else if (pieceNum == 1)
			nextPiece = new SquarePiece();
		else if (pieceNum == 2)
			nextPiece = new L1Piece();
		else if (pieceNum == 3)
			nextPiece = new L2Piece();
		else if (pieceNum == 4)
			nextPiece = new Z1Piece();
		else if (pieceNum == 5)
			nextPiece = new Z2Piece();
		else
			nextPiece = new TPiece();
		
		return thisPiece;
	}
	
	/**
	 * Returns if the game is paused.
	 *
	 * @return If the game is paused.
	 */
	
	public boolean isPaused() {
		return isPaused;
	}
	
	/**
	 * Returns if the delay should be changed.
	 *
	 * @return If the delay should be changed.
	 */
	
	public boolean getChangeDelay() {
		return changeDelay;
	}
	
	/**
	 * Returns the board.
	 */
	
	public void resetBoard() {
		board = new PlayingField();
		updateGUI();
	}
	
	/**
	 * ihashvaljvaivruviulrinriurenirealianiinlinlrnira
	 * ihashvaljvaivruviulrinriurenirealianiinlinlrnira
	 * ihashvaljvaivruviulrinriurenirealianiinlinlrnira
	 * ihashvaljvaivruviulrinriurenirealianiinlinlrnira
	 * ihashvaljvaivruviulrinriurenirealianiinlinlrnira
	 * ihashvaljvaivruviulrinriurenirealianiinlinlrnira
	 */
	
	public void setBoard(PlayingField board) {
		this.board = board;
	}
	
	/**
	 * Returns the board.
	 *
	 * @return The board.
	 */
	
	public PlayingField getBoard() {
		return board;
	}

	/**
	 * The pause listener. Pauses the game and updates the GUI.
	 * 
	 * @author Bradley Oesch
	 * @version 1.0
	 */
	
	private class PauseListener implements ActionListener {
	
		public void actionPerformed(ActionEvent e) {
			if (isPaused == true) {
				isPaused = false;
			} else {
				isPaused = true;
			}
			updateGUI();
		}
	}

	/**
	 * The panel for the upcoming piece. Just draws it.
	 * 
	 * @author Bradley Oesch
	 * @version 1.0
	 */
	
	private class NextPiecePanel extends JPanel {
		
		public NextPiecePanel() {
			setBackground(new Color(35, 35, 35));
			setPreferredSize(new Dimension(80, 80));
		}
		
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			nextPiece.draw(g, board.getWSize(), board.getHSize(), true);
		}
	}

	/**
	 * The listener for the save button. Saves the board (which contains the
	 * level and score), the current piece, and the next piece.
	 * 
	 * @author Bradley Oesch
	 * @version 1.0
	 */
	
	private class SaveListener implements ActionListener {
	
		public void actionPerformed(ActionEvent event) {
			try {
				FileOutputStream fileOut = new FileOutputStream("savedGame.ser");
				ObjectOutputStream oos = new ObjectOutputStream(fileOut);
				oos.writeObject(board);
				oos.writeObject(thisPiece);
				oos.writeObject(nextPiece);
				oos.close();
			} catch (FileNotFoundException e) {
			} catch (IOException e) {
			}
			saveButton.setEnabled(false);
		}
	}

	/**
	 * The listener for the load button. It loads the board (which contains the
	 * level and score), the current piece, and upcoming piece.
	 * Although it does not draw the board or current piece, it does set and show
	 * the upcoming piece and set the level and score. I can't reference PlayingPanel
	 * from this class, and switching the parameters would yield the same issue
	 * from the other direcion. I have tried everything I can think of using getters
	 * and setters in every location but PlayingField refuses to take it.
	 * 
	 * @author Bradley Oesch
	 * @version 1.0
	 */
	
	private class LoadListener extends JPanel implements ActionListener {
	
		public void actionPerformed(ActionEvent event) {
			try {
				FileInputStream fileIn = new FileInputStream("savedGame.ser");
				ObjectInputStream ois = new ObjectInputStream(fileIn);
				board = (PlayingField) ois.readObject();
				thisPiece = (Piece) ois.readObject();
				nextPiece = (Piece) ois.readObject();
				ois.close();
			} catch (ClassNotFoundException e) {
			} catch (FileNotFoundException e) {
			} catch (IOException e) {
			}
			nextPiecePanel.repaint();
			loadButton.setEnabled(false);
		}
	}
}