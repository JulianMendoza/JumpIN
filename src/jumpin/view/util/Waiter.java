package jumpin.view.util;

import java.awt.Cursor;
import java.awt.Window;

import javax.swing.SwingWorker;

/**
 * A couple GUI-interaction tasks that are performed in a background thread
 * 
 * @author Giuseppe
 */
public class Waiter extends SwingWorker<Void, Void> {

	private Window waitFrame;
	private boolean isWaiting;

	public Waiter(Window waitFrame) {
		this.waitFrame = waitFrame;
	}

	public void startWaiting() {
		isWaiting = true;
		execute();
	}

	public void stopWaiting() {
		isWaiting = false;
	}

	@Override
	protected Void doInBackground() throws Exception {
		waitFrame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		while (isWaiting) {
			Thread.sleep(500);
		}
		return null;
	}

	@Override
	protected void done() {
		waitFrame.setCursor(Cursor.getDefaultCursor());
	}

}
