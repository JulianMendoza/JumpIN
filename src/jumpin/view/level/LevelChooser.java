package jumpin.view.level;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import jumpin.view.AbstractFrame;

/**
 * 
 * @author Giuseppe, Julian
 *
 */
public class LevelChooser extends JFileChooser implements AbstractFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1086416369922928104L;

	public LevelChooser() {
		super();
		populate();
	}

	@Override
	public void populate() {
		setCurrentDirectory(new java.io.File("."));
		setDialogTitle("Choose which level to load");
		setFileFilter(new FileNameExtensionFilter("xml files (*.xml)", "xml"));
	}

}
