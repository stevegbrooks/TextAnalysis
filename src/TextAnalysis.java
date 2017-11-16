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
	private ArrayList<Word> words;
	private ArrayList<Quote> quotes;
	private BookReader stopList;
	private ArrayList<Word> stopListWords;
	
	public TextAnalysis(ArrayList<Letter> letters, ArrayList<Word> words, ArrayList<Quote> quotes) {
		this.letters = letters;
		this.words = words;
		this.quotes = quotes;
		stopList = new BookReader("stop-list.txt");
		stopListWords = stopList.getWordsArray();
	}
	/**
	 * this method computes the frequency of each letter
	 * and prints the top 10 letters by frequency
	 */
	public void rankTopLetters(int n) {
		HashMap<String,Integer> lettersCount = new HashMap<>();
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
		printTopNbyFreq(lettersCount, n);
	}
	/**
	 * this method computes the frequency of each word
	 * and prints the top 10 words by frequency.
	 */
	public void rankTopWords(int n) {
		HashMap<String,Integer> wordsCount = new HashMap<>();
		wordsCount = calculateFreqFromWords(words);
		printTopNbyFreq(wordsCount, n);
	}
	/**
	 * this method computes the frequency of each word,
	 * compares it against a stop-list, and prints out 
	 * the top 10 letters by frequency which aren't in 
	 * the stop-list.
	 */
	public void rankTopWordsWithStopList(int n) {
		HashMap<String,Integer> wordsCount = new HashMap<>();
		for (int i = 0; i < stopListWords.size(); i++) {
			for (int j = 0; j < words.size(); j++) {
				if (words.get(j).getWord().equals(stopListWords.get(i).getWord())) {
					words.remove(j);
				}
			}
		}
		wordsCount = calculateFreqFromWords(words);
		printTopNbyFreq(wordsCount, n);
	}
	
	public void rankTopNLongestQuotes(int n) {
		Integer largestLength = 0;
		Integer lastLength = 0;
		Quote largestQuote = null;
		Quote lastQuote = null;
		for (int j = 0; j < n; j++) {
			for (int i = 0; i < quotes.size(); i++) {
				lastQuote = quotes.get(i);
				lastLength = lastQuote.getQuote().length();
				if (lastLength > largestLength) {
					largestLength = lastLength;
					largestQuote = lastQuote;
				}
			}
			System.out.println((j+1) + ". " + largestQuote.getQuote() 
				+ " : " + largestLength);
			quotes.remove(largestQuote);
			largestLength = 0;
			lastLength = 0;
		}
	}
	/**
	 * This method creates a HashMap where the key are words
	 * and the values are how many times that word appears in the
	 * provided ArrayList of type Word.
	 * @param stringArray an ArrayList of type Word
	 * @return a HashMap with frequencies
	 */
	private HashMap<String,Integer> calculateFreqFromWords(ArrayList<Word> stringArray) {
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
	private void printTopNbyFreq(HashMap<String,Integer> hashmap, int numberOfRanks) {
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
	private String[] getAlphabet() {
		String[] alphabet = {"A", "B", "C", "D", 
				"E", "F", "G", "H", "I", "G", "K", 
				"L", "M", "N", "O", "P", "Q", "R", 
				"S", "T", "U", "V", "W", "X", "Y", 
				"Z"};	
		return alphabet;
	}
}
