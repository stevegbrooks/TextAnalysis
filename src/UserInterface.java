import java.util.ArrayList;
import java.util.HashMap;

public class UserInterface {

	public static void main(String[] args) {
		
		String[] bookList = {"alice-in-wonderland.txt",
				"christmas-carol.txt", "huck-finn.txt",
				"les-mis.txt", "metamorphosis.txt",
				"my-man-jeeves.txt", "pride-prejudice.txt",
				"tale-of-two-cities.txt", "tom-sawyer.txt"};

//		for (String element : bookList) {
		BookReader book = new BookReader(bookList[8]);
		ArrayList<Letter> bookLetters = book.getLettersArray();
		ArrayList<Word> bookWords = book.getWordsArray();
		TextAnalysis textAnalysis = new TextAnalysis(bookLetters, bookWords);
		
//		System.out.println(element);
		System.out.println("===============================");
		textAnalysis.rankTopLetters(10);
		System.out.println("===============================");
		textAnalysis.rankTopWords(10);
		System.out.println("===============================");
		textAnalysis.rankTopWordsWithStopList(10);
		System.out.println("===============================");
//		}
		
	}
}