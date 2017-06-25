package homework6;

import homework6dict.HashTableChained;

/* Homework6Test.java */

/**
 * Initializes a hash table, then stocks it with random SimpleBoards.
 **/

public class Homework6Test {

	/**
	 * Generates a random 8 x 8 SimpleBoard.
	 **/

	private static SimpleBoard randomBoard() {
		SimpleBoard board = new SimpleBoard();
		for (int y = 0; y < 8; y++) {
			for (int x = 0; x < 8; x++) {
				double fval = Math.random() * 12;
				int value = (int) fval;

				board.setElementAt(x, y, value);
			}
		}
		return board;
	}

	/**
	 * Empties the given table, then inserts "numBoards" boards into the table.
	 * 
	 * @param table
	 *            is the hash table to be initialized.
	 * @param numBoards
	 *            is the number of random boards to place in the table.
	 **/

	public static void initTable(HashTableChained table, int numBoards) {
		table.makeEmpty();
		for (int i = 0; i < numBoards; i++) {
			table.insert(randomBoard(), new Integer(i));
		}
	}

	/**
	 * Creates a hash table and stores a certain number of SimpleBoards in it.
	 * The number is 100 by default, but you can specify any number at the
	 * command line. For example:
	 *
	 * java Homework6Test 12000
	 **/

	public static void main(String[] args) {
		// TEST INSERT REMOVE FIND on HASHTABLECHAINED!!!
		// HashTableChained map = new HashTableChained(10);
		// map.insert(5, 'a');
		// map.insert(5, 'b');
		// map.insert(5, 'c');
		// map.insert(5, 'd');
		// map.insert(1, 'a');
		// map.insert(3, 'b');
		// map.insert(5, 'g');
		// map.insert(4, 'c');
		// map.insert(16, 'c');
		// map.insert(53, 'c');
		// map.insert(66, 'd');
		// map.insert(13, 'f');
		// map.insert(5, 'f');
		// map.insert(15, 'f');
		// map.insert(23, 'f');
		// map.insert(23, 'f');
		// map.insert(45, 'f');
		// map.insert(52, 'f');
		// System.out.println("size: " + map.size());
		//
		// System.out.println(map.remove(15) + "\n" + map.remove(5) + "\n" +
		// map.remove(100));
		// map.printCollisions();
		//
		// System.out.println(map.find(5));
		// System.out.println(map.find(23));
		// System.out.println("size: " + map.size());
		//
		// map.remove(1);
		// map.remove(3);
		// map.remove(5);
		// map.remove(4);
		// map.remove(16);
		// map.remove(53);
		// map.remove(66);
		// map.remove(13);
		// map.remove(5);
		// map.remove(15);
		// map.remove(23);
		// map.remove(23);
		// map.remove(45);
		// map.remove(52);
		// System.out.println("size: " + map.size());
		// System.out.println(map.toString());
		// -------------------------------------------
		// numBoards tests
		int numBoards;

		if (args.length == 0) {
			numBoards = 100;
		} else {
			numBoards = Integer.parseInt(args[0]);
		}
		HashTableChained table = new HashTableChained(numBoards);
		initTable(table, numBoards);
		System.out.println("Start of the project:");

		table.printCollisions();

		// To test your hash function, add a method to your HashTableChained
		// class
		// that counts the number of collisions--or better yet, also prints
		// a histograph of the number of entries in each bucket. Call this
		// method
		// from here.
	}

}
