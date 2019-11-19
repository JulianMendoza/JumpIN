package jumpin.model.logic;

import java.util.ArrayList;
import java.util.List;

import jumpin.model.board.Board;
import jumpin.model.move.Move;
import jumpin.model.move.MoveSet;
import jumpin.model.piece.pieces.Fox;
import jumpin.model.piece.pieces.Rabbit;
/**
 * Class to determine the logic of the board
 * 
 * @author Giuseppe
 *
 */
public class BoardLogic {

	/**
	 * Method to return valid moves on board
	 * 
	 * @param board
	 * @return valid moves
	 */
	public static List<MoveSet> getValidMoves(Board board) {
		List<MoveSet> validMoves = new ArrayList<MoveSet>();
		if (board.getSelectedPiece() instanceof Rabbit) {
			validMoves.addAll(RabbitLogic.findRabbitMoves(board));
		} else if (board.getSelectedPiece() instanceof Fox) {
			validMoves.addAll(FoxLogic.findFoxMoves(board));
		}
		return validMoves;
	}
	
	/**
	 * Method to generate moveset based on a move for a piece
	 * 
	 * @param move
	 * @param board
	 * @return moveset for selected piece
	 */
	public static MoveSet generateMoveSet(Move move, Board board) {
		if(board.getSelectedPiece() instanceof Rabbit) {
			return MoveSet.createSingleMoveSet(move);
		} else if(board.getSelectedPiece() instanceof Fox) {
			  return FoxLogic.createMoveSet(move, board);
		} else {
			return new MoveSet();
		}
		
	}

}
