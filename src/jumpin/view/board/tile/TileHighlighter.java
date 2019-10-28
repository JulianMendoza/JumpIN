package jumpin.view.board.tile;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingWorker;
import javax.swing.border.LineBorder;

public class TileHighlighter extends SwingWorker<Void, Void> {

	private final int MAX_VAL = 255;
	private final int MIN_VAL = 175;
	private final int RED = 255;
	private final int BLUE = 0;
	private final int GREEN = 0;

	private List<TileView> highlightTiles;
	private List<TileView> selectTiles;
	private boolean isPaused;

	public TileHighlighter() {
		isPaused = true;
		execute();
	}

	public void highlight(List<TileView> highlightTiles, List<TileView> selectTiles) {
		this.highlightTiles = new ArrayList<TileView>(highlightTiles);
		this.selectTiles = new ArrayList<TileView>(selectTiles);
		isPaused = false;
	}

	public void stopHighlighting() {
		isPaused = true;
		if (highlightTiles != null) {
			for (TileView tile : highlightTiles) {
				tile.setDefaultBorder();
			}
			highlightTiles = null;
		}

		if (selectTiles != null) {
			for (TileView tile : selectTiles) {
				tile.setDefaultBorder();
			}
			selectTiles = null;
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
		for (TileView tile : highlightTiles) {
			tile.setBorder(new LineBorder(new Color(RED, i, BLUE), 4));
		}

		for (TileView tile : selectTiles) {
			tile.setBorder(new LineBorder(new Color(i, GREEN, BLUE), 4));
		}

		try {
			Thread.sleep(25);
		} catch (InterruptedException e) {
		}
	}

}
