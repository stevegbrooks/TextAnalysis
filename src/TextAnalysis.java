import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class will do text analysis on the books 
 * read in by BookReader.
 * @author sgb
 *
 */
public class TextAnalysis {
	private ArrayList<Letter> letters;
	
	public TextAnalysis(ArrayList<Letter> letters) {
		this.letters = letters;
	}
	/**
	 * this method delivers the frequency of each letter
	 * and returns the top 10 letters in frequency
	 */
	public HashMap<String,Integer> letterCount() {
		HashMap<String,Integer> topTen = new HashMap<>();
		//TODO take in the full letter array from alice
		//and calculate the freq, and then filter for 
		//top ten
		String[] alphabet = getAlphabet();
		for (int i = 0; i < alphabet.length; i++) {
			for (int j = 0; j < letters.size(); j++) {
				if (letters.get(j).getLetter().toString()
						.equalsIgnoreCase(alphabet[i])) {
					if (topTen.containsKey(alphabet[i])) {
						topTen.put(alphabet[i], 
								topTen.get(alphabet[i]) + 1);
					} else {
						topTen.put(alphabet[i], 1);
					}
				}
			}
		}
		return topTen;
	}
	public void rankTopLetters(int n) {
		HashMap<String,Integer> lettersCount = new HashMap<>();
		//TODO take in the full letter array from a book
		//and calculate the freq, and then print out 
		//top ten
		String[] alphabet = getAlphabet();
		for (int i = 0; i < alphabet.length; i++) {
			for (int j = 0; j < letters.size(); j++) {
				if (letters.get(j).getLetter().toString()
						.equalsIgnoreCase(alphabet[i])) {
					if (lettersCount.containsKey(alphabet[i])) {
						lettersCount.put(alphabet[i], 
								lettersCount.get(alphabet[i]) + 1);
					} else {
						lettersCount.put(alphabet[i], 1);
					}
				}
			}
		}

		Integer largestValue = 0;
		Integer lastValue = null;
		String largestKey = null;
		String lastKey = null;
		
		for (int i = 0; i < n; i++) {
			for (String key : lettersCount.keySet()) {
				lastValue = lettersCount.get(key);
				lastKey = key;
				if (lastValue > largestValue) {
					largestValue = lastValue;
					largestKey = lastKey;
				}
			}
			System.out.println(largestKey + " : " + largestValue);
			lettersCount.remove(largestKey);
			largestValue = 0;
			lastValue = 0;
		}
	}
	/**
	 * This method ranks a HashMap based on its largest
	 * values, and returns an ArrayList of keys that correspond
	 * to the top n values in that HashMap
	 * @param toRank the HashMap of type <String,Integer> to rank
	 * @param n the top n values for the HashMap
	 * @return an ArrayList of type String of the top n keys of the HashMap
	 */
	public ArrayList<String> rankHashMap(HashMap<String,Integer> toRank, int n) {
		ArrayList<String> ranked = new ArrayList<>();
		String nextKey = null;
		for (int i = 0; i < n; i++) {
			nextKey = getLargestValue(toRank);
			ranked.add(nextKey);
		}
		return ranked;
	}
	private String getLargestValue(HashMap<String, Integer> getMax) {
		Integer largestValue = 0;
		Integer lastValue = null;
		String largestKey = null;
		String lastKey = null;
		
		for (String key : getMax.keySet()) {
			lastValue = getMax.get(key);
			lastKey = key;
			if (lastValue > largestValue) {
				largestValue = lastValue;
				largestKey = lastKey;
			}
		}
		getMax.remove(largestKey);
		return largestKey;
	}
	private String[] getAlphabet() {
		String[] alphabet = {"A", "B", "C", "D", 
				"E", "F", "G", "H", "I", "G", "K", 
				"L", "M", "N", "O", "P", "Q", "R", 
				"S", "T", "U", "V", "W", "X", "Y", 
				"Z"};	
		return alphabet;
	}
}
