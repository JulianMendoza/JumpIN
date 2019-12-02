package John;

import jumpin.model.board.Board;
import jumpin.model.constants.FoxPart;
import jumpin.model.constants.Orientation;
import jumpin.model.constants.PieceID;
import jumpin.model.piece.pieces.Fox;

public class AssignID {
	private Fox fox1,fox2,fox3;
	
	public AssignID(){
		super();
	}
	
	public void assignFoxID(Board board,Fox fox){
		fox1 = new Fox(FoxPart.HEAD, Orientation.EAST_WEST, PieceID.FOX_ID_1);
		fox2 = new Fox(FoxPart.TAIL, Orientation.EAST_WEST, PieceID.FOX_ID_1);
		fox3 = new Fox(FoxPart.HEAD, Orientation.NORTH_SOUTH, PieceID.FOX_ID_2);
		
	    if(fox.getPart().equals(fox1.getPart()) && fox.getOrientation().equals(fox1.getOrientation())){
	    	fox.setPieceID(PieceID.FOX_ID_1);
	    }
	    else if(fox.getPart().equals(fox2.getPart()) && fox.getOrientation().equals(fox2.getOrientation())){
	    	fox.setPieceID(PieceID.FOX_ID_1);
	    }
	    else if(fox.getPart().equals(fox3.getPart()) && fox.getOrientation().equals(fox3.getOrientation())){
	    	fox.setPieceID(PieceID.FOX_ID_2);
	    }
	    else{
	    	fox.setPieceID(PieceID.FOX_ID_2);
	    }
	}

}
