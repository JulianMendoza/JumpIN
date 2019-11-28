package jumpin.view.game.board.tile;

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
import jumpin.view.game.piece.PieceView;

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

	/**
	 * Default constructor to initialize board tiles
	 * 
	 * @param model
	 */
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

	/**
	 * Method to set piece on tile
	 * 
	 * @param pieceView selected piece
	 */
	public void setPiece(PieceView pieceView) {
		add(pieceView);
		this.pieceView = pieceView;
	}

	/**
	 * Method to clear tile from piece
	 * 
	 * @return cleared tile
	 */
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

	/**
	 * Method to get location path for image
	 * 
	 * @return the location path
	 */
	private String getImageLocation() {
		if (model instanceof RabbitHole) {
			return ImageConstants.RABBIT_HOLE;
		} else {
			return ImageConstants.TILE;
		}
	}

	/**
	 * Method to set color around tile
	 * 
	 */
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
