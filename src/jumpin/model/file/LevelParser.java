package jumpin.model.file;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import jumpin.model.GameState;
import jumpin.model.board.Board;
import jumpin.model.constants.FoxPart;
import jumpin.model.constants.Orientation;
import jumpin.model.constants.PieceID;
import jumpin.model.constants.StateOfGame;
import jumpin.model.exception.LevelParseException;
import jumpin.model.piece.Piece;
import jumpin.model.piece.pieces.Fox;
import jumpin.model.piece.pieces.Mushroom;
import jumpin.model.piece.pieces.Rabbit;
import jumpin.model.structures.Position;

/**
 * Class for parsing levels using XML file and String
 * 
 * @author Julian, Cameron
 */
public class LevelParser {

	private Board board;
	private GameState gameState;

	public LevelParser(Board board, GameState gameState) {
		this.gameState = gameState;
		this.board = board;
	}

	public void parseLevelXML(File level) throws LevelParseException {
		if (level.getName().endsWith(".xml")) {
			try {
				DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
				Document doc = dBuilder.parse(level.getAbsolutePath());
				doc.getDocumentElement().normalize();
				NodeList nList = doc.getElementsByTagName("piece");

				for (int i = 0; i < nList.getLength(); i++) {
					Piece piece;
					Node nNode = nList.item(i);
					if (nNode.getNodeType() == Node.ELEMENT_NODE) {
						Element eElement = (Element) nNode;
						eElement.getAttribute("name");
						if (eElement.getAttribute("name").equals("fox")) {
							piece = new Fox(
									FoxPart.getFoxPart(
											eElement.getElementsByTagName("foxPart").item(0).getTextContent()),
									Orientation.getOrientation(
											eElement.getElementsByTagName("orientation").item(0).getTextContent()),
									eElement.getElementsByTagName("id").item(0).getTextContent());
						} else if (eElement.getAttribute("name").equals("rabbit")) {
							piece = new Rabbit(eElement.getElementsByTagName("id").item(0).getTextContent());
						} else {
							piece = new Mushroom();
						}
						Node n = (eElement.getElementsByTagName("position").item(0));
						Element position = (Element) n;
						int x = Integer.parseInt(position.getAttribute("x"));
						int y = Integer.parseInt(position.getAttribute("y"));
						board.assignPiece(new Position(x, y), piece);
					}
				}
				gameState.setNumToWin(Integer.parseInt(doc.getDocumentElement().getAttribute("numToWin")));
				gameState.setState(StateOfGame.IN_PROGRESS);
				board.addModelListener(gameState);
			} catch (Exception e) {
				throw new LevelParseException("Error parsing level");
			}
		} else {
			throw new LevelParseException("Illegal file type");
		}
	}

	/**
	 * Parse a level builder string input
	 * 
	 * @param level
	 * @throws LevelParseException
	 */
	public void parseLevelString(String level) throws LevelParseException {
		try {
			String pieces[] = level.split(", ");
			int numFoxes = 0;
			int numRabbits = 0;
			int x, y;
			
			for (String p : pieces) {
				String tokens[] = p.split("-");
				x = Character.getNumericValue(tokens[1].charAt(0));
				y = Character.getNumericValue(tokens[1].charAt(1));
				
				if (tokens[0].startsWith("fox")) {
					numFoxes++;
					int foxID = Character.getNumericValue(tokens[0].charAt(3));
					
					if (numFoxes < 3) {
						if (tokens[0].endsWith("NS")) {
							if (foxID == 1) {
								board.assignPiece(new Position(x, y), new Fox(FoxPart.HEAD, Orientation.NORTH_SOUTH, PieceID.FOX_ID_1));
								board.assignPiece(new Position(x, y-1), new Fox(FoxPart.TAIL, Orientation.NORTH_SOUTH, PieceID.FOX_ID_1));
							} else if (foxID == 2) {
								board.assignPiece(new Position(x, y), new Fox(FoxPart.HEAD, Orientation.NORTH_SOUTH, PieceID.FOX_ID_2));
								board.assignPiece(new Position(x, y-1), new Fox(FoxPart.TAIL, Orientation.NORTH_SOUTH, PieceID.FOX_ID_2));
							} else {
								throw new LevelParseException("Unknown fox ID value");
							}
							
						} else if (tokens[0].endsWith("EW")) {
							if (foxID == 1) {
								board.assignPiece(new Position(x, y), new Fox(FoxPart.HEAD, Orientation.EAST_WEST, PieceID.FOX_ID_1));
								board.assignPiece(new Position(x, y-1), new Fox(FoxPart.TAIL, Orientation.EAST_WEST, PieceID.FOX_ID_1));
							} else if (foxID == 2) {
								board.assignPiece(new Position(x, y), new Fox(FoxPart.HEAD, Orientation.EAST_WEST, PieceID.FOX_ID_2));
								board.assignPiece(new Position(x+1, y), new Fox(FoxPart.TAIL, Orientation.EAST_WEST, PieceID.FOX_ID_2));
							} else {
								throw new LevelParseException("Unknown fox ID value");
							}
						} else {
							throw new LevelParseException("Unrecognizable fox token");
						}
					} else {
						throw new LevelParseException("Too many foxes");
					}
					
				} else if (tokens[0].startsWith("rabbit")) {
					numRabbits++;
					
					if (numRabbits < 4) {
						
						if (tokens[0].endsWith("1")) {
							board.assignPiece(new Position(x, y), new Rabbit(PieceID.RABBIT_ID_1));
							
						} else if (tokens[0].endsWith("2")) {
							board.assignPiece(new Position(x, y), new Rabbit(PieceID.RABBIT_ID_2));
							
						} else if (tokens[0].endsWith("3")) {
							board.assignPiece(new Position(x, y), new Rabbit(PieceID.RABBIT_ID_3));
							
						} else {
							throw new LevelParseException("Unrecognizable rabbit token");
						}
					} else {
						throw new LevelParseException("Too many rabbits");
					}
					
				} else if (tokens[0].equals("mush")) {
					board.assignPiece(new Position(x, y), new Mushroom());
				} else {
					throw new LevelParseException("Unknown token");
				}
			}
			
			gameState.setNumToWin(numRabbits);
			gameState.setState(StateOfGame.IN_PROGRESS);
			board.addModelListener(gameState);
			
		} catch (Exception e) {
			throw new LevelParseException("Error parsing level");
		}
	}

}
