import java.util.ArrayList;

public class UserInterface {

	public static void main(String[] args) {

		BookReader alice = new BookReader("alice-in-wonderland.txt");
		ArrayList<Letter> aliceLetters = alice.getLettersArray();

		for (int i = 0; i < aliceLetters.size(); i++) {
			System.out.println(aliceLetters.get(i).getLetter());
		}

	}
}