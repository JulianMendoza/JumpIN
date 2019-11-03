package jumpin.view;

import javax.swing.BoxLayout;
import javax.swing.JFrame;

import jumpin.controller.GameController;
import jumpin.controller.listener.BoardListener;
import jumpin.controller.listener.PieceListener;
import jumpin.model.GameModel;
import jumpin.view.board.BoardView;
import jumpin.view.board.tile.TileView;
import jumpin.view.constants.ComponentSize;
import jumpin.view.constants.ViewConstants;
import jumpin.view.factory.FontFactory;
import jumpin.view.menu.MainMenu;

public class GameView extends JFrame implements AbstractFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5089660197653181626L;
	private GameModel model;
	private BoardView boardView;

	public GameView(GameModel model) {
		this.model = model;
		populate();
	}

	public BoardView getBoardView() {
		return boardView;
	}

	public static void main(String args[]) {
		GameModel model = new GameModel();
		model.getGenerator().createLevel1();
		GameView view = new GameView(model);
		GameController controller = new GameController(model, view);
		controller.launch();
	}

	@Override
	public void populate() {
		setFont(FontFactory.createDefaultFont());
		setResizable(false);
		setTitle(ViewConstants.FRAME_TITLE);
		setSize(ComponentSize.FRAME_WIDTH, ComponentSize.FRAME_HEIGHT);
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

		MainMenu menu = new MainMenu();
		getContentPane().add(menu);

		boardView = new BoardView(model.getBoard().getModel());
		getContentPane().add(boardView);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void addPieceListener(PieceListener l) {
		for (TileView view : boardView.getTileViews()) {
			if (view.getPieceView() != null) {
				view.getPieceView().addMouseListener(l);
			}
		}
	}

	public void addBoardListener(BoardListener l) {
		for (TileView view : boardView.getTileViews()) {
			view.addMouseListener(l);
		}
	}

	@Override
	public void repopulate() {
		boardView.repopulate();
	}
}
