package jumpin.model.util;

import jumpin.model.board.Board;
import jumpin.model.board.RabbitHole;
import jumpin.model.board.Tile;
import jumpin.model.constants.Direction;
import jumpin.model.move.FoxMove;
import jumpin.model.move.Move;
import jumpin.model.piece.pieces.Fox;

public class PieceLogic {

	/**
	 * 
	 * @param board
	 * @param pos
	 * @param direction
	 * @return
	 */
	public static FoxMove findFoxMove(Board board, Direction direction, int moveTiles) {
		Fox fox = (Fox) board.getSelectedPiece();

		Position currentPos = board.getSelectedPosition().nextPosition(direction);
		Tile currentTile = board.getTile(currentPos);
		/*
		 * Remove this if statement if foxes can block holes
		 */
		if (board.getTile(currentPos) instanceof RabbitHole) {
			return null;
		}
		if (!currentTile.isEmpty()) {
			if (fox.isSameFox(currentTile.getPiece())) {
				board.selectPiece(currentPos); // set the board to select the direction facing piece of the fox
				return findFoxSlide(board, direction, moveTiles);
			} else {
				return null;
			}
		}

		return findFoxSlide(board, direction, moveTiles); // direction facing piece of fox is already selected
	}

	private static FoxMove findFoxSlide(Board board, Direction direction, int moveTiles) {
		Position currentPos = board.getSelectedPosition().nextPosition(direction);
		// remove !(board.getTile(currentPos.nextPosition(direction)) instanceof
		// RabbitHole) if rabbits can block holes
		while (BoardUtilities.isValidPosition(currentPos.nextPosition(direction)) && board.getTile(currentPos.nextPosition(direction)).isEmpty() && !(board.getTile(currentPos.nextPosition(direction)) instanceof RabbitHole) && moveTiles != 0) {
			currentPos = currentPos.nextPosition(direction);
			moveTiles--;
		}
		
		return new FoxMove(new Move(board.getSelectedPosition(), currentPos), new Move(board.getSelectedPosition().prevPosition(direction), currentPos.prevPosition(direction)));
	}

	/**
	 * Returns pos if no move
	 * 
	 * @param board
	 * @param pos
	 * @param direction
	 * @return
	 */
	public static Move findRabbitMove(Board board, Direction direction) {
		Position selectedPosition = board.getSelectedPosition();
		Position currentPos = selectedPosition.nextPosition(direction);
		if (board.getTile(currentPos).isEmpty()) { // nothing for rabbit to jump over
			return null;
		}
		while (BoardUtilities.isValidPosition(currentPos)) {
			currentPos = currentPos.nextPosition(direction);
			if (board.getTile(currentPos).isEmpty()) { // we found an empty tile on the board
				return new Move(selectedPosition, currentPos);
			}
		}
		return null;
	}
}
