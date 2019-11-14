package jumpin.model.board;

import java.util.Stack;

import jumpin.model.move.MoveSet;

public class BoardHistory {
	
	private Stack<MoveSet> undoMoves;
	private Stack<MoveSet> redoMoves;

	
	public BoardHistory() {
		undoMoves = new Stack<MoveSet>();
		redoMoves = new Stack<MoveSet>();
	}
	
	
	
	public MoveSet redo() {
		undoMoves.push(redoMoves.pop());
		return undoMoves.peek();
	}
	
	
	public MoveSet undo() {
		redoMoves.push(undoMoves.pop());
		return redoMoves.peek().invert();
	}
	
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
	
}
