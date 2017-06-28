package list;

import java.util.Iterator;

public class SListIterator implements Iterator {

	SListNode node;

	public SListIterator(SList s) {
		node = s.head;
	}

	@Override
	public boolean hasNext() {
		return node != null;
	}

	@Override
	public Object next() {
		if (node == null) {
			throw new NullPointerException();
		}

		Object i = node.item;
		node = node.next;
		return i;

	}
}
