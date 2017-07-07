/* SibTreeNode.java */

package Lab10tree;

/**
 * A SibTreeNode object is a node in a SibTree (sibling-based general tree).
 * 
 * @author Jonathan Shewchuk
 */

class SibTreeNode extends TreeNode {

	/**
	 * (inherited) item references the item stored in this node. (inherited)
	 * valid is true if and only if this is a valid node in some Tree. myTree
	 * references the Tree that contains this node. parent references this
	 * node's parent node. firstChild references this node's first (leftmost)
	 * child. nextSibling references this node's next sibling.
	 *
	 * DO NOT CHANGE THE FOLLOWING FIELD DECLARATIONS.
	 */

	/**
	 * ADT implementation invariants: 1) if valid == true, myTree != null. 2) if
	 * valid == true, then this is a descendent of myTree.root. 3) if valid ==
	 * true, myTree satisfies all the invariants of a SibTree (listed in
	 * SibTree.java).
	 */

	protected SibTree myTree;
	protected SibTreeNode parent;
	protected SibTreeNode firstChild;
	protected SibTreeNode nextSibling;

	/**
	 * Construct a valid SibTreeNode referring to a given item.
	 */
	SibTreeNode(SibTree tree, Object item) {
		this.item = item;
		valid = true;
		myTree = tree;
		parent = null;
		firstChild = null;
		nextSibling = null;
	}

	/**
	 * Construct an invalid SibTreeNode.
	 */
	SibTreeNode() {
		valid = false;
	}

	/**
	 * children() returns the number of children of the node at this position.
	 * WARNING: Does not run in constant time. Actually counts the kids.
	 */
	@Override
	public int children() {
		if (isValidNode()) {
			int count = 0;
			SibTreeNode countNode = firstChild;
			while (countNode != null) {
				count++;
				countNode = countNode.nextSibling;
			}
			return count;
		} else {
			return 0;
		}
	}

	/**
	 * parent() returns the parent TreeNode of this TreeNode. Throws an
	 * exception if `this' is not a valid node. Returns an invalid TreeNode if
	 * this node is the root.
	 */
	@Override
	public TreeNode parent() throws InvalidNodeException {
		// REPLACE THE FOLLOWING LINE WITH YOUR SOLUTION TO PART I.
		if (!this.isValidNode()) {
			throw new InvalidNodeException();
		} else {
			if (this.parent == null) {

				return new SibTreeNode();
			}
			return this.parent;
		}

	}

	/**
	 * child() returns the cth child of this TreeNode. Throws an exception if
	 * `this' is not a valid node. Returns an invalid TreeNode if there is no
	 * cth child.
	 */
	@Override
	public TreeNode child(int c) throws InvalidNodeException {
		if (isValidNode()) {
			if (c < 1) {
				return new SibTreeNode();
			}
			SibTreeNode kid = firstChild;
			while ((kid != null) && (c > 1)) {
				kid = kid.nextSibling;
				c--;
			}
			if (kid == null) {
				return new SibTreeNode();
			} else {
				return kid;
			}
		} else {
			throw new InvalidNodeException();
		}
	}

	/**
	 * nextSibling() returns the next sibling TreeNode to the right from this
	 * TreeNode. Throws an exception if `this' is not a valid node. Returns an
	 * invalid TreeNode if there is no sibling to the right of this node.
	 */
	@Override
	public TreeNode nextSibling() throws InvalidNodeException {
		if (isValidNode()) {
			if (nextSibling == null) {
				return new SibTreeNode();
			} else {
				return nextSibling;
			}
		} else {
			throw new InvalidNodeException();
		}
	}

	/**
	 * insertChild() inserts an item as the cth child of this node. Existing
	 * children numbered c or higher are shifted one place to the right to
	 * accommodate. If the current node has fewer than c children, the new item
	 * is inserted as the last child. If c < 1, act as if c is 1.
	 *
	 * Throws an InvalidNodeException if "this" node is invalid.
	 */
	@Override
	public void insertChild(Object item, int c) throws InvalidNodeException {
		// FILL IN YOUR SOLUTION TO PART II HERE.
		if (!this.isValidNode()) {
			throw new InvalidNodeException();
		}

		SibTreeNode newNode = new SibTreeNode(myTree, item);
		newNode.parent = this;

		if (this.firstChild == null) {
			this.firstChild = newNode;
			myTree.size++;
			return;
		}
		if (c <= 1) {
			newNode.nextSibling = this.firstChild;
			this.firstChild = newNode;

		} else if (this.children() < c) {
			SibTreeNode last = (SibTreeNode) child(this.children());
			last.nextSibling = newNode;
		} else {
			SibTreeNode cNode = (SibTreeNode) child(c - 1);
			newNode.nextSibling = cNode.nextSibling;
			cNode.nextSibling = newNode;

		}
		myTree.size++;
	}

	/**
	 * removeLeaf() removes the node at the current position from the tree if it
	 * is a leaf. Does nothing if `this' has one or more children. Throws an
	 * exception if `this' is not a valid node. If 'this' has siblings to its
	 * right, those siblings are all shifted left by one.
	 */
	@Override
	public void removeLeaf() throws InvalidNodeException {
		// FILL IN YOUR SOLUTION TO PART III HERE.

		if (!this.isValidNode()) {
			throw new InvalidNodeException();
		}
		if (this.firstChild != null) {
			return;
		}

		if (this.parent == null) {
			this.valid = false;
			myTree.size--;
			myTree.root.valid = false;
			return;
		}

		SibTreeNode first = this.parent.firstChild;
		if (first == this) {
			if (first.nextSibling == null) {
				this.parent.firstChild = null;
			} else {
				this.parent.firstChild = this.nextSibling;
			}
		} else {
			while (first.nextSibling != this) {
				first = first.nextSibling;
			}
			first.nextSibling = this.nextSibling;

		}
		this.valid = false;
		myTree.size--;
	}

}