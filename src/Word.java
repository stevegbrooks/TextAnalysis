/**
 * This class is simply to store information about 
 * each word in the book, including the word itself
 * and its frequency as it appears in the book.
 * @author sgb
 *
 */
public class Word {
	private String word;
	private int freq;
	
	/**
	 * Constructor class to create a word object.
	 * @param word the word to store
	 */
	public Word(String word) {
		this.word = word;
	}

	/**
	 * @return the word
	 */
	public String getWord() {
		return word;
	}

	/**
	 * @param word the word to set
	 */
	public void setWord(String word) {
		this.word = word;
	}

	/**
	 * @return the freq
	 */
	public int getFreq() {
		return freq;
	}

	/**
	 * @param freq the freq to set
	 */
	public void setFreq(int freq) {
		this.freq = freq;
	}
}
