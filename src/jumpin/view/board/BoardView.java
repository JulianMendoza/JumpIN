package jumpin.view.board;

import java.awt.GridLayout;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JLayeredPane;

import jumpin.model.board.BoardModel;
import jumpin.model.util.Position;
import jumpin.view.board.tile.TileHighlighter;
import jumpin.view.board.tile.TileView;
import jumpin.view.constants.ComponentSize;
import jumpin.view.factory.ComponentFactory;

public class BoardView extends JLayeredPane implements JumpINContainer {

	private BoardModel model;
	private Map<Position, TileView> tileMap;

	/**
	 * 
	 */
	private static final long serialVersionUID = -5069662942928656921L;
	private TileHighlighter highlighter;

	public BoardView(BoardModel model) {
		setBounds(0, 0, ComponentSize.BOARD_WIDTH, ComponentSize.BOARD_HEIGHT);
		setLayout(new GridLayout(model.getWidth(), model.getHeight(), 0, 0));
		setMaximumSize(getSize());
		this.model = model;
		tileMap = new HashMap<Position, TileView>();
		populate();
		highlighter = new TileHighlighter();
		// highlighter.execute();
	}

	@Override
	public void populate() {
		for (int y = 0; y < model.getHeight(); y++) {
			for (int x = 0; x < model.getWidth(); x++) {
				TileView view = ComponentFactory.createTileView(model.getTile(x, y));
				add(view);
				tileMap.put(model.getTile(x, y).getPosition(), view);
			}
		}
	}

	public Map<Position, TileView> getTileMap() {
		return tileMap;
	}

	public TileView getTileView(Position position) {
		return tileMap.get(position);
	}

	public void highlight(List<TileView> highlightTiles, TileView selectTile) {
		highlighter.stopHighlighting();
		highlighter.highlight(highlightTiles, selectTile);
	}

	public void stopHighlighting() {
		highlighter.stopHighlighting();
	}

	@Override
	public void repopulate() {
		for (TileView tile : tileMap.values()) {
			tile.repopulate();
		}

	}

}
