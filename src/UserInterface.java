import java.util.ArrayList;
/**
 * This class implements the TextAnalysis class
 * as well as the BookReader in order to return
 * textual analyses of .txt files.
 * 
 * @author sgb
 *
 */
public class UserInterface {

	public static void main(String[] args) {
		
		String[] bookList = {"alice-in-wonderland.txt",
				"christmas-carol.txt", "huck-finn.txt",
				"les-mis.txt", "metamorphosis.txt",
				"my-man-jeeves.txt", "pride-prejudice.txt",
				"tale-of-two-cities.txt", "tom-sawyer.txt"};

		for (String element : bookList) {
		
		BookReader book = new BookReader(bookList[0]);
		ArrayList<Letter> bookLetters = book.getLettersArray();
		ArrayList<Word> bookWords = book.getWordsArray();
		ArrayList<Quote> bookQuotes = book.getQuotesArray();
		TextAnalysis textAnalysis = new TextAnalysis(bookLetters, 
				bookWords, bookQuotes);
		
		System.out.println("Textual Analysis of: " + element);
		System.out.println("************************"
				+ "************************");
		System.out.println("Top Letters in Terms of Frequency");
		textAnalysis.rankTopLetters(10);
		System.out.println("====================================");
		System.out.println("Top Words in Terms of Frequency");
		textAnalysis.rankTopWords(10);
		System.out.println("====================================");
		System.out.println("Top non-StopList Words in Terms of Frequency");
		textAnalysis.rankTopWordsWithStopList(10);
		System.out.println("====================================");
		System.out.println("Top Quotes in Terms of Length");
		textAnalysis.rankTopNLongestQuotes(10);
		System.out.println("====================================");
		System.out.println("The First 10 Words Scrambled");
		ArrayList<StringBuilder> scrambler = textAnalysis.wordScrambler();
			for (int i = 0; i < 10; i++) {
				System.out.println(scrambler.get(i));
			}
		System.out.println("************************"
				+ "************************");
		}
	}
}