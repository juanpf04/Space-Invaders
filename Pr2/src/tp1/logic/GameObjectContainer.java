package tp1.logic;

import java.util.ArrayList;
import java.util.List;

import tp1.logic.gameobjects.GameObject;

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
			//TODO fill with your code
		}
		//TODO fill with your code
	}

	public void computerActions() {
		// TODO fill with your code
	}

	public String toString(Position position) {
		int i = 0;
		String s = "";
		
		while(i < objects.size() && !this.objects.get(i).isOnPosition(position)) {
			
			i++;
		}
		
		if(i < objects.size())
			s = this.objects.get(i).getSymbol();
			
		return null;
	}

	//TODO fill with your code
	
}