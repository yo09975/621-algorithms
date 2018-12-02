package assignment3;

/**
 * Test driver for SignalUntangler
 * 
 * @version 11272018
 *
 */
public class SignalUntanglerDriver {
	/**
	 * Creates and returns a string with length @length consisting of 1s and 0s
	 * Note that there is no guarantee that the signal generated will untangle
	 * neatly
	 * @param length Amount of 'bits' the string contains
	 */
	public static String generate_signal(int length) {
		StringBuilder sb = new StringBuilder();

		// Use a stringbuilder and random functions to fill a string with 'bits'
		for (int i = 0; i < length; i++) {sb.append(Math.round(Math.random()));}

		return sb.toString();
	}

	public static void main(String args[]) {
		final int SIGNAL_LENGTH = 9;
		
		
		final String x = "101";
		final String y = "000";
		
		// You can use these to test a successful/unsuccessful interleaving
		final String bad_signal = "1000101010010100";
		final String good_signal = "100010101";
		
		String signal = generate_signal(SIGNAL_LENGTH);

		SignalUntangler su = new SignalUntangler();
		while(su.untangle(x, y, signal) == false) {
			System.out.println(signal);
			signal = generate_signal(SIGNAL_LENGTH);
		}

		System.out.println("Signal untangled successfully");
		su.print_untangled();

	}
}
