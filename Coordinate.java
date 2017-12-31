/**
 *	A row/column pair on a two-dimensional grid
 *
 *	@author		Kevin Zhou
 *	@since 		Novembver 15, 2017
 */
public class Coordinate {
	
	private int row, col;
	
	public Coordinate(int row, int col) {
		this.row = row;
		this.col = col;
	}
	
	/** Accessor methods for private fields
	 */
	public int getRow() { return row; }
	public int getCol() { return col; }
	 
	/**
	 * Creates a string of the Coordinate
	 * @return		a string of the Coordinate
	 */
	@Override
	public String toString() {
		return "(" + row + "," + col + ")";
	}
	  
	/**
	 * Test whether two coordinates are equal by first checking if
	 * the coordinate is null, chcecking if the passed Object is a
	 * coordinate, and finally checking if the row and col values are
	 * the same by casting the passed coordinate as a Coordinate object.
	 * @param other		another Coordinate object
	 * @return			true if this Coordinate equals other;
	 * 					false otherwiise
	 */
	@Override
	public boolean equals(Object other) {
		return (other != null &&
			other instanceof Coordinate &&
			row == ((Coordinate)other).row && 
			col == ((Coordinate)other).col);
	 }
	  
}