package jumpin.view.menu;

import jumpin.view.AbstractFrame;
import jumpin.view.menu.listener.MenuListener;

/**
 * Functional interface for the main menu
 * 
 * @author Giuseppe
 */
public interface MenuFrame extends AbstractFrame {

	public void addMenuListener(MenuListener l);

}
