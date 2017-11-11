import java.util.ArrayList;
import java.util.HashMap;

public class UserInterface {

	public static void main(String[] args) {

		BookReader alice = new BookReader("les-mis.txt");
		ArrayList<Letter> aliceLetters = alice.getLettersArray();
		TextAnalysis analysis = new TextAnalysis(aliceLetters);
		
		HashMap<String,Integer> allLetters = analysis.letterCount();
		HashMap<String,Integer> toRank = analysis.letterCount();
		ArrayList<String> rankedLetters = analysis.rankHashMap(toRank, 10);
		for (int i = 0; i < rankedLetters.size(); i++) {
			System.out.println(rankedLetters.get(i) + " : " 
					+ allLetters.get(rankedLetters.get(i)));
		}
		System.out.println("");
		analysis.rankTopLetters(10);
	
	}
}