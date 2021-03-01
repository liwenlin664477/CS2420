package assign05;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/** JUnit Tester file for ArrayListSorter. Should be in same package as ArrayListSorter.
 * 
 * @author Carter Edginton & Wenlin Li
 *
 */
class TesterArrayListSorter {

	//set up private variables
	ArrayList<Integer> shortList;
	ArrayList<Integer> invShortList;
	ArrayList<Integer> longList;
	ArrayList<Integer> invLongList;
	
	@BeforeEach
	void setUp() throws Exception {
		//instantiate private variables
		shortList = new ArrayList<Integer>();
		shortList.add(1);
		shortList.add(2);
		shortList.add(3);
		invShortList = new ArrayList<Integer>();
		invShortList.add(3);
		invShortList.add(2);
		invShortList.add(1);
		
		longList = new ArrayList<Integer>();
		longList.add(1);
		longList.add(2);
		longList.add(3);
		longList.add(4);
		longList.add(5);
		longList.add(6);
		longList.add(7);
		longList.add(8);
		invLongList = new ArrayList<Integer>();
		invLongList.add(8);
		invLongList.add(7);
		invLongList.add(6);
		invLongList.add(5);
		invLongList.add(4);
		invLongList.add(3);
		invLongList.add(2);
		invLongList.add(1);

		ArrayListSorter.quickPivotType = 2;
		
	}

	@Test
	void generateDescendingShort() {
		assertTrue(invShortList.equals(ArrayListSorter.generateDescending(3)));
	}
	
	@Test
	void generateAscendingShort() {
		assertTrue(shortList.equals(ArrayListSorter.generateAscending(3)));
	}
	
	@Test
	void generatePermutedShort() {
		//NOTE: MAY FAIL ONCE IN A WHILE. its just random chance, but unlikely.
		shortList.add(4);
		shortList.add(5);
		shortList.add(6);
		shortList.add(7);
		shortList.add(8);
		shortList.add(9);
		shortList.add(10);
		assertFalse(shortList.equals(ArrayListSorter.generatePermuted(10)));
	}
	
	@Test
	void mergeSortWorksShort() {
		//NOTE: will likely be using just insertion sort, so there is also a test with a longer list
		//so that it does not fit in the threshold first time and actually uses mergeRecursion() and merge(). 
		ArrayList<Integer> newList = new ArrayList<Integer>(invShortList);
		ArrayListSorter.mergesort(newList);
		assertTrue(shortList.equals(newList));
	}
	
	@Test
	void mergeSortWorksLong() {
		//written as threshold was 5. if it is now larger and you want to test, be sure to change the ints in the
		//generators.
		ArrayList<Integer> toBeSorted = ArrayListSorter.generateDescending(10);
		ArrayList<Integer> reference = ArrayListSorter.generateAscending(10);
		ArrayListSorter.mergesort(toBeSorted);
		 
		assertTrue(reference.equals(toBeSorted));
	}
	
	@Test
	void mergeSortNoModify() {
		//written as threshold was 5. if it is now larger and you want to test, be sure to change the ints in the
		//generators.
		ArrayList<Integer> toBeSorted = ArrayListSorter.generateAscending(10);
		ArrayList<Integer> reference = ArrayListSorter.generateAscending(10);
		ArrayListSorter.mergesort(toBeSorted);
		 
		assertTrue(reference.equals(toBeSorted));
	}
	
	@Test // all items in arraylist are same value. 
	void mergeSortDuplicates() {
		ArrayList<Integer> duplicates = new ArrayList<Integer>();
		ArrayList<Integer> reference = new ArrayList<Integer>();

		for (int n = 0; n < 20; n++) {
			duplicates.add(1);
			reference.add(1);
		}
		ArrayListSorter.mergesort(duplicates);
		assertTrue(reference.equals(duplicates));
	}
	
	@Test
	void quickSortWorksShort() {
		ArrayList<Integer> invShort = new ArrayList<Integer>(invShortList);
		ArrayListSorter.quicksort(invShort);
		assertTrue(shortList.equals(invShort));
	}
	
	@Test
	void quickSortWorksLong() {
		ArrayList<Integer> invLong = new ArrayList<Integer>(invLongList);
		ArrayListSorter.quicksort(invLong);
		assertTrue(longList.equals(invLong));
	}
	
	@Test
	void quickSortWorksRandom() {
		ArrayList<Integer> invLong = new ArrayList<Integer>(invLongList);
		ArrayListSorter.quickPivotType = 1; //check this variables declaration in sorter. Random.
		ArrayListSorter.quicksort(invLong);
		assertTrue(longList.equals(invLong));
	}
	
	@Test
	void quickSortMedianOfThree() {
		ArrayList<Integer> invLong = new ArrayList<Integer>(invLongList);
		ArrayListSorter.quickPivotType = 3; //check this variables declaration in sorter. Median of 3.
		ArrayListSorter.quicksort(invLong);
		assertTrue(longList.equals(invLong));
	}
	
	@Test
	void mergeIsGeneric() {
		ArrayList<String> reference = new ArrayList<String>();
		reference.add("a");
		reference.add("b");
		reference.add("c");
		ArrayList<String> toBeSorted = new ArrayList<String>();
		toBeSorted.add("c");
		toBeSorted.add("b");
		toBeSorted.add("a");
		ArrayListSorter.mergesort(toBeSorted);
		assertTrue(reference.equals(toBeSorted));		
	}
	@Test
	void quickIsGeneric() {
		ArrayList<String> reference = new ArrayList<String>();
		reference.add("a");
		reference.add("b");
		reference.add("c");
		ArrayList<String> toBeSorted = new ArrayList<String>();
		toBeSorted.add("c");
		toBeSorted.add("b");
		toBeSorted.add("a");
		ArrayListSorter.quicksort(toBeSorted);
		assertTrue(reference.equals(toBeSorted));
	}

}