package jumpin.model.board.validator;

import jumpin.model.board.Board;
import jumpin.model.board.util.BoardUtilities;
import jumpin.model.exception.InvalidBoardException;

public class BoardValidator {

	public static void validate(Board board, int maxMoves) throws InvalidBoardException{
		BoardUtilities.organizeID(board);
		/*
		 * Check pieces are all in valid places (fox heads have tails, not in holes, mushrooms arent in holes etc)
		 * Check the number of pieces doesn't not exceed the maximum allowable on the board SEE: BoardConstants
		 * Validate there is a solution within the max number of moves
		 * throw new InvalidBoardException("WITH ERROR MESSAGE HERE") if one of the above tests fails
		 */
	}

}
