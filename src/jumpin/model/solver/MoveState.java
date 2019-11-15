package jumpin.model.solver;

import jumpin.model.move.MoveSet;

public class MoveState {

	private final int rabbitsToWin;
	private final MoveSet moveSet;
	
	public MoveState(MoveSet moveSet, int rabbitsToWin) {
		this.rabbitsToWin = rabbitsToWin;
		this.moveSet = moveSet;
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
	
}
