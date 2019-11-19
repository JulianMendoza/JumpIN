package jumpin.model.factory;

import jumpin.model.board.tile.RabbitHole;
import jumpin.model.board.tile.Tile;

/**
 * Factory for creating tile components
 * if there is extra work to do to create a component it goes here
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
	public static Tile createTile() {
		return new Tile();
	}

	/**
	 * method to create a new RabbitHol
	 * 
	 * @return New RabbitHole
	 */
	public static RabbitHole createRabbitHole() {
		return new RabbitHole();
	}

}
