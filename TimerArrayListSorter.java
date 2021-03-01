package assign05;

import java.util.ArrayList;

/**
 * A timer file for timing ArrayListSorter.
 * 
 * @author Carter Edginton & Wenlin Li
 *
 */
public class TimerArrayListSorter {
	// private vars
	static int timesToLoop = 100;
	static int NStart = 10000;
	static int NIncrement = 10000;
	static int NStop = 100000;

	public static void main(String[] args) {

		System.out.println("Mergesort Threshold experiment");
		System.out.println("N;   0;   5;   10;   25;   50;");
		for (int N = NStart; N <= NStop; N += NIncrement) {
			ArrayList<Integer> list = ArrayListSorter.generatePermuted(N);
			System.out.println(N + "   " + mergeTimeThreshold(list, 0) + "   " + mergeTimeThreshold(list, 5) + "   "
					+ mergeTimeThreshold(list, 10) + "   " + mergeTimeThreshold(list, 25) + "   "
					+ mergeTimeThreshold(list, 50));
		}
		System.out.println();
		System.out.println("Quicksort Pivot experiment");
		System.out.println("N;   Random;   Middle;   Median of 3;");
		for (int N = NStart; N <= NStop; N += NIncrement) {
			ArrayList<Integer> list = ArrayListSorter.generatePermuted(N);
			System.out.println(N + "   " + quickTimePivot(list, 1) + "   " + quickTimePivot(list, 2) + "   "
					+ quickTimePivot(list, 3));
		}
		System.out.println();
		// TODO: Time the first two tests then figure out which is the best of each,
		// then plug those to these to figure out which one is faster than the other.
		// Use resulting values for result of the question in analysis.
//		System.out.println("merge;   quicksort;");
//		ArrayList<Integer> ascend = ArrayListSorter.generateAscending(NStop);
//		ArrayList<Integer> descend = ArrayListSorter.generateDescending(NStop);
//		ArrayList<Integer> permute = ArrayListSorter.generatePermuted(NStop);
//		System.out.println("Ascending case:");
//		System.out.println(mergeTimeThreshold(ascend, ) + "   "+ quickTimePivot(ascend, ));
//		System.out.println("Descending case:");
//		System.out.println(mergeTimeThreshold(descend, ) + "   "+ quickTimePivot(descend, ));
//		System.out.println("Permuted case:");
//		System.out.println(mergeTimeThreshold(permute, ) + "   "+ quickTimePivot(permute, ));
//		

	}

	/**
	 * helper method for finding the time it takes on average to sort a list using
	 * mergesort.
	 * 
	 * @param list
	 * @param thresholdSize
	 * @return time in nanoseconds on average to sort this list.
	 */
	private static long mergeTimeThreshold(ArrayList<Integer> list, int thresholdSize) {
		long totalTime;
		long startTime = System.nanoTime();
		ArrayListSorter.mergeInsertionThreshold = thresholdSize;
		// wait for a second
		while (System.nanoTime() - startTime < 1000000000) {
		} // EMPTY BLOCK
		startTime = System.nanoTime();
		// Gather time it takes to complete.
		for (int loop = 0; loop < timesToLoop; loop++) {
			ArrayList<Integer> toSort = new ArrayList<Integer>(list);
			ArrayListSorter.mergesort(toSort);
		}
		long midTime = System.nanoTime();
		// Gather bad time to subtract
		for (int loop = 0; loop < timesToLoop; loop++) {
			@SuppressWarnings("unused")
			ArrayList<Integer> toSort = new ArrayList<Integer>(list);
		}
		long stopTime = System.nanoTime();

		totalTime = ((midTime - startTime) - (stopTime - midTime)) / timesToLoop;
		return totalTime;
	}

	private static long quickTimePivot(ArrayList<Integer> list, int pivotMethod) {
		long totalTime;
		long startTime = System.nanoTime();
		ArrayListSorter.quickPivotType = pivotMethod;
		// wait for a second.
		while (System.nanoTime() - startTime < 1000000000) {
		} // EMPTY BLOCK
		startTime = System.nanoTime();
		// Gather time it takes to complete.
		for (int loop = 0; loop < timesToLoop; loop++) {
			ArrayList<Integer> toSort = new ArrayList<Integer>(list);
			ArrayListSorter.quicksort(toSort);
		}
		long midTime = System.nanoTime();
		// Gather bad time to subtract
		for (int loop = 0; loop < timesToLoop; loop++) {
			@SuppressWarnings("unused")
			ArrayList<Integer> toSort = new ArrayList<Integer>(list);
		}
		long stopTime = System.nanoTime();

		totalTime = ((midTime - startTime) - (stopTime - midTime)) / timesToLoop;
		return totalTime;
	}

}
