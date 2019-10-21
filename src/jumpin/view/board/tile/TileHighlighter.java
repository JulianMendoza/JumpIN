package jumpin.view.board.tile;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingWorker;
import javax.swing.border.LineBorder;

public class TileHighlighter extends SwingWorker<Void, Void> {

	private final int MAX_VAL = 250;
	private final int MIN_VAL = 170;
	private final int RED = 255;
	private final int BLUE = 0;
	private final int GREEN = 0;

	private List<TileView> tiles;
	private TileView selectTile;
	private boolean isPaused;

	public TileHighlighter() {
		isPaused = true;
		execute();
	}

	public void highlight(List<TileView> highlightTiles, TileView selectTile) {
		this.tiles = new ArrayList<TileView>(highlightTiles);
		this.selectTile = selectTile;
		isPaused = false;
	}

	public void stopHighlighting() {
		isPaused = true;
		if (tiles != null) {
			for (TileView tile : tiles) {
				tile.setDefaultBorder();
			}
			tiles = null;
		}

		if (selectTile != null) {
			selectTile.setDefaultBorder();
			selectTile = null;
		}
	}

	@Override
	protected Void doInBackground() throws Exception {
		while (true) {
			if (!isPaused) {
				for (int i = MIN_VAL; i < MAX_VAL; i += 5) { // count up
					updateAndWait(i);
				}

				for (int i = MAX_VAL; i > MIN_VAL; i -= 5) { // count down
					updateAndWait(i);
				}
			}
			Thread.sleep(100);
		}
	}

	private void updateAndWait(int i) {
		for (TileView tile : tiles) {
			tile.setBorder(new LineBorder(new Color(RED, i, BLUE), 4));
		}

		selectTile.setBorder(new LineBorder(new Color(i, GREEN, BLUE), 4));

		try {
			Thread.sleep(25);
		} catch (InterruptedException e) {
		}
	}

}
