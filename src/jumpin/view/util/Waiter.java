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
	/**
	 * Invoke waiter
	 */
	public void startWaiting() {
		isWaiting = true;
		execute();
	}
	/**
	 * Stop waiter
	 */
	public void stopWaiting() {
		isWaiting = false;
	}
	/**
	 * doInBackground for threads only occurs once.
	 */
	@Override
	protected Void doInBackground() throws Exception {
		waitFrame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		while (isWaiting) {
			Thread.sleep(500);
		}
		return null;
	}
	/**
	 * Method applies after thread is done executing.
	 */
	@Override
	protected void done() {
		waitFrame.setCursor(Cursor.getDefaultCursor());
	}

}
