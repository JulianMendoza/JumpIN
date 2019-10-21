package jumpin.model.util;

import jumpin.util.Pair;

public class Index extends Pair<Integer, Integer> {

	public Index(Position position) {
		super(position.getX() - 1, position.getY() - 1);
	}

	public Index(int x, int y) {
		super(x, y);
	}

	public int getX() {
		return getFirst();
	}

	public int getY() {
		return getSecond();
	}

}
