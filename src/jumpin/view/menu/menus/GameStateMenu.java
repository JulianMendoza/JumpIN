package jumpin.view.menu.menus;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import jumpin.model.GameState;
import jumpin.view.AbstractFrame;
import jumpin.view.constants.ComponentSize;
import jumpin.view.constants.ViewConstants;
import jumpin.view.factory.ComponentFactory;
import jumpin.view.util.GroupBox;

/**
 * 
 * @author Giuseppe
 *
 */
public class GameStateMenu extends GroupBox implements AbstractFrame {

	private JLabel state;

	/**
	 * 
	 */
	private static final long serialVersionUID = -4841219774868685113L;

	public GameStateMenu() {
		super("Game State");
		populate();
	}

	@Override
	public void populate() {
		setBackground(ViewConstants.BOARD_COLOR);

		state = ComponentFactory.createMenuLabel("");
		state.setHorizontalAlignment(SwingConstants.CENTER);

		setPreferredSize(ComponentSize.MAIN_MENU_PANEL);

		add(state);
	}

	public void update(GameState gameState) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				state.setText(getText(gameState));
			}
		});
	}

	private String getText(GameState gameState) {
		return "<html># of Rabbits left to win: " + gameState.getNumToWin() + "<p>Game State: " + gameState.getState().toString() + "</html>";
	}

}
