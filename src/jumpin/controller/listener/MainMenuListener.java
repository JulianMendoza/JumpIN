package jumpin.controller.listener;

import javax.swing.JOptionPane;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import jumpin.model.GameModel;
import jumpin.model.exception.IllegalMoveException;
import jumpin.view.GameView;
import jumpin.view.menu.MainMenu;
import jumpin.view.menu.listener.MenuEvent;
import jumpin.view.menu.listener.MenuListener;
import jumpin.view.menu.menus.solver.ThresholdPrompt;
import jumpin.view.util.Waiter;

/**
 * 
 * @author Giuseppe
 *
 */
public class MainMenuListener implements MenuListener {

	private GameView view;
	private GameModel model;

	public MainMenuListener(GameModel model, GameView view) {
		this.view = view;
		this.model = model;
	}

	@Override
	public void menuActionPerformed(int menuEvent) {
		Waiter waiter = new Waiter(view);
		waiter.startWaiting();
		MainMenu menu = view.getMainMenu();

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
		case MenuEvent.XML_SAVE:
			try {
				model.getGenerator().saveLevelXML();
			} catch (ParserConfigurationException e) {
				
			} catch (TransformerException e) {
				
			}
			break;
		case MenuEvent.XML_LOAD:
			System.out.println("TODO -> MainMenuListener");
			break;
		case MenuEvent.SERIALIZE:
			System.out.println("TODO -> MainMenuListener");
			break;
		case MenuEvent.DESERIALIZE:
			System.out.println("TODO -> MainMenuListener");
			break;
		}

		waiter.stopWaiting();

	}

}
