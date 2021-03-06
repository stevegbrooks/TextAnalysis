import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class reads in a file that is specified.
 * @author swap
 */
public class FileReader {
	
	private String filename;
	private ArrayList<String> lines;
	private StringBuilder wholeBook;
	
	/**
	 * The constructor
	 * @param file the file to read
	 */
	public FileReader(String file) {
		filename = file;
		lines = new ArrayList<String>();
		wholeBook = new StringBuilder();
		readFile();
		readEntire();
	}

	/**
	 * This will read in the entire file.
	 * It will store the contents in the lines ArrayList.
	 */
	private void readFile() {
		try {
			File inputFile = new File(filename);
			Scanner in = new Scanner(inputFile, "utf-8");
			
			while (in.hasNextLine()) {
				String line = in.nextLine();
				lines.add(line);
			}
			
			in.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * This method reads in the entire file 
	 * to a single line of StringBuilder text.
	 */
	private void readEntire() {
		for (String element : lines) {
			wholeBook.append(element + " ");
		}
	}
	
	/**
	 * The accessor method for lines
 	 * @return the lines arraylist
	 */
	public ArrayList<String> getLines() {
		return lines;
	}
	public StringBuilder getWholeBook() {
		return wholeBook;
	}

}
