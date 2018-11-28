package assignment3;

public class signal_interleaving {
	public static void main(String args[]) {
		final int SIGNAL_LENGTH = 3;
		String x = "101";
		String y = "000";
		String s = "1000101010010100";
		boolean untied[] = new boolean[s.length()];
		boolean untied_y[] = new boolean[s.length()];

		int found[] = {-1, -1, -1};
		int count = 0;
		
		// Initialize array to keep track of bits unraveled to a signal
		for (int i = 0; i < s.length(); i++) { untied[i] = false; }
		
		// Check for x signals
		for(int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == x.charAt(count)) // Found a bit that matches the signal
			{
				// store indexes of signal bits we find
				found[count] = i;
				count++;
				
				// Reset count since we finished finding one of the signals
				// and add the indices to the unraveled array
				if (count == x.length()) { 
					for(int j = 0; j < SIGNAL_LENGTH; j++)
						untied[found[j]] = true;
					count = 0; 
				}
			}
		}
		
		// Check for y signals
		for(int i = 0; i < s.length(); i++) {
			if (!untied[i] && (s.charAt(i) == y.charAt(count))) // Found a bit that matches the signal
			{
	
				// store indexes of signal bits we find
				found[count] = i;
				count++;
				
				// Reset count since we finished finding one of the signals
				// and add the indices to the unraveled array
				if (count == y.length()) { 
					for(int j = 0; j < SIGNAL_LENGTH; j++)
						untied_y[found[j]] = true;
					count = 0; 
				}
			}
		}
		
		// DEBUG: testing array of found signals
		System.out.println("X signal indices:");
		for(int i = 0; i < s.length(); i++) {
			if(untied[i] == true) {
				System.out.println(i+1);
			}
		}
		System.out.println("Y signal indices:");
		for(int i = 0; i < s.length(); i++) {
			if(untied_y[i] == true) {
				System.out.println(i+1);
			}
		}
	}

}
