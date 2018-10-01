package assignment_1;

/**
 * Represents a dataset that consists of two arrays, one of length k and one
 * of length sqrt(k). This StrangeSet data structure fills the smaller array
 * using insertion sort, merging the two arrays when the smaller array is
 * filled. Merging is also performed via insertion sort. The data structure
 * will reallocate when there are no more slots left in either array.
 * @version 10012018
 *
 */
public class StrangeSet {
	private int[] big_array;
	private int[] small_array;
	private int small_elements = 0;
	private int big_elements = 0;

	/**
	 * Constructor for a StrangeSet data structure
	 * @param size The size of the small array in the data structure
	 */
	StrangeSet(int size){
		small_array = new int[size];
		small_elements = 0;
		big_array = new int[size*size];
		big_elements = 0;
	}


	/**
	 * Inserts an integer into the data structure
	 * @param val An integer value to insert into the data structure
	 */
	public void insert(int val) {

		//this is a SET so no duplicates
		for(int index = 0; index < small_elements; index++) {
			if (search(val)) {
				System.out.println("Duplicate element (" + val + "), skipping");
				return;
			}
		}

		//System.out.println("Inserting element (" + val + ") into small array");
		small_array[small_elements] = val;
		small_elements++;

		small_array = sort_array(small_array);

		if (small_array.length == small_elements) {
			//System.out.println("Small array is now full, merging...");
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
	 * If the larger array doesn't have room, merge() will reallocate
	 * both arrays.
	 */
	private void merge() {
		if (big_array.length - big_elements >= small_array.length) {
			for(int index = 0; index < small_array.length; index++) {
				big_array[big_elements + index] = small_array[index];

			}
			big_elements += small_elements;
			small_elements = 0;
		} else {
			reallocate();
			merge();
			return;
		}
		big_array = sort_array(big_array);

	}

	/** 
	 * Enlarges both arrays and copies the old data when the old arrays were
	 * too small to hold the data. The new size of the smaller array is
	 * increased by 1. The larger array is the square of the new small
	 * array size. 
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

	}

	/**
	 * Performs a binary search for an integer value on the data structure
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
	public boolean binary_search(int val, int[] array) {
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
	public void print_small(){
		System.out.println("Printing small array (" + small_elements + ")");
		for(int index = 0; index < small_elements; index++) {
			System.out.println(small_array[index] + " ");
		}
	}
	
	/**
	 * Prints the integer elements within the larger array 
	 */
	public void print_large() {
		System.out.println("Printing big array (" + big_elements + ")");
		for(int index = 0; index < big_elements; index++) {
			System.out.println(big_array[index] + " ");
		}
	}
}
