package jumpin.view.builder;

import java.awt.Component;

import javax.swing.BoxLayout;
import javax.swing.JFrame;

import jumpin.model.board.BoardModel;
import jumpin.model.board.util.BoardUtilities;
import jumpin.view.AbstractFrame;
import jumpin.view.builder.menu.BuilderMenu;
import jumpin.view.constants.ComponentSize;
import jumpin.view.constants.ViewConstants;
import jumpin.view.factory.FontFactory;
import jumpin.view.factory.ImageFactory;
import jumpin.view.game.board.BoardView;
import jumpin.view.game.board.tile.TileView;

public class BuilderView extends JFrame implements AbstractFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1916881036341049618L;
	private BuilderMenu menu;

	private BoardView boardView;

	public BuilderView() {
		super();
		populate();
	}

	@Override
	public void populate() {
		setFont(FontFactory.createDefaultFont());
		setResizable(false);
		setTitle(ViewConstants.GAME_FRAME_TITLE + " Level Builder");
		setSize(ComponentSize.BUILDER_FRAME_WIDTH, ComponentSize.BUILDER_FRAME_HEIGHT);
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		setIconImage(ImageFactory.generateRabbit());
		setLocationRelativeTo(null);

		menu = new BuilderMenu();
		getContentPane().add(new BuilderMenuScroller(menu));

		boardView = new BoardView(new BoardModel(BoardUtilities.createDefaultBoardModel()));
		TileDropHandler handler = new TileDropHandler(boardView);

		getContentPane().add(boardView);
		for (Component c : boardView.getComponents()) {
			if (c instanceof TileView) {
				TileView tileView = (TileView) c;
				tileView.setTransferHandler(handler);
			}
		}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String args[]) {
		BuilderView view = new BuilderView();
		view.setVisible(true);
	}
}
