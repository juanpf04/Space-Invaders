package tp1.logic.gameobjects;

import java.util.Arrays;
import java.util.List;

import tp1.logic.AlienManager;
import tp1.logic.GameWorld;
import tp1.logic.Position;
import tp1.view.Messages;

public class ShipFactory {

	private static final List<AlienShip> AVAILABLE_ALIEN_SHIPS = Arrays.asList(
			new RegularAlien(),
			new DestroyerAlien(),
			new ExplosiveAlien()
	);
	
	private static final List<Ship> AVAILABLE_SHIPS = Arrays.asList(
			new RegularAlien(),
			new DestroyerAlien(),
			new ExplosiveAlien(),
			new UCMShip(),
			new Ufo()
	);
	
	public static AlienShip spawnAlienShip(String input, GameWorld game, Position pos, AlienManager am) {
		for (AlienShip a: AVAILABLE_ALIEN_SHIPS) 
			if(input.equalsIgnoreCase(a.getSymbol()))
				return a.copy(game, pos, am);
		
		return null;
	}
	
	public static String getInfo() {
		StringBuilder info = new StringBuilder();
		
		for (Ship s: AVAILABLE_SHIPS) 
			info.append(s.getInfo()).append(Messages.LINE_SEPARATOR);
		
		return info.toString();
	}
}
