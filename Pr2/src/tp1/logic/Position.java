package tp1.logic;

import java.lang.Math;

import tp1.logic.Move;
import tp1.view.Messages;

import java.util.Objects;
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
	
	@Override
	public int hashCode() {
		return Objects.hash(col, row);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Position other = (Position) obj;
		return this.getCol() == other.getCol() && this.getRow() == other.getRow();
	}
	
	public Position newPos(Move move) {
		return new Position(this.getCol() + move.getX(), this.getRow() + move.getY());
	}

	public boolean posValida() {
		return this.getCol() < Game.DIM_X && this.getRow() < Game.DIM_Y
			&& this.getCol() >= 0 && this.getRow() >= 0;
	}
	
	
	private int getCol() {
		return this.col;
	}

	private int getRow() {
		return this.row;
	}
	
	public boolean validPos(Move move) {
		return this.newPos(move).posValida();
	}
	
	public boolean isInBorder() {
		return this.getCol() == 0 
			|| this.getCol() == Game.DIM_X - 1;
	}

	public boolean inFinalRow() {
		return this.getRow() == Game.DIM_Y - 1;
	}
	
	public boolean adjacent(Position pos) {
		return Math.abs(pos.getCol()-this.getCol()) == 1 && Math.abs(pos.getRow()-this.getRow()) < 2 
			|| Math.abs(pos.getCol()-this.getCol()) < 2 && Math.abs(pos.getRow()-this.getRow()) == 1; 
		
	}

	@Override
	public String toString() {
		return Messages.POSITION.formatted(this.getCol(), this.getRow());
	}

	
	
}
