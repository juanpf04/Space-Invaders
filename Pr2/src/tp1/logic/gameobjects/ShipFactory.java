package tp1.logic.gameobjects;

import java.util.Arrays;
import java.util.List;

import tp1.logic.AlienManager;
import tp1.logic.GameWorld;
import tp1.logic.Position;

public class ShipFactory {

	private static final List<AlienShip> AVAILABLE_ALIEN_SHIPS = Arrays.asList(
			new RegularAlien(),
			new DestroyerAlien(),
			new ExplosiveAlien()
		);
	
	public static AlienShip spawnAlienShip(String input, GameWorld game, Position pos, AlienManager am) {
		for (AlienShip a: AVAILABLE_ALIEN_SHIPS) {
			if(input.equals(a.toString()))
				return a.copy(game, pos, am);
		}
		return null;
	}
}
