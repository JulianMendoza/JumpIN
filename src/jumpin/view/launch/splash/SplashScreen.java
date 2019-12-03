package jumpin.view.launch.splash;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JWindow;

import jumpin.view.AbstractFrame;
import jumpin.view.constants.ComponentSize;
import jumpin.view.constants.ImageConstants;
import jumpin.view.constants.ViewConstants;
import jumpin.view.util.Waiter;

/**
 * 
 * @author Giuseppe
 *
 */
public class SplashScreen extends JWindow implements AbstractFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2383405811428507600L;
	private JProgressBar loadBar;
	private static final int LOAD_TIME = 2000;

	/**
	 * constructor method
	 */
	public SplashScreen() {
		super();
		populate();
	}

	@Override
	public void populate() {
		setSize(ComponentSize.SPLASH_WIDTH, ComponentSize.SPLASH_HEIGHT);
		setLocationRelativeTo(null);
		configureLoadBar();

		getContentPane().add(loadBar, BorderLayout.SOUTH);

		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.NORTH);
		JLabel screen = new JLabel(new ImageIcon(ImageConstants.SPLASH_SCREEN));
		panel.add(screen);
		screen.setPreferredSize(new Dimension(ComponentSize.SPLASH_IMAGE_WIDTH, ComponentSize.SPLASH_IMAGE_HEIHGT));
		pack();
		setBackground(Color.WHITE);
	}

	public void splash() {
		setVisible(true);
		Waiter waiter = new Waiter(this);
		waiter.startWaiting();
		load();
		setVisible(false);
	}

	private void load() {
		while (loadBar.getMaximum() != loadBar.getValue()) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			loadBar.setValue(loadBar.getValue() + 10);
			loadBar.setString("Loading... " + computePercentage(loadBar.getValue()));
		}
	}

	private void configureLoadBar() {
		loadBar = new JProgressBar(0, LOAD_TIME);
		loadBar.setStringPainted(true);
		loadBar.setForeground(ViewConstants.SPLASH_COLOR);
	}

	private String computePercentage(int value) {
		double decimal = (double) value / (double) LOAD_TIME;
		return String.format("%.2f", (decimal * 100)) + "%";
	}

}
