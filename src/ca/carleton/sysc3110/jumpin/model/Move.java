package ca.carleton.sysc3110.jumpin.model;

public class Move {

	private Position oldPos;
	private Position newPos;
	
	public Move(Position oldPos, Position newPos) {
		this.oldPos = oldPos;
		this.newPos = newPos;
	}
	
	public Position getNewPos() {
		return newPos;
	}

	public Position getOldPos() {
		return oldPos;
	}
	
}
