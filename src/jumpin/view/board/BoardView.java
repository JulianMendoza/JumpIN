package jumpin.view.board;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLayeredPane;

import org.apache.commons.collections4.BidiMap;
import org.apache.commons.collections4.bidimap.DualHashBidiMap;

import jumpin.model.board.BoardModel;
import jumpin.model.util.Position;
import jumpin.view.AbstractFrame;
import jumpin.view.board.tile.TileHighlighter;
import jumpin.view.board.tile.TileView;
import jumpin.view.constants.ComponentSize;
import jumpin.view.factory.ComponentFactory;

public class BoardView extends JLayeredPane implements AbstractFrame {

	private BoardModel model;
	private BidiMap<Position, TileView> tileMap;

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
		tileMap = new DualHashBidiMap<Position, TileView>();
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
				tileMap.put(new Position(x, y), view);
			}
		}
	}

	public TileView getTileView(Position position) {
		return tileMap.get(position);
	}

	public Position getPosition(TileView tileView) {
		return tileMap.getKey(tileView);
	}

	public List<TileView> getTileViews() {
		return new ArrayList<TileView>(tileMap.values());
	}

	public void highlight(List<TileView> highlightTiles, List<TileView> selectTiles) {
		highlighter.stopHighlighting();
		highlighter.highlight(highlightTiles, selectTiles);
	}

	public void stopHighlighting() {
		highlighter.stopHighlighting();
	}

	@Override
	public void repopulate() {
		for (TileView tileView : getTileViews()) {
			tileView.repopulate();
		}

	}

}
