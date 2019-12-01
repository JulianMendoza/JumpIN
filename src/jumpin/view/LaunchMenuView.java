package jumpin.view;

import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import jumpin.controller.launch.LaunchController;
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
		JLabel background = new JLabel(new ImageIcon("images\\splashscreen.jpg"));
		add(background);
		background.setLayout(new FlowLayout());
		// setLayout(new FlowLayout());
		setSize(600, 400);
		background.add(launchMenu);
		setLocationRelativeTo(null);
		// getContentPane().add(startMenu);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
}
