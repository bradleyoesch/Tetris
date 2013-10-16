import java.awt.*;

/**
 * The square piece. Makes a square shape.
 * 
 * @author Bradley Oesch
 * @version 1.0
 */

public class SquarePiece extends Piece {
	
	public SquarePiece() {
		super();
		rots = 1;
		rows = 2;
		cols = 2;
		color = Color.YELLOW;
		model = new int[rots][rows][cols];
		model[0] = new int[][]{{1,1},{1,1}};
	}
}