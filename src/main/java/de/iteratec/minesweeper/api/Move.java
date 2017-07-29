
package de.iteratec.minesweeper.api;

/**
 * TODO gene: missing JavaDoc.
 *
 * @author <a href="mailto:Gerd.Neugebauer@iteratec.de">Gerd Neugebauer</a>
 */
public class Move {

	/**
	 * The field <tt>x</tt> contains the x coordinate.
	 */
	private final int x;
	/**
	 * The field <tt>y</tt> contains the y coordinate.
	 */
	private final int y;
	
	/**
	 * Creates a new object.
	 *
	 * @param x the x coordinate
	 * @param y the y coordinate
	 */
	public Move(int x, int y) {

		this.x = x;
		this.y = y;
	}
	
	/**
	 * Getter for x.
	 *
	 * @return the x
	 */
	public int getX() {
		return x;
	}
	/**
	 * Getter for y.
	 *
	 * @return the y
	 */
	public int getY() {
		return y;
	}
	
	
}
