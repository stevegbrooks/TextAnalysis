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
	/**
	 * This is the constructor. It takes in the file name for the
	 * book, parses the text, and then builds
	 * an ArrayList of type Letter, Word or Quote to hold the data.
	 * @param file
	 */
	public BookReader(String file) {
		bookFile = new FileReader(file);
		ArrayList<String> rawData = bookFile.getLines();
		StringBuilder wholeBook = bookFile.getWholeBook();
		
		//fill letters array
		for (int i = 0; i < rawData.size(); i++) {
			String line = rawData.get(i);
			Matcher matcher = Pattern.compile("[a-zA-Z]{1}").matcher(line);
			while (matcher.find()) {
				for (int j = 0; j <= matcher.groupCount(); j++) {
					Letter letter = new Letter(matcher.group(j));
					letters.add(letter);
				}
			}
		}
		//fill word array
		for (int i = 0; i < rawData.size(); i++) {
			String line = rawData.get(i);
			Matcher matcher = Pattern.compile("[a-zA-Z\\']+").matcher(line);
			while (matcher.find()) {
				String inputUpperCase = matcher.group(0).toUpperCase();
				inputUpperCase = removePosessive(inputUpperCase);
				inputUpperCase = removeSingleQuotation(inputUpperCase);
				if (inputUpperCase.length() > 1
						&& (inputUpperCase.contains("A")
						|| inputUpperCase.contains("E")
						|| inputUpperCase.contains("I")
						|| inputUpperCase.contains("O")
						|| inputUpperCase.contains("U")
						|| inputUpperCase.contains("Y"))) {
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
		//fill quotes array
		Matcher doubleQMatcher = Pattern.compile("\\\"([^\"]+)\\\"").matcher(wholeBook);
		while (doubleQMatcher.find()) {
			String input = doubleQMatcher.group();
			Quote quote = new Quote(input);
			quotes.add(quote);
		}
		Matcher singleQMatcher = Pattern.compile("(?<![\\w])\\'([^\\']+)(?=[\\.\\!\\'])\\'(?=[\\s])").matcher(wholeBook);
		while (singleQMatcher.find()) {
			String input = singleQMatcher.group();
			Quote quote = new Quote(input);
			quotes.add(quote);
		}
	}
	public String removePosessive(String line) {
		if (line.contains("'S")) {
			line = line.replace("'S", "");
		}
		return line;
	}
	public String removeSingleQuotation(String line) {
		if (line.contains("'")) {
			line = line.replace("'", "");
		}
		return line;
	}
//	public String removeInnards(String line) {
//		if (line.charAt(0).equals("'")) {
//			//delimit by (?![$\'])([\s][\w\s]+[\s])(?=[$\'])
//		}
//	}
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
	public ArrayList<Quote> getQuotesArray() {
		return quotes;
	}
	
}




