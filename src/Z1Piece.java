
import java.awt.*;

/**
 * The first Z piece. Makes a zig zag shape.
 * 
 * @author Bradley Oesch
 * @version 1.0
 */

public class Z1Piece extends Piece {
	
	private static final long serialVersionUID = 6049604979004464641L;

	public Z1Piece() {
		super();
		rots = 4;
		size = 3;
		color = Color.GREEN;
		model = new int[rots][size][size];
		model[0] = new int[][]{{0,1,1},{1,1,0},{0,0,0}};
		model[1] = new int[][]{{0,1,0},{0,1,1},{0,0,1}};
		model[2] = new int[][]{{0,0,0},{0,1,1},{1,1,0}};
		model[3] = new int[][]{{1,0,0},{1,1,0},{0,1,0}};
	}
}