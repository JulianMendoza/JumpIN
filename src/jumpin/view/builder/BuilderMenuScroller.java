package jumpin.view.builder;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import jumpin.view.AbstractFrame;
import jumpin.view.builder.menu.BuilderMenu;
import jumpin.view.constants.ComponentSize;

public class BuilderMenuScroller extends JPanel implements AbstractFrame {

	private JScrollPane scrollPane;

	/**
	 * 
	 */
	private static final long serialVersionUID = -2068785327419860500L;

	public BuilderMenuScroller(BuilderMenu menu) {
		super(new BorderLayout());
		scrollPane = new JScrollPane(menu,JScrollPane.VERTICAL_SCROLLBAR_NEVER,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		populate();
	}

	@Override
	public void populate() {
		setBounds(0, 0, ComponentSize.BUILDER_MENU_WIDTH, ComponentSize.BUILDER_MENU_HEIGHT + ComponentSize.FRAME_BAR_HEIGHT);
		setMaximumSize(getSize());
		add(scrollPane);
	}

}
