package jumpin.view.builder.menu;

import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import jumpin.view.builder.menu.menus.BuilderControlMenu;
import jumpin.view.builder.menu.menus.PieceMenu;
import jumpin.view.builder.menu.menus.TrashCan;
import jumpin.view.constants.ComponentSize;
import jumpin.view.constants.ViewConstants;
import jumpin.view.game.menu.MenuFrame;
import jumpin.view.listener.MenuListener;

/**
 * 
 * @author Giuseppe
 *
 */
public class BuilderMenu extends JPanel implements MenuFrame {

	private JScrollPane scrollPane;
	private BuilderControlMenu controlMenu;

	/**
	 * 
	 */
	private static final long serialVersionUID = -2068785327419860500L;

	public BuilderMenu(PieceMenu pieceMenu) {
		super();
		constructScrollPane(pieceMenu);
		populate();
	}

	private void constructScrollPane(PieceMenu pieceMenu) {
		scrollPane = new JScrollPane(pieceMenu, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setPreferredSize(new Dimension(300, ComponentSize.BUILDER_MENU_HEIGHT));
	}

	@Override
	public void populate() {
		setBounds(0, 0, ComponentSize.BUILDER_MENU_WIDTH, ComponentSize.BUILDER_MENU_HEIGHT + ComponentSize.FRAME_BAR_HEIGHT);
		setMaximumSize(getSize());
		setBackground(ViewConstants.BOARD_COLOR);
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		controlMenu = new BuilderControlMenu("Builder Control");
		add(controlMenu);
		add(new TrashCan());
		add(scrollPane);

	}

	@Override
	public void addMenuListener(MenuListener l) {
		controlMenu.addMenuListener(l);
		;
	}

}
