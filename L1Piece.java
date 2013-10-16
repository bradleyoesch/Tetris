import java.awt.*;

/**
 * The first L piece. Makes an L shape.
 * 
 * @author Bradley Oesch
 * @version 1.0
 */

public class L1Piece extends Piece {

	public L1Piece() {
		super();
		rots = 4;
		rows = 3;
		cols = 3;
		color = new Color(0, 50, 245);
		model = new int[rots][rows][cols];
		model[0] = new int[][]{{1,0,0},{1,1,1},{0,0,0}};
		model[1] = new int[][]{{0,1,1},{0,1,0},{0,1,0}};
		model[2] = new int[][]{{0,0,0},{1,1,1},{0,0,1}};
		model[3] = new int[][]{{0,1,0},{0,1,0},{1,1,0}};
	}
}