package jumpin.view.board.tile;

import java.awt.Color;
import java.util.List;

import javax.swing.SwingWorker;
import javax.swing.border.LineBorder;

public class TileHighlighter extends SwingWorker<Void, Void> {

	private final int MAX_GREEN = 250;
	private final int MIN_GREEN = 170;
	private final int RED = 255;
	private final int BLUE = 0;

	private List<TileView> tiles;
	private boolean isPaused;

	public TileHighlighter() {
		isPaused = true;
		this.execute();
	}

	public void highlight(List<TileView> tiles) {
		this.tiles = tiles;
		isPaused = false;
	}

	public void stopHighlighting() {
		isPaused = true;
		for (TileView tile : tiles) {
			tile.setDefaultBorder();
		}
		tiles = null;

	}

	@Override
	protected Void doInBackground() throws Exception {
		while (true) {
			if (!isPaused) {
				for (int i = MIN_GREEN; i < MAX_GREEN; i += 5) { // count up
					updateAndWait(i);
				}

				for (int i = MAX_GREEN; i > MIN_GREEN; i -= 5) { // count down
					updateAndWait(i);
				}
			}
		}
	}

	private void updateAndWait(int green) {
		for (TileView tile : tiles) {
			tile.setBorder(new LineBorder(new Color(RED, green, BLUE), 2));
		}
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
		}
	}

}
