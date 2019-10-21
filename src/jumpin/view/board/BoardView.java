package jumpin.view.board;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import jumpin.model.board.BoardModel;
import jumpin.view.board.tile.TileHighlighter;
import jumpin.view.board.tile.TileView;
import jumpin.view.constants.ComponentSize;
import jumpin.view.factory.ComponentFactory;

public class BoardView extends JPanel implements JumpINContainer {

	private BoardModel model;

	/**
	 * 
	 */
	private static final long serialVersionUID = -5069662942928656921L;
	private TileHighlighter highlighter;
	List<TileView> views = new ArrayList<TileView>();

	public BoardView(BoardModel model) {
		setBounds(0, 0, ComponentSize.BOARD_WIDTH, ComponentSize.BOARD_HEIGHT);
		setLayout(new GridLayout(model.getWidth(), model.getHeight(), 0, 0));
		setMaximumSize(getSize());
		this.model = model;
		populate();
		highlighter = new TileHighlighter();
	}

	/*
	 * This needs to be in line with grid layout population - reference java docs
	 * it's wrong rn
	 */
	@Override
	public void populate() {
		for (int y = 0; y < model.getHeight(); y++) {
			for (int x = 0; x < model.getWidth(); x++) {
				add(ComponentFactory.createTileView(model.getTile(x, y)));
			}
		}
	}

	public void highlight(List<TileView> tiles) {
		highlighter.highlight(tiles);
	}

	public void stopHighlighting() {
		highlighter.stopHighlighting();
	}

}
