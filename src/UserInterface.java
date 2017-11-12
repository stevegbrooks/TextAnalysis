import java.util.ArrayList;
import java.util.HashMap;

public class UserInterface {

	public static void main(String[] args) {

		BookReader book = new BookReader("alice-in-wonderland.txt");
		ArrayList<Letter> bookLetters = book.getLettersArray();
		ArrayList<Word> bookWords = book.getWordsArray();
		TextAnalysis textAnalysis = new TextAnalysis(bookLetters, bookWords);
		
		textAnalysis.rankTopLetters(10);
		System.out.println("===================");
		textAnalysis.rankTopWords(10);
		System.out.println("===================");
		textAnalysis.rankTopWordsWithStopList(10);
//		for (int i = 0; i < bookWords.size(); i++) {
//			System.out.println(bookWords.get(i).getWord());
//		}
//		System.out.println(bookWords.size());
		
		
	}
}