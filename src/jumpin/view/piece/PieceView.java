package jumpin.view.piece;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import jumpin.model.constants.Orientation;
import jumpin.model.constants.PieceID;
import jumpin.model.piece.Piece;
import jumpin.model.piece.pieces.Fox;
import jumpin.model.piece.pieces.Rabbit;
import jumpin.view.constants.ImageConstants;

public class PieceView extends JLabel {

	private Piece model;

	/**
	 * 
	 */
	private static final long serialVersionUID = 159673643052373533L;

	public PieceView(Piece model) {
		setBounds(0, 0, 75, 75);
		setMaximumSize(getSize());
		this.model = model;
		ImageIcon imageIcon = new ImageIcon(getImageLocation());

		setIcon(imageIcon);
	}

	private String getImageLocation() {
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
			}
		}
		return ImageConstants.MUSHROOM;
	}

	public Piece getModel() {
		return model;
	}

}
