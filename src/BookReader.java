import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class reads in a book and parses it into an ArrayList
 * of Letter, Word and Quote types.
 * @author sgb
 *
 */
public class BookReader {
	private ArrayList<Letter> letters = new ArrayList<>();
	private ArrayList<Word> words = new ArrayList<>();
	private ArrayList<Quote> quotes = new ArrayList<>();
	private FileReader bookFile;
	private BookReaderUtility utils;
	/**
	 * This is the constructor. It takes in the file name for the
	 * book, parses the text, and then builds
	 * an ArrayList of type Letter, Word and Quote to hold the data.
	 * TODO Javadocs
	 * @param file the name of the file to read
	 */
	public BookReader(String file) {
		//Setup
		bookFile = new FileReader(file);
		ArrayList<String> lines = bookFile.getLines();
		StringBuilder wholeBook = bookFile.getWholeBook();
		utils = new BookReaderUtility();
		//Finding letters and filling Letter ArrayList
		extractLetters(lines);
		//Finding words and filling Word ArrayList
		extractWords(lines);
		//Finding double quotation mark quotes and filling Quote ArrayList
		extractQuotes2(wholeBook);
		//Then, single quotation mark quotes
		extractQuotes1(wholeBook);
	}
	/**
	 * @param wholeBook
	 */
	private void extractQuotes1(StringBuilder wholeBook) {
		Matcher singleQMatcher = Pattern.compile("(?:^|\\s)[\\']([^']*?)[\\'](?:\\s|$)", 
				Pattern.MULTILINE).matcher(wholeBook);
		
		while (singleQMatcher.find()) {
			String input = singleQMatcher.group();
			input = input.replaceAll("[\\s]+", " ");
			input = input.trim();
			Quote quote = new Quote(input);
			quotes.add(quote);
		}
	}
	/**
	 * @param wholeBook
	 */
	private void extractQuotes2(StringBuilder wholeBook) {
		Matcher doubleQMatcher = Pattern.compile("[\\\"]([^\\\"]+?)[\\\"]")
				.matcher(wholeBook);
		while (doubleQMatcher.find()) {
			
			String input = doubleQMatcher.group();
			
			if (!input.startsWith("\" ") && !input.endsWith(" \"")
					&& !input.startsWith("\";")) {
				input = input.replaceAll("[\\s]+", " ");
				input = input.trim();
				Quote quote = new Quote(input);
				quotes.add(quote);
			}
		}
	}
	/**
	 * @param rawData
	 */
	private void extractWords(ArrayList<String> rawData) {
		Pattern wordPattern = Pattern.compile("[a-zA-Z\\d\\']+");
		for (int i = 0; i < rawData.size(); i++) {
			String line = rawData.get(i);
			Matcher matcher = wordPattern.matcher(line);
			while (matcher.find()) {	
				String inputUpperCase = matcher.group(0).toUpperCase();
				//TODO make these methods accessed in a static way
				inputUpperCase = utils.removePosessive(inputUpperCase);
				inputUpperCase = utils.removeSingleQuotation(inputUpperCase);
				
				String[] wordComponents = {"A", "E", "I", "O", "U", "Y", "[\\d]*"};
				
				if (inputUpperCase.length() > 1) {
					for (int j = 0; j < wordComponents.length; j++) {
						if (inputUpperCase.contains(wordComponents[j])) {
							Word word = new Word(inputUpperCase);
							words.add(word);
						}
					}
				
				if (inputUpperCase.length() == 1) {
					for (int j = 0; j == 0 | j == 2 | j == 3; j++) {
							if (inputUpperCase.contains(wordComponents[j])) {
								Word word = new Word(inputUpperCase);
								words.add(word);
							}
						}
					}
				}
			}
		}
	}
	/**
	 * @param rawData
	 */
	private void extractLetters(ArrayList<String> rawData) {
		Pattern letterPattern = Pattern.compile("[a-zA-Z]{1}");
		for (int i = 0; i < rawData.size(); i++) {
			String line = rawData.get(i);
			Matcher matcher = letterPattern.matcher(line);	
			while (matcher.find()) {
				for (int j = 0; j <= matcher.groupCount(); j++) {
					Letter letter = new Letter(matcher.group(j));
					letters.add(letter);
				}
			}
		}
	}
	/**
	 * Return the words ArrayList
	 * @return the arraylist of words
	 */
	public ArrayList<Word> getWords() {
		return words;
	}
	/**
	 * Returns the quotes ArrayList
	 * @return the arraylist of quotes
	 */
	public ArrayList<Quote> getQuotes() {
		return quotes;
	}
	/**
	 * @return the letters
	 */
	public ArrayList<Letter> getLetters() {
		return letters;
	}
	
}




