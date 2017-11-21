import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * This class will do text analysis on the books 
 * read in by BookReader. It receives support 
 * from the TextAnalysisUtility class.
 * 
 * @author sgb
 *
 */
public class TextAnalysis {
	private ArrayList<Letter> letters;
	private ArrayList<Word> words;
	private ArrayList<Quote> quotes;
	private BookReader stopList;
	private ArrayList<Word> stopListWords;
	private TextAnalysisUtility utils;
	
	/**
	 * The constructor allows one to run textual analyses on
	 * an ArrayList of type Letter, Word, and Quote.
	 * 
	 * @param letters an ArrayList of type Letter
	 * @param words an ArrayList of type Word
	 * @param quotes an ArrayList of type Quote
	 */
	public TextAnalysis(ArrayList<Letter> letters, 
			ArrayList<Word> words, ArrayList<Quote> quotes) {
		
		this.letters = letters;
		this.words = words;
		this.quotes = quotes;
		
		stopList = new BookReader("stop-list.txt");
		stopListWords = stopList.getWordsArray();
		utils = new TextAnalysisUtility();
	}
	/**
	 * this method computes the frequency of each letter
	 * and prints the top 10 letters by frequency
	 */
	public void rankTopLetters(int n) {
		HashMap<String,Integer> lettersCount = new HashMap<>();
		String[] alphabet = utils.getAlphabet();
		//check through the alphabet array
		for (int i = 0; i < alphabet.length; i++) {
			for (int j = 0; j < letters.size(); j++) {
				if (letters.get(j).getLetter().toString()
						.equalsIgnoreCase(alphabet[i])) {
					//fill hashmap with letter frequency
					if (lettersCount.containsKey(alphabet[i])) {
						lettersCount.put(alphabet[i], 
								lettersCount.get(alphabet[i]) + 1);
					} else {
						lettersCount.put(alphabet[i], 1);
					}
				}
			}
		}
		utils.printTopNbyFreq(lettersCount, n);
	}
	/**
	 * this method computes the frequency of each word
	 * and prints the top 10 words by frequency.
	 */
	public void rankTopWords(int n) {
		HashMap<String,Integer> wordsCount = new HashMap<>();
		wordsCount = utils.countWords(words);
		utils.printTopNbyFreq(wordsCount, n);
	}
	/**
	 * this method computes the frequency of each word,
	 * compares it against a stop-list, and prints out 
	 * the top 10 letters by frequency which aren't in 
	 * the stop-list.
	 */
	public void rankTopWordsWithStopList(int n) {
		HashMap<String,Integer> wordsCount = new HashMap<>();
		
		//check against stop-list array
		for (int i = 0; i < stopListWords.size(); i++) {
			for (int j = 0; j < words.size(); j++) {
				if (words.get(j).getWord().equals(stopListWords.get(i).getWord())) {
					words.remove(j);
				}
			}
		}
		
		wordsCount = utils.countWords(words);
		utils.printTopNbyFreq(wordsCount, n);
	}
	/**
	 * This method iteratively pulls the largest Quote by length
	 * from the ArrayList, prints it, and then removes it. 
	 * This will get done n times based on the integer passed to it.
	 * @param n an integer for the desired number of ranks.
	 */
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
			System.out.println((j+1) + ". " 
					+ largestQuote.getQuote() 
					+ " : " + largestLength);
			quotes.remove(largestQuote);
			largestLength = 0;
			lastLength = 0;
		}
	}
	/**
	 * This method is in response to Q5. It will take each 
	 * Word, scramble its letters, and then add it to a new
	 * ArrayList of type StringBuilder.
	 * @return arraylist of type StringBuilder
	 */
	public ArrayList<StringBuilder> wordScrambler() {
		ArrayList<StringBuilder> scrambledWords = new ArrayList<>();
		Random rand = new Random();
		
		for (int i = 0; i < words.size(); i++) {
			StringBuilder scrambledWord = new StringBuilder();
			StringBuilder wordToScramble = new StringBuilder();
			wordToScramble = wordToScramble.append(words.get(i).getWord());
			char[] scrambledChars = new char[wordToScramble.length()];
			
			for (int j = 0; j < scrambledChars.length; j++) {
				int replacementIndex = rand.nextInt(wordToScramble.length());
				char replacementChar = wordToScramble.charAt(replacementIndex);
				wordToScramble = wordToScramble.deleteCharAt(replacementIndex);
				scrambledChars[j] = replacementChar;
			}
			for (char element : scrambledChars) {
				scrambledWord.append(element); 
			}
			scrambledWords.add(scrambledWord);
		}
		return scrambledWords;
	}
}
