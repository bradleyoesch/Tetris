import java.awt.*;
import javax.swing.*;
import java.io.*;

/**
 * The piece. Holds the current row, column, rotation, color, and model.
 * 
 * @author Bradley Oesch
 * @version 1.0
 */

abstract public class Piece implements Serializable {
	
	protected int row, col, rot = 0;
	protected int rots, rows, cols;
	protected Color color;
	protected int[][][] model;
	protected static PlayingField board = new PlayingField();
	
	public Piece() {
		row = 0;
		col = board.getCols()/2;
	}
	
	/**
	 * Moves the piece one left, right, or down.
	 *
	 * @param direction Which direction the piece is to move.
	 * @return Whether the user lost the game.
	 */
	
	public boolean move(char direction) {
		int count1 = 0, count2 = 0, icount = 0;
		boolean canMove = true;
		switch (direction) {
			case 'l':
				for (int i=0; i<rows; i++) {
					for (int j=0; j<cols; j++) {
						if (model[rot][i][j] == 1) {
							if (col+j > board.getOff()) {
								if (board.isTileVacant(row+i,col+j-1))
									count1++;
							}
						}
					}
				}
				if (count1 == 4) {
					col--;
					return true;
				} else {
					return false;
				}
			case 'r':
				for (int i=0; i<rows; i++) {
					for (int j=0; j<cols; j++) {
						if (model[rot][i][j] == 1) {
							if (col+j+1 < board.getCols()) {
								if (board.isTileVacant(row+i,col+j+1))
									count1++;
							}
						}
					}
				}
				if (count1 == 4) {
					col++;
					return true;
				} else {
					return false;
				}
			case 'd':
				for (int i=0; i<rows; i++) {
					for (int j=0; j<cols; j++) {
						if (model[rot][i][j] == 1){
							icount = i; //finds lowest row
							if (i+1 < rows && model[rot][i+1][j] == 0) {
								count1++;
								if (row+icount+1 < board.getRows() && board.isTileVacant(row+i+1, col+j)) {
									count2++;
								} 
							}
						}
					}
				}
				for (int j=0; j<cols; j++) {
					if (model[rot][icount][j] == 1) {
						count1++;
						if (row+icount+1 < board.getRows()) {
							if (board.isTileVacant(row+icount+1, col+j)) {
								count2++;
							}
						}
					}
				}
	 			if (count1 == count2){
					row++;
					return true;
				} else {
					settle();
					return false;
				}
			case 's':
				if (rotate())
					return true;
				else
					return false;
		}
		return false;
	}
	
	/**
	 * Rotates the piece 90 degrees.
	 *
	 * @return If the piece rotates or not.
	 */
	
 	public boolean rotate() {
		int count = 0;
		int jLowCount = 100;
		int jHighCount = 0;
		int iHighCount = 0;
		for (int i=0; i<rows; i++) {
			for (int j=0; j<cols; j++) {
				if (model[(rot+1)%rots][i][j] == 1) {
					if(j < jLowCount)
						jLowCount = j;
					if (j > jHighCount)
						jHighCount = j;
					if (i > iHighCount)
						iHighCount = i;
				}
			}
		}
		
		if (jLowCount+col < board.getOff())
			col = board.getOff();
		else if (jHighCount+col > board.getCols()-cols)
			col = board.getCols()-cols;
		else if (iHighCount+row > board.getRows()-rows)
			row = board.getRows()-rows;
			
		for (int i=0; i<rows; i++) {
			for (int j=0; j<cols; j++) {
				if (model[(rot+1)%rots][i][j] == 1) {
					if (board.isTileVacant(row+i,col+j))
						count += 1;
				}
			}
		}
		
		if (count == 4) {
			rot = (rot + 1) % rots;
			return true;
		} else {
			return false;
		}
 	}
	
	/**
	 * Settles the piece as low as it can move.
	 */
	
	public void settle() {
		for (int i=0; i<rows; i++) {
			for (int j=0; j<cols; j++) {
				if (model[rot][i][j] == 1) {
					board.fillTile(row+i, col+j, color);
				}
			}
		}
	}
	
	/**
	 * Draws the piece for the board.
	 * 
	 * @param g The graphics
	 * @param wSize Width of the piece.
	 * @param hSize Height of the piece.
	 */
	
	public void draw(Graphics g, int wSize, int hSize) {
		g.setColor(color);
		for (int i=0; i<rows; i++) {
			for (int j=0; j<cols; j++) {
				if (model[rot][i][j] == 1) {
					g.fill3DRect((col+j-board.getOff())*wSize, (row+i-board.getOff())*hSize, wSize, hSize, true);
				}
			}
		}
	}
	
	/**
	 * Draws the piece for the next piece.
	 * 
	 * @param g The graphics
	 * @param wSize Width of the piece.
	 * @param hSize Height of the piece.
	 * @param bool Arbitrary parameter to make a different draw method.
	 */
	
	public void draw(Graphics g, int wSize, int hSize, boolean bool) {
		g.setColor(color);
		double a = 0, b = 0;
		if (rows == 2) {
			a = 1;
			b = 1;
		} else if (rows == 3) {
			a = .5;
			b = 1;
		} else if (rows == 4) {
			b = .5;
		}
		for (int i=0; i<rows; i++) {
			for (int j=0; j<cols; j++) {
				if (model[0][i][j] == 1) {
					g.fill3DRect((int)((a+j)*wSize), (int)((b+i)*hSize), wSize, hSize, true);
				}
			}
		}
	}
}