package jumpin.model.board;

import jumpin.model.piece.pieces.Rabbit;

public class RabbitHole extends Tile {

	public RabbitHole() {
		super();
	}

	private Rabbit rabbit;

	public boolean isFilled() {
		return rabbit != null;
	}

	public Rabbit getRabbit() {
		return rabbit;
	}

	public void setRabbit(Rabbit rabbit) {
		this.rabbit = rabbit;
	}

	public String toString() {
		return super.toString().replace('[', '{').replace(']', '}');

	}

}
