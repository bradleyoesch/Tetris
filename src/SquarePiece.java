import java.awt.Color;

/**
 * The square piece. Makes a square shape.
 * 
 * @author Bradley Oesch
 * @version 1.0
 */

public class SquarePiece extends Piece {
	
	private static final long serialVersionUID = 4931639188212379548L;

	public SquarePiece() {
		super();
		rots = 1;
		size = 2;
		color = Color.YELLOW;
		model = new int[rots][size][size];
		model[0] = new int[][]{{1,1},{1,1}};
	}
}