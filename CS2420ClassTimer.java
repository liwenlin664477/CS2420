package assign02;

import java.util.Random;

public class CS2420ClassTimer {
	
	public static void main(String[] args) {
	Random randomNumberGenerator = new Random();
	
	// Do 10000 lookups and use the average running time
	int timesToLoop = 10000;

	// For each problem size n . . .
	for(int n = 10000; n <= 100000; n += 10000) {
		
		// Generate an array of n uNIDs
		int[] uNID = new int[n];
		for(int i = 0; i < n; i++) 
			uNID[i] = 1000000 + i;
		
		// Randomly shuffle the array
		for(int i = 0; i < n; i++) {
			int randomIndex = randomNumberGenerator.nextInt(n);
			int temp = uNID[i];
			uNID[i] = uNID[randomIndex];
			uNID[randomIndex] = temp;
		}

		// Generate a new "random" CS2420Class of n students
		// NOTE: student names and contact information are meaningless and unimportant
		CS2420Class randomClass = new CS2420Class();
		for(int i = 0; i < n; i++) 
			randomClass.addStudent(new CS2420Student("FirstName", "LastName", uNID[i],
					new EmailAddress("UserName", "DomainName")));

		long startTime, midpointTime, stopTime;

		// First, spin computing stuff until one second has gone by
		// This allows this thread to stabilize
		startTime = System.nanoTime();
		while(System.nanoTime() - startTime < 1000000000) { // empty block
		}

		// Now, run the test
		startTime = System.nanoTime();

		for(int i = 0; i < timesToLoop; i++)
			// Lookup a student with a uNID that does not exist,
			// so method will not return early
			randomClass.lookup(1);

		midpointTime = System.nanoTime();

		// Run a loop to capture the cost of running the "timesToLoop" loop
		for(int i = 0; i < timesToLoop; i++) { // empty block
		}

		stopTime = System.nanoTime();

		// Compute the time, subtract the cost of running the loop
		// from the cost of running the loop and doing the lookups
		// Average it over the number of runs
		double averageTime = ((midpointTime - startTime) - (stopTime - midpointTime)) / 
				(double) timesToLoop;

		System.out.println(n + "\t" + averageTime);

}
}
}