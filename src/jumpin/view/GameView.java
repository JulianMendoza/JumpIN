package jumpin.view;

import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.JFrame;

import jumpin.controller.GameController;
import jumpin.controller.listener.PieceListener;
import jumpin.model.GameModel;
import jumpin.view.board.BoardView;
import jumpin.view.board.JumpINContainer;
import jumpin.view.board.tile.TileView;
import jumpin.view.constants.ComponentSize;
import jumpin.view.menu.MainMenu;

public class GameView extends JFrame implements JumpINContainer {

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

	@SuppressWarnings("unused")
	public static void main(String args[]) {
		GameModel model = new GameModel();
		model.getGenerator().createLevel1();
		GameView view = new GameView(model);
		GameController controller = new GameController(model, view);
		System.out.println(model);
		view.setVisible(true);
	}

	@Override
	public void populate() {
		setFont(new Font("Angsana New", Font.PLAIN, 12));
		setResizable(false);
		setTitle("JumpIN");
		setSize(ComponentSize.FRAME_WIDTH, ComponentSize.FRAME_HEIGHT);
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

		MainMenu menu = new MainMenu();
		getContentPane().add(menu);

		boardView = new BoardView(model.getBoard().getModel());
		getContentPane().add(boardView);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void addPieceListener(PieceListener l) {
		for (TileView view : boardView.getTileMap().values()) {
			if (view.getPieceView() != null) {
				view.getPieceView().addMouseListener(l);
			}
		}
	}

	@Override
	public void repopulate() {
		boardView.populate();
	}
}
