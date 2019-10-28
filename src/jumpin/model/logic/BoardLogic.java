package jumpin.model.logic;

import java.util.ArrayList;
import java.util.List;

import jumpin.model.board.Board;
import jumpin.model.move.Move;
import jumpin.model.piece.pieces.Fox;
import jumpin.model.piece.pieces.Rabbit;

public class BoardLogic {

	public static List<Move> getValidMoves(Board board) {
		List<Move> validMoves = new ArrayList<Move>();
		if (board.getSelectedPiece() instanceof Rabbit) {
			validMoves.addAll(RabbitLogic.findRabbitMoves(board));
		} else if (board.getSelectedPiece() instanceof Fox) {
			validMoves.addAll(FoxLogic.findFoxMoves(board));
		}
		return validMoves;
	}

}
