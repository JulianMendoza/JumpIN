package John;

import java.util.Random;

import jumpin.model.board.Board;
import jumpin.model.structures.Position;

public class RandomPosition {

	
	public RandomPosition(Board board){
		super();
	}
	
	public static Position getRandomPosition(Board board) {
		Random rn = new Random();
		int x = rn.nextInt(board.getModel().getWidth()-1);
		int y = rn.nextInt(board.getModel().getHeight()-1);
		return new Position (x,y);
	}
	
	public static Position[] getEastWest(Board board) {
		Position headTail[] = new Position[2]; 
		Random rn = new Random();
		int x = rn.nextInt(board.getModel().getWidth()-1);
		int y = rn.nextInt(board.getModel().getHeight()-1);
		headTail[0] = new Position(x,y);
		headTail[1] = new Position(x+1,y);
		return headTail;
	}
	
	public static Position[] getNorthSouth(Board board) {
		Position headTail[] = new Position[2]; 
		Random rn = new Random();
		int x = rn.nextInt(board.getModel().getWidth()-1);
		int y = rn.nextInt(board.getModel().getHeight()-1);
		headTail[0] = new Position(x,y);
		headTail[1] = new Position(x,y-1);
		return headTail;
	}

}
