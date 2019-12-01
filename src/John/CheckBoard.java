package John;

import jumpin.model.board.Board;
import jumpin.model.board.tile.RabbitHole;
import jumpin.model.piece.pieces.Fox;
import jumpin.model.piece.pieces.Mushroom;
import jumpin.model.piece.pieces.Rabbit;
import jumpin.model.structures.Position;

/**
 * 
 * @author JOHN
 *
 */

public class CheckBoard {
	public CheckBoard(){
		super();
	}
	
	public boolean validSpace(Board board, Position pos){
		board.deselectPiece();
		board.selectPiece(pos);
		int x = pos.getX();
		int y = pos.getY();
		int xHigh = board.getModel().getWidth();
		int low = 0;
		int yHigh = board.getModel().getWidth();
		
		if(x < low || x > xHigh){
			return false;
		}
		
		if(y < low || y > yHigh){
			return false;
		}
		if(board.getTile(pos) instanceof RabbitHole|| board.getSelectedPiece() instanceof Rabbit || board.getSelectedPiece() instanceof Fox || board.getSelectedPiece() instanceof Mushroom){
			return false;
		}
		
		return true;
		
	}
}
