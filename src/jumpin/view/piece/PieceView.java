package jumpin.view.piece;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import jumpin.model.piece.Piece;
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
		return ImageConstants.RABBIT_1;
	}

}
