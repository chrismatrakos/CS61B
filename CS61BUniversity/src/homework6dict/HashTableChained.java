package homework6dict;

import list.InvalidNodeException;
/* HashTableChained.java */
import list.List;
import list.ListNode;

/**
 * HashTableChained implements a Dictionary as a hash table with chaining. All
 * objects used as keys must have a valid hashCode() method, which is used to
 * determine which bucket of the hash table an entry is stored in. Each object's
 * hashCode() is presumed to return an int between Integer.MIN_VALUE and
 * Integer.MAX_VALUE. The HashTableChained class implements only the compression
 * function, which maps the hash code to a bucket in the table's range.
 *
 * DO NOT CHANGE ANY PROTOTYPES IN THIS FILE.
 **/

public class HashTableChained implements Dictionary {

	/**
	 * Place any data fields here.
	 **/
	private int size = 0; // # of elemnts stored in arrayBucket
	private int N;// size of Arraybucket
	private List slist;
	private List[] arrayBucket;
	private long scale;
	private long shift;
	private int prime = 109345121;

	/**
	 * Construct a new empty hash table intended to hold roughly sizeEstimate
	 * entries. (The precise number of buckets is up to you, but we recommend
	 * you use a prime number, and shoot for a load factor between 0.5 and 1.)
	 **/

	public HashTableChained(int sizeEstimate) {
		// Your solution here.
		int loadFactor = (int) (sizeEstimate / 0.75);
		this.N = sieve(loadFactor);
		arrayBucket = new List[N];
		java.util.Random rand = new java.util.Random();
		scale = rand.nextInt((prime - 1) + 1);
		shift = rand.nextInt(prime);
	}

	/**
	 * Construct a new empty hash table with a default size. Say, a prime in the
	 * neighborhood of 100.
	 **/

	public HashTableChained() {
		// Your solution here.

		N = 107;
		arrayBucket = new List[N];
		java.util.Random rand = new java.util.Random();
		scale = rand.nextInt((prime - 1) + 1);
		shift = rand.nextInt(prime);
	}

	/**
	 * Converts a hash code in the range Integer.MIN_VALUE...Integer.MAX_VALUE
	 * to a value in the range 0...(size of hash table) - 1.
	 *
	 * This function should have package protection (so we can test it), and
	 * should be used by insert, find, and remove.
	 **/

	int compFunction(int code) {
		// Replace the following line with your solution.
		return (int) Math.abs((((scale * code + shift) % prime) % N));

	}

	/**
	 * Returns the number of entries stored in the dictionary. Entries with the
	 * same key (or even the same key and value) each still count as a separate
	 * entry.
	 * 
	 * @return number of entries in the dictionary.
	 **/

	@Override
	public int size() {
		// Replace the following line with your solution.
		return size;
	}

	/**
	 * Tests if the dictionary is empty.
	 *
	 * @return true if the dictionary has no entries; false otherwise.
	 **/

	@Override
	public boolean isEmpty() {
		// Replace the following line with your solution.
		return size == 0;
	}

	/**
	 * Create a new Entry object referencing the input key and associated value,
	 * and insert the entry into the dictionary. Return a reference to the new
	 * entry. Multiple entries with the same key (or even the same key and
	 * value) can coexist in the dictionary.
	 *
	 * This method should run in O(1) time if the number of collisions is small.
	 *
	 * @param key
	 *            the key by which the entry can be retrieved.
	 * @param value
	 *            an arbitrary object.
	 * @return an entry containing the key and value.
	 **/

	@Override
	public Entry insert(Object key, Object value) {
		// Replace the following line with your solution.
		if (key == null || value == null) {
			System.out.println("Invalid key/value");
			return null;
		}
		Entry e = new Entry();
		e.key = key;
		e.value = value;
		int bucketIndex = compFunction(key.hashCode());
		//
		// // check for the size if its full and double size and rehash
		// // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		// if (arrayBucket[bucketIndex] == null) {
		// arrayBucket[bucketIndex] = new SList();
		// arrayBucket[bucketIndex].insertBack(e);
		// } else {
		// arrayBucket[bucketIndex].insertBack(e);
		// }
		this.size++;
		return e;
	}

	/**
	 * Search for an entry with the specified key. If such an entry is found,
	 * return it; otherwise return null. If several entries have the specified
	 * key, choose one arbitrarily and return it.
	 *
	 * This method should run in O(1) time if the number of collisions is small.
	 *
	 * @param key
	 *            the search key.
	 * @return an entry containing the key and an associated value, or null if
	 *         no entry contains the specified key.
	 * @throws InvalidNodeException
	 **/

	@Override
	public Entry find(Object key) {
		// Replace the following line with your solution.
		if (key == null) {
			System.out.println("Invalid key");
			return null;
		}
		int keyIndex = compFunction(key.hashCode());
		slist = arrayBucket[keyIndex];

		if (slist.isEmpty()) {
			return null;
		} else {
			try {
				ListNode node = slist.front();
				if (((Entry) node.item()).key().equals(key)) {
					return (Entry) node.item();
				} else {
					while (node.next() != null) {
						node = node.next();
						if (((Entry) node.item()).key().equals(key)) {
							return (Entry) node.item();
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * Remove an entry with the specified key. If such an entry is found, remove
	 * it from the table and return it; otherwise return null. If several
	 * entries have the specified key, choose one arbitrarily, then remove and
	 * return it.
	 *
	 * This method should run in O(1) time if the number of collisions is small.
	 *
	 * @param key
	 *            the search key.
	 * @return an entry containing the key and an associated value, or null if
	 *         no entry contains the specified key.
	 */

	@Override
	public Entry remove(Object key) {
		// Replace the following line with your solution.
		if (key == null) {
			System.out.println("Invalid key");
			return null;
		}

		this.size--;
		return null;
	}

	/**
	 * Remove all entries from the dictionary.
	 */
	@Override
	public void makeEmpty() {
		// Your solution here.
		for (int i = 0; i < this.size(); i++) {
			arrayBucket[i] = null;
		}
	}

	private void rehash() {

	}

	private int sieve(int capacity) {
		boolean b[] = new boolean[capacity];
		for (int i = 0; i < b.length; i++) {
			b[i] = true;
		}
		for (int i = 2; i * i <= capacity; i++) {
			if (b[i] == true) {
				for (int j = i * i; j < capacity; j += i) {
					b[j] = false;
				}
			}
		}
		//
		// for (int i = 2; i < b.length; i++) {
		// if (b[i]) {
		// System.out.println(i);
		// }
		// }
		int lastPrime = b.length - 1;
		while (!b[lastPrime]) {
			lastPrime--;
		}
		return lastPrime;
	}

}
