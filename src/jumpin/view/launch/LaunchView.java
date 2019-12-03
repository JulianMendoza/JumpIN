package jumpin.view.launch;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import jumpin.view.AbstractFrame;
import jumpin.view.constants.ImageConstants;
import jumpin.view.factory.ImageFactory;
import jumpin.view.launch.menu.LaunchMenu;

/**
 * 
 * @author: John
 */
public class LaunchView extends JFrame implements AbstractFrame {
	private static final long serialVersionUID = 1L;
	private LaunchMenu launchMenu;

	/**
	 * constructor method
	 */
	public LaunchView() {
		super("Welcome To JumpIN");
		launchMenu = new LaunchMenu();
		populate();
	}

	@Override
	public void populate() {
		setResizable(false);
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
	}

	public LaunchMenu getMenu() {
		return launchMenu;
	}
}
