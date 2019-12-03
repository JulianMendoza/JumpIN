package jumpin.controller.builder.listener;

import javax.swing.JOptionPane;

import jumpin.controller.builder.BuilderController;
import jumpin.model.GameModel;
import jumpin.model.GameState;
import jumpin.model.board.Board;
import jumpin.model.board.util.BoardUtilities;
import jumpin.model.exception.InvalidBoardException;
import jumpin.view.builder.BuilderView;
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
public class BuilderMenuListener implements MenuListener {

	private BuilderView view;
	private BuilderController bc;

	/**
	 * constructor method
	 * @param bc
	 */
	public BuilderMenuListener(BuilderController bc) {
		this.view = bc.getView();
		this.bc = bc;
	}

	@Override
	public void menuActionPerformed(int menuEvent) {
		Waiter waiter = new Waiter(view);
		waiter.startWaiting();

		switch (menuEvent) {
		case MenuEvent.VALIDATE_LEVEL:
			ThresholdPrompt thresholdPrompt = new ThresholdPrompt();
			int result = JOptionPane.showConfirmDialog(view, thresholdPrompt, "Search threshold", JOptionPane.OK_CANCEL_OPTION);
			if (result == JOptionPane.OK_OPTION) {
				try {
					int threshold = Integer.parseInt(thresholdPrompt.getText());
					BoardUtilities.validate(createBoard(), threshold);
					view.getMenu().getMenu().setSaveEnabled(true);
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(view, "Please only enter integers", "Invalid entry!", JOptionPane.ERROR_MESSAGE);
					thresholdPrompt.clearText();
				} catch (InvalidBoardException e2) {
					JOptionPane.showMessageDialog(view, e2.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
					thresholdPrompt.clearText();
				}
			}

			break;
		case MenuEvent.SAVE_LEVEL:
			LevelDialog saver = new LevelDialog();
			saver.saveLevel(createGameModel());
			break;
		case MenuEvent.BACK:
			bc.handleBack();
			break;
		}
		waiter.stopWaiting();
	}

	/**
	 * method to create new board
	 * @return board
	 */
	private Board createBoard() {
		Board board = new Board();
		board.setModel(view.getBoardView().getModel());
		return board;
	}

	/**
	 * method to create new GameModel
	 * @return GameModel
	 */
	private GameModel createGameModel() {
		Board board = createBoard();
		GameState gameState = new GameState();
		gameState.setNumToWin(BoardUtilities.getRabbitsToWin(board));
		return new GameModel(board, gameState);
	}
}
