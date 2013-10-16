import java.awt.*;
import javax.swing.*;
import java.io.*;

/**
 * The Tetris board itself. Holds the number of columns, rows, score, and level.
 * 
 * @author Bradley Oesch
 * @version 1.0
 */

public class PlayingField implements Serializable {
	
	private int rows, cols, wSize, hSize, off, width, height, score, level, clearCount;
	private static Color[][] board;
	
	public PlayingField() {
		rows = 21;
		cols = 13;
		wSize = 20;
		hSize = 20;
		off = 1;
		width = (cols-off)*wSize;
		height = (rows-off)*hSize;
		board = new Color[rows][cols];
		score = 0;
		level = 1;
		clearCount = 0;
	}
	
	/**
	 * Checks if any rows are completely full.
	 */
	
	public void checkForClears() {
		int count = 0;
		for (int i=rows-1; i>=off; i--) {
			for (int j=off; j<cols; j++) {
				if (board[i][j] != null) {
					count++;
				}
			}
			if (count == cols-off) {
				clearRow(i);
				score += level;
				clearCount++;
			}
			count = 0;
		}
		if (clearCount == 5) {
			level++;
			clearCount = 0;
		}
	}
	
	/**
	 * Clears the current row and sets it as the row above.
	 */
	
	public void clearRow(int x) {
		for (int i=x; i>0; i--) {
			for (int j=off; j<cols; j++) {
				board[i][j] = board[i-1][j];
			}
		}
	}
	
	/**
	 * Checks if the user lost the game.
	 *
	 * @return Whether the user lost the game.
	 */
	
	public boolean isLossConditionMet() {
		for (int j=off; j<cols; j++) {
			if (board[off][j] != null) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Checks if the current tile is vacant.
	 *
	 * @return If the current tile is vacant.
	 */
	
	public boolean isTileVacant(int i, int j) {
		if (board[i][j] == null)
			return true;
		else
			return false;
	}
	
	/**
	 * Fills the tile on the board.
	 */
	
	public void fillTile(int i, int j, Color color) {
		board[i][j] = color;
	}
	
	/**
	 * Draws the board.
	 */
	
	public void draw(Graphics g) {
		for (int i=off; i<rows; i++) {
			for (int j=off; j<cols; j++) {
				if (board[i][j] != null) {
					g.setColor(board[i][j]);
				} else {
					g.setColor(new Color(35, 35, 35));
				}
				g.fill3DRect(wSize*(j-off), hSize*(i-off), wSize, hSize, true);
			}
		}
	}
	
	/**
	 * Returns the level.
	 *
	 * @return The level.
	 */
	
	public int getLevel() {
		return level;
	}
	
	/**
	 * Returns the tile width.
	 *
	 * @return The tile width.
	 */
	
	public int getWSize() {
		return wSize;
	}
	
	/**
	 * Returns the tile height.
	 *
	 * @return The tile height.
	 */
	
	public int getHSize() {
		return hSize;
	}
	
	/**
	 * Returns the width.
	 *
	 * @return The width.
	 */
	
	public int getWidth() {
		return width;
	}
	
	/**
	 * Returns the height.
	 *
	 * @return The height.
	 */
	
	public int getHeight() {
		return height;
	}
	
	/**
	 * Returns the off amount (number of rows/cols that are part of the board
	 * but not painted onto the panel.
	 *
	 * @return The off amount.
	 */
	
	public int getOff() {
		return off;
	}
	
	/**
	 * Returns the number of rows.
	 *
	 * @return The number of rows.
	 */
	
	public int getRows() {
		return rows;
	}
	
	/**
	 * Returns the number of columns.
	 *
	 * @return The number of columns.
	 */
	
	public int getCols() {
		return cols;
	}
	
	/**
	 * Returns the score.
	 *
	 * @return The score.
	 */
	
	public int getScore() {
		return score;
	}
}