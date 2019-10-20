package jumpin.view;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import java.awt.Window.Type;
import java.awt.Font;

public class GameView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5089660197653181626L;
	
	
	public GameView() {
		setFont(new Font("Angsana New", Font.PLAIN, 12));
		setResizable(false);
		setTitle("JumpIN");
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu gameMenu = new JMenu("Game");
		menuBar.add(gameMenu);
		
		JMenu helpMenu = new JMenu("Help");
		menuBar.add(helpMenu);
		
	}

}
