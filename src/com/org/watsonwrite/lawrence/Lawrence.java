package com.org.watsonwrite.lawrence;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import au.com.bytecode.opencsv.CSVReader;

public class Lawrence {
	// syllables = used to store dictionary of words and syllable counts
	private Map<String, Integer> syllables = new HashMap<String, Integer>();
	private int UPPERBOUND = 40; // Scores above this are good.
	private int LOWERBOUND = 30; // Scores above this need toning, scores below are flabby.
	
	public Lawrence() throws IOException {
		/** 
		 * Load syllable dictionary to save time on loading it again.
		 */
		loadSyllableDict();
	}
	
	public Lawrence(int upper, int lower) throws IOException {
		/**
		 * Overload constructor, if the user wants to change the bounds.
		 */
		this.UPPERBOUND = upper;
		this.LOWERBOUND = lower;
		loadSyllableDict();
	}
	
	public double[] getScoreAndStatus(String sentence) {
		/**
		 * Calculates the score of a sentence and returns the status.
		 * The status is whether it is good, needs toning, or flabby. 
		 */
		double[] results = {0,0};
		results[0] = fleschKincaidSentence(sentence);
		
		// 0 = good; 1 = needs toning; 2 = flabby.
		if (results[0] > UPPERBOUND) results[1] = 0;
		else if (results[0] > LOWERBOUND) results[1] = 1;
		else results[1] = 2;
		
		return results;
		
	}

	public double fleschKincaidSentence(String sentence) {
		/**
		 * Calculates Kincaid score for any given sentence
		 */
		String[] words = removeJunk(sentence2Words(sentence));
		int wordCount = words.length;
		int syllableCount = 0;

		for (String word : words) {

			if (isWordInDict(word)) {

				// Get syllable count for each word and add it to syllableCount
				int syllable = syllables.get(word);	
				syllableCount += syllable;
				
			} else {
				
				// If word not in dict, count syllables with dodgy method.
				int syllable = countSyllables(word);
				syllableCount += syllable;
			}
		}
		// Calculate Flesch Kincaid for single sentence.
		double kincaidScore = fleschKincaid(wordCount, syllableCount);
		return kincaidScore;
	}

	public int getSyllable(String word) {
		/**
		 * Get syllable count whether it be in the dictionary or the dodgy
		 * method.
		 */
		if (syllables.get(word) != null)
			return syllables.get(word);
		else
			return countSyllables(word);
	}

	private void loadSyllableDict() throws IOException {
		/**
		 * Load in syllable dictionary using OpenCSV library. Place the array
		 * into a dictionary.
		 */
		String csvPath = "/com/org/watsonwrite/lawrence/resources/syllables.txt";
		InputStreamReader isr = new InputStreamReader(getClass().getResourceAsStream(csvPath));
		BufferedReader br = new BufferedReader(isr);

		CSVReader reader = new CSVReader(br);
		String[] nextLine;
		while ((nextLine = reader.readNext()) != null) {
			syllables.put(nextLine[0], Integer.parseInt(nextLine[1]));
		}
		reader.close();
	}

	public boolean isWordInDict(String word) {
		/**
		 * Check if a word is in the syllable dictionary. If it is, return true,
		 * else false.
		 */
		if (syllables.get(word) != null)
			return true;
		else
			return false;
	}

	public double fleschKincaid(int wordCount, int syllableCount) {
		/**
		 * Calculates Flesch-Kincaid algorithm. This is the raw score not the
		 * grade version. For single sentences only.
		 */
		double flesch = (206.835 - ((1.015 * wordCount) / 1)) - (((84.6 * syllableCount) / wordCount));
		return flesch;
	}

	private String[] sentence2Words(String sentence) {
		/**
		 * Takes a sentence string and returns an array of words.
		 */
		String[] words = sentence.split(" ");
		return words;
	}

	private String[] removeJunk(String[] line) {
		/**
		 * Takes an array of words (sentence) and returns an array of words
		 * minus non-alphabet characters.
		 */
		for (int i = 0; i < line.length; i++) {
			line[i] = line[i].replaceAll("[^a-zA-Z]", "");
			line[i] = line[i].toLowerCase();
		}
		return line;
	}

	private int countSyllables(String word) {
		/**
		 * Plan B to count syllables Count syllables that the dictionary method
		 * could not. Counts number of vowels in word and assumes it is the same
		 * as syllable count.
		 */
		int count = 0;
		word = word.toLowerCase();
		for (int i = 0; i < word.length(); i++) {
			if (word.charAt(i) == '\"' || word.charAt(i) == '\'' || word.charAt(i) == '-' || word.charAt(i) == ','
					|| word.charAt(i) == ')' || word.charAt(i) == '(') {
				word = word.substring(0, i) + word.substring(i + 1, word.length());
			}
		}
		boolean isPrevVowel = false;
		for (int j = 0; j < word.length(); j++) {
			if (word.contains("a") || word.contains("e") || word.contains("i") || word.contains("o")
					|| word.contains("u")) {
				if (isVowel(word.charAt(j)) && !((word.charAt(j) == 'e') && (j == word.length() - 1))) {
					if (isPrevVowel == false) {
						count++;
						isPrevVowel = true;
					}
				} else {
					isPrevVowel = false;
				}
			} else {
				count++;
				break;
			}
		}
		return count;
	}

	private boolean isVowel(char c) {
		/**
		 * Tells you if a letter is a vowel or not. This method is used by the
		 * countSyllables() method.
		 */
		if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
			return true;
		} else {
			return false;
		}
	}
}