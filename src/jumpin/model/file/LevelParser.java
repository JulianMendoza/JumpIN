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
import jumpin.model.constants.StateOfGame;
import jumpin.model.exception.LevelParseException;
import jumpin.model.piece.Piece;
import jumpin.model.piece.pieces.Fox;
import jumpin.model.piece.pieces.Mushroom;
import jumpin.model.piece.pieces.Rabbit;
import jumpin.model.structures.Position;

public class LevelParser {

	private Board board;
	private GameState gameState;

	public LevelParser(Board board, GameState gameState) {
		this.gameState = gameState;
		this.board = board;
	}

	public void parseLevel(File level) throws LevelParseException {
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
							piece = new Fox(FoxPart.getFoxPart(eElement.getElementsByTagName("foxPart").item(0).getTextContent()), Orientation.getOrientation(eElement.getElementsByTagName("orientation").item(0).getTextContent()), eElement.getElementsByTagName("id").item(0).getTextContent());
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

}
