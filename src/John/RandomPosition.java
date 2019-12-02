package John;

import java.util.Random;

import jumpin.model.board.Board;
import jumpin.model.structures.Position;

public class RandomPosition {

	
	public RandomPosition(Board board){
		super();
	}
	
	public static Position getRandomPosition() {
		Random rn = new Random();
		int x = rn.nextInt(4);
		int y = rn.nextInt(4);
		return new Position (x,y);
	}
	
	public static Position[] getEastWest() {
		Position headTail[] = new Position[2]; 
		Random rn = new Random();
		int x = rn.nextInt(4);
		int y = rn.nextInt(4);
		headTail[0] = new Position(x,y);
		headTail[1] = new Position(x+1,y);
		return headTail;
	}
	
	public static Position[] getNorthSouth() {
		Position headTail[] = new Position[2]; 
		Random rn = new Random();
		int x = rn.nextInt(4);
		int y = rn.nextInt(4);
		headTail[0] = new Position(x,y);
		headTail[1] = new Position(x,y-1);
		return headTail;
	}

}
