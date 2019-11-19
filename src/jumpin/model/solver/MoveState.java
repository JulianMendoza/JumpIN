package jumpin.model.solver;

import java.util.List;
import jumpin.model.move.MoveSet;
/**
 * 
 * @author Giuseppe
 *
 */
public class MoveState {

	private final int rabbitsToWin;
	private final List<MoveSet> moveSets;
	private final int depth;
	
	/**
	 * MoveState Constructor
	 * 
	 * @param movesTogetHere
	 * @param rabbitsToWin
	 * @param depth
	 */
	public MoveState(List<MoveSet> movesTogetHere, int rabbitsToWin,int depth) {
		this.rabbitsToWin = rabbitsToWin;
		this.moveSets = movesTogetHere;
		this.depth = depth;
	}

	/**
	 * Get a list of MoveSet
	 * 
	 * @return list of MoveSet
	 */
	public List<MoveSet> getMoveSet() {
		return moveSets;
	}

	/**
	 * Get number of rabbits needed to win 
	 *  
	 * @return number of rabbits
	 */
	public int getRabbitsToWin() {
		return rabbitsToWin;
	}
	
	public String toString() {
		String s="Rabbits: " + rabbitsToWin+"\n Depth: "+depth+"\nHistory:";
		
		for(MoveSet m:moveSets) {
			s+= m.toString();
		}
		return s;
	}

	/**
	 * Get depth number
	 * 
	 * @return integer depth
	 */
	public int getDepth() {
		return depth;
	}
	
}
