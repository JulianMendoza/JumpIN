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
		int hashCode = 0;
		for(Move move : this) {
			hashCode += move.hashCode();
		}
		return hashCode;
	}
	@Override
	public boolean contains(Object o) {
		if(o instanceof Move) {
		Move m=(Move)o;

		for(Move m2:this) {
		
			if((m2.getNewPos().getX()==m.getNewPos().getX())&&(m2.getNewPos().getY()==m.getNewPos().getY())&&(m2.getOldPos().getX()==m.getOldPos().getX())&&
					(m2.getOldPos().getY()==m.getOldPos().getY()))	{
				return true;
			}
		}
		}
		return false;
		
	}
	
}
