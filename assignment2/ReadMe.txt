Eclipse IDE 2018-09 (4.9.0)

I've augmented the CLRS code for Partitioner and Quicksort to count comparisons

Average case analysis:
QuicksortMedian always outperforms Quicksort since the PartitionerMedian
prevents large bad splits of n-1 and 0 like in QuickSort's Partitioner.

This average case is on an unsorted array. The difference is observable and
repeatable.

Worst case analysis:
QuicksortMedian far outperforms Quicksort in the worst case. In this case, the
worst case analysis is performed on a pre-sorted array.

Sample runs:

INPUTS:
{ 0, 2, 4, 3, 1 }
{ 0, 1, 2, 3, 4 }

OUTPUTS:
Sorter QuicksortMedian sorted an unsorted array of size 5 in 6 comparisons
Sorter Quicksort sorted an unsorted array of size 5 in 7 comparisons
Sorter QuicksortMedian sorted a sorted array of size 5 in 6 comparisons
Sorter Quicksort sorted a sorted array of size 5 in 10 comparisons

INPUTS:
{ 9, 1, 13, 5, 8, 6, 0, 4, 3, 14, 2, 11, 12, 10, 7 }
{ 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14 }

OUTPUTS:
Sorter QuicksortMedian sorted an unsorted array of size 15 in 36 comparisons
Sorter Quicksort sorted an unsorted array of size 15 in 42 comparisons
Sorter QuicksortMedian sorted a sorted array of size 15 in 34 comparisons
Sorter Quicksort sorted a sorted array of size 15 in 105 comparisons