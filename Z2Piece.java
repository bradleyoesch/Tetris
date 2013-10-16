import java.awt.*;

/**
 * The second Z piece. Makes a zig zag shape.
 * 
 * @author Bradley Oesch
 * @version 1.0
 */

public class Z2Piece extends Piece {
		
	public Z2Piece() {
		super();
		rots = 4;
		rows = 3;
		cols = 3;
		color = Color.RED;
		model = new int[rots][rows][cols];
		model[0] = new int[][]{{1,1,0},{0,1,1},{0,0,0}};
		model[1] = new int[][]{{0,0,1},{0,1,1},{0,1,0}};
		model[2] = new int[][]{{0,0,0},{1,1,0},{0,1,1}};
		model[3] = new int[][]{{0,1,0},{1,1,0},{1,0,0}};
	}
}