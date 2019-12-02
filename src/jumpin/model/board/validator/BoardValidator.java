package jumpin.model.board.validator;

import jumpin.model.board.Board;
import jumpin.model.board.tile.RabbitHole;
import jumpin.model.board.tile.Tile;
import jumpin.model.board.util.BoardUtilities;
import jumpin.model.constants.BoardConstants;
import jumpin.model.constants.FoxPart;
import jumpin.model.exception.InvalidBoardException;
import jumpin.model.piece.UniquePiece;
import jumpin.model.piece.pieces.Fox;
import jumpin.model.piece.pieces.Rabbit;
import jumpin.model.structures.Position;

public class BoardValidator {

	public static void validate(Board board, int maxMoves) throws InvalidBoardException{
		BoardUtilities.organizeID(board);
		int rabbitCount=0,numToWin=0,countHead=0,countTail=0;
		for(int y=0;y<BoardConstants.HEIGHT;y++) {
			for(int x=0;x<BoardConstants.WIDTH;x++) {
				Position pos = new Position(x, y);
				if (!board.getTile(pos).isEmpty()) {
					Tile tile=board.getTile(pos);
					if(tile instanceof RabbitHole) {
						if(tile.getPiece()!=null) {
							if(tile.getPiece() instanceof Rabbit) {
								rabbitCount++;
							}else {
								throw new InvalidBoardException("Cannot populate hole with "+tile.getPiece().toString());
							}
						}
					}else if(tile.getPiece() instanceof Rabbit) {
						rabbitCount++;
						numToWin++;
					} else if (tile.getPiece() instanceof Fox) {
						if(((Fox)tile.getPiece()).getPart()==FoxPart.HEAD) {
							countHead++;
						}else {
							countTail++;
						}
						containsOtherPiece((UniquePiece)tile.getPiece(),board);
					}
				}
			}
		}
		if(countTail!=countHead) {
			throw new InvalidBoardException("Too many heads or tails");
		}else if(BoardConstants.MAX_FOX<countTail) {
			throw new InvalidBoardException("Too many foxes on the board");
		}else if(rabbitCount>BoardConstants.MAX_RABBIT) {
			throw new InvalidBoardException("Too many rabbits on the board");
		}
		try {
			board.computeSolution(maxMoves);
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		if(board.getSolution()==null||numToWin==0) {
			throw new InvalidBoardException("Board is not solvable");
		}
		/*
		 * Check pieces are all in valid places (fox heads have tails, not in holes, mushrooms arent in holes etc)
		 * Check the number of pieces doesn't not exceed the maximum allowable on the board SEE: BoardConstants
		 * Validate there is a solution within the max number of moves
		 * throw new InvalidBoardException("WITH ERROR MESSAGE HERE") if one of the above tests fails
		 */
	}

	private static void containsOtherPiece(UniquePiece piece,Board board) throws InvalidBoardException {
		boolean found=false;
		for(int y=0;y<BoardConstants.HEIGHT;y++) {
			for(int x=0;x<BoardConstants.WIDTH;x++) {
				Position pos = new Position(x, y);
				if (!board.getTile(pos).isEmpty()) {
					Tile tile = board.getTile(pos);
					if(tile.getPiece() instanceof Fox) {
						System.out.println(((UniquePiece) tile.getPiece()).getPieceID());
						if(!piece.equals(tile.getPiece())){
							if((piece.getPieceID().equals(((UniquePiece) tile.getPiece()).getPieceID()))) {
								found=true;
								break;
							}
						}
				}	
				}
			}
		}
		if(!found) {
			throw new InvalidBoardException("Fox is misoriented");
		}
		

}
}
