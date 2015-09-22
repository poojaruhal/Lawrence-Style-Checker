package com.org.watsonwrite.lawrence.test;

import java.io.IOException;
import com.org.watsonwrite.lawrence.Lawrence;

public class testLawrence {

	public static void main(String[] args) throws IOException {
		// Instantiate the Lawrence object.
		Lawrence lawrence = new Lawrence();

		// Test the isWordInDict() function.
		System.out.println(lawrence.isWordInDict("elephant")); // false
		System.out.println(lawrence.isWordInDict("reasonable")); // true

		// Let the object decide what method to use:
		System.out.println(lawrence.getSyllable("elephant")); // 3
		System.out.println(lawrence.getSyllable("reasonable")); // 4

		// Calculate readability of sentence.
		System.out.println(lawrence.fleschKincaidSentence("Here is a good sentence."));

		// Get score of sentence and status
		// 0 = good; 1 = needs toning; 2 = flabby.
		double[] scoreStatus = lawrence.getScoreAndStatus("Here is an unbelievably reconstruction longitudinal sentence that you may need to reconfiguration down.");
		System.out.println(scoreStatus[0] + " " + scoreStatus[1]);
		
	}

}
