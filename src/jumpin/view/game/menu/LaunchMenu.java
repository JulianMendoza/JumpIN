package jumpin.view.game.menu;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import jumpin.controller.launch.LaunchController;
import jumpin.view.factory.ComponentFactory;
/**
 *  @author: John, Julian
 */
public class LaunchMenu extends JPanel{
	private static final long serialVersionUID = 1555205823441698028L;
	private JButton playButton, loadButton, buildButton;
	private final String PLAY_TEXT = "Play Game";
	private final String LOAD_TEXT = "Load Level";
	private final String BUILD_TEXT = "Build Level";

	
	private LaunchController controller;

	public LaunchMenu(LaunchController launchController) {
		super();
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
		controller=launchController;
	}

	private void addButtonListeners() {
		ActionListener l = new ButtonListener();
		playButton.addActionListener(l);
		loadButton.addActionListener(l);
		buildButton.addActionListener(l);
	}
	
	class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource().equals(playButton)) {
				controller.handlePlay();
			} else if (e.getSource().equals(loadButton)) {
				controller.handleLoad();
			} else if (e.getSource().equals(buildButton)) {
				controller.handleBuild();
			}

		}

	}
}