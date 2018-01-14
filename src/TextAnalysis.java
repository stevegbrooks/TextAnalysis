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
		utils = new TextAnalysisUtility();
	}
	/**
	 * The second constructor allows one to run textual analysis on
	 * an ArrayList of type Letter, Word, and Quote and also
	 * include a stop list for more interesting results.
	 * 
	 * @param letters an ArrayList of type Letter
	 * @param words an ArrayList of type Word
	 * @param quotes an ArrayList of type Quote
	 * @param stopListFileName a String matching the 
	 * file name of the stop list
	 */
	public TextAnalysis(ArrayList<Letter> letters, 
			ArrayList<Word> words, ArrayList<Quote> quotes,
			String stopListFileName) {
		
		this.letters = letters;
		this.words = words;
		this.quotes = quotes;
		stopList = new BookReader(stopListFileName);
		stopListWords = stopList.getWords();
		utils = new TextAnalysisUtility();
	}
	/**
	 * this method computes the frequency of each letter
	 * and prints the top 10 letters by frequency
	 * 
	 * TODO don't make it the resp of this method to make sure 
	 * its a letter, just make the letters array not have other things
	 */
	public void rankTopLetters(int n) {
		HashMap<String,Integer> lettersCount = new HashMap<>();
		//TODO make these methods accessed in a static way
		lettersCount = utils.countLetters(letters);
//		String[] alphabet = utils.getAlphabet();
//		//check through the alphabet array
//		for (int i = 0; i < alphabet.length; i++) {
//			for (int j = 0; j < letters.size(); j++) {
//				if (letters.get(j).getLetter().toString()
//						.equalsIgnoreCase(alphabet[i])) {
//					//fill hashmap with letter frequency
//					if (lettersCount.containsKey(alphabet[i])) {
//						lettersCount.put(alphabet[i], 
//								lettersCount.get(alphabet[i]) + 1);
//					} else {
//						lettersCount.put(alphabet[i], 1);
//					}
//				}
//			}
//		}
		//TODO make these methods accessed in a static way
		utils.printTopNbyFreq(lettersCount, n);
	}
	/**
	 * this method computes the frequency of each word
	 * and prints the top 10 words by frequency.
	 */
	public void rankTopWords(int n) {
		HashMap<String,Integer> wordsCount = new HashMap<>();
		//TODO make these methods accessed in a static way
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
				if (words.get(j).getWord().equals(
						stopListWords.get(i).getWord())) {
					words.remove(j);
				}
			}
		}
		//TODO make these methods accessed in a static way
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
	 * 
	 *TODO collections.shuffle
	 * 
	 * @return arraylist of type StringBuilder
	 */
	public ArrayList<StringBuilder> wordScrambler() {
		ArrayList<StringBuilder> scrambledWords = new ArrayList<>();
		Random rand = new Random();
		//*** for each word in the source file...
		for (int i = 0; i < words.size(); i++) {
			//create two SB objects: One to hold the result, 
			//the other to hold the raw materials.
			StringBuilder scrambledWord = new StringBuilder();
			StringBuilder wordToScramble = new StringBuilder();
			//append raw materials, and create a char array to process
			wordToScramble = wordToScramble.append(words.get(i).getWord());
			char[] scrambledChars = new char[wordToScramble.length()];
			//***for each char in that word, knowing that the char array and the word
				//are necessarily the same length and the same word...
			for (int j = 0; j < scrambledChars.length; j++) {
				
				//take a random char in the origin word...
				int replacementIndex = rand.nextInt(wordToScramble.length());
				char replacementChar = wordToScramble.charAt(replacementIndex);
				
				//...and put it into the char array
				scrambledChars[j] = replacementChar;
					
				//selection without replacement
				wordToScramble = wordToScramble.deleteCharAt(replacementIndex);
			}
			//after all that's done, create an object for the scrambled word
			for (char element : scrambledChars) {
				scrambledWord.append(element); 
			}
			//and add it to the array. 
			scrambledWords.add(scrambledWord);
		}
		//***
		return scrambledWords;
	}
}
