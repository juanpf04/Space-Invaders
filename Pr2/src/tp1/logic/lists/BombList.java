package tp1.logic.lists;

import tp1.logic.Level;
import tp1.logic.Position;
import tp1.logic.gameobjects.Bomb;
import tp1.logic.gameobjects.UCMLaser;
import tp1.logic.gameobjects.UCMShip;

public class BombList {
	
	private Bomb[] objects;
	private int size;
	private int maxBombs;
	
	public BombList (Level level) {
		
		this.size = 0;
		this.maxBombs = level.getNumDestroyerAliens();
		objects = new Bomb[this.maxBombs];
	}
		

	public int length() {
		
		return this.size;
	}

	
	public int bombInPos(Position pos) {
		int i = 0;
		
		while(i < this.size && !this.objects[i].inPos(pos)) {
			
			i++;
		}
		
		if(i >= this.size)
			i = -1;
		
		return i;
	}

	/**
	 * returns the String that represents the symbol of the bomb in pos.
	 * @param pos the position of the bomb
	 * @return the String that represents the symbol of the bomb in pos.
	 */
	public String showbombInPos(Position pos) {
		
		return this.objects[this.bombInPos(pos)].symbol();
	}

	/**
	 *  Implements the automatic movement of all the bombs	
	 */
	public void automaticMove() {
		int i = 0;
		
		while(i < this.length()) {
			
			this.objects[i].automaticMove();
			
			if(this.objects[i].isOut())
				this.remove(i);
			else
				i++;
		}
	}

	
	public void addObject(Bomb bomb) {
		
		if(!this.isFull()) {
			this.objects[this.length()] = bomb;
			this.size++;
		}
	}
	
	
	public boolean isFull() {
		
		return this.length() >= this.maxBombs;
	}
	
	
	public boolean isEmpty() {
		
		return this.length() == 0;
	}
	
	
	public void performAttack(UCMLaser ucmLaser) { // pasamos por parametro el laser, asi no tenemos que hacer un for
		int i = 0;
		while( i < this.size && !objects[i].performAttack(ucmLaser)) {
			
			i++;
		}
		if (i < this.size) 
			this.remove(i);
		
	}
	
	
	public void performAttack(UCMShip ucmShip) {
		int i = 0;
		while( i < this.size && !objects[i].performAttack(ucmShip)) {
			
			i++;
		}
		if (i < this.size) 
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

}
