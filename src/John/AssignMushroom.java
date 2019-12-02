package John;

import java.util.Random;

import jumpin.model.board.Board;
import jumpin.model.piece.pieces.Mushroom;
import jumpin.model.structures.Position;

public class AssignMushroom {
	
	private Board board;
	private Mushroom mush;
	private CheckBoard check;
	private Position pos;

	public AssignMushroom(Board board) {
		this.board = board;
		this.check = new CheckBoard();
	}
	
	public void assign() {
		Random rn = new Random();
		
		for(int i = 0; i < rn.nextInt(3)+1; i++) {
			mush = new Mushroom();
			pos = RandomPosition.getRandomPosition();
			board.selectPiece(pos);
			while(!check.validSpace(board, pos)){
				pos = RandomPosition.getRandomPosition();
			}
			board.assignPiece(pos, mush);
		}
		
	}
}