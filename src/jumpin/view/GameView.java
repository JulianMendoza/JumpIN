package jumpin.view;

import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import java.awt.GridLayout;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import jumpin.model.GameModel;
import jumpin.view.board.BoardView;

public class GameView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5089660197653181626L;
	private GameModel model;
	
	public GameView(GameModel model) {
		this.model = model;
		
		setFont(new Font("Angsana New", Font.PLAIN, 12));
		setResizable(false);
		setTitle("JumpIN");
		setSize(750,750);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu gameMenu = new JMenu("Game");
		menuBar.add(gameMenu);
		
		JMenu helpMenu = new JMenu("Help");
		menuBar.add(helpMenu);
		
		JMenuItem howtoMenuItem = new JMenuItem("How to play");
		helpMenu.add(howtoMenuItem);
		
		JMenu exitMenu = new JMenu("Exit");
		menuBar.add(exitMenu);
		
		JMenuItem exitMenuItem = new JMenuItem("Exit");
		exitMenu.add(exitMenuItem);
		getContentPane().setLayout(null);
		
		BoardView board = new BoardView(model.getBoard().getModel());
		
	}
	
	public static void main(String args[]) {
		GameModel model = new GameModel();
		GameView view = new GameView(model);
		view.show();
	}
}
