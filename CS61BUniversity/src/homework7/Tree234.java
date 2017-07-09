/* Tree234.java */

package homework7;

/**
 * A Tree234 implements an ordered integer dictionary ADT using a 2-3-4 tree.
 * Only int keys are stored; no object is associated with each key. Duplicate
 * keys are not stored in the tree.
 *
 * @author Jonathan Shewchuk
 **/
public class Tree234 extends IntDictionary {

	/**
	 * You may add fields if you wish, but don't change anything that would
	 * prevent toString() or find() from working correctly.
	 *
	 * (inherited) size is the number of keys in the dictionary. root is the
	 * root of the 2-3-4 tree.
	 **/
	Tree234Node root;

	/**
	 * Tree234() constructs an empty 2-3-4 tree.
	 *
	 * You may change this constructor, but you may not change the fact that an
	 * empty Tree234 contains no nodes.
	 */
	public Tree234() {
		root = null;
		size = 0;
	}

	/**
	 * toString() prints this Tree234 as a String. Each node is printed in the
	 * form such as (for a 3-key node)
	 *
	 * (child1)key1(child2)key2(child3)key3(child4)
	 *
	 * where each child is a recursive call to toString, and null children are
	 * printed as a space with no parentheses. Here's an example. ((1)7(11
	 * 16)22(23)28(37 49))50((60)84(86 95 100))
	 *
	 * DO NOT CHANGE THIS METHOD. The test code depends on it.
	 *
	 * @return a String representation of the 2-3-4 tree.
	 **/
	@Override
	public String toString() {
		if (root == null) {
			return "";
		} else {
			/* Most of the work is done by Tree234Node.toString(). */
			return root.toString();
		}
	}

	/**
	 * printTree() prints this Tree234 as a tree, albeit sideways.
	 *
	 * You're welcome to change this method if you like. It won't be tested.
	 **/
	public void printTree() {
		if (root != null) {
			/* Most of the work is done by Tree234Node.printSubtree(). */
			root.printSubtree(0);
		}
	}

	/**
	 * find() prints true if "key" is in this 2-3-4 tree; false otherwise.
	 *
	 * @param key
	 *            is the key sought.
	 * @return true if "key" is in the tree; false otherwise.
	 **/
	public boolean find(int key) {
		Tree234Node node = root;
		while (node != null) {
			if (key < node.key1) {
				node = node.child1;
			} else if (key == node.key1) {
				return true;
			} else if ((node.keys == 1) || (key < node.key2)) {
				node = node.child2;
			} else if (key == node.key2) {
				return true;
			} else if ((node.keys == 2) || (key < node.key3)) {
				node = node.child3;
			} else if (key == node.key3) {
				return true;
			} else {
				node = node.child4;
			}
		}
		return false;
	}

	/**
	 * insert() inserts the key "key" into this 2-3-4 tree. If "key" is already
	 * present, a duplicate copy is NOT inserted.
	 *
	 * @param key
	 *            is the key sought.
	 **/
	public void insert(int key) {
		// Fill in your solution here.

		size++;
		if (root == null) {
			Tree234Node node = new Tree234Node(null, key);
			root = node;

			return;
		}

		Tree234Node node = root;
		while (node.child1 != null) {
			if (node.keys == 3) {
				node = split234Node(node, key);
			}
			if (key < node.key1) {
				node = node.child1;
			} else if (key == node.key1) {
				return;
			} else if ((node.keys == 1) || (key < node.key2)) {
				node = node.child2;
			} else if (key == node.key2) {
				return;
			} else if ((node.keys == 2) || (key < node.key3)) {
				if (node.child3 != null) {
					node = node.child3;
				} else {
					if (key < node.key1) {
						node.key3 = node.key2;
						node.key2 = node.key1;
						node.key1 = key;
						return;
					} else if (key > node.key2) {
						node.key3 = key;
						return;
					} else {
						node.key3 = node.key2;
						node.key2 = key;
						return;
					}
				}
			}

		}

		if (node.keys == 3) {
			node = split234Node(node, key);
		}

		if (node.keys == 1) {
			if (key < node.key1) {
				int temp = node.key1;
				node.key1 = key;
				node.key2 = temp;
				node.keys++;

			} else {
				node.key2 = key;
				node.keys++;
			}
		} else {
			if (key > node.key2) {
				node.key3 = key;
				node.keys++;
			} else {
				if (key < node.key1) {
					int temp1 = node.key1;
					int temp2 = node.key2;
					node.key1 = key;
					node.key2 = temp1;
					node.key3 = temp2;
					node.keys++;
				} else {
					node.key3 = node.key2;
					node.key2 = key;
					node.keys++;
				}
			}
		}

	}

	private Tree234Node split234Node(Tree234Node node, int compkey) {

		if (node == root) {

			Tree234Node newnode = new Tree234Node(null, node.key2);
			root = newnode;
			Tree234Node newnode1 = new Tree234Node(root, node.key1);
			Tree234Node newnode2 = new Tree234Node(root, node.key3);
			newnode1.child1 = node.child1;
			newnode1.child2 = node.child2;
			newnode2.child1 = node.child3;
			newnode2.child2 = node.child4;

			if (newnode1.child1 != null) {
				newnode1.child1.parent = newnode1;
			}
			if (newnode1.child2 != null) {
				newnode1.child2.parent = newnode1;
			}
			if (newnode2.child1 != null) {
				newnode2.child1.parent = newnode2;
			}
			if (newnode2.child2 != null) {
				newnode2.child2.parent = newnode2;
			}
			root.child1 = newnode1;
			root.child2 = newnode2;
			node.parent = null;
			if (compkey < root.key1) {
				return newnode1;
			} else {
				return newnode2;
			}
		} else {

			Tree234Node newnode1 = new Tree234Node(node.parent, node.key1);
			Tree234Node newnode2 = new Tree234Node(node.parent, node.key3);
			newnode1.child1 = node.child1;
			newnode1.child2 = node.child2;
			newnode2.child1 = node.child3;
			newnode2.child2 = node.child4;
			int key = node.key2;
			Tree234Node parent = node.parent;
			if (parent.keys == 1) {
				if (key < parent.key1) {
					parent.key2 = parent.key1;
					parent.key1 = key;
					parent.child3 = parent.child2;
					parent.child1 = newnode1;
					parent.child2 = newnode2;

				} else {
					parent.key2 = key;
					parent.child2 = newnode1;
					parent.child3 = newnode2;

				}
				parent.keys++;
				if (compkey > key) {
					return newnode2;
				} else {
					return newnode1;
				}

			} else {
				if (key < parent.key1) {
					parent.key3 = parent.key2;
					parent.key2 = parent.key1;
					parent.key1 = key;
					parent.child4 = parent.child3;
					parent.child3 = parent.child2;
					parent.child1 = newnode1;
					parent.child2 = newnode2;

				} else if (key > parent.key2) {
					parent.key3 = key;
					parent.child3 = newnode1;
					parent.child4 = newnode2;

				} else {
					int temp = parent.key2;
					parent.key2 = key;
					parent.key3 = temp;
					parent.child4 = parent.child3;
					parent.child2 = newnode1;
					parent.child3 = newnode2;

				}

				parent.keys++;
				if (compkey > key) {
					return newnode2;
				} else {
					return newnode1;
				}
			}

		}

	}

	/**
	 * testHelper() prints the String representation of this tree, then compares
	 * it with the expected String, and prints an error message if the two are
	 * not equal.
	 *
	 * @param correctString
	 *            is what the tree should look like.
	 **/
	public void testHelper(String correctString) {
		String treeString = toString();
		System.out.println(treeString);
		if (!treeString.equals(correctString)) {
			System.out.println("ERROR:  Should be " + correctString);
		}
	}

	/**
	 * main() is a bunch of test code. Feel free to add test code of your own;
	 * this code won't be tested or graded.
	 **/
	public static void main(String[] args) {
		Tree234 t = new Tree234();

		System.out.println("\nInserting 84.");
		t.insert(84);
		t.testHelper("84");

		System.out.println("\nInserting 7.");
		t.insert(7);
		t.testHelper("7 84");

		System.out.println("\nInserting 22.");
		t.insert(22);
		t.testHelper("7 22 84");

		System.out.println("\nInserting 95.");
		t.insert(95);
		t.testHelper("(7)22(84 95)");

		System.out.println("\nInserting 50.");
		t.insert(50);
		t.testHelper("(7)22(50 84 95)");

		System.out.println("\nInserting 11.");
		t.insert(11);
		t.testHelper("(7 11)22(50 84 95)");

		System.out.println("\nInserting 37.");
		t.insert(37);
		t.testHelper("(7 11)22(37 50)84(95)");

		System.out.println("\nInserting 60.");
		t.insert(60);
		t.testHelper("(7 11)22(37 50 60)84(95)");

		System.out.println("\nInserting 1.");
		t.insert(1);
		t.testHelper("(1 7 11)22(37 50 60)84(95)");

		System.out.println("\nInserting 23.");
		t.insert(23);
		t.testHelper("(1 7 11)22(23 37)50(60)84(95)");

		System.out.println("\nInserting 16.");
		t.insert(16);
		t.testHelper("((1)7(11 16)22(23 37))50((60)84(95))");

		System.out.println("\nInserting 100.");
		t.insert(100);
		t.testHelper("((1)7(11 16)22(23 37))50((60)84(95 100))");

		System.out.println("\nInserting 28.");
		t.insert(28);
		t.testHelper("((1)7(11 16)22(23 28 37))50((60)84(95 100))");

		System.out.println("\nInserting 86.");
		t.insert(86);
		t.testHelper("((1)7(11 16)22(23 28 37))50((60)84(86 95 100))");

		System.out.println("\nInserting 49.");
		t.insert(49);
		t.testHelper("((1)7(11 16)22(23)28(37 49))50((60)84(86 95 100))");

		System.out.println("\nInserting 81.");
		t.insert(81);
		t.testHelper("((1)7(11 16)22(23)28(37 49))50((60 81)84(86 95 100))");

		System.out.println("\nInserting 51.");
		t.insert(51);
		t.testHelper("((1)7(11 16)22(23)28(37 49))50((51 60 81)84(86 95 100))");

		System.out.println("\nInserting 99.");
		t.insert(99);
		t.testHelper("((1)7(11 16)22(23)28(37 49))50((51 60 81)84(86)95(99 100))");

		System.out.println("\nInserting 75.");
		t.insert(75);
		t.testHelper("((1)7(11 16)22(23)28(37 49))50((51)60(75 81)84(86)95" + "(99 100))");

		System.out.println("\nInserting 66.");
		t.insert(66);
		t.testHelper("((1)7(11 16)22(23)28(37 49))50((51)60(66 75 81))84((86)95" + "(99 100))");

		System.out.println("\nFinal tree:");
		t.printTree();
	}

}