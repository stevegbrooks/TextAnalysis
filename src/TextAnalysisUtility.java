import java.util.ArrayList;
import java.util.HashMap;
/**
 * This class lends support to the TextAnalysis class
 * to carry out its analyses.
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
	public HashMap<String,Integer> countWords(ArrayList<Word> stringArray) {
		HashMap<String,Integer> wordsCount = new HashMap<>();
		for (int i = 0; i < stringArray.size(); i++) {
			if (wordsCount.containsKey(stringArray.get(i).getWord())) {
				wordsCount.put(stringArray.get(i).getWord(), 
						wordsCount.get(stringArray.get(i).getWord()) + 1);
			} else {
				wordsCount.put(stringArray.get(i).getWord(), 1);
			}
		}
		return wordsCount;
	}
	/**
	 * This method prints the n highest frequency words/letters 
	 * in a HashMap
	 * @param hashmap a HashMap of words/letters and their frequencies
	 * @param numberOfRanks the desired number of ranks
	 */
	public void printTopNbyFreq(HashMap<String,Integer> hashmap, int numberOfRanks) {
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
			System.out.println((i+1) + ". " + largestKey + " : " + largestValue);
			hashmap.remove(largestKey);
			largestValue = 0;
			lastValue = 0;
		}
	}
	/**
	 * This method returns a string array 
	 * of the letters in the English alphabet in caps
	 * @return string array of the 26 letters 
	 */
	public String[] getAlphabet() {
		String[] alphabet = {"A", "B", "C", "D", 
				"E", "F", "G", "H", "I", "G", "K", 
				"L", "M", "N", "O", "P", "Q", "R", 
				"S", "T", "U", "V", "W", "X", "Y", 
				"Z"};	
		return alphabet;
	}
}
