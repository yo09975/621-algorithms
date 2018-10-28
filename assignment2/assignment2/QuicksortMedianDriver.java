package assignment2;

import clrs.Quicksort;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Test harness for checking correctness of the QuicksortMedian implementation
 * 
 * @version 10282018
 */
public class QuicksortMedianDriver {
	private static Comparable[] generate_random_array(int size) {
		ArrayList<Integer> list = new ArrayList<Integer>(size);
		
		for(int i = 0; i < size; i++) {
			list.add(i);
		}
		
		Collections.shuffle(list);
		
		Comparable[] array = new Comparable[size];
		for(int i = 0; i < size; i++) {
			array[i] = list.get(i);
		}
			
		return array;
	}
	
	private static boolean isSorted(Comparable[] array) {
		for(int i = 0; i < array.length-1; i++) {
			if (array[i].compareTo(array[i+1]) > 0) {
				return false;
			}
		}
		return true;
	}
	private static void test_mediansorter(int max_size_array) {
		Comparable[] array = null;
		Quicksort mediansorter = new QuicksortMedian();

		for(int size = 1; size <= max_size_array; size++) {
			array = generate_random_array(size);
			mediansorter.sort(array);
			if (! isSorted(array)) {
				System.out.println("ERROR: failed to sort array of size " + size);
				System.exit(1);
			}
			System.out.println("Sorted an array of size " + size);
		}
	}
	public static void main(String[] args) {
		test_mediansorter(1000);
	}
}
