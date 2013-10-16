import java.awt.*;

/**
 * The line piece. A straight line.
 * 
 * @author Bradley Oesch
 * @version 1.0
 */

public class LinePiece extends Piece {
	
	public LinePiece() {
		super();
		rots = 4;
		rows = 4;
		cols = 4;
		color = Color.CYAN;
		model = new int[rots][rows][cols];
		model[0] = new int[][]{{0,0,0,0},{1,1,1,1},{0,0,0,0},{0,0,0,0}};
		model[1] = new int[][]{{0,0,1,0},{0,0,1,0},{0,0,1,0},{0,0,1,0}};
		model[2] = new int[][]{{0,0,0,0},{0,0,0,0},{1,1,1,1},{0,0,0,0}};
		model[3] = new int[][]{{0,1,0,0},{0,1,0,0},{0,1,0,0},{0,1,0,0}};
	}
}