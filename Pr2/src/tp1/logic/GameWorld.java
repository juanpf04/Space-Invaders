package tp1.logic;

import java.util.Random;

import tp1.logic.gameobjects.AlienShip;
import tp1.logic.gameobjects.GameObject;

public interface GameWorld {

	public Level getLevel();
	public Random getRandom();
	public void addObject(GameObject object);
	public void receivePoints(int points);
	public void enableShockWave();
	public void enableLaser();
	public int getRemainingAliens();
	public void checkLaserAttack(AlienShip alienShip);
	
}
