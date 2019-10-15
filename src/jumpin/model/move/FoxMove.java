package jumpin.model.move;

import jumpin.util.Pair;

/**
 * A pair of moves for the fox head and tail
 * 
 * @author Giuseppe
 *
 */
public class FoxMove extends Pair<Move, Move>{

	/**
	 * Constructs a FoxMove using 2 moves for head and tail
	 * 
	 * @param first Head of Fox
	 * @param second Tail of Fox
	 */
	public FoxMove(Move first, Move second) {
		super(first, second);
	}
	
}
