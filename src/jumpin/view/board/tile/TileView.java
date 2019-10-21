package jumpin.view.board.tile;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagLayout;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import jumpin.model.board.tile.RabbitHole;
import jumpin.model.board.tile.Tile;
import jumpin.view.board.JumpINContainer;
import jumpin.view.constants.ComponentSize;
import jumpin.view.constants.ImageConstants;
import jumpin.view.factory.ComponentFactory;
import jumpin.view.piece.PieceView;

public class TileView extends JPanel implements JumpINContainer {
	/**
	 * 
	 */
	private static final long serialVersionUID = 9177793025684748087L;

	private Tile model;

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
	}

	public void clearPiece() {
		removeAll();
	}

	@Override
	public void populate() {
		if (!model.isEmpty()) {
			add(ComponentFactory.createPieceView(model.getPiece()));
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
		setBorder(new LineBorder(Color.WHITE, 2));
	}

}
