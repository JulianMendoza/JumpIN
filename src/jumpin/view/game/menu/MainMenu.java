package jumpin.view.game.menu;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import jumpin.model.GameModel;
import jumpin.view.constants.ComponentSize;
import jumpin.view.constants.ViewConstants;
import jumpin.view.game.menu.listener.MenuListener;
import jumpin.view.game.menu.menus.GameStateMenu;
import jumpin.view.game.menu.menus.HistoryMenu;
import jumpin.view.game.menu.menus.LevelMenu;
import jumpin.view.game.menu.menus.solver.SolverMenu;

/**
 * GUI Components for the main menu (undo, redo, state, etc.)
 * 
 * @author Julian, Giuseppe, John, Cameron Davis
 */
public class MainMenu extends JPanel implements MenuFrame {

	private SolverMenu solverMenu;
	private HistoryMenu historyMenu;
	private GameStateMenu gameStateMenu;
	private LevelMenu saveLoadMenu;
	private static final long serialVersionUID = -436968148338186761L;

	public MainMenu() {
		populate();
	}

	/**
	 * Fills top MenuFrame with solver, history, game state components
	 */
	@Override
	public void populate() {
		solverMenu = new SolverMenu();
		historyMenu = new HistoryMenu();
		gameStateMenu = new GameStateMenu();
		saveLoadMenu = new LevelMenu();

		setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.WHITE));
		setBounds(0, 0, ComponentSize.GAME_MENU_WIDTH, ComponentSize.GAME_MENU_HEIGHT);
		setMaximumSize(getSize());
		setBackground(ViewConstants.BOARD_COLOR);

		add(gameStateMenu);
		add(historyMenu);
		add(solverMenu);
		add(saveLoadMenu);
	}

	@Override
	public void addMenuListener(MenuListener l) {
		solverMenu.addMenuListener(l);
		historyMenu.addMenuListener(l);
		saveLoadMenu.addMenuListener(l);
	}

	public SolverMenu getSolverMenu() {
		return solverMenu;
	}

	public HistoryMenu getHistoryMenu() {
		return historyMenu;
	}

	public GameStateMenu getGameStateMenu() {
		return gameStateMenu;
	}

	/**
	 * Update game state menu with current state
	 * 
	 * @param model the Game Model
	 */
	public void initialize(GameModel model) {
		gameStateMenu.update(model.getGameState());
	}
	public LevelMenu getLevelMenu() {
		return saveLoadMenu;
	}
}
