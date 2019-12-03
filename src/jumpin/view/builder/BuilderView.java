package jumpin.view.builder;

import javax.swing.BoxLayout;
import javax.swing.JFrame;

import jumpin.model.board.BoardModel;
import jumpin.model.board.util.BoardUtilities;
import jumpin.view.AbstractFrame;
import jumpin.view.builder.menu.BuilderMenu;
import jumpin.view.builder.menu.menus.PieceMenu;
import jumpin.view.builder.transfer.handler.DropHandler;
import jumpin.view.constants.ComponentSize;
import jumpin.view.constants.ViewConstants;
import jumpin.view.factory.FontFactory;
import jumpin.view.factory.ImageFactory;
import jumpin.view.game.board.BoardView;
import jumpin.view.game.board.tile.TileView;

/**
 * 
 * @author Giuseppe
 *
 */
public class BuilderView extends JFrame implements AbstractFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1916881036341049618L;
	private BuilderMenu menu;

	private BoardView boardView;

	/**
	 * constructor method 
	 */
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

		boardView = new BoardView(new BoardModel(BoardUtilities.createDefaultBoardModel()));

		menu = new BuilderMenu(new PieceMenu(), new DropHandler(this));
		getContentPane().add(menu);

		getContentPane().add(boardView);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public BuilderMenu getMenu() {
		return menu;
	}

	@Override
	public void dispose() {
		/**
		 * Reset the board
		 */
		getMenu();
		for (TileView tileView : boardView.getTileViews()) {
			tileView.getModel().clear();
			tileView.clearPiece();
		}
		super.dispose();
	}

	public BoardView getBoardView() {
		return boardView;
	}

}
