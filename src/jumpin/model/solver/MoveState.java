package jumpin.model.solver;

import java.util.ArrayList;
import java.util.List;

import jumpin.model.board.Board;
import jumpin.model.move.MoveSet;

public class MoveState {

	private final int rabbitsToWin;
	private final List<MoveSet> moveSets;
	private final int depth;
	private final Board board;
	
	public MoveState(List<MoveSet> movesTogetHere, int rabbitsToWin, Board b,int depth) {
		this.rabbitsToWin = rabbitsToWin;
		this.moveSets=movesTogetHere;
		this.depth = depth;
		board=b;
	}

	public List<MoveSet> getMoveSet() {
		return moveSets;
	}

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

	public int getDepth() {
		return depth;
	}
	
}
