package homework6;
/* SimpleBoard.java */

/**
 * Simple class that implements an 8x8 game board with three possible values for
 * each cell: 0, 1 or 2.
 *
 * DO NOT CHANGE ANY PROTOTYPES IN THIS FILE.
 **/

public class SimpleBoard {
	private final static int DIMENSION = 8;
	private int[][] grid;
	private long shift;
	private int prime = 109345121;

	/**
	 * Invariants: (1) grid.length == DIMENSION. (2) for all 0 <= i < DIMENSION,
	 * grid[i].length == DIMENSION. (3) for all 0 <= i, j < DIMENSION,
	 * grid[i][j] >= 0 and grid[i][j] <= 2.
	 **/

	/**
	 * Construct a new board in which all cells are zero.
	 */

	public SimpleBoard() {
		grid = new int[DIMENSION][DIMENSION];
		java.util.Random rand = new java.util.Random();
		shift = rand.nextInt(prime);
	}

	/**
	 * Set the cell (x, y) in the board to the given value mod 3.
	 * 
	 * @param value
	 *            to which the element should be set (normally 0, 1, or 2).
	 * @param x
	 *            is the x-index.
	 * @param y
	 *            is the y-index.
	 * @exception ArrayIndexOutOfBoundsException
	 *                is thrown if an invalid index is given.
	 **/

	public void setElementAt(int x, int y, int value) {
		grid[x][y] = value % 3;
		if (grid[x][y] < 0) {
			grid[x][y] = grid[x][y] + 3;
		}
	}

	/**
	 * Get the valued stored in cell (x, y).
	 * 
	 * @param x
	 *            is the x-index.
	 * @param y
	 *            is the y-index.
	 * @return the stored value (between 0 and 2).
	 * @exception ArrayIndexOutOfBoundsException
	 *                is thrown if an invalid index is given.
	 */

	public int elementAt(int x, int y) {
		return grid[x][y];
	}

	/**
	 * Returns true if "this" SimpleBoard and "board" have identical values in
	 * every cell.
	 * 
	 * @param board
	 *            is the second SimpleBoard.
	 * @return true if the boards are equal, false otherwise.
	 */

	@Override
	public boolean equals(Object board) {
		// Replace the following line with your solution. Be sure to return
		// false
		// (rather than throwing a ClassCastException) if "board" is not
		// a SimpleBoard.
		if (board instanceof SimpleBoard) {
			if (((SimpleBoard) board).DIMENSION == this.DIMENSION) {

				for (int i = 0; i < grid.length; i++) {
					for (int j = 0; j < grid.length; j++) {
						if (((SimpleBoard) board).grid[i][j] != this.grid[i][j]) {
							return false;
						}
					}
				}
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	/**
	 * Returns a hash code for this SimpleBoard.
	 * 
	 * @return a number between Integer.MIN_VALUE and Integer.MAX_VALUE.
	 */

	@Override
	public int hashCode() {
		// Replace the following line with your solution.
		int hcode = 0;
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid.length; j++) {
				hcode += (i + j) * grid[i][j];
			}
		}
		hcode += shift;
		return hcode;
	}

}
