package jumpin.view;

import javax.swing.BoxLayout;
import javax.swing.JFrame;

import jumpin.controller.listener.BoardListener;
import jumpin.controller.listener.PieceListener;
import jumpin.model.GameModel;
import jumpin.view.board.BoardView;
import jumpin.view.board.tile.TileView;
import jumpin.view.constants.ComponentSize;
import jumpin.view.constants.ViewConstants;
import jumpin.view.factory.FontFactory;
import jumpin.view.menu.MainMenu;

/**
 * 
 * @author Giuseppe
 */
public class GameView extends JFrame implements AbstractFrame {

	private static final long serialVersionUID = -5089660197653181626L;
	private GameModel model;
	private BoardView boardView;
	private MainMenu menu;

	/** 
	 * Default constructor that initializes the game
	 * @param model game
	 */
	public GameView(GameModel model) {
		this.model = model;
		populate();
	}

	/**
	 * Method to return constructed board
	 * 
	 * @return constructed board
	 */
	public BoardView getBoardView() {
		return boardView;
	}

	/**
	 * Method to set up the game
	 */
	@Override
	public void populate() {
		setFont(FontFactory.createDefaultFont());
		setResizable(false);
		setTitle(ViewConstants.FRAME_TITLE);
		setSize(ComponentSize.FRAME_WIDTH, ComponentSize.FRAME_HEIGHT);
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

		menu = new MainMenu();
		getContentPane().add(menu);

		boardView = new BoardView(model.getBoard().getModel());
		getContentPane().add(boardView);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/**
	 * Method to add a piece listener
	 * 
	 * @param l
	 */
	public void addPieceListener(PieceListener l) {
		for (TileView view : boardView.getTileViews()) {
			if (view.getPieceView() != null) {
				view.getPieceView().addMouseListener(l);
			}
		}
	}

	/**
	 * Method to add a board listener
	 * 
	 * @param l
	 */
	public void addBoardListener(BoardListener l) {
		for (TileView view : boardView.getTileViews()) {
			view.addMouseListener(l);
		}
	}
	
	public MainMenu getMainMenu() {
		return menu;
	}

}
