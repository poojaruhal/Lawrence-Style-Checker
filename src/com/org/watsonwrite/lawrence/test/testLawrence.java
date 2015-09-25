package com.org.watsonwrite.lawrence.test;

import java.io.IOException;
import com.org.watsonwrite.lawrence.Lawrence;

public class testLawrence {

	public static void main(String[] args) throws IOException {
		// Instantiate the Lawrence object.
		Lawrence lawrence = new Lawrence();

		// Turn the text into an array of sentences.
		String sentences = "Curious creatures to whom the ant is la haute cuisine. One day recently I picked up a few termites and ate them, wondering at their widespread appeal. These particular ones proved to be dry and hard and with a faintly resinous aftertaste in fact, I have yet to eat ants or termites that tasted good raw. My opinion notwithstanding, these insects are the preferred food of some of the most unusual animals alive today: the myrmecophagous (ant- and termite-eating) mammals. This group includes anteaters, armadillos, pangolins, the aardwolf, echidnas, the numbat and, beloved of crossword-puzzle addicts and amateur poets, the aardvark. With the exception of armadillos and anteaters, these mammals are not closely related, sharing only a common passion for ants and termites. For a long time termites were called 'white ants, and so the descriptive name anteater was used to refer to both ant- and termite-eating mammals. Most myrmecophagous mammals eat both, although some species appear to restrict themselves to one or the other. Only 22 mammals can be considered true ant and termite eaters: not many out of the 4,170 known species. This is particularly striking when one considers that in some areas ants and termites comprise up to 75 percent of the total animal biomass. In fact, the number of individual ants and termites alive at this moment is greater than the number of all humans who have ever lived. That is a lot of potential food. Where you find a lot of ants or termites you usually find one of these curious predators. Each of the world's tropical areas has its own anteaters. In South and Central America, where I spent two and a half years, anteaters share the myrmecophagous niche with a variety of armadillos. The anteaters range from the giant at 100 pounds or so down to the pygmy at about a pound. Armadillos, which range from Kansas and Missouri to Argentina, are unique in the mammal world in possessing a hardened shell made of bony plates and teeth with no enamel.";
		String[] sentences2 = sentences.split("(?<=[a-z])\\.\\s+");

		int count = 0;
		
		for (String sentence : sentences2) {
			sentence = sentence.replace("-", " "); // split double words
			for (String word : sentence.split(" ")) {
				
				// Get rid of punctuation marks and spaces.
				word = lawrence.cleanWord(word);
				
				// If the word is null, skip it.
				if (word.length() < 1)
					continue;
				
				// Print out the word and it's syllable on one line.
				System.out.print(word + ",");
				System.out.println(lawrence.getSyllable(word));
				count += lawrence.getSyllable(word);
			}
		}
		System.out.println(count);

	}

}
