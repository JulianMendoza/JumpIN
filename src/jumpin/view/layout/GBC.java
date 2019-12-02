package jumpin.view.layout;

import java.awt.GridBagConstraints;
import java.awt.Insets;

/**
 * 
 * @author Giuseppe
 *
 */
public class GBC extends GridBagConstraints {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8294595887845159178L;

	public GBC(int x, int y) {
		super();
		gridwidth = 1;
		gridheight = 1;
		gridx = x;
		gridy = y;
		weightx = x;
		weighty = 1.0;
		insets = new Insets(5, 5, 5, 5);
		anchor = (x == 0) ? GridBagConstraints.LINE_START : GridBagConstraints.LINE_END;
		fill = GridBagConstraints.HORIZONTAL;
	}

}
