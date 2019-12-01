package jumpin.view.game.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import jumpin.controller.launch.LaunchController;
/**
 *  @author: John, Julian
 */
public class LaunchMenu extends JPanel{
	private static final long serialVersionUID = 1555205823441698028L;
	private JButton playBtn, buildBtn, loadBtn;
	private LaunchController controller;

	public LaunchMenu(LaunchController launchController) {
		super();
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		playBtn = new JButton("Play Game");
		loadBtn = new JButton("Load Level");
		buildBtn = new JButton("Build Level");
		addButtonListeners();
		add(Box.createVerticalStrut(50));
		add(playBtn);
		add(Box.createVerticalStrut(50));
		add(loadBtn);
		add(Box.createVerticalStrut(50));
		add(buildBtn);
		controller=launchController;
	}

	private void addButtonListeners() {
		ActionListener l = new ButtonListener();
		playBtn.addActionListener(l);
		loadBtn.addActionListener(l);
		buildBtn.addActionListener(l);
	}
	
	class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource().equals(playBtn)) {
				controller.handlePlay();
			} else if (e.getSource().equals(loadBtn)) {
				controller.handleLoad();
			} else if (e.getSource().equals(buildBtn)) {
				controller.handleBuild();
			}

		}

	}
}