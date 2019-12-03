package jumpin.controller.game.listener;

import javax.swing.JOptionPane;

import jumpin.controller.game.GameController;
import jumpin.model.GameModel;
import jumpin.model.exception.IllegalMoveException;
import jumpin.model.file.LevelParser;
import jumpin.view.game.GameView;
import jumpin.view.game.menu.GameMenu;
import jumpin.view.level.LevelDialog;
import jumpin.view.listener.MenuEvent;
import jumpin.view.listener.MenuListener;
import jumpin.view.prompt.ThresholdPrompt;
import jumpin.view.util.Waiter;

/**
 * 
 * @author Giuseppe
 *
 */
public class GameMenuListener implements MenuListener {

	private GameView view;
	private GameModel model;
	private GameController gameController;

	/**
	 * constructor method
	 * @param gc
	 */
	public GameMenuListener(GameController gc) {
		this.model = gc.getModel();
		this.view = gc.getGameView();
		this.gameController = gc;
	}

	@Override
	public void menuActionPerformed(int menuEvent) {
		Waiter waiter = new Waiter(view);
		waiter.startWaiting();
		GameMenu menu = view.getMainMenu();

		switch (menuEvent) {
		case MenuEvent.UNDO:
			model.getBoard().undoMove();
			menu.getSolverMenu().resetSolution();
			break;
		case MenuEvent.REDO:
			model.getBoard().redoMove();
			menu.getSolverMenu().resetSolution();
			break;
		case MenuEvent.DO_MOVE:
			try {
				model.getBoard().doNextBestMove();
			} catch (IllegalMoveException e) {
				e.printStackTrace();
			}
			break;
		case MenuEvent.SHOW_SOLUTION:
			JOptionPane.showMessageDialog(view, model.getBoard().getSolution().toString(), "Current Best Solution", JOptionPane.PLAIN_MESSAGE);
			break;
		case MenuEvent.FIND_SOLUTION:
			ThresholdPrompt thresholdPrompt = menu.getSolverMenu().getThresholdPrompt();
			int result = JOptionPane.showConfirmDialog(view, thresholdPrompt, "Search threshold", JOptionPane.OK_CANCEL_OPTION);
			if (result == JOptionPane.OK_OPTION) {
				try {
					int threshold = Integer.parseInt(thresholdPrompt.getText());
					model.getBoard().computeSolution(threshold);
					if (model.getBoard().getSolution() == null) {
						JOptionPane.showMessageDialog(view, "No Solutions found!", "Invalid entry!", JOptionPane.ERROR_MESSAGE);
						thresholdPrompt.clearText();
					} else { // successful computation
						menu.getSolverMenu().setSolution();
					}
				} catch (Exception x) {
					JOptionPane.showMessageDialog(view, "Please only enter integers", "Invalid entry!", JOptionPane.ERROR_MESSAGE);
					thresholdPrompt.clearText();
				}
			}
			break;
		case MenuEvent.SAVE_LEVEL:
			LevelDialog saver = new LevelDialog();
			saver.saveLevel(model);
			break;
		case MenuEvent.LOAD_LEVEL:
			LevelDialog loader = new LevelDialog();
			LevelParser parser = loader.parseLevel(false);
			if (parser.successfulyParsed()) {
				gameController.handleLoad(parser);
			}
			break;
		case MenuEvent.BUILD_LEVEL:
			view.dispose();
			gameController.handleBuild();
			break;
		}

		waiter.stopWaiting();

	}

}
