package assignment3;

import java.lang.StringBuilder;

/**
 * Takes interleaved "signals" represented by sequences of the characters '0'
 * and '1', and determines whether or not the sequence unravels into simple
 * repetitions of two sub-signals x and y.
 * 
 * @version 11272018
 * 
 */
public class SignalUntangler {

	private boolean untangled_x[], untangled_y[];
	private boolean success = false;
	private int comparisons = 0;
	
	public boolean untangle(String x, String y, String signal) {
		comparisons = 0;
		final int SIGNAL_LENGTH = signal.length();
		
		/*
		 * 	Arrays of booleans that represent a mapping of bits to sub-signals
		 *  A value of true in the x array means the bit at that signal index
		 * was mapped to the x sub-signal.	
		 */
		untangled_x = new boolean[SIGNAL_LENGTH];
		untangled_y = new boolean[SIGNAL_LENGTH];
		
		// Holds the indices of detected x signal bits
		int found[] = new int[x.length()];

		for (int i = 0; i < x.length(); i++) { found[i] = -1; }
		int count = 0;

		// Initialize array to keep track of bits unraveled to a signal
		for (int i = 0; i < SIGNAL_LENGTH; i++) { untangled_x[i] = false; }

		// Check for x signals
		for(int i = 0; i < SIGNAL_LENGTH; i++) {
			comparisons++;
			// Found a bit that matches the signal
			if (signal.charAt(i) == x.charAt(count)) 
			{
				// store indexes of signal bits we find
				found[count] = i;
				count++;

				// Reset count since we finished finding one of the signals
				// and add the indices to the untangled_x array
				if (count == x.length()) { 
					for(int j = 0; j < x.length(); j++)
						untangled_x[found[j]] = true;
					count = 0; 
				}
			}
		}
		
		// Recreate the index array for the y signal
		found = new int[y.length()];
		count = 0;
		
		// Check for y signals
		for(int i = 0; i < SIGNAL_LENGTH; i++) {
			comparisons++;
			// Found a bit that matches the signal
			if (!untangled_x[i] && (signal.charAt(i) == y.charAt(count)))
			{

				// store indexes of signal bits we find
				found[count] = i;
				count++;

				// Reset count since we finished finding one of the signals
				// and add the indices to the unraveled array
				if (count == y.length()) { 
					for(int j = 0; j < y.length(); j++)
						untangled_y[found[j]] = true;
					count = 0; 
				}
			}
		}

		// Check for a clean interleaving of x and y
		for(int i = 0; i < SIGNAL_LENGTH; i++) {
			comparisons++;
			if (!(untangled_x[i] == true || untangled_y[i] == true)) {
				success=false;
				return success;
			}
		}
		success=true;
		return success;
	}

	/**
	 * Prints the indices of the untangled signal bits for x and y
	 * NOTE: The problem description uses 1-indexed array indices and this
	 * method follows that convention
	 */
	public void print_untangled() {
		if(success) {
			final int SIGNAL_LENGTH = this.untangled_x.length;
			System.out.println("Comparisons used to unravel: " + comparisons);
			System.out.println("X signal indices:");
			for(int i = 0; i < SIGNAL_LENGTH; i++) {
				if(untangled_x[i] == true) {
					System.out.print(i+1 + " ");
				}
			}
			System.out.println();
			System.out.println("Y signal indices:");
			for(int i = 0; i < SIGNAL_LENGTH; i++) {
				if(untangled_y[i] == true) {
					System.out.print(i+1 + " ");
				}
			}
			System.out.println();
		}
	}
}
