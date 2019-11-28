package jumpin.model.board;

import java.util.Stack;

import jumpin.model.structures.move.MoveSet;

/**
 * 
 * History of moves to undo and redo
 * 
 * @author Giuseppe
 *
 */
public class BoardHistory implements Cloneable {

	private Stack<MoveSet> undoMoves;
	private Stack<MoveSet> redoMoves;

	/**
	 * BoardHistory Constructor
	 */
	public BoardHistory() {
		undoMoves = new Stack<MoveSet>();
		redoMoves = new Stack<MoveSet>();
	}

	/**
	 * Redo move
	 * 
	 * @return last move in stack
	 */
	public MoveSet redo() {
		undoMoves.push(redoMoves.pop());
		return undoMoves.peek();
	}

	/**
	 * Undo Move
	 * 
	 * @return to previous move
	 */
	public MoveSet undo() {
		redoMoves.push(undoMoves.pop());
		return redoMoves.peek().invert();
	}

	/**
	 * Add move
	 * 
	 * @param moveSet board move
	 */
	public void add(MoveSet moveSet) {
		undoMoves.push(moveSet);
		redoMoves.clear();
	}

	public boolean hasUndo() {
		return !undoMoves.isEmpty();
	}

	public boolean hasRedo() {
		return !redoMoves.isEmpty();
	}

	@Override
	public BoardHistory clone() throws CloneNotSupportedException {
		return (BoardHistory) super.clone();

	}
}
