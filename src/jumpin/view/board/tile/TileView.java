package jumpin.view.board.tile;

import java.awt.Graphics;
import java.awt.GridBagLayout;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import jumpin.model.board.tile.RabbitHole;
import jumpin.model.board.tile.Tile;
import jumpin.view.AbstractFrame;
import jumpin.view.constants.ComponentSize;
import jumpin.view.constants.ImageConstants;
import jumpin.view.constants.ViewConstants;
import jumpin.view.factory.ComponentFactory;
import jumpin.view.piece.PieceView;

/**
 * Panel that represents a tile
 * 
 * @author Giuseppe
 *
 */
public class TileView extends JPanel implements AbstractFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 9177793025684748087L;

	private Tile model;
	private PieceView pieceView;

	public TileView(Tile model) {
		setDefaultBorder();

		setBounds(0, 0, ComponentSize.TILE_WIDTH, ComponentSize.TILE_HEIGHT);
		setMaximumSize(getSize());
		this.model = model;
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0 };
		gridBagLayout.rowHeights = new int[] { 0 };
		gridBagLayout.columnWeights = new double[] { Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { Double.MIN_VALUE };
		setLayout(gridBagLayout);
		populate();
	}

	public void setPiece(PieceView pieceView) {
		add(pieceView);
		this.pieceView = pieceView;
	}

	public PieceView clearPiece() {
		if (pieceView != null) {
			PieceView clearedPiece = pieceView;
			remove(pieceView);
			pieceView = null;
			return clearedPiece;
		}
		return null;
	}

	public PieceView getPieceView() {
		return pieceView;
	}

	@Override
	public void populate() {
		if (!model.isEmpty()) {
			setPiece(ComponentFactory.createPieceView(model.getPiece()));
		} else {
			clearPiece();
		}

	}

	@Override
	protected void paintComponent(Graphics g) {
		ImageIcon imageIcon = new ImageIcon(getImageLocation());
		super.paintComponent(g);
		g.drawImage(imageIcon.getImage(), 0, 0, null);

	}

	private String getImageLocation() {
		if (model instanceof RabbitHole) {
			return ImageConstants.RABBIT_HOLE;
		} else {
			return ImageConstants.TILE;
		}
	}

	public void setDefaultBorder() {
		setBorder(ViewConstants.DEFAULT_TILE_BORDER);
	}

	public Tile getModel() {
		return model;
	}

	@Override
	public int hashCode() {
		return model.hashCode() * 37 | super.hashCode() * 17;
	}

}
