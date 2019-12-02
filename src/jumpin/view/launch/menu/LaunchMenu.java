package jumpin.view.launch.menu;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import jumpin.view.factory.ComponentFactory;
import jumpin.view.listener.MenuEvent;
import jumpin.view.listener.MenuFrame;
import jumpin.view.listener.MenuListener;

/**
 * @author: John, Julian
 */
public class LaunchMenu extends JPanel implements MenuFrame {
	private static final long serialVersionUID = 1555205823441698028L;
	private JButton playButton, loadButton, buildButton;
	private final String PLAY_TEXT = "Play Game";
	private final String LOAD_TEXT = "Load Level";
	private final String BUILD_TEXT = "Build Level";
	private List<MenuListener> menuListeners;

	public LaunchMenu() {
		super();
		menuListeners = new ArrayList<MenuListener>();
		populate();
	}

	@Override
	public void populate() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		playButton = ComponentFactory.create3DMenuButton(PLAY_TEXT);
		loadButton = ComponentFactory.create3DMenuButton(LOAD_TEXT);
		buildButton = ComponentFactory.create3DMenuButton(BUILD_TEXT);

		playButton.setBackground(Color.GRAY);
		loadButton.setBackground(Color.GRAY);
		buildButton.setBackground(Color.GRAY);

		addButtonListeners();
		add(Box.createVerticalStrut(60));
		add(playButton);
		add(Box.createVerticalStrut(60));
		add(loadButton);
		add(Box.createVerticalStrut(60));
		add(buildButton);
	}

	private void addButtonListeners() {
		ActionListener l = new ButtonListener();
		playButton.addActionListener(l);
		loadButton.addActionListener(l);
		buildButton.addActionListener(l);
	}

	@Override
	public void addMenuListener(MenuListener l) {
		menuListeners.add(l);
	}

	class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource().equals(playButton)) {
				for (MenuListener l : menuListeners) {
					l.menuActionPerformed(MenuEvent.PLAY_GAME);
				}
			} else if (e.getSource().equals(loadButton)) {
				for (MenuListener l : menuListeners) {
					l.menuActionPerformed(MenuEvent.LOAD_LEVEL);
				}
			} else if (e.getSource().equals(buildButton)) {
				for (MenuListener l : menuListeners) {
					l.menuActionPerformed(MenuEvent.BUILD_LEVEL);
				}
			}

		}

	}

}