package tp1.logic.lists;

import tp1.logic.Position;
import tp1.logic.Level;
import tp1.logic.gameobjects.RegularAlien;
import tp1.logic.gameobjects.UCMLaser;

/**
 * Container of regular aliens, implemented as an array with a counter
 * It is in charge of forwarding petitions from the game to each regular alien
 * 
 */
public class RegularAlienList {

	private RegularAlien[] objects;
	private int size;
	private int maxAliens;
	
	public RegularAlienList (Level level) {
		
		this.size = 0;
		this.maxAliens = level.getNumRegularAliens();
		objects = new RegularAlien[this.maxAliens];
	}
	
	public int length() {
		
		return this.size;
	}


	public int alienInPos(Position pos) {
		int i = 0;
		
		while(i < this.size && !this.objects[i].isOnPosition(pos)) {
			
			i++;
		}
		
		if(i >= this.size)
			i = -1;
		
		return i;
	}

	/**
	 * returns the String that represents the symbol of the regular alien in pos.
	 * @param pos the position of the regular alien
	 * @return the String that represents the symbol of the regular alien in pos.
	 */
	public String showAlienInPos(Position pos) {
		
		return this.objects[this.alienInPos(pos)].getSymbol();
	}

	/**
	 *  Implements the automatic movement of all the regular aliens	
	 */
	public void automaticMove() {
		int i = 0;

		while ( i < this.length()) {
			
			this.objects[i].automaticMove();
			
			if (!objects[i].isAlive()) {
				this.remove(i);
			}
			else
				i++;
		}
	}

	
	public void addObject(RegularAlien regularAlien) {
		
		if(!this.isFull()) {
			this.objects[this.length()] = regularAlien;
			this.size++;
		}
	}
	
	
	public boolean isFull() {
		
		return this.length() >= this.maxAliens;
	}
	
	
	public boolean isEmpty() {
		
		return this.length() == 0;
	}
	
	
	public void checkLaserAttack(UCMLaser ucmLaser) { // pasamos por parametro el laser, asi no tenemos que hacer un for
		int i = 0;
		while( i < this.size && !ucmLaser.performAttack(objects[i])) {
			
			i++;
		}
		if (i < this.size && !objects[i].isAlive()) 
			this.remove(i);
		
	}

	/**
	 * method that removes the index i from the list
	 * @param i the index wanted to delete
	 */
	private void remove(int i) {
		
		if(!this.isEmpty()) {
			this.size--;
			objects[i] = objects[this.size];
		}
	}

	
	public void allRecieveAttack() {
		int i = 0;

		while ( i < this.length()) {
			objects[i].decreaseLive();
			
			if (!objects[i].isAlive()) {
				this.remove(i);
			}
			else
				i++;
		}
	}
	
}
