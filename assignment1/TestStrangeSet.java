package assignment_1;

public class TestStrangeSet {

	/**
	 * Print running time in nanoseconds for insert and search.
	 * Note that running time of insert is amortized.
	 * @param n Number of elements to insert into the StrangeSet
	 */
	public static void test(int n) {
		long start, end;
		StrangeSet s = new StrangeSet(1);
		System.out.println("Testing with a set of size " + n);
		start = System.nanoTime();
		// Test for a StrangeSet with n elements
		for(int value = 0; value < n; value++) {
			s.insert(value);
		}
		end = System.nanoTime();
		System.out.println("Amortized running time of inserting " + n + " elements (nanoseconds):" + new Long((end-start)/n));
		
		start = System.nanoTime();
		
		// Search for a non-existent element (the worst case search)
		s.search(-1);
		
		end = System.nanoTime();
		System.out.println("Search time in a " + n + " element data structure (nanoseconds): " + new Long(end - start));
		System.out.println();
	}
	public static void main(String[] args) {
		// Test both insert and search operations
		test(4);
		test(16);
		test(32);
		test(64);
		test(128);
		test(256);
		
		// Show that searching works
		// Note that the values inserted are:
		// {0, 1, 2, 3, 4, 5, 6, 7, 8, 9}
		StrangeSet searchtest = new StrangeSet(4);
		for(int value = 0; value < 10; value++) {
			searchtest.insert(value);
		}
		
		System.out.println("Was value of 5 found? " + searchtest.search(5));
		System.out.println("Was value of -1 found? " + searchtest.search(-1));
		searchtest.print_small();
		searchtest.print_large();
	}

}
