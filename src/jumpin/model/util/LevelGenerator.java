package jumpin.model.util;

import jumpin.model.GameState;
import jumpin.model.board.Board;
import jumpin.model.constants.BoardConstants;
import jumpin.model.constants.FoxPart;
import jumpin.model.constants.Orientation;
import jumpin.model.constants.PieceID;
import jumpin.model.constants.StateOfGame;
import jumpin.model.piece.Piece;
import jumpin.model.piece.pieces.Fox;
import jumpin.model.piece.pieces.Mushroom;
import jumpin.model.piece.pieces.Rabbit;
import java.io.File;
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
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;

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

	/**
	 * method to create the first level of the game
	 * 
	 */
	public void createLevel1() {
		Fox fox = new Fox(FoxPart.HEAD, Orientation.NORTH_SOUTH, PieceID.FOX_ID_1);
		Fox fox2 = new Fox(FoxPart.TAIL, Orientation.NORTH_SOUTH, PieceID.FOX_ID_1);
		Rabbit rabbit = new Rabbit(PieceID.RABBIT_ID_1);
		Mushroom mushroom = new Mushroom();
		Mushroom mushroom2 = new Mushroom();
		Fox foxb = new Fox(FoxPart.HEAD, Orientation.EAST_WEST, PieceID.FOX_ID_2);
		Fox foxb2 = new Fox(FoxPart.TAIL, Orientation.EAST_WEST, PieceID.FOX_ID_2);
		Rabbit rabbit2 = new Rabbit(PieceID.RABBIT_ID_2);
		Rabbit rabbit3 = new Rabbit(PieceID.RABBIT_ID_3);
		board.assignPiece(new Position(1, 0), fox2);
		board.assignPiece(new Position(1, 1), fox);
		board.assignPiece(new Position(3, 0), rabbit);
		board.assignPiece(new Position(3, 1), mushroom);
		board.assignPiece(new Position(4, 3), foxb2);
		board.assignPiece(new Position(3, 3), foxb);
		board.assignPiece(new Position(2, 4), mushroom2);
		board.assignPiece(new Position(1, 4), rabbit2);
		board.assignPiece(new Position(4, 2), rabbit3);
		gameState.setNumToWin(3);
		gameState.setState(StateOfGame.IN_PROGRESS);
		board.addModelListener(gameState);
	}

	public void createLevelTest() {
		Fox fox = new Fox(FoxPart.HEAD, Orientation.EAST_WEST, PieceID.FOX_ID_2);
		Fox fox2 = new Fox(FoxPart.TAIL, Orientation.EAST_WEST, PieceID.FOX_ID_2);
		Mushroom mushroom = new Mushroom();
		Mushroom mushroom2 = new Mushroom();
		Rabbit rabbit = new Rabbit(PieceID.RABBIT_ID_1);
		board.assignPiece(new Position(3, 2), rabbit);
		board.assignPiece(new Position(3, 3), fox);
		board.assignPiece(new Position(4, 3), fox2);
		board.assignPiece(new Position(2, 4), mushroom);
		board.assignPiece(new Position(1, 4), mushroom2);
		gameState.setNumToWin(1);
		gameState.setState(StateOfGame.IN_PROGRESS);
		board.addModelListener(gameState);
	}

	public void createLevelTest2() {
		Fox fox = new Fox(FoxPart.HEAD, Orientation.EAST_WEST, PieceID.FOX_ID_2);
		Fox fox2 = new Fox(FoxPart.TAIL, Orientation.EAST_WEST, PieceID.FOX_ID_2);
		Fox fox3 = new Fox(FoxPart.HEAD, Orientation.NORTH_SOUTH, PieceID.FOX_ID_1);
		Fox fox4 = new Fox(FoxPart.TAIL, Orientation.NORTH_SOUTH, PieceID.FOX_ID_1);
		Mushroom mushroom = new Mushroom();
		Mushroom mushroom2 = new Mushroom();
		Mushroom mushroom3 = new Mushroom();
		Rabbit rabbit = new Rabbit(PieceID.RABBIT_ID_1);
		Rabbit rabbit2 = new Rabbit(PieceID.RABBIT_ID_1);
		board.assignPiece(new Position(3, 0), rabbit);
		board.assignPiece(new Position(0, 2), rabbit2);
		board.assignPiece(new Position(1, 3), fox3);
		board.assignPiece(new Position(1, 2), fox4);
		board.assignPiece(new Position(2, 1), fox);
		board.assignPiece(new Position(3, 1), fox2);
		board.assignPiece(new Position(3, 2), mushroom);
		board.assignPiece(new Position(3, 3), mushroom2);
		board.assignPiece(new Position(2, 4), mushroom3);
		gameState.setNumToWin(2);
		gameState.setState(StateOfGame.IN_PROGRESS);
		board.addModelListener(gameState);
	}

	public void createLevelTest3() {
		Fox fox = new Fox(FoxPart.HEAD, Orientation.EAST_WEST, PieceID.FOX_ID_2);
		Fox fox2 = new Fox(FoxPart.TAIL, Orientation.EAST_WEST, PieceID.FOX_ID_2);
		Fox fox3 = new Fox(FoxPart.HEAD, Orientation.NORTH_SOUTH, PieceID.FOX_ID_1);
		Fox fox4 = new Fox(FoxPart.TAIL, Orientation.NORTH_SOUTH, PieceID.FOX_ID_1);
		Mushroom mushroom = new Mushroom();
		Mushroom mushroom2 = new Mushroom();
		Mushroom mushroom3 = new Mushroom();
		Rabbit rabbit = new Rabbit(PieceID.RABBIT_ID_1);
		Rabbit rabbit2 = new Rabbit(PieceID.RABBIT_ID_1);
		Rabbit rabbit3 = new Rabbit(PieceID.RABBIT_ID_1);
		board.assignPiece(new Position(2, 0), rabbit);
		board.assignPiece(new Position(0, 2), rabbit2);
		board.assignPiece(new Position(3, 4), rabbit3);
		board.assignPiece(new Position(1, 1), fox3);
		board.assignPiece(new Position(1, 0), fox4);
		board.assignPiece(new Position(3, 1), fox);
		board.assignPiece(new Position(4, 1), fox2);
		board.assignPiece(new Position(3, 2), mushroom);
		board.assignPiece(new Position(3, 3), mushroom2);
		board.assignPiece(new Position(2, 4), mushroom3);
		gameState.setNumToWin(3);
		gameState.setState(StateOfGame.IN_PROGRESS);
		board.addModelListener(gameState);
	}

	public void loadLevelXML(String filename) {
		try {
			File inputFile = new File(filename);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(inputFile);
			doc.getDocumentElement().normalize();
			NodeList nList = doc.getElementsByTagName("piece");
			
			for (int i = 0; i < nList.getLength(); i++) {
				Piece piece;
				Node nNode = nList.item(i);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					eElement.getAttribute("name");
					if(eElement.getAttribute("name").equals("fox")) {
						piece=new Fox(
						FoxPart.getFoxPart(eElement.getElementsByTagName("foxPart").item(0).getTextContent()),
						Orientation.getOrientation(eElement.getElementsByTagName("orientation").item(0).getTextContent()),
						eElement.getElementsByTagName("id").item(0).getTextContent());
					}else if(eElement.getAttribute("name").equals("rabbit")) {
						piece=new Rabbit(eElement.getElementsByTagName("id").item(0).getTextContent());
					}else {
						piece = new Mushroom();
					}
				 Node n=(Node)(eElement.getElementsByTagName("position").item(0));
				 Element position=(Element)n;
				 int x=Integer.parseInt(position.getAttribute("x"));
				 int y=Integer.parseInt(position.getAttribute("y"));
				 board.assignPiece(new Position(x,y),piece);
				}
			}
			
			gameState.setNumToWin(Integer.parseInt(doc.getDocumentElement().getAttribute("numToWin")));
			gameState.setState(StateOfGame.IN_PROGRESS);
			board.addModelListener(gameState);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void saveLevelXML() throws ParserConfigurationException, TransformerException {
		  DocumentBuilderFactory dbFactory =DocumentBuilderFactory.newInstance();
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
						Piece piece=board.getSelectedPiece();
						Element pieceElem=doc.createElement("piece");
						Attr attrpiece=doc.createAttribute("name");;
						if(piece instanceof Fox) {
						    attrpiece.setValue("fox");
							Element foxPart=doc.createElement("foxPart");
							foxPart.appendChild(doc.createTextNode(((FoxPart)(((Fox)piece).getPart())).toString())); 
							pieceElem.appendChild(foxPart);
							Element orientation=doc.createElement("orientation");
							orientation.appendChild(doc.createTextNode(((Orientation)((Fox)piece).getOrientation()).toString()));
							pieceElem.appendChild(orientation);
							Element id=doc.createElement("id");
							id.appendChild(doc.createTextNode(((Fox)piece).getPieceID())); 
							pieceElem.appendChild(id);
						}else if(piece instanceof Rabbit) {
						    attrpiece.setValue("rabbit");
							Element id=doc.createElement("id");
							id.appendChild(doc.createTextNode(((Rabbit)piece).getPieceID())); 
							pieceElem.appendChild(id);
						}else {
						    attrpiece.setValue("mushroom");
						}
						pieceElem.setAttributeNode(attrpiece);
						Element position=doc.createElement("position");
						Attr x=doc.createAttribute("x");
						x.setValue(String.valueOf(pos.getX()));
						Attr y=doc.createAttribute("y");
						y.setValue(String.valueOf(pos.getY()));
						position.setAttributeNode(x);
						position.setAttributeNode(y);
						pieceElem.appendChild(position);
						rootElement.appendChild(pieceElem);
					}

				}
			}
	      //to a file
	      TransformerFactory transformerFactory = TransformerFactory.newInstance();
	      Transformer transformer = transformerFactory.newTransformer();
	      DOMSource source = new DOMSource(doc);
	      //StreamResult result = new StreamResult(new File("C:\\cars.xml"));
	      //transformer.transform(source, result);
	      
	   // Output to console for testing
	         StreamResult consoleResult = new StreamResult(System.out);
	         transformer.transform(source, consoleResult);
	}
}
