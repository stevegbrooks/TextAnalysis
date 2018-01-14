/**
 * This class lends support to the BookReader
 * class so that it can carry out its reading
 * functions.
 * 
 * @author sgb
 *
 */
public class BookReaderUtility {
	
	/**
	 * Removes possessives (i.e. "'S") from a 
	 * line of text. It does not remove possessives
	 * which are written as "s'".
	 * @param line a line of text
	 * @return a line of text without possessives
	 */
	public static String removePosessive(String line) {
		if (line.contains("'S")) {
			line = line.replace("'S", "");
		}
		return line;
	}
	/**
	 * Removes the single quotation mark from the line
	 * @param line a line of text
	 * @return the same line without any single quotation marks
	 */
	public static String removeSingleQuotation(String line) {
		if (line.contains("'")) {
			line = line.replace("'", "");
		}
		return line;
	}
}
