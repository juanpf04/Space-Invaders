package tp1.logic;

import java.util.ArrayList;
import java.util.List;

import tp1.logic.gameobjects.Bomb;
import tp1.logic.gameobjects.GameObject;
import tp1.logic.gameobjects.ShockWave;

public class GameObjectContainer {

	private List<GameObject> objects;

	public GameObjectContainer() {
		objects = new ArrayList<>();
	}

	public void add(GameObject object) {
		objects.add(object);
	}

	public void remove(GameObject object) {
		objects.remove(object);
	}

	public void automaticMoves() {
		for (int i=0;i<objects.size();i++) {
			GameObject object = objects.get(i);
			object.automaticMove();
		}
		this.deleteDeads();
	}

	private void deleteDeads() {
		int i = 0;
		while ( i < objects.size()) {
			
			GameObject object = objects.get(i);
			
			if (!object.isAlive()) 
				this.remove(object);
			else 
				i++;
		}
	}
	
	public void computerActions() {
		for (int i=0;i<objects.size();i++) {
			GameObject object = objects.get(i);
			object.computerAction();
		}
	}

	public String toString(Position position) {
		int i = 0;
		String box = "";
		GameObject object = objects.get(i);
		while(i < objects.size() && !object.isOnPosition(position)) {
			object = objects.get(i);
			i++;
		}
		
		if(i < objects.size())
			box = object.toString();
			
		return box;
	}

	public void alienReceiveAttack(ShockWave shockWave) {
		int i = 0;

		while ( i < objects.size() && shockWave.isAlive()) {
			
			GameObject object = objects.get(i);
			shockWave.performAttack(object);
			
			if (!object.isAlive()) 
				this.remove(object);
			else 
				i++;
		}
	}

	public boolean shootShockWave() {
		boolean canShoot = this.shockWave.isAlive();
		
		if(canShoot)
			this.shockWave.performAttack(this.regularAlienList, this.destroyerAlienList);
		
		return canShoot;
	}
	
}