package John;

import java.util.Random;

import jumpin.model.board.Board;
import jumpin.model.constants.PieceID;
import jumpin.model.piece.pieces.Rabbit;
import jumpin.model.structures.Position;

public class AssignRabbit {
	
	private Board board;
	private Rabbit rabbit;
	private Position pos;
	private CheckBoard check;
	
	public AssignRabbit(Board board) {
		this.board = board;
		this.check = new CheckBoard();
	}

	public int assign() {
		Random rn = new Random();
		int numRabbit = rn.nextInt(3)+1;
		for(int i = 1; i <= numRabbit; i++) {
			if(i == 1) {
				rabbit = new Rabbit(PieceID.RABBIT_ID_1);
			}
			else if(i == 2) {
				rabbit = new Rabbit(PieceID.RABBIT_ID_2);
			}
			else {
				rabbit = new Rabbit(PieceID.RABBIT_ID_3);
			}
			pos = Position.getRandomPosition();
			while(!check.validSpace(board, pos)){
				pos = Position.getRandomPosition();
			}
			board.assignPiece(pos, rabbit);
		}
		return numRabbit;
	}

}
