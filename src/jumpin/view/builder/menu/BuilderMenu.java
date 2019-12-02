package jumpin.view.builder.menu;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import jumpin.view.builder.menu.menus.BuilderControlMenu;
import jumpin.view.builder.menu.menus.PieceMenu;
import jumpin.view.builder.menu.menus.TrashCan;
import jumpin.view.builder.transfer.handler.DropHandler;
import jumpin.view.constants.ComponentSize;
import jumpin.view.constants.ViewConstants;
import jumpin.view.factory.FontFactory;
import jumpin.view.listener.MenuFrame;
import jumpin.view.listener.MenuListener;

/**
 * 
 * @author Giuseppe
 *
 */
public class BuilderMenu extends JPanel implements MenuFrame {

	private JScrollPane scrollPane;
	private BuilderControlMenu controlMenu;
	private TrashCan trashCan;

	/**
	 * 
	 */
	private static final long serialVersionUID = -2068785327419860500L;

	public BuilderMenu(PieceMenu pieceMenu, DropHandler handler) {
		super();
		constructTrashCan(handler);
		constructScrollPane(pieceMenu);
		populate();
	}

	private void constructTrashCan(DropHandler handler) {
		trashCan = new TrashCan(handler);
	}

	private void constructScrollPane(PieceMenu pieceMenu) {
		scrollPane = new JScrollPane(pieceMenu, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setPreferredSize(new Dimension(100, ComponentSize.BUILDER_MENU_HEIGHT));
		scrollPane.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Piece Picker", TitledBorder.CENTER, TitledBorder.TOP, FontFactory.createMenuFont(Font.BOLD, 14), new Color(0, 0, 0)));
		scrollPane.setBackground(ViewConstants.BOARD_COLOR);
		scrollPane.setViewportBorder(null);
	}

	@Override
	public void populate() {
		setBounds(0, 0, ComponentSize.BUILDER_MENU_WIDTH, ComponentSize.BUILDER_MENU_HEIGHT + ComponentSize.FRAME_BAR_HEIGHT);
		setMaximumSize(getSize());
		setBackground(ViewConstants.BOARD_COLOR);
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.WHITE));

		controlMenu = new BuilderControlMenu("Builder Control");
		add(controlMenu);
		add(trashCan);
		add(scrollPane);

	}

	public BuilderControlMenu getMenu() {
		return controlMenu;
	}

	@Override
	public void addMenuListener(MenuListener l) {
		controlMenu.addMenuListener(l);
	}

}
