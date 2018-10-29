package assignment2;

import clrs.Partitioner;
import clrs.InsertionSort;
/**
 * Class for Partitioning arrays of Comparable objects using
 * Median of Three partitioning for subproblems with at least
 * 3 Comparable objects.
 * 
 * If the subproblem has less that 3 objects, this implementation will
 * revert to the CLRS Quicksort partitioning which chooses the rightmost
 * element as the partition
 * 
 * @version 10282018
 */
public class PartitionerMedian extends Partitioner {
	
	
	@Override
	/**
	 * This is the partition function that utilizes Median of Three partitioning
	 * when the subproblem size is at least 3 elements. For subproblems that
	 * are less than three elements, this implementation will revert to CLRS
	 * partitioning which chooses the rightmost element as the partition.
	 * 
	 * The invariant maintenance code is from the CLRS Quicksort partition
	 */
	public int partition(Comparable[] array, int p, int r)
    {
	// x is the pivot element
    Comparable x = null;
    
    int pivot_index = median_of_three(array, p, r);
    x = array[pivot_index];
    exchange(array, pivot_index, r);
    		
	int i = p - 1;
	
	// Maintain the following invariant:
	//   array[p..i] <= x,
	//   array[i+1..j-1] > x, and
	//   array[r] = x.
	for (int j = p; j < r; j++) {
		comparisons++;
	    if (array[j].compareTo(x) <= 0) {
		i++;
		exchange(array, i, j);
	    }
	}

	// Put the pivot value in its correct place and return that
	// index.
	exchange(array, i+1, r);
	return i + 1;
    }
	
    /**
     * Calculates the median of three (potentially unsorted) elements in an array
     * 
     * @param i leftmost index
     * @param j rightmost index
     */
    public int median_of_three(Comparable[] array, int i, int j)
    {
    	// If the subproblem size is too small for Mo3, just return the
    	// rightmost element of the subarray
    	if (j - i < 2)
    	{
    		return j;
    	}
    	
    	int k = Math.floorDiv(i + j, 2);
    	
    	Comparable[] sorted = {array[i], array[j], array[k]};
    	
    	// Use InsertionSort for sorting the three elements
    	new InsertionSort().sort(sorted);
       	
    	// Hacky
    	if (sorted[1] == array[i]) {
    		return i;
    	}else if (sorted[1] == array[j]) {
    		return j;
    	}else {
    		return k;
    	}    	
    }
}
