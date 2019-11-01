package jumpin.model.logic;

import java.util.ArrayList;
import java.util.List;

import jumpin.model.board.Board;
import jumpin.model.board.tile.RabbitHole;
import jumpin.model.board.tile.Tile;
import jumpin.model.constants.Direction;
import jumpin.model.constants.Orientation;
import jumpin.model.move.Move;
import jumpin.model.move.MoveSet;
import jumpin.model.piece.pieces.Fox;
import jumpin.model.util.Position;
import jumpin.util.Pair;

public class FoxLogic {

	public static List<MoveSet> findFoxMoves(Board board) {
		List<MoveSet> foxMoves = new ArrayList<MoveSet>();

		Fox fox = (Fox) board.getSelectedPiece();

		int maxMove;
		if (fox.getOrientation().equals(Orientation.NORTH_SOUTH)) {
			maxMove = board.getModel().getHeight()-2;
		} else {
			maxMove = board.getModel().getWidth()-2;
		}

		for (Direction direction : fox.getOrientation().getValidDirections()) {
			for (int i = 1; i <= maxMove; i++) {
				if (isValidMove(board, direction, i)) {
					Move move = new Move(board.getSelectedPosition(), board.getSelectedPosition().nextPosition(direction, i));
					foxMoves.add(createMoveSet(move, board));
				}
			}
		}
		return foxMoves;
	}
	public static MoveSet createMoveSet(Move move,Board board) {
		MoveSet moveSet = new MoveSet();
		moveSet.add(move);
		Position oldPos = getOtherFoxPosition(board, (Fox) board.getSelectedPiece());
		Pair<Integer, Integer> inc = move.getIncrement();
		Position newPos = new Position(oldPos.getX() + inc.getFirst(), oldPos.getY() + inc.getSecond());
		moveSet.add(new Move(oldPos, newPos));
		return moveSet;
	}
	
	public static Position getOtherFoxPosition(Board board, Fox fox) {
		for (Direction direction : fox.getOrientation().getValidDirections()) {
			Position currentPos = board.getSelectedPosition().nextPosition(direction);
			Tile currentTile = board.getTile(currentPos);

			if (currentTile != null && !currentTile.isEmpty()) {
				if (fox.isSameFox(currentTile.getPiece())) {
					return currentPos;
				}
			}
		}
		return null; // should never return null
	}

	private static boolean isValidMove(Board board, Direction direction, int distance) {
		Position currentPos = board.getSelectedPosition();

		while (isNextValid(board,currentPos,direction) && (isNextEmpty(board,currentPos,direction) || isNextSameFox(board,currentPos, direction)) && !isNextRabbitHole(board,currentPos,direction) &&  distance > 0&&!isOnEdge(board,currentPos,direction)) {
			
			currentPos = currentPos.nextPosition(direction);
			distance--;
		}

		return distance == 0; // if the fox moved anything other than its total distance
		
	}
	
	private static boolean isNextRabbitHole(Board board,Position currentPos,Direction direction) {
		return (board.getTile(currentPos.nextPosition(direction)) instanceof RabbitHole);
	}
	private static boolean isNextValid(Board board,Position currentPos,Direction direction) {
		return board.isValidPosition(currentPos.nextPosition(direction));
	}
	private static boolean isNextEmpty(Board board,Position currentPos,Direction direction) {
		return board.getTile(currentPos.nextPosition(direction)).isEmpty();
	}
	private static boolean isNextSameFox(Board board,Position currentPos,Direction direction) {
		Position nextPos = currentPos.nextPosition(direction);
		if(board.getTile(nextPos).getPiece() instanceof Fox) {
			return ((Fox) board.getTile(nextPos).getPiece()).isSameFox(board.getSelectedPiece());
		}
		return false;
	}
	/**
	 * @TODO calling position from tile indexed from 1-5 Calling index from board indexed 0-4
	 * @param board
	 * @param currentPos
	 * @param direction
	 * @return
	 */
	private static boolean isOnEdge(Board board,Position currentPos,Direction direction) {
		Fox fox = (Fox) board.getSelectedPiece();
		Position fox2=getOtherFoxPosition(board,fox);
		int check;
		switch(fox.getOrientation()) {
		case EAST_WEST: 
			check=board.getSelectedPosition().getX()-fox2.getX();
			if(direction==Direction.EAST) {
				return board.getSelectedPosition().getX()==4 || fox2.getX()==5||(check==-1)?currentPos.getX()==3:currentPos.getX()==4;
			}else {
				return board.getSelectedPosition().getX()==0||fox2.getX()==1||(check==-1)?currentPos.getX()==1:currentPos.getX()==0;
			}
		case NORTH_SOUTH:
			check=board.getSelectedPosition().getY()-fox2.getY();
			if(direction==Direction.SOUTH) {
				return board.getSelectedPosition().getY()==4 || fox2.getY()==5||(check==-1)?currentPos.getY()==3:currentPos.getY()==4;
			}else {
				return board.getSelectedPosition().getY()==0||fox2.getY()==1||(check==-1)?currentPos.getY()==0:currentPos.getY()==1;
			}
		default: return false;
		}
	}
}
