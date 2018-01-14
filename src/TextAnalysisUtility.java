import java.util.ArrayList;
import java.util.HashMap;
/**
 * This class lends support to the TextAnalysis class
 * to carry out its analyses.
 * 
 * @author sgb
 *
 */
public class TextAnalysisUtility {
	/**
	 * This method creates a HashMap where the key are words
	 * and the values are how many times that word appears in the
	 * provided ArrayList of type Word.
	 * @param stringArray an ArrayList of type Word
	 * @return a HashMap with frequencies
	 */
	public static HashMap<String,Integer> countWords(ArrayList<Word> stringArray) {
		HashMap<String,Integer> wordsCount = new HashMap<>();
		for (Word word : stringArray) {
			if (wordsCount.containsKey(word.getWord())) {
				wordsCount.put(word.getWord(), 
						wordsCount.get(word.getWord()) + 1);
			} else {
				wordsCount.put(word.getWord(), 1);
			}
		}
		return wordsCount;
	}
	
	public static HashMap<String,Integer> countLetters(ArrayList<Letter> stringArray) {
		HashMap<String,Integer> lettersCount = new HashMap<>();
		for (Letter letter : stringArray) {
			if (lettersCount.containsKey(letter.getLetter())) {
				lettersCount.put(letter.getLetter(), 
						lettersCount.get(letter.getLetter()) + 1);
			} else {
				lettersCount.put(letter.getLetter(), 1);
			}
		}
		return lettersCount;
	}
	/**
	 * This method prints the n highest frequency words/letters 
	 * in a HashMap
	 * 
	 * @param hashmap a HashMap of words/letters and their frequencies
	 * @param numberOfRanks the desired number of ranks
	 */
	public static void printTopNbyFreq(HashMap<String,Integer> hashmap, int numberOfRanks) {
		if (hashmap.size() >= numberOfRanks) {
			Integer largestValue = 0;
			Integer lastValue = null;
			String largestKey = null;
			String lastKey = null;

			for (int i = 0; i < numberOfRanks; i++) {
				for (String key : hashmap.keySet()) {
					lastValue = hashmap.get(key);
					lastKey = key;
					if (lastValue > largestValue) {
						largestValue = lastValue;
						largestKey = lastKey;
					}
				}
				System.out.println((i+1) + ". " + largestKey 
						+ " : " + largestValue);
				hashmap.remove(largestKey);
				largestValue = 0;
				lastValue = 0;
			}
		} else {
			throw new IllegalArgumentException("Number of ranks exceeds "
					+ "number of elements in data set");
		}
	}
	/**
	 * This method returns a string array 
	 * of the letters in the English alphabet in caps
	 * @return string array of the 26 letters 
	 */
	public static String[] getAlphabet() {
		String[] alphabet = {"A", "B", "C", "D", 
				"E", "F", "G", "H", "I", "G", "K", 
				"L", "M", "N", "O", "P", "Q", "R", 
				"S", "T", "U", "V", "W", "X", "Y", 
				"Z"};	
		return alphabet;
	}
}
