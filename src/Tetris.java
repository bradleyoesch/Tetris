import java.awt.BorderLayout;

import javax.swing.JFrame;

/**
 * Runs the Tetris game.
 * 
 * @author Bradley Oesch
 * @version 1.0
 */

public class Tetris {

	public static void main(String[] args) {
		JFrame frame = new JFrame("TETRIS");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SidePanel sidepanel = new SidePanel();
		frame.getContentPane().add(sidepanel, BorderLayout.WEST);
		frame.getContentPane().add(new PlayingPanel(sidepanel));
		frame.setResizable(false);
		frame.setVisible(true);
		frame.pack();
	}
}