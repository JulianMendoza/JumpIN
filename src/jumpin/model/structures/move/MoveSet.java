package jumpin.model.structures.move;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Class to determine the set of moves
 * 
 * @author Giuseppe
 *
 */
public class MoveSet extends ArrayList<Move> {

	private static final long serialVersionUID = 3982025784054049899L;

	/**
	 * method to create move set given move
	 * @param move
	 * @return
	 */
	public static MoveSet createSingleMoveSet(Move move) {
		MoveSet moveSet = new MoveSet();
		moveSet.add(move);
		return moveSet;
	}

	@Override
	public boolean equals(Object o) {
		if (o == null) {
			return false;
		} else if (o == this) {
			return true;
		} else if (o instanceof MoveSet) {
			MoveSet set = (MoveSet) o;
			for (Move move : set) {
				if (!contains(move)) {
					return false;
				}
			}
		}
		return true;
	}

	@Override
	public int hashCode() {
		int hashCode = 0;
		for (Move move : this) {
			hashCode += move.hashCode();
		}
		return hashCode;
	}
	
	/**
	 * Method to invert moveSet 
	 *  
	 * @return inverted moveSet
	 */
	public MoveSet invert() {
		MoveSet invertedSet = new MoveSet();
		for(Move move : this) {
			invertedSet.add(move.invert());
		}
		Collections.reverse(invertedSet);
		return invertedSet;
	}
	public MoveSet clone() {
		return (MoveSet)super.clone();
		
	}

}
