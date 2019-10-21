package jumpin.view.board;

import java.awt.GridLayout;

import javax.swing.JPanel;

import jumpin.model.board.BoardModel;
import jumpin.model.constants.BoardConstants;
import jumpin.view.constants.ViewConstants;
import jumpin.view.factory.TileFactory;

public class BoardView extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5069662942928656921L;

	public BoardView(BoardModel model) {
		setBounds(0, 0, ViewConstants.BOARD_WIDTH, ViewConstants.BOARD_HEIGHT);
		setLayout(new GridLayout(BoardConstants.WIDTH, BoardConstants.HEIGHT, 0, 0));
		
		populate(model);
	}
	
	/*
	 * This needs to be in line with grid layout population - reference java docs
	 * it's wrong rn
	 */
	public void populate(BoardModel model) {
		for(int x = 0; x < model.getWidth(); x++) {
			for(int y = 0; y < model.getHeight(); y++) {
				add(TileFactory.createTileView(model.getTile(x, y)));
			}
		}
	}
	
	
}
