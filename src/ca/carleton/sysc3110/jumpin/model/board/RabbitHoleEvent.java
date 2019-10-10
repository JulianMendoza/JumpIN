package ca.carleton.sysc3110.jumpin.model.board;

public class RabbitHoleEvent {
	private boolean onRabbitHole;
	public RabbitHoleEvent(boolean onRabbitHole) {
		this.onRabbitHole=onRabbitHole;
	}
	public boolean getEvent() {
		return this.onRabbitHole;
	}
}
