package assignment2;

import clrs.Quicksort;

/**
 * An adaptation of the CLRS provided Quicksort algorithm that instead uses
 * Median of Three partitioning (when possible).
 * 
 * @version 10282018
 *
 */
public class QuicksortMedian extends Quicksort {

	@Override
    /**
     * Sorts an array of <code>Comparable</code> objects.
     * This is a modified version of CLRS Quicksort that uses a custom
     * Partitioner object that implements Median of Three partitioning
     * 
     * @param array The array of <code>Comparable</code> objects to be
     * sorted.
     */
    public void sort(Comparable[] array)
    {
		part = new PartitionerMedian(); // use a Median of Three partitioner
		quicksort(array, 0, array.length - 1); // sort using it
    }

}
