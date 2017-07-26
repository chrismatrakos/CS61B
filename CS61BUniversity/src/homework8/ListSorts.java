/* ListSorts.java */
package homework8;

import java.util.Random;

import listhomework8.LinkedQueue;
import listhomework8.QueueEmptyException;

public class ListSorts {

	private final static int SORTSIZE = 1000;
	public static LinkedQueue queue1 = new LinkedQueue();

	/**
	 * makeQueueOfQueues() makes a queue of queues, each containing one item of
	 * q. Upon completion of this method, q is empty.
	 * 
	 * @param q
	 *            is a LinkedQueue of objects.
	 * @return a LinkedQueue containing LinkedQueue objects, each of which
	 *         contains one object from q.
	 * @throws QueueEmptyException
	 **/
	public static LinkedQueue makeQueueOfQueues(LinkedQueue q) {
		// Replace the following line with your solution.
		if (q.isEmpty() || q == null) {
			System.out.println("Empty Queue");
			return null;
		}
		LinkedQueue queueOfqueues = new LinkedQueue();
		while (!q.isEmpty()) {
			LinkedQueue temp = new LinkedQueue();
			try {
				temp.enqueue(q.dequeue());
			} catch (QueueEmptyException e) {
				e.printStackTrace();
			}
			queueOfqueues.enqueue(temp);
		}

		return queueOfqueues;
	}

	/**
	 * mergeSortedQueues() merges two sorted queues into a third. On completion
	 * of this method, q1 and q2 are empty, and their items have been merged
	 * into the returned queue.
	 * 
	 * @param q1
	 *            is LinkedQueue of Comparable objects, sorted from smallest to
	 *            largest.
	 * @param q2
	 *            is LinkedQueue of Comparable objects, sorted from smallest to
	 *            largest.
	 * @return a LinkedQueue containing all the Comparable objects from q1 and
	 *         q2 (and nothing else), sorted from smallest to largest.
	 * @throws QueueEmptyException
	 **/
	public static LinkedQueue mergeSortedQueues(LinkedQueue q1, LinkedQueue q2) {
		// Replace the following line with your solution.
		if (q1 == null && q2 == null) {
			System.out.println("EmptyQueue");
		}
		if (q1 == null) {
			return q2;
		}
		if (q2 == null) {
			return q1;
		}
		LinkedQueue q = new LinkedQueue();

		try {
			while (!q1.isEmpty() && !q2.isEmpty()) {
				if (((Comparable) q1.front()).compareTo(q2.front()) < 0) {
					q.enqueue(q1.dequeue());
				} else {
					q.enqueue(q2.dequeue());
				}
			}
			if (q1.isEmpty()) {
				while (!q2.isEmpty()) {
					q.enqueue(q2.dequeue());
				}
			}
			if (q2.isEmpty()) {
				while (!q1.isEmpty()) {
					q.enqueue(q1.dequeue());
				}
			}
		} catch (QueueEmptyException e) {
			e.printStackTrace();
		}
		return q;
	}

	/**
	 * partition() partitions qIn using the pivot item. On completion of this
	 * method, qIn is empty, and its items have been moved to qSmall, qEquals,
	 * and qLarge, according to their relationship to the pivot.
	 * 
	 * @param qIn
	 *            is a LinkedQueue of Comparable objects.
	 * @param pivot
	 *            is a Comparable item used for partitioning.
	 * @param qSmall
	 *            is a LinkedQueue, in which all items less than pivot will be
	 *            enqueued.
	 * @param qEquals
	 *            is a LinkedQueue, in which all items equal to the pivot will
	 *            be enqueued.
	 * @param qLarge
	 *            is a LinkedQueue, in which all items greater than pivot will
	 *            be enqueued.
	 **/
	public static void partition(LinkedQueue qIn, Comparable pivot, LinkedQueue qSmall, LinkedQueue qEquals,
			LinkedQueue qLarge) {
		// Your solution here.

		while (!qIn.isEmpty()) {
			try {
				if (pivot.compareTo(qIn.front()) == 0) {
					qEquals.enqueue(qIn.dequeue());
				} else if (pivot.compareTo(qIn.front()) > 0) {
					qSmall.enqueue(qIn.dequeue());
				} else {
					qLarge.enqueue(qIn.dequeue());
				}
			} catch (QueueEmptyException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * mergeSort() sorts q from smallest to largest using mergesort.
	 * 
	 * @param q
	 *            is a LinkedQueue of Comparable objects.
	 * @throws QueueEmptyException
	 **/

	public static void mergeSort(LinkedQueue q) {
		// Your solution here.
		if (q.size() <= 1) {
			return;
		}
		LinkedQueue qq = makeQueueOfQueues(q);
		while (qq.size() > 1) {
			try {
				LinkedQueue q1 = (LinkedQueue) qq.dequeue();
				LinkedQueue q2 = (LinkedQueue) qq.dequeue();
				LinkedQueue mq = mergeSortedQueues(q1, q2);
				qq.enqueue(mq);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		try {
			q.append((LinkedQueue) qq.dequeue());
		} catch (QueueEmptyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void mergeSort2(LinkedQueue q) {
		if (q.size() < 2) {
			return;
		}
		int i = 0;
		LinkedQueue q1 = new LinkedQueue();
		LinkedQueue q2 = new LinkedQueue();
		try {
			while (i < q.size() / 2) {
				q1.enqueue(q.dequeue());
				i++;
			}
		} catch (QueueEmptyException e) {
			e.printStackTrace();
		}
		try {
			while (!q.isEmpty()) {
				q2.enqueue(q.dequeue());
			}
		} catch (QueueEmptyException e2) {
			e2.printStackTrace();
		}

		mergeSort2(q1);
		mergeSort2(q2);
		mergeSortedQueues2(q1, q2, q);
	}

	public static void mergeSortedQueues2(LinkedQueue q1, LinkedQueue q2, LinkedQueue q) {
		// Replace the following line with your solution.
		if (q1 == null && q2 == null) {
			System.out.println("EmptyQueue");
		}

		try {
			while (!q1.isEmpty() && !q2.isEmpty()) {
				if (((Comparable) q1.front()).compareTo(q2.front()) < 0) {
					q.enqueue(q1.dequeue());
				} else {
					q.enqueue(q2.dequeue());
				}
			}
			if (q1.isEmpty()) {
				while (!q2.isEmpty()) {
					q.enqueue(q2.dequeue());
				}
			}
			if (q2.isEmpty()) {
				while (!q1.isEmpty()) {
					q.enqueue(q1.dequeue());
				}
			}
		} catch (QueueEmptyException e) {
			e.printStackTrace();
		}
	}

	/**
	 * quickSort() sorts q from smallest to largest using quicksort.
	 * 
	 * @param q
	 *            is a LinkedQueue of Comparable objects.
	 **/
	public static void quickSort(LinkedQueue q) {
		// Your solution here.
		if (q.size() < 2) {
			return;
		}
		int random = new Random().nextInt(q.size() - 1);
		random += 1;
		int pivot = (int) q.nth(random);

		LinkedQueue left = new LinkedQueue();
		LinkedQueue right = new LinkedQueue();
		LinkedQueue mid = new LinkedQueue();
		partition(q, pivot, left, mid, right);
		quickSort(left);
		quickSort(right);
		q.append(left);
		q.append(mid);
		q.append(right);
	}

	/**
	 * makeRandom() builds a LinkedQueue of the indicated size containing
	 * Integer items. The items are randomly chosen between 0 and size - 1.
	 * 
	 * @param size
	 *            is the size of the resulting LinkedQueue.
	 **/
	public static LinkedQueue makeRandom(int size) {
		LinkedQueue q = new LinkedQueue();
		for (int i = 0; i < size; i++) {
			q.enqueue(new Integer((int) (size * Math.random())));
		}
		return q;
	}

	/**
	 * main() performs some tests on mergesort and quicksort. Feel free to add
	 * more tests of your own to make sure your algorithms works on boundary
	 * cases. Your test code will not be graded.
	 * 
	 * @throws QueueEmptyException
	 **/
	public static void main(String[] args) {

		// LinkedQueue qt1 = makeRandom(4);
		// System.out.println(qt1.toString());
		// LinkedQueue qt2 = makeRandom(4);
		// System.out.println(qt2.toString());
		// LinkedQueue qt;
		// qt = mergeSortedQueues(qt1, qt2);
		// System.out.println(qt.toString());
		//
		// LinkedQueue qt3 = makeQueueOfQueues(makeRandom(4));
		// System.out.println(qt3.toString());

		// System.out.println("MergeSort using Divide & Conquer");
		// LinkedQueue q2 = makeRandom(10);
		// System.out.println(q2.toString());
		// mergeSort2(q2);
		// System.out.println(q2.toString());

		System.out.println("\nStart existing CS tests:\nMergeSort:");
		LinkedQueue q = makeRandom(10);
		System.out.println(q.toString());
		mergeSort(q);
		System.out.println(q.toString());

		System.out.println("QuickSort:");
		LinkedQueue qs = makeRandom(10);
		System.out.println(qs.toString());
		quickSort(qs);
		System.out.println(qs.toString());

		System.out.println("\nBenchmarking:");
		for (int i = 1; i < 1000; i *= 10) {
			Timer stopWatch = new Timer();
			q = makeRandom(i * SORTSIZE);
			stopWatch.start();
			mergeSort(q);
			stopWatch.stop();
			System.out.println("Mergesort time, " + i * SORTSIZE + " Integers:  " + stopWatch.elapsed() + " msec.");

			stopWatch.reset();
			q = makeRandom(SORTSIZE * i);
			stopWatch.start();
			quickSort(q);
			stopWatch.stop();
			System.out.println("Quicksort time, " + i * SORTSIZE + " Integers:  " + stopWatch.elapsed() + " msec.");
		}

		// RESULTS:
		// Benchmarking:
		// Mergesort time, 1000 Integers: 6 msec.
		// Quicksort time, 1000 Integers: 8 msec.
		// Mergesort time, 10000 Integers: 26 msec.
		// Quicksort time, 10000 Integers: 24 msec.
		// Mergesort time, 100000 Integers: 272 msec.
		// Quicksort time, 100000 Integers: 211 msec.
		//
	}

}