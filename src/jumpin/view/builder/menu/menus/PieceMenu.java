package jumpin.view.builder.menu.menus;

import javax.swing.JPanel;

import jumpin.model.constants.FoxPart;
import jumpin.model.constants.Orientation;
import jumpin.model.constants.PieceID;
import jumpin.model.piece.pieces.Fox;
import jumpin.model.piece.pieces.Mushroom;
import jumpin.model.piece.pieces.Rabbit;
import jumpin.view.AbstractFrame;
import jumpin.view.constants.ComponentSize;
import jumpin.view.constants.ViewConstants;
import jumpin.view.factory.DragFactory;
import jumpin.view.game.piece.PieceView;

/**
 * 
 * @author Giuseppe
 *
 */
public class PieceMenu extends JPanel implements AbstractFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3784367179192639288L;

	/**
	 * constructor method
	 */
	public PieceMenu() {
		super();
		populate();
	}

	@Override
	public void populate() {
		setBounds(0, 0, ComponentSize.BUILDER_MENU_WIDTH, ComponentSize.BUILDER_MENU_HEIGHT);
		setMaximumSize(getSize());
		setBackground(ViewConstants.BOARD_COLOR);

		add(DragFactory.makeDraggablePiece(new PieceView(new Rabbit(PieceID.RABBIT))));
		add(DragFactory.makeDraggablePiece(new PieceView(new Fox(FoxPart.HEAD, Orientation.NORTH_SOUTH, PieceID.FOX))));
		add(DragFactory.makeDraggablePiece(new PieceView(new Fox(FoxPart.TAIL, Orientation.NORTH_SOUTH, PieceID.FOX))));
		add(DragFactory.makeDraggablePiece(new PieceView(new Fox(FoxPart.HEAD, Orientation.EAST_WEST, PieceID.FOX))));
		add(DragFactory.makeDraggablePiece(new PieceView(new Fox(FoxPart.TAIL, Orientation.EAST_WEST, PieceID.FOX))));
		add(DragFactory.makeDraggablePiece(new PieceView(new Mushroom())));
	}

}
