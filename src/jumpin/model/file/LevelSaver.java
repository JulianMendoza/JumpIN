package jumpin.model.file;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import jumpin.model.GameModel;
import jumpin.model.GameState;
import jumpin.model.board.Board;
import jumpin.model.constants.BoardConstants;
import jumpin.model.exception.LevelSaveException;
import jumpin.model.piece.Piece;
import jumpin.model.piece.pieces.Fox;
import jumpin.model.piece.pieces.Rabbit;
import jumpin.model.structures.Position;

public class LevelSaver {

	/**
	 * method to save level to xml
	 * @param file
	 * @param model
	 * @throws LevelSaveException
	 */
	public static void saveLevel(File file, GameModel model) throws LevelSaveException {
		GameState gameState = model.getGameState();
		Board board = model.getBoard();

		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = null;
		try {
			dBuilder = dbFactory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			throw new LevelSaveException("Failed to create document builder");
		}

		Document doc = dBuilder.newDocument();
		Attr attr = doc.createAttribute("numToWin");
		attr.setValue(Integer.toString(gameState.getNumToWin()));
		Element rootElement = doc.createElement("level");
		rootElement.setAttributeNode(attr);
		doc.appendChild(rootElement);
		for (int i = 0; i < BoardConstants.HEIGHT; i++) {
			for (int j = 0; j < BoardConstants.WIDTH; j++) {
				Position pos = new Position(j, i);
				if (!board.getTile(pos).isEmpty()) {
					board.selectPiece(pos);
					Piece piece = board.getSelectedPiece();
					Element pieceElem = doc.createElement("piece");
					Attr attrpiece = doc.createAttribute("name");
					;
					if (piece instanceof Fox) {
						attrpiece.setValue("fox");
						Element foxPart = doc.createElement("foxPart");
						foxPart.appendChild(doc.createTextNode((((Fox) piece).getPart()).toString()));
						pieceElem.appendChild(foxPart);
						Element orientation = doc.createElement("orientation");
						orientation.appendChild(doc.createTextNode(((Fox) piece).getOrientation().toString()));
						pieceElem.appendChild(orientation);
						Element id = doc.createElement("id");
						id.appendChild(doc.createTextNode(((Fox) piece).getPieceID()));
						pieceElem.appendChild(id);
					} else if (piece instanceof Rabbit) {
						attrpiece.setValue("rabbit");
						Element id = doc.createElement("id");
						id.appendChild(doc.createTextNode(((Rabbit) piece).getPieceID()));
						pieceElem.appendChild(id);
					} else {
						attrpiece.setValue("mushroom");
					}
					pieceElem.setAttributeNode(attrpiece);
					Element position = doc.createElement("position");
					Attr x = doc.createAttribute("x");
					x.setValue(String.valueOf(pos.getX()));
					Attr y = doc.createAttribute("y");
					y.setValue(String.valueOf(pos.getY()));
					position.setAttributeNode(x);
					position.setAttributeNode(y);
					pieceElem.appendChild(position);
					rootElement.appendChild(pieceElem);
				}

			}
		}

		File fileToSave = new File(file.getAbsolutePath() + ".xml");
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer;
		try {
			transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(fileToSave);
			transformer.transform(source, result);
		} catch (TransformerException e) {
			throw new LevelSaveException("Error saving level");
		}

	}

}
