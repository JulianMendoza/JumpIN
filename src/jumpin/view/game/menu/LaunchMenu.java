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
	private JButton play, build, load;
	private LaunchController controller;

	public LaunchMenu(LaunchController launchController) {
		super();
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		play = new JButton("Play Game");
		load = new JButton("Load Level");
		build = new JButton("Build Level");
		addButtonListeners();
		add(Box.createVerticalStrut(50));
		add(play);
		add(Box.createVerticalStrut(50));
		add(load);
		add(Box.createVerticalStrut(50));
		add(build);
		controller=launchController;
	}

	private void addButtonListeners() {
		ActionListener l = new ButtonListener();
		play.addActionListener(l);
		load.addActionListener(l);
		build.addActionListener(l);
	}
	
	class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource().equals(play)) {
				controller.handlePlay();
			} else if (e.getSource().equals(load)) {
				controller.handleLoad();
			} else if (e.getSource().equals(build)) {
				controller.handleBuild();
			}

		}

	}
}