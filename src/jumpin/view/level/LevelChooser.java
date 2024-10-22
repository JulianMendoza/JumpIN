package jumpin.view.level;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import jumpin.view.AbstractFrame;

/**
 * Class to choose a level
 * 
 * @author Giuseppe, Julian
 *
 */
public class LevelChooser extends JFileChooser implements AbstractFrame {


	private static final long serialVersionUID = -1086416369922928104L;

	public LevelChooser() {
		super();
		populate();
	}
	/**
	 * Styling defaults to current directory.
	 */
	@Override
	public void populate() {
		setCurrentDirectory(new java.io.File("."));
		setDialogTitle("Choose level file");
		setFileFilter(new FileNameExtensionFilter("xml files (*.xml)", "xml"));
	}

}
