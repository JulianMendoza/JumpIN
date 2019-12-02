package John;

import jumpin.model.constants.FoxPart;
import jumpin.model.constants.Orientation;
import jumpin.model.constants.PieceID;
import jumpin.model.piece.pieces.Fox;

/**
 * 
 * @author JOHN
 *
 */

public class RandomFox {
	private boolean trigger;
	public RandomFox() {
		trigger=true;
	}
	public Fox[] getFox(int rnd){
		Fox fox[] = new Fox[2];
		
	    switch(rnd){
	    
		case 1:
			if(trigger) {
				fox[1] = new Fox(FoxPart.TAIL, Orientation.EAST_WEST, PieceID.FOX_ID_1);
				fox[0] = new Fox(FoxPart.HEAD, Orientation.EAST_WEST, PieceID.FOX_ID_1);
			}else {
				fox[1] = new Fox(FoxPart.TAIL, Orientation.EAST_WEST, PieceID.FOX_ID_2);
				fox[0] = new Fox(FoxPart.HEAD, Orientation.EAST_WEST, PieceID.FOX_ID_2);
			}
			trigger=false;
			return fox;
			
		case 2:
			if(trigger) {
				fox[1] = new Fox(FoxPart.TAIL, Orientation.NORTH_SOUTH, PieceID.FOX_ID_1);
				fox[0] = new Fox(FoxPart.HEAD, Orientation.NORTH_SOUTH, PieceID.FOX_ID_1);
			}else {
				fox[1] = new Fox(FoxPart.TAIL, Orientation.NORTH_SOUTH, PieceID.FOX_ID_2);
				fox[0] = new Fox(FoxPart.HEAD, Orientation.NORTH_SOUTH, PieceID.FOX_ID_2);
			}
			trigger=false;
			return fox;
		}
		return null;
		
	}

}