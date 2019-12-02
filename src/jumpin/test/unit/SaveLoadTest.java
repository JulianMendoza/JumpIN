package jumpin.test.unit;

import java.io.File;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import jumpin.model.GameModel;
import jumpin.model.board.Board;
import jumpin.model.constants.FoxPart;
import jumpin.model.constants.Orientation;
import jumpin.model.constants.PieceID;
import jumpin.model.exception.LevelParseException;
import jumpin.model.file.LevelGenerator;
import jumpin.model.file.LevelParser;
import jumpin.model.piece.pieces.Fox;
import jumpin.model.piece.pieces.Mushroom;
import jumpin.model.piece.pieces.Rabbit;
import jumpin.model.structures.Position;
import junit.framework.TestCase;

/**
 * Test case for level saving and loading functionality
 * 
 * @author Cameron
 */
public class SaveLoadTest extends TestCase {

	private LevelGenerator levelSaver;
	private LevelParser levelLoader;
	private GameModel theModel;
	private Board savedBoard, loadedBoard;
	private Fox foxHead, foxTail;
	private Rabbit rabbit;
	private Mushroom mush1, mush2;
	
	/**
	 * Creates a sample level, saves it, loads it, and test for correct 
	 * piece positioning on the board that was loaded
	 * 
	 * @throws ParserConfigurationException
	 * @throws TransformerException
	 * @throws LevelParseException
	 */
	public void testSaveLoad() throws ParserConfigurationException, TransformerException, LevelParseException {
		theModel = new GameModel();
		savedBoard = theModel.getBoard();
		loadedBoard = theModel.getBoard();
		
		foxHead = new Fox(FoxPart.HEAD, Orientation.NORTH_SOUTH, PieceID.FOX_ID_1);
		foxTail = new Fox(FoxPart.TAIL, Orientation.NORTH_SOUTH, PieceID.FOX_ID_1);
		rabbit = new Rabbit(PieceID.RABBIT_ID_1);
		mush1 = new Mushroom();
		mush2 = new Mushroom();
		
		savedBoard.assignPiece(new Position(1, 1), foxHead);
		savedBoard.assignPiece(new Position(1, 0), foxTail);
		savedBoard.assignPiece(new Position(3, 0), rabbit);
		savedBoard.assignPiece(new Position(3, 1), mush1);
		savedBoard.assignPiece(new Position(2, 4), mush2);
		
		levelSaver = new LevelGenerator(savedBoard, theModel.getGameState());
		// **SAVE IT AS "test.xml"**
		levelSaver.saveLevelXML(); 
		
		File savedLevel = new File("test.xml");
		levelLoader = new LevelParser(loadedBoard, theModel.getGameState());
		levelLoader.parseLevel(savedLevel);
		
		loadedBoard.selectPiece(new Position(1, 1));
		assertTrue(loadedBoard.getSelectedPiece() instanceof Fox);
		loadedBoard.selectPiece(new Position(1, 0));
		assertTrue(loadedBoard.getSelectedPiece() instanceof Fox);
		loadedBoard.selectPiece(new Position(3, 0));
		assertTrue(loadedBoard.getSelectedPiece() instanceof Rabbit);
		loadedBoard.selectPiece(new Position(3, 1));
		assertTrue(loadedBoard.getSelectedPiece() instanceof Mushroom);
		loadedBoard.selectPiece(new Position(2, 4));
		assertTrue(loadedBoard.getSelectedPiece() instanceof Mushroom);
		
	}

}
