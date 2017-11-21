/**
 * This class can hold letter characters
 * as String and their frequency in a text.
 * @author sgb
 *
 */
public class Letter {
	private String letter;
	private double freq;
	/**
	 * The constructor takes in a String
	 * @param letter
	 */
	public Letter(String letter) {
		this.letter = letter;
	}
	/**
	 * @return the freq
	 */
	public double getFreq() {
		return freq;
	}

	/**
	 * @param freq the freq to set
	 */
	public void setFreq(double freq) {
		this.freq = freq;
	}
	/**
	 * @return the letter
	 */
	public String getLetter() {
		return letter;
	}

	/**
	 * @param letter the letter to set
	 */
	public void setLetter(String letter) {
		this.letter = letter;
	}
	
	
}
