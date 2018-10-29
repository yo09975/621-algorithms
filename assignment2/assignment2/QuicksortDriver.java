package assignment2;

import clrs.Quicksort;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Test harness for checking correctness of the QuicksortMedian implementation
 * and for comparing its performance against CLRS Quicksort
 * 
 * @version 10282018
 */
public class QuicksortDriver {
	
	/**
	 * Generates an array integers of a provided length that is unlikely
	 * to be sorted
	 * @param size The size of the array to generate
	 * @return an randomized array of size @size 
	 */
	private static Comparable[] generate_random_array(int size) {
		ArrayList<Integer> list = new ArrayList<Integer>(size);
		
		for(int i = 0; i < size; i++) {
			list.add(i);
		}
		
		Collections.shuffle(list);
		
		// Hack? Not a java guru
		Comparable[] array = new Comparable[size];
		for(int i = 0; i < size; i++) {
			array[i] = list.get(i);
		}
			
		return array;
	}
	
	/**
	 * Generates a sorted array of integers
	 * @param size The size of the array to generate
	 * @return an array containing integers in increasing (sorted) order
	 */
	private static Comparable[] generate_sorted_array(int size) {
		Comparable[] array = new Comparable[size];
		for (int i = 0; i < size; i++) {
			array[i] = i;
		}
		return array;
	}
	
	/**
	 * A simple test method to validate that an array is sorted
	 * @param array
	 * @return true if array was sorted, false otherwise
	 */
	private static boolean isSorted(Comparable[] array) {
		for(int i = 0; i < array.length-1; i++) {
			if (array[i].compareTo(array[i+1]) > 0) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * A method to test the efficiency of a QuickSort implementation
	 * Makes a copy of the array passed in to enable reuse of the original array
	 * in subsequent calls.
	 * @param array An array of Comparables to sort
	 * @param s A QuickSort object
	 * @param sorted A boolean indicating whether the array is pre-sorted
	 */
	private static void test_sorter(Comparable[] array, Quicksort s, boolean sorted) {
		// Make a copy to prevent changing the original array
		Comparable[] array_copy = array.clone();
		
		s.sort(array_copy);
		int size = array_copy.length;
		if (! isSorted(array_copy)) {
			System.out.println("ERROR: failed to sort array of size " + size);
			System.exit(1);
		}
		String sorter = (s instanceof QuicksortMedian ? "QuicksortMedian" : "Quicksort");
		System.out.print("Sorter " + sorter + " sorted " + (sorted ? "a sorted " : "an unsorted ") + "array of size ");
		System.out.println(size + " in " + s.getComparisons() + " comparisons");
	}
	
	/**
	 * Method for cleanly printing the arrays we were sorting
	 * @param array
	 */
	private static void print_array(Comparable[] array) {
		System.out.print("{");
		for(int i = 0; i < array.length; i++) {
			System.out.print(" " + array[i] + (i == array.length - 1 ? " " : ","));
		}
		System.out.println("}");
	}
	
	public static void main(String[] args) {
		
		// Compare performance of QuicksortMedian and Quicksort
		// with arrays of size 5
		Comparable[] random_array = generate_random_array(5);
		Comparable[] sorted_array = generate_sorted_array(5);
		
		print_array(random_array);
		print_array(sorted_array);
		
		test_sorter(random_array, new QuicksortMedian(), false);
		test_sorter(random_array, new Quicksort(), false);
		
		test_sorter(sorted_array, new QuicksortMedian(), true);
		test_sorter(sorted_array, new Quicksort(), true);
		
		// Compare performance of QuicksortMedian and Quicksort
		// with arrays of size 15
		random_array = generate_random_array(15);
		sorted_array = generate_sorted_array(15);
		
		print_array(random_array);
		print_array(sorted_array);
		
		test_sorter(random_array, new QuicksortMedian(), false);
		test_sorter(random_array, new Quicksort(), false);
		
		test_sorter(sorted_array, new QuicksortMedian(), true);
		test_sorter(sorted_array, new Quicksort(), true);
		}
}
