package tp1.logic;

import java.util.ArrayList;
import java.util.List;

import tp1.logic.gameobjects.AlienShip;
import tp1.logic.gameobjects.GameObject;
import tp1.logic.gameobjects.ShockWave;
import tp1.logic.gameobjects.Weapon;

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
			this.checkAttack(object);
		}
		this.deleteDeads();
	}

	private void deleteDeads() {
		for (int i=objects.size()-1;i>=0;i--) {
			GameObject object = objects.get(i);
			if (!object.isAlive()) 
				this.remove(object);
		}
//		int i = 0;
//		while ( i < objects.size()) {
//			
//			GameObject object = objects.get(i);
//			
//			if (!object.isAlive()) 
//				this.remove(object);
//			else 
//				i++;
//		}
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
		
		if(object.isOnPosition(position))
			box = object.toString();
			
		return box;
	}

	public void checkAttack(GameObject other) {
		int i = 0;

		while ( i < objects.size() && other.isAlive()) {
			GameObject object = objects.get(i);
			other.performAttack(object);
			i++;
		}
	}

	public void performAttack(AlienShip alienShip) {
		int i = 0;

		while ( i < objects.size() && alienShip.isAlive()) {
			GameObject object = objects.get(i);
			object.performAttack(alienShip);
			i++;
		}
	}

	public int size() {
		return objects.size();
	}
	
}