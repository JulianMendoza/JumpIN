package jumpin.model.board;

import jumpin.model.board.event.BoardModelEvent;

/**
 * Interface of the board model's listeners
 * 
 * @author Julian
 * @documentation Cameron Davis
 */
public interface BoardModelListener {
	/**
	 * Update the listeners
	 * 
	 * @param event the update event
	 */
	public void update(BoardModelEvent e);
}