package ca.carleton.sysc3110.jumpin.model.util;

import java.util.ArrayList;
import java.util.List;

import ca.carleton.sysc3110.jumpin.model.Move;
import ca.carleton.sysc3110.jumpin.model.Position;
import ca.carleton.sysc3110.jumpin.model.board.Board;
import ca.carleton.sysc3110.jumpin.model.board.RabbitHole;
import ca.carleton.sysc3110.jumpin.model.board.Tile;
import ca.carleton.sysc3110.jumpin.model.constants.Direction;
import ca.carleton.sysc3110.jumpin.model.piece.pieces.Fox;

public class PieceLogic {

	
	/**
	 * 
	 * @param board
	 * @param pos
	 * @param direction
	 * @return
	 */
	public static List<Move> findFoxMove(Board board, Direction direction) {
		Fox fox = (Fox) board.getSelectedPiece();
		
		Position currentPos = board.getSelectedPosition().nextPosition(direction);
		Tile currentTile = board.getTile(currentPos);
		/*
		 * Remove this if statement if foxes can block holes
		 */
		if (board.getTile(currentPos) instanceof RabbitHole) {
			return null;
		}
		if(!currentTile.isEmpty()) {
			if(fox.isSameFox(currentTile.getPiece())) {
				board.selectPiece(currentPos); //set the board to select the direction facing piece of the fox
				return findFoxSlide(board, direction);
			} else {
				return null;
			}			
		} 	
		
		return findFoxSlide(board, direction); //direction facing piece of fox is already selected
	} 
	
	private static List<Move> findFoxSlide(Board board, Direction direction) {
		List<Move> moves = new ArrayList<Move>();
		Position currentPos = board.getSelectedPosition().nextPosition(direction);
		//remove !(board.getTile(currentPos.nextPosition(direction)) instanceof RabbitHole) if rabbits can block holes
		while(BoardUtilities.isValidPosition(currentPos.nextPosition(direction)) && board.getTile(currentPos.nextPosition(direction)).isEmpty() && !(board.getTile(currentPos.nextPosition(direction)) instanceof RabbitHole)) {
			currentPos = currentPos.nextPosition(direction);
		}
		moves.add(new Move(board.getSelectedPosition(), currentPos));
		moves.add(new Move(board.getSelectedPosition().prevPosition(direction), currentPos.prevPosition(direction))); //trailing piece of fox
		
		return moves;
	}

	/**
	 * Returns pos if no move
	 * @param board
	 * @param pos
	 * @param direction
	 * @return
	 */
	public static Move findRabbitMove(Board board, Direction direction) {
		Position selectedPosition = board.getSelectedPosition();
		Position currentPos = selectedPosition.nextPosition(direction);
		if(board.getTile(currentPos).isEmpty()) { //nothing for rabbit to jump over
			return null;
		}
		while(BoardUtilities.isValidPosition(currentPos)) {
			currentPos = currentPos.nextPosition(direction);
			if(board.getTile(currentPos).isEmpty()) { //we found an empty tile on the board
				return new Move(selectedPosition, currentPos); 
			}
		}
		return null;
	}
}
