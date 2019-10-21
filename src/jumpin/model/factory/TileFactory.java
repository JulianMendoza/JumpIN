package jumpin.model.factory;

import jumpin.model.board.tile.RabbitHole;
import jumpin.model.board.tile.Tile;
import jumpin.model.util.Position;

/**
 * <b>Factory for creating tile components</b>
 * <p>
 * if there is extra work to do to create a component it goes here
 * <p>
 * (there is none currently)
 * 
 * @author Giuseppe
 *
 */
public class TileFactory {

	/**
	 * method to create a new Tile
	 * 
	 * @return New tile
	 */
	public static Tile createTile(Position position) {
		return new Tile(position);
	}

	/**
	 * method to create a new RabbitHol
	 * 
	 * @return New RabbitHole
	 */
	public static RabbitHole createRabbitHole(Position position) {
		return new RabbitHole(position);
	}

}
