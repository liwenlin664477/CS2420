package assign05;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * A class for sorting ArrayLists using mergesort and quicksort, while also
 * having methods for creating some basic ArrayLists.
 * 
 * @author Carter Edginton & Wenlin Li
 *
 */
public class ArrayListSorter {

	protected static int mergeInsertionThreshold = 0;

	/*
	 * 1 for random, 2 for middle, 3 for median of 3 to change this in another class
	 * in the package, ArrayListSorter.quickPivotType = (Value);
	 */
	protected static int quickPivotType = 2;

	/**
	 * This method performs a merge sort on the generic ArrayList given as input.
	 * This is the Driver method for the recurison.
	 * 
	 * @param <T>
	 */
	public static <T extends Comparable<? super T>> void mergesort(ArrayList<T> list) {
		ArrayList<T> tempList = new ArrayList<T>(list.size());
		for (int repeat = 0; repeat < list.size(); repeat++) {
			tempList.add(null);
		}
		int mid = (0 + list.size()-1) / 2;
		mergesortRecursion(list, tempList, 0, mid);
		mergesortRecursion(list, tempList, mid + 1, list.size()-1);
		merge(list, tempList, 0, mid + 1, list.size()-1);
	}

	/**
	 * This method performs a quicksort on the generic ArrayList given as input.
	 * Check the quickPivotType value declaration at the top of the class for
	 * information about how to pick which type of
	 * 
	 * @param <T>
	 */
	public static <T extends Comparable<? super T>> void quicksort(ArrayList<T> list) {
		Random rng = new Random();
		quickRecursion(list, 0, list.size() - 1, rng);
	}

	/**
	 * This method generates and returns an ArrayList of integers 1 to size in
	 * ascending order.
	 * 
	 * @param size
	 * @return
	 */
	public static ArrayList<Integer> generateAscending(int size) {
		ArrayList<Integer> ascending = new ArrayList<Integer>();
		for (int idx = 0; idx < size; idx++) {
			ascending.add(idx + 1);
		}
		return ascending;

	}

	/**
	 * This method generates and returns an ArrayList of integers 1 to size in
	 * permuted order (i,e., randomly ordered).
	 */
	public static ArrayList<Integer> generatePermuted(int size) {
		ArrayList<Integer> permuted = generateAscending(size);
		Collections.shuffle(permuted);
		return permuted;
	}

	/**
	 * This method generates and returns an ArrayList of integers 1 to size in
	 * descending order. This has similar code to generateAscending, but to save on
	 * a bit of time (for the user), it does NOT call ascending and reverse, but
	 * rather just generates a descending.
	 * 
	 * @param size
	 * @return
	 */
	public static ArrayList<Integer> generateDescending(int size) {
		ArrayList<Integer> descending = new ArrayList<Integer>();
		for (int idx = 0; idx < size; idx++) {
			descending.add(size - idx);
		}
		return descending;
	}

	// Helper methods

	/**
	 * private helper method for mergesort. by the time this is called, it will make
	 * sure
	 * 
	 * @param <T>
	 * @param list
	 * @param left  INDEX OF left element
	 * @param right INDEX OF right elemnt. call size()-1.
	 */
	private static <T extends Comparable<? super T>> void mergesortRecursion(ArrayList<T> list, ArrayList<T> temp,
			int left, int right) {
		int mid = (left + right) / 2;
		if (right - left < mergeInsertionThreshold) {
			insertionSort(list, left, right);
		} else if (left < right) {
			mergesortRecursion(list, temp, left, mid);
			mergesortRecursion(list, temp, mid + 1, right);
			merge(list, temp, left, mid + 1, right);
		}
	}

	/**
	 * private helper method for mergesort, when the size that is being sorted is
	 * small enough.
	 * 
	 * @param <T>
	 * 
	 */
	private static <T extends Comparable<? super T>> void insertionSort(ArrayList<T> list, int left, int right) {
		for (int loop = left + 1; loop < right + 1; loop++) {
			T val = list.get(loop);
			int inner;
			for (inner = loop - 1; inner >= left && list.get(inner).compareTo(val) > 0; inner--) {
				list.set(inner + 1, list.get(inner));
			}
			list.set(inner + 1, val);
		}
	}

	/**
	 * private helper method for mergesort.
	 * 
	 * @param <T>
	 * @param list
	 * @param left
	 * @param middle
	 * @param right
	 */
	private static <T extends Comparable<? super T>> void merge(ArrayList<T> list, ArrayList<T> temp, int left,
			int middle, int right) {
		int leftSpot = left;
		int rightSpot = middle;
		int endSpot = right;
		int currentIdx = left;

		while (leftSpot <= middle - 1 && rightSpot <= endSpot) {
			int comparison = list.get(leftSpot).compareTo(list.get(rightSpot));
			if (comparison == 0) {
				temp.set(currentIdx++, list.get(leftSpot++));
				temp.set(currentIdx++, list.get(rightSpot++));
			} else if (comparison < 0) {
				temp.set(currentIdx++, list.get(leftSpot++));
			} else {
				temp.set(currentIdx++, list.get(rightSpot++));
			}
		}
		// get any leftovers
		while (leftSpot <= middle - 1) {
			temp.set(currentIdx++, list.get(leftSpot++));
		}
		while (rightSpot <= endSpot) {
			temp.set(currentIdx++, list.get(rightSpot++));
		}
		// by this point it should be sorted.
		for (currentIdx = left; currentIdx <= (endSpot); currentIdx++) {
			list.set(currentIdx, temp.get(currentIdx));
		}

	}

	/**
	 * helper method for quicksort, implementing all the necessary values.
	 * 
	 * @param <T>
	 * @param list
	 * @param left
	 * @param right
	 */
	private static <T extends Comparable<? super T>> void quickRecursion(ArrayList<T> list, int left, int right,
			Random rng) {
		if (left < right) {
			int pivotPoint;
			if (quickPivotType == 1) {// Random
				pivotPoint = left + rng.nextInt((right-left) + 1); //nextInt arg is exclusive, add 1. 
			} else if (quickPivotType == 2) {// Middle
				pivotPoint = (left + right) / 2;
			} else { // quickPivotType will default to this if 3 or some other int.
				int mid = (left + right) / 2;
				// basically a stooge sort, but sorts the indexes and finds the median
				if (list.get(left).compareTo(list.get(mid)) > 0)
					swap(mid, left, list);
				if (list.get(mid).compareTo(list.get(right)) > 0)
					swap(mid, right, list);
				if (list.get(left).compareTo(list.get(mid)) > 0)
					swap(left, mid, list);
				// median element will now be at mid.
				pivotPoint = mid;
			}
			int pivot = partition(list, left, pivotPoint, right);
			quickRecursion(list, left, pivot - 1, rng);
			quickRecursion(list, pivot + 1, right, rng);
		}
	}

	/**
	 * The work portion of quicksort. does all (almost all, if you have median of 3
	 * enabled) swapping.
	 * 
	 * @param <T>
	 * @param list
	 * @param left
	 * @param pivot
	 * @param right
	 * @return
	 */
	private static <T extends Comparable<? super T>> int partition(ArrayList<T> list, int left, int pivot, int right) {
		int endPoint = right;
		if (quickPivotType == 1 || quickPivotType == 2) {
			swap(pivot, right, list);
		} else {
			endPoint--;
			swap(pivot, right - 1, list);
		}
		int memory = left - 1;
		T pivotValue = list.get(endPoint); // from pivot to endPoint
		for (int sweep = left; sweep < endPoint; sweep++) {
			if (list.get(sweep).compareTo(pivotValue) <= 0) {
				swap(++memory, sweep, list);
			}
		}
		swap(memory + 1, endPoint, list);
		return memory + 1;
	}

	/**
	 * private helper method to make swapping and partition code more readable.
	 * TAKES IN INDEXES, NOT VALUES.
	 * 
	 * @param <T>
	 * @param list
	 * @param idx1
	 * @param idx2
	 */
	private static <T extends Comparable<? super T>> void swap(int idx1, int idx2, ArrayList<T> list) {
		T stored = list.get(idx1);
		list.set(idx1, list.get(idx2));
		list.set(idx2, stored);
	}

}