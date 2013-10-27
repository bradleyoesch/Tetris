
import java.awt.*;

/**
 * The first T piece. Makes an T shape.
 * 
 * @author Bradley Oesch
 * @version 1.0
 */

public class TPiece extends Piece {
	
	private static final long serialVersionUID = 2577119148324319293L;

	public TPiece() {
		super();
		rots = 4;
		size = 3;
		color = Color.MAGENTA;
		model = new int[rots][size][size];
		model[0] = new int[][]{{0,1,0},{1,1,1},{0,0,0}};
		model[1] = new int[][]{{0,1,0},{0,1,1},{0,1,0}};
		model[2] = new int[][]{{0,0,0},{1,1,1},{0,1,0}};
		model[3] = new int[][]{{0,1,0},{1,1,0},{0,1,0}};
	}
}