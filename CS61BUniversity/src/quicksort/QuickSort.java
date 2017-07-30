package quicksort;

public class QuickSort {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int a[] = { 3, 1, 8, 4, 5, 2, 12, 2, 3, 15, 4 };
		quickSort2(a, 0, a.length - 1);
		for (int x : a) {
			System.out.printf("%d ", x);
		}

	}

	public static void quickSort(int a[], int low, int high) {

		if (low < high) {

			int r = new java.util.Random().nextInt((high - low) + 1) + low;
			int pivot = a[r];
			a[r] = a[high];
			a[high] = pivot;
			int i = low - 1;
			int j = high;

			do {
				do {
					i++;
				} while (a[i] < pivot);
				do {
					j--;
				} while (j > low && a[j] > pivot);
				if (i < j) {
					int temp = a[j];
					a[j] = a[i];
					a[i] = temp;
				}
			} while (i < j);
			a[high] = a[i];
			a[i] = pivot;

			quickSort(a, low, i - 1);
			quickSort(a, i + 1, high);
		}
	}

	public static void quickSort2(int a[], int low, int high) {

		if (low < high) {

			int r = new java.util.Random().nextInt((high - low) + 1) + low;
			// int r = (low + high) / 2;
			int pivot = a[r];
			a[r] = a[high];
			a[high] = pivot;
			int i = low;
			int j = high;

			while (i < j) {
				while (a[i] < pivot) {
					i++;
				}
				while (j > low && a[j] >= pivot) {
					j--;
				}
				if (i < j) {
					int temp = a[j];
					a[j] = a[i];
					a[i] = temp;
				}
			}
			a[high] = a[i];
			a[i] = pivot;

			quickSort2(a, low, i - 1);
			quickSort2(a, i + 1, high);
		}
	}

}
