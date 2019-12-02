package jumpin.view.builder.menu.menus;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import jumpin.view.builder.transfer.handler.DropHandler;
import jumpin.view.constants.ImageConstants;

public class TrashCan extends JLabel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6579266040927455111L;

	public TrashCan(DropHandler handler) {
		super(new ImageIcon(ImageConstants.TRASH_CAN));
		setTransferHandler(handler);
		setBounds(0, 0, 150, 150);
		setMaximumSize(getSize());
	}
}
