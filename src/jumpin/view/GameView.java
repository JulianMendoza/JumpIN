package jumpin.view;

import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.JFrame;

import jumpin.model.GameModel;
import jumpin.view.board.BoardView;
import jumpin.view.constants.ComponentSize;
import jumpin.view.menu.MainMenu;

public class GameView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5089660197653181626L;
	private GameModel model;

	public GameView(GameModel model) {
		this.model = model;

		setFont(new Font("Angsana New", Font.PLAIN, 12));
		setResizable(false);
		setTitle("JumpIN");
		setSize(ComponentSize.FRAME_WIDTH, ComponentSize.FRAME_HEIGHT);
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

		MainMenu menu = new MainMenu();
		getContentPane().add(menu);

		BoardView board = new BoardView(model.getBoard().getModel());
		getContentPane().add(board);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String args[]) {
		GameModel model = new GameModel();
		GameView view = new GameView(model);
		view.show();
	}
}
