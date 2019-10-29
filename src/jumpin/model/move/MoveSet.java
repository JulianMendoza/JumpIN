package jumpin.model.move;

import java.util.ArrayList;

public class MoveSet extends ArrayList<Move> {

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3982025784054049899L;

	public static MoveSet createSingleMoveSet(Move move) {
		MoveSet moveSet = new MoveSet();
		moveSet.add(move);
		return moveSet;
	}
	
	@Override
	public boolean equals(Object o) {
		if(o == null) {
			return false;
		} else if(o == this) {
			return true;
		} else if (o instanceof MoveSet){
			MoveSet set = (MoveSet) o;
			for(Move move : set) {
				if(!contains(move)) {
					return false;
				}
			}
		}
		return true;
	}
	
	@Override
	public int hashCode() {
		int hashCode;
		for(Move move : this) {
			hashCode += move.hashCode();
		}
		return hashCode;
	}
	
}
