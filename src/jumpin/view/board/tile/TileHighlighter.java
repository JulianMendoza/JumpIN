package jumpin.view.board.tile;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.border.LineBorder;

import jumpin.view.board.BoardView;

/**
 * SwingWorker for highlighting and selecting tiles in background
 * 
 * @author Giuseppe
 *
 */
public class TileHighlighter extends SwingWorker<Void, Void> {

	private final int MAX_VAL = 255;
	private final int MIN_VAL = 175;
	private final int RED = 255;
	private final int BLUE = 0;
	private final int GREEN = 0;

	private List<TileView> highlightTiles;
	private List<TileView> selectTiles;

	/**
	 * Default constructor to initialize TileHighlighter
	 * 
	 * @param highlightTiles array of tiles to be highlighted to show possible piece
	 *                       move
	 * @param selectTiles    array of select tiles
	 */
	public TileHighlighter(List<TileView> highlightTiles, List<TileView> selectTiles) {
		this.highlightTiles = new ArrayList<TileView>(highlightTiles);
		this.selectTiles = new ArrayList<TileView>(selectTiles);
	}

	/**
	 * Method to stop highlighting the tiles
	 * 
	 * @param boardView board representaion
	 */
	public void stopHighlighting(BoardView boardView) {
		/*
		 * If we aren't doing the work on the background thread (swing worker), dispatch
		 * it to edt
		 */
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
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

				/*
				 * If random tiles are highlighted from the swing edt being trash
				 */
				for (TileView tileView : boardView.getTileViews()) {
					tileView.setDefaultBorder();
				}
			}
		});
	}

	/**
	 * Method to update in background
	 * 
	 */
	@Override
	protected Void doInBackground() throws Exception {
		while (true) {
			for (int i = MIN_VAL; i < MAX_VAL; i += 5) { // count up
				updateAndWait(i);
			}

			for (int i = MAX_VAL; i > MIN_VAL; i -= 5) { // count down
				updateAndWait(i);
			}
		}
	}

	/**
	 * Method that initializes tile border color for highlight and select tiles
	 * 
	 * @param i integer used to initialize different colors
	 */
	private void updateAndWait(int i) {
		for (TileView tile : highlightTiles) {
			tile.setBorder(new LineBorder(new Color(RED, i, BLUE), 2));
		}

		for (TileView tile : selectTiles) {
			tile.setBorder(new LineBorder(new Color(i, GREEN, BLUE), 2));
		}

		try {
			Thread.sleep(25);
		} catch (InterruptedException e) {
		}
	}

}
