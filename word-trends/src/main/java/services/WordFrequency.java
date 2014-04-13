package services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import api.IWordFrequency;

/**
 * Service implementation of IWordFrequency. Assumes that words are delimited by
 * whitespace
 * 
 * Runs in O(n) time where n is the number of characters in the input String.
 * 
 * @author nboumaza
 */
public class WordFrequency implements IWordFrequency{

	private HashMap<String, WordTrend> wordFreq;

	// delimiter characters: whitespace, punctuation
	// ...add/update in function of subject"s context and adjust unit test
	private static final Set<String> delimSet = new HashSet<String>(
			Arrays.asList(" ", "\r", "\n", "\t", // whitespace
					":", ";", ",", ".", "?", "!", // punctuation
					"$", "%", "^", "&", "*", "+", "-"  // other
			));

	public WordFrequency() {
		super();
	}

	// @Override
	public List<String> getWordFrequency(String input, int max) {

		if (input == null || max <= 0) 
			return null;
		
		if(input.isEmpty())
			return null;
		
		wordFreq = new HashMap<String, WordTrend>();
		StringBuffer word = new StringBuffer();
		char[] ina = input.toCharArray();
		int i = 0;

		while (i < ina.length - 1) {

			// skip potential sequence of delimiters
			while (i < ina.length && delimSet.contains(String.valueOf(ina[i]))) {
				i++;
			}
			// a word is defined as a sequence of one or more non "delimiter"
			// character

			while (i < ina.length && !delimSet.contains(String.valueOf(ina[i]))) {
				word.append(String.valueOf(ina[i]));
				i++;
			}

			if (word.length() > 0) { // we might have "non delimiters" through
										// the end of input
				updateFrequency(word.toString());
				word.delete(0, word.length());
			}

		}

		// we're done with processing the input.
		// sort wordTrends and return the the smallerOf(max, list.size) trending
		// words
		ArrayList<WordTrend> wordTrends = new ArrayList<WordTrend>(
				wordFreq.values());
		Collections.sort(wordTrends, new WordTrend());
		List<String> trendList;

		int len = wordTrends.size();
		if (len < max) {
			trendList = new ArrayList<String>(len);
			max = len;
		} else
			trendList = new ArrayList<String>(max);

		// get the first max word
		for (int j = 0; j < max; j++) {
			trendList.add(wordTrends.get(j).word);
		}

		return trendList;

	}

	/**
	 * 
	 * @param word
	 *            string for which we want to update the frequency count
	 * 
	 */
	private void updateFrequency(String word) {
		WordTrend wt = wordFreq.get(word);
		if (wt == null)
			wt = new WordTrend(word, 1);
		else
			wt.count++;

		wordFreq.put(word, wt);
	}

	/**
	 * 
	 * Inner class representation of a word string and its frequency count from
	 * the submitted input
	 * 
	 * We want to maintain a descending sort order If two distinct words have
	 * same no of frequency retain the natural order
	 */
	private class WordTrend implements Comparator<WordTrend> {
		String word;
		int count;

		public WordTrend(String word, int count) {
			this.word = word;
			this.count = count;
		}

		public WordTrend() {
		}

		@Override
		public int compare(WordTrend w1, WordTrend w2) {

			if (w1.count == w2.count) {
				// use default natural order for equal counts
				return w1.word.compareTo(w2.word);
			} else
				// use highest frequency
				return w2.count - w1.count;
		}	
	
	}

}
