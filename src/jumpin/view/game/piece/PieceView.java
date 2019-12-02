package jumpin.view.game.piece;

import java.io.Serializable;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import jumpin.model.constants.Orientation;
import jumpin.model.constants.PieceID;
import jumpin.model.piece.Piece;
import jumpin.model.piece.UniquePiece;
import jumpin.model.piece.pieces.Fox;
import jumpin.model.piece.pieces.Rabbit;
import jumpin.view.constants.ImageConstants;

/**
 * View class for a game piece
 * 
 * @author Giuseppe
 */
public class PieceView extends JLabel implements Serializable {

	private static final long serialVersionUID = 159673643052373533L;
	private Piece model;

	/**
	 * Default Constructor
	 * 
	 * @param model
	 */
	public PieceView(Piece model) {
		super(new ImageIcon(getImageLocation(model)));
		this.model = model;
		setBounds(0, 0, 75, 75);
		setMaximumSize(getSize());
	}

	public PieceView(PieceView pieceView) {
		super(pieceView.getIcon());
		if (pieceView.getPiece() instanceof UniquePiece) {

		} else {
			this.model = pieceView.getPiece();
		}
		setBounds(0, 0, 75, 75);
		setMaximumSize(getSize());
	}

	public Piece getPiece() {
		return model;
	}

	/**
	 * Method to check images against the constants
	 * 
	 * @return location of image path
	 */
	private static String getImageLocation(Piece model) {
		if (model instanceof Fox) {
			Fox fox = (Fox) model;
			switch (fox.getPart()) {
			case HEAD:
				if (fox.getOrientation().equals(Orientation.NORTH_SOUTH)) {
					return ImageConstants.FOX_HEAD_NORTHSOUTH;
				} else {
					return ImageConstants.FOX_HEAD_EASTWEST;
				}
			case TAIL:
				if (fox.getOrientation().equals(Orientation.NORTH_SOUTH)) {
					return ImageConstants.FOX_TAIL_NORTHSOUTH;
				} else {
					return ImageConstants.FOX_TAIL_EASTWEST;
				}
			default:
				break;

			}
		} else if (model instanceof Rabbit) {
			switch (((Rabbit) model).getPieceID()) {
			case PieceID.RABBIT_ID_1:
				return ImageConstants.RABBIT_1;
			case PieceID.RABBIT_ID_2:
				return ImageConstants.RABBIT_2;
			case PieceID.RABBIT_ID_3:
				return ImageConstants.RABBIT_3;
			default:
				return ImageConstants.RABBIT_1;
			}
		}
		return ImageConstants.MUSHROOM;
	}

}
