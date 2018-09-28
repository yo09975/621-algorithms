package assignment_1;

public class StrangeSet {
	private int[] big_array;
	private int[] small_array;
	private int small_elements = 0;
	private int big_elements = 0;

	StrangeSet(int size){
		small_array = new int[size];
		small_elements = 0;
		big_array = new int[size*size];
		big_elements = 0;
		System.out.println("Small array length: " + small_array.length);
		System.out.println("Big array length: " + big_array.length);
	}


	/**
	 * Inserts an integer into the data structure
	 * @param val An integer value to insert into the data structure
	 */
	public void insert(int val) {

		//this is a SET so no duplicates
		// TODO: Check value in large array too
		// TODO: (or we can just assume no duplicates per the professor)
		for(int index = 0; index < small_elements; index++) {
			if (small_array[index] == val) {
				System.out.println("Duplicate element (" + val + "), skipping");
				return;
			}
		}

		System.out.println("Inserting element (" + val + ") into small array");
		small_array[small_elements] = val;
		small_elements++;

		small_array = sort_array(small_array);

		if (small_array.length == small_elements) {
			System.out.println("Small array is now full, merging...");
			merge();
		}
		return;
	}

	/**
	 * Performs an insertion sort on an array of integers
	 * 
	 * @param array An array of integers to sort
	 * @return sorted array containing elements in the array passed in
	 */
	private int[] sort_array(int[] array) {
		int key;
		int left;
		for (int right = 1; right < small_elements; right++) {
			key = small_array[right];
			left = right - 1;
			while (left >= 0 && small_array[left] > key) {
				small_array[right] = small_array[left];
				left--;
			}
			small_array[left + 1] = key;
		}
		return array;
	}

	/**
	 * Merges the data structure's smaller array into the larger array
	 */
	private void merge() {
		if (big_array.length - big_elements >= small_array.length) {
			//System.out.println("Big array had room");
			for(int index = 0; index < small_array.length; index++) {
				big_array[big_elements + index] = small_array[index];

			}
			big_elements += small_elements;
			small_elements = 0;
		} else {
			//System.out.println("Big array did not have room");
			reallocate();
			merge();
			return;
		}
		big_array = sort_array(big_array);

	}

	/** 
	 * Enlarges both arrays and copies the old data when the old arrays were
	 * too small to hold the data
	 * 
	 */
	private void reallocate() {
		int new_small_size = small_array.length + 1;
		int[] new_small = new int[new_small_size];
		int[] new_big = new int[new_small_size * new_small_size];

		for(int index = 0; index < big_elements; index++) {
			new_big[index] = big_array[index];
		}

		for(int index = 0; index < small_elements; index++) {
			new_small[index] = small_array[index];
		}
		big_array = new_big;
		small_array = new_small;
		//System.out.println("Big array now has length " + big_array.length);
		//System.out.println("Small array now has length " + small_array.length);
	}

	/**
	 * Performs a binary search for an integer value on the data structure
	 * TODO: Not implemented 
	 * @param val
	 * @return true if integer val was found in the data structure, false
	 * otherwise
	 */
	public boolean search(int val) {
		return binary_search(val, small_array) || binary_search(val, big_array);
	}

	/**
	 * Performs binary search on an array of integers
	 * @param val The value to search for
	 * @param array The array to search through
	 * @return true if val found in array, false otherwise
	 */
	private boolean binary_search(int val, int[] array) {
		int left = 0;
		int right = array.length - 1;
		int middle;
		while (left <= right) {
			middle = (int) (left + right) / 2;
			if (array[middle] < val) {
				left = middle + 1;
			} else if (array[middle] > val) {
				right = middle - 1;
			} else {
				return true;
			}
		}
		return false;
	}
	/**
	 * Prints the integer elements within the smaller array
	 */
	public void printSmall(){
		System.out.println("Printing small array (" + small_elements + ")");
		for(int index = 0; index < small_elements; index++) {
			System.out.println(small_array[index] + " ");
		}
	}
	
	/**
	 * Prints the integer elements within the larger array 
	 */
	public void printLarge() {
		System.out.println("Printing big array (" + big_elements + ")");
		for(int index = 0; index < big_elements; index++) {
			System.out.println(big_array[index] + " ");
		}
	}
}
