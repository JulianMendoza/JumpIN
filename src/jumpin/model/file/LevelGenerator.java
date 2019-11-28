package jumpin.model.file;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
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

import jumpin.model.GameState;
import jumpin.model.board.Board;
import jumpin.model.constants.BoardConstants;
import jumpin.model.piece.Piece;
import jumpin.model.piece.pieces.Fox;
import jumpin.model.piece.pieces.Rabbit;
import jumpin.model.structures.Position;

/**
 * Class to generate levels on load
 * 
 * @author Julian
 *
 */
public class LevelGenerator {
	private Board board;
	private GameState gameState;

	/**
	 * Default constructor for LevelGenerator
	 * 
	 * @param board
	 * @param gameState
	 */
	public LevelGenerator(Board board, GameState gameState) {
		this.board = board;
		this.gameState = gameState;
	}

	public void loadLevelXML() {
		/**
		 * DELEGATE THIS PART
		 */
		JFrame parentFrame = new JFrame();
		JFileChooser fc = new JFileChooser();
		fc.setCurrentDirectory(new java.io.File("."));
		fc.setDialogTitle("Choose which level to load");
		FileNameExtensionFilter filter = new FileNameExtensionFilter("xml files (*.xml)", "xml");
		fc.setFileFilter(filter);
		int userSelection = fc.showOpenDialog(parentFrame);
		/**
		 * 
		 */
		if (userSelection == JFileChooser.APPROVE_OPTION) {

		} else if (userSelection == JFileChooser.CANCEL_OPTION) {
			JOptionPane.showMessageDialog(null, "No selected file", "Error", JOptionPane.ERROR_MESSAGE);
			loadLevelXML();
		}
	}

	public void saveLevelXML() throws ParserConfigurationException, TransformerException {
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
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
		/**
		 * DELEGATE THIS PART
		 */
		JFrame parentFrame = new JFrame();
		JFileChooser fc = new JFileChooser();
		fc.setCurrentDirectory(new java.io.File("."));
		fc.setDialogTitle("Choose where to save the level");
		FileNameExtensionFilter filter = new FileNameExtensionFilter("xml files (*.xml)", "xml");
		fc.setFileFilter(filter);
		int userSelection = fc.showSaveDialog(parentFrame);
		File fileToSave = null;
		if (userSelection == JFileChooser.APPROVE_OPTION) {
			fileToSave = new File(fc.getSelectedFile().getAbsolutePath() + ".xml");
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(fileToSave);
			transformer.transform(source, result);
		} else if (userSelection == JFileChooser.CANCEL_OPTION) {
			JOptionPane.showMessageDialog(null, "File not saved", "Error", JOptionPane.ERROR_MESSAGE);
		}
		/**
		 * 
		 */
	}
}
