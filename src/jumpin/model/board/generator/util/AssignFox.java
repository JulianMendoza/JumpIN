package jumpin.model.board.generator.util;

import java.util.Random;

import jumpin.model.board.Board;
import jumpin.model.constants.Orientation;
import jumpin.model.piece.pieces.Fox;
import jumpin.model.structures.Position;

/**
 * 
 * @author johnwarde
 *
 */

public class AssignFox {
	
	private Board board;
	private Fox[] fox;
	private Position pos[];
	private CheckBoard check;
	
	/**
	 * constructor method
	 * @param board
	 */
	public AssignFox(Board board) {
		this.board = board;
		this.check = new CheckBoard();
		this.pos = new Position[2];
	}
	
	/**
	 * method to assign fox piece to board
	 */
	public void assign() {
		Random rn = new Random();
		RandomFox rndFox = new RandomFox();
		
		for(int i = 0; i < rn.nextInt(2)+1; i++ ) {
			fox = rndFox.getFox(rn.nextInt(2)+1);
			if(fox[0].getOrientation().equals(Orientation.EAST_WEST)){
				pos = RandomPosition.getEastWest(board);
				while(!check.validSpace(board, pos[0]) || !check.validSpace(board, pos[1])){
					pos = RandomPosition.getEastWest(board);
				}
				
				board.assignPiece(pos[0], fox[0]);
				board.assignPiece(pos[1], fox[1]);
			}
			
			else{
				pos = RandomPosition.getNorthSouth(board);
				while(!check.validSpace(board, pos[0]) || !check.validSpace(board, pos[1])){
					pos = RandomPosition.getNorthSouth(board);
				}

				
				board.assignPiece(pos[0], fox[0]);
				board.assignPiece(pos[1], fox[1]);
			}
		}
	}
}
