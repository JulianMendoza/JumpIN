package jumpin.model.board;

import java.util.ArrayList;
import java.util.List;

import jumpin.model.util.RabbitHoleListener;
/**
 * 
 * @author Julian Mendoza
 *
 */
public class ListenerModel {
	private List<RabbitHoleListener> listeners;

	public ListenerModel() {
		listeners = new ArrayList<RabbitHoleListener>();
	}

	public List<RabbitHoleListener> getListeners() {
		return listeners;
	}

	public void add(RabbitHoleListener l) {
		listeners.add(l);
	}
}
