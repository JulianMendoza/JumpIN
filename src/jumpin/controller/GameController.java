package jumpin.controller;

import jumpin.controller.listener.BoardListener;
import jumpin.controller.listener.PieceListener;
import jumpin.controller.listener.ViewModelListener;
import jumpin.model.GameModel;
import jumpin.view.GameView;

/**
 * 
 * @author Giuseppe
 *
 */
public class GameController {

	private GameModel model;
	private GameView view;

	public GameController(GameModel model, GameView view) {
		this.view = view;
		this.model = model;

		initializeListeners();
	}

	public void launch() {
		view.setVisible(true);
	}

	private void initializeListeners() {
		view.addPieceListener(new PieceListener(model, view));
		view.addBoardListener(new BoardListener(model, view));
		model.getBoard().addModelListener(new ViewModelListener(view.getBoardView()));
	}

}
