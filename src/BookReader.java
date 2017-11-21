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
	 * 
	 * Its fairly involved, so bear with me...
	 * 
	 * @param file the name of the file to read
	 */
	public BookReader(String file) {
		//Setup
		bookFile = new FileReader(file);
		ArrayList<String> rawData = bookFile.getLines();
		StringBuilder wholeBook = bookFile.getWholeBook();
		utils = new BookReaderUtility();

		//Finding letters and filling Letter ArrayList
		for (int i = 0; i < rawData.size(); i++) {
			String line = rawData.get(i);
			Matcher matcher = Pattern.compile("[a-zA-Z]{1}")
					.matcher(line);
			
			while (matcher.find()) {
				for (int j = 0; j <= matcher.groupCount(); j++) {
					Letter letter = new Letter(matcher.group(j));
					letters.add(letter);
				}
			}
		}
		
		//Finding words and filling Word ArrayList
		for (int i = 0; i < rawData.size(); i++) {
			
			String line = rawData.get(i);
			Matcher matcher = Pattern.compile("[a-zA-Z\\d\\']+")
					.matcher(line);
			
			while (matcher.find()) {
				
				String inputUpperCase = matcher.group(0).toUpperCase();
				inputUpperCase = utils.removePosessive(inputUpperCase);
				inputUpperCase = utils.removeSingleQuotation(inputUpperCase);
				
				if (inputUpperCase.length() > 1
						&& (inputUpperCase.contains("A")
								|| inputUpperCase.contains("E")
								|| inputUpperCase.contains("I")
								|| inputUpperCase.contains("O")
								|| inputUpperCase.contains("U")
								|| inputUpperCase.contains("Y")
								|| inputUpperCase.contains("[\\d]*"))) {
					Word word = new Word(inputUpperCase);
					words.add(word);
				
				} else if (inputUpperCase.length() == 1
						&& (inputUpperCase.equals("A") ||
								inputUpperCase.equals("I") ||
								inputUpperCase.equals("O"))) {
					Word word = new Word(inputUpperCase);
					words.add(word);
				}
			}
		}
		
		//Finding quotes and filling Quote ArrayList
			//First, double quotation mark quotes
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
			//Then, single quotation mark quotes
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
	 * Return the letters ArrayList
	 * @return the arraylist of letters
	 */
	public ArrayList<Letter> getLettersArray() {
		return letters;
	}
	/**
	 * Return the words ArrayList
	 * @return the arraylist of words
	 */
	public ArrayList<Word> getWordsArray() {
		return words;
	}
	/**
	 * Returns the quotes ArrayList
	 * @return the arraylist of quotes
	 */
	public ArrayList<Quote> getQuotesArray() {
		return quotes;
	}
	
}




