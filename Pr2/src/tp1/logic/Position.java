package tp1.logic;

import tp1.logic.Move;
import tp1.logic.Game;
/**
 * 
 * Immutable class to encapsulate and manipulate positions in the game board
 * 
 */
public class Position {

	private int col;
	private int row;

	public Position(int x, int y) {
		this.col = x;
		this.row = y;
	}

	public Position(Position pos) {
		this.col = pos.getCol();
		this.row = pos.getRow();
	}
	
	/**
	 * Checks if the position is the same as the position given
	 * @param pos the position received
	 * @return <code>true</code> if the position is the same as the position given
	 */
	public boolean equals(Position pos) {
		
		return this.col == pos.getCol() && this.row == pos.getRow();
	}
	
	/**
	 * Methods that returns the new position after the move
	 * @param move the move that wants to make
	 * @return the new position after the move
	 */
	public Position newPos(Move move) {
		return new Position(this.col + move.getX(), this.row + move.getY());
	}

	/**
	 * Checks if the position is a valid position
	 * @return <code>true</code> if the position is a valid position
	 */
	public boolean posValida() {
		
		return this.col <= Game.DIM_Y && this.row <= Game.DIM_X && this.col >= 0 && this.row >= 0;
	}
	
	/**
	 * Returns column corresponding the Position
	 * @return column
	 */
	private int getCol() {
		
		return this.col;
	}

	/**
	 * Returns row corresponding the Position
	 * @return row
	 */
	private int getRow() {
		
		return this.row;
	}
	
	/**
	 * Checks if the position after the move is a valid position
	 * @param move the move that wants to make
	 * @return <code>true</code> if the position after the move is a valid position
	 */
	public boolean validPos(Move move) {
		
		return this.newPos(move).posValida();
	}
	
	/**
	 * Checks if the position is in border of the board
	 * @return <code>true</code> if the position is in border of the board
	 */
	public boolean isInBorder() {
		
		return this.col == 0 || this.col == Game.DIM_Y;
	}

	/**
	 * Checks if the position is in final row
	 * @return <code>true</code> if the position is in final row
	 */
	public boolean inFinalRow() {
		
		return this.row == 7;
	}

}
