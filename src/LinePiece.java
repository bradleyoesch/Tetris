import java.awt.Color;

/**
 * The line piece. A straight line.
 * 
 * @author Bradley Oesch
 * @version 1.0
 */

public class LinePiece extends Piece {
	
	private static final long serialVersionUID = -7187657071063659651L;

	public LinePiece() {
		super();
		rots = 4;
		size = 4;
		color = Color.CYAN;
		model = new int[rots][size][size];
		model[0] = new int[][]{{0,0,0,0},{1,1,1,1},{0,0,0,0},{0,0,0,0}};
		model[1] = new int[][]{{0,0,1,0},{0,0,1,0},{0,0,1,0},{0,0,1,0}};
		model[2] = new int[][]{{0,0,0,0},{0,0,0,0},{1,1,1,1},{0,0,0,0}};
		model[3] = new int[][]{{0,1,0,0},{0,1,0,0},{0,1,0,0},{0,1,0,0}};
	}
}