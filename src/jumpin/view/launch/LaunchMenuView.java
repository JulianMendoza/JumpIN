package jumpin.view.launch;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import jumpin.controller.launch.LaunchController;
import jumpin.view.constants.ImageConstants;
import jumpin.view.factory.ImageFactory;
import jumpin.view.game.menu.LaunchMenu;

/**
 * 
 * @author: John
 */
public class LaunchMenuView extends JFrame {
	private static final long serialVersionUID = 1L;
	private LaunchMenu launchMenu;

	public LaunchMenuView(LaunchController lc) {
		super("Welcome To JumpIN");
		launchMenu = new LaunchMenu(lc);
		populate();
	}

	public void populate() {
		JLabel background = new JLabel(new ImageIcon(ImageConstants.START_MENU));
		add(background);
		background.setLayout(new FlowLayout());
		setSize(600, 455);
		launchMenu.setBackground(new Color(0, 0, 0, 0));
		launchMenu.setOpaque(false);
		background.add(launchMenu);
		setLocationRelativeTo(null);
		setIconImage(ImageFactory.generateRabbit());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
}
