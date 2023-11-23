package tp1.logic;

/**
 * 
 * Represents the allowed movements in the game
 *
 */
public enum Move {
	LEFT(-1,0), LLEFT(-2,0), RIGHT(1,0), RRIGHT(2,0), DOWN(0,1), UP(0,-1), NONE(0,0);
	
	private int x;
	private int y;
	
	private Move(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Returns x corresponding the Move
	 * @return x
	 */
	public int getX() {
		
		return x;
	}
	
	/**
	 * Returns y corresponding the Move
	 * @return y
	 */
	public int getY() {
		
		return y;
	}

	/**
	 * Returns the inverted Movement of this corresponding the Move
	 * @return the inverted Movement of this
	 */
	public Move flip() {
		Move move = this;
		
		if(this == RIGHT) 
			move = LEFT;
		
		else if(this == LEFT) 
			move = RIGHT;
		
		return move;
	}
	
	public static boolean validMove(String dir) {
		
		for(Move m: Move.values()) {
			
			if(dir.equalsIgnoreCase(m.name())) return true;
		}
		
		return false;
	}
}
