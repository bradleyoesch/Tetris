import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.Popup;
import java.io.*;
import java.util.Random;

/**
 * The panel for the Tetris board.
 * 
 * @author Bradley Oesch
 * @version 1.0
 */

public class PlayingPanel extends JPanel {
	
	private int delay;
	private static Timer timer;
	private static Random rand = new Random();
	private PlayingField board;
	private static SidePanel sidepanel;
	private Piece piece;
	
	/**
	 * Constructor that sets up the panel.
	 *
	 * @param sidepanel The other panel that displays info.
	 */
	
	public PlayingPanel(SidePanel sidepanel) {
		addKeyListener(new KeyboardListener());
		setFocusable(true);
		delay = 500;
		timer = new Timer(delay, new TimerListener());
		timer.start();
		
		this.sidepanel = sidepanel;
		board = sidepanel.getBoard();
		piece = sidepanel.createPiece();
		
		setPreferredSize(new Dimension(board.getWidth(), board.getHeight()));
	}
	
	/**
	 * Lowers the delay for the timer to make the pieces drop faster.
	 */
	
	/**
	 * Tells you that you lost then restars the game.
	 */
	
	public void youLose() {
		JOptionPane.showMessageDialog(this, "YOU LOST!");
		board = new PlayingField();
		sidepanel.resetBoard();
		piece = sidepanel.createPiece();
	}
	
	/**
	 * Paints the panel.
	 * 
	 * @param g The graphics.
	 */
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		board.draw(g);
		piece.draw(g, board.getWSize(), board.getHSize());
		
		if (sidepanel.getChangeDelay() && board.getScore() % 5 == 0) {
			if (delay > 50) {
				delay -= 25;
				timer.setDelay(delay);
				timer.restart();
			}
		}
	}

	/**
	 * Keyboard listener. It will move the piece left, right, down.
	 * 
	 * @author Bradley Oesch
	 * @version 1.0
	 */
	
	private class KeyboardListener implements KeyListener {
		
		public void keyPressed(KeyEvent e) {
			if (! sidepanel.isPaused()) {
				switch (e.getKeyCode()) {
					case KeyEvent.VK_LEFT:
						piece.move('l');
						repaint();
						break;
					case KeyEvent.VK_RIGHT:
						piece.move('r');
						repaint();
						break;
					case KeyEvent.VK_UP:
						boolean move = true;
						while (move) {
							move = piece.move('d');
						}
					case KeyEvent.VK_DOWN:
						piece.move('d');
						repaint();
						timer.restart();
						break;
					case KeyEvent.VK_SPACE:
						piece.move('s');
						repaint();
						break;
				}
			}
		}
		public void keyTyped(KeyEvent e) {}
		public void keyReleased(KeyEvent e) {}
	}

	/**
	 * Timer listener. Moves the piece, checks for clears and loss.
	 * 
	 * @author Bradley Oesch
	 * @version 1.0
	 */
	
	private class TimerListener implements ActionListener {
		
		public void actionPerformed(ActionEvent event) {
			
			if (! sidepanel.isPaused()) {
				boolean down = piece.move('d');
				if (! down) {
					piece = sidepanel.createPiece();
				}
				board.checkForClears();
				sidepanel.updateGUI();
				sidepanel.setBoard(board);
				repaint();
				boolean lose = board.isLossConditionMet();
				if (lose) {
					youLose();
				}
			} else {
				board = sidepanel.getBoard();
			}
		}
	}
}