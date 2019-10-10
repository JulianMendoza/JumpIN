package ca.carleton.sysc3110.jumpin.model;

import java.util.List;

import ca.carleton.sysc3110.jumpin.model.board.Board;
import ca.carleton.sysc3110.jumpin.model.constants.Direction;
import ca.carleton.sysc3110.jumpin.model.constants.FoxPart;
import ca.carleton.sysc3110.jumpin.model.constants.Orientation;
import ca.carleton.sysc3110.jumpin.model.constants.PieceConstants;
import ca.carleton.sysc3110.jumpin.model.exception.IllegalMoveException;
import ca.carleton.sysc3110.jumpin.model.exception.NoPieceException;
import ca.carleton.sysc3110.jumpin.model.piece.Piece;
import ca.carleton.sysc3110.jumpin.model.piece.pieces.Fox;
import ca.carleton.sysc3110.jumpin.model.piece.pieces.Rabbit;
import ca.carleton.sysc3110.jumpin.model.util.BoardUtilities;
import ca.carleton.sysc3110.jumpin.model.util.GameState;

public class Game {
	
	private Board board;
	private GameState gameState;
	
	public Game() {
		board = new Board();
		Fox fox = new Fox(FoxPart.HEAD, Orientation.EAST_WEST, PieceConstants.FOX_ID_1);
		Fox fox2 = new Fox(FoxPart.TAIL, Orientation.EAST_WEST, PieceConstants.FOX_ID_1);
		Rabbit rabbit = new Rabbit(PieceConstants.RABBIT_ID_1);
		board.setTile(new Position(1,0), fox2);
		board.setTile(new Position(2,0), fox);
		board.setTile(new Position(3,0), rabbit);
		gameState=new GameState(1,"IN_PROGRESS");
		board.addListener(gameState);
	}
	
	public void movePiece(Position pos, Direction direction) throws NoPieceException, IllegalMoveException {
		Piece piece = board.selectPiece(pos);
		
		//First check if a piece exists at a selected position
		if(piece == null) {
			throw new NoPieceException();
		}

		//Second check if the piece is allowed to move in this direction
		if(!piece.allowsDirection(direction)) {
			throw new IllegalMoveException(piece + " is not allowed to move " + direction);
		}
		
		//Third check if the board allows a move in this direction
		if(!BoardUtilities.allowsDirection(direction, pos)) {
			throw new IllegalMoveException(piece + " cannot move " + direction + " off the board");
		}
		
		if(piece instanceof Rabbit) {
			Move move = BoardUtilities.findRabbitMove(board, direction);
			if(move == null) {
				throw new IllegalMoveException("Illegal move for " + piece);
			}
			board.updateBoard(move);
		} else if(piece instanceof Fox) { //Have to move multiple pieces because fox is 2 pieces
			List<Move> moves = BoardUtilities.findFoxMove(board, direction);
			if(moves == null) {
				throw new IllegalMoveException("Illegal move for " + piece);
			}
			for(Move move : moves) {
				board.updateBoard(move);
			}
		}
	}
	
	public String toString() {
		return gameState.toString() + board.toString();
	}
	
}
