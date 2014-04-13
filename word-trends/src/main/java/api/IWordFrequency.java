package api;

import java.util.List;

/**
 * Word frequency service interface definition
 * 
 * @author nboumaza
 * 
 */
public interface IWordFrequency {

	/**
	 * 
	 * @param input
	 *            text string to process
	 * @param wordNumber
	 *            max number of word in the returned list
	 * @return for a given non null or empty input text, returns the list of
	 *         words ordered by word frequency, the most frequently occurring
	 *         word first, null otherwise.
	 * 
	 */
	List<String> getWordFrequency(String input, int wordNumber);

}
