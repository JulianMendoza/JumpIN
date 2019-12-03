package jumpin.model.board.event;

import jumpin.model.structures.move.MoveSet;

/**
 * Constants for identifying Board Model Events
 * 
 * @author Giuseppe, Julian
 * @documentation Cameron Davis
 */
public class BoardModelEvent {

	private MoveSet moveSet;
	private int id;

	/**
	 * constructor method
	 * @param id
	 * @param moveSet
	 */
	public BoardModelEvent(int id, MoveSet moveSet) {
		this.moveSet = moveSet;
		this.id = id;
	}

	public MoveSet getUpdates() {
		return moveSet;
	}

	public int getID() {
		return id;
	}

}