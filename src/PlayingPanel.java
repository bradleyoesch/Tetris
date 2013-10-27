import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * The panel for the Tetris board.
 * 
 * @author Bradley Oesch
 * @version 1.0
 */
public class PlayingPanel extends JPanel {
	
	private static final long serialVersionUID = 4209579883455837410L;
	private int delay;
	private static Timer timer;
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
		
		PlayingPanel.sidepanel = sidepanel;
		board = sidepanel.getBoard();
		piece = sidepanel.createPiece();
		
		setPreferredSize(new Dimension(board.getWidth(), board.getHeight()));
	}
	
	/**
	 * Tells you that you lost then restarts the game.
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
		piece.draw(g, board.getWSize(), board.getHSize(), true);
		
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