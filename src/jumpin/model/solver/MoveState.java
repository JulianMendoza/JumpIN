package jumpin.model.solver;

import jumpin.model.move.MoveSet;

public class MoveState {

	private final int rabbitsToWin;
	private final MoveSet moveSet;
	private final int depth;
	
	public MoveState(MoveSet moveSet, int rabbitsToWin, int depth) {
		this.rabbitsToWin = rabbitsToWin;
		this.moveSet = moveSet;
		this.depth = depth;
	}

	public MoveSet getMoveSet() {
		return moveSet;
	}

	public int getRabbitsToWin() {
		return rabbitsToWin;
	}
	
	public String toString() {
		return "Rabbits: " + rabbitsToWin + " MoveSet: " + moveSet;
	}

	public int getDepth() {
		return depth;
	}
	
}
