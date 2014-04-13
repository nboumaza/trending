package services;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class WordFrequencyTest {

	WordFrequency wf;
	StringBuffer sb = new StringBuffer();

	// input text
	private static final String text0 = "";
	private static final String text00 = "     ! ,, ; ?   ";
	private static final String text1 = "this is some text, this is is? and and and and text text    whitespaces";
	private static final String text2 = "word1 word1, word2 ! word3? word3 word3 word3";
	private static final String text3 = "           !,:w1 w2!!!!!xx,xx w1 w1 w1 !";
	private static final String text4 = "w1             ";
	private static final String text5 = "          w1";
	private static final String text6 = "xxx, zzz, bbb  aaa";

	// expected output per order of above listing using maxWord3 from below
	private static final String text0Result = null;
	private static final int text00ResultSize = 0 ;
	private static final String text1Result = "and is text this some whitespaces";
	private static final String text2Result = "word3 word1 word2";
	private static final String text3Result = "w1 xx w2";
	private static final String text4Result = "w1";
	private static final String text5Result = "w1";
	private static final String text6Result = "aaa bbb xxx zzz";

	// vary max result
	private static final int maxWord0 = 0;
	private static final int maxWord1 = 2;
	private static final int maxWord2 = 100;

	// expected result using text1 and varying maxWord - result by order of
	// above listing
	private static final String maxWord0Text1Result = null;
	private static final int maxWord1RText1ResultSize = 2;
	private static final int maxWord2Text1ResultSize = 6;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {

	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		wf = new WordFrequency();
		sb = new StringBuffer();
	}

	@After
	public void tearDown() throws Exception {
	}


	@Test
	/**
	 * asserts expected vs. actual for each input text scenario as defined in this test case
	 */
	public void testGetWordFrequency() {
		assertNull(testNullInput());
		assertTrue(text00ResultSize == testEmptyResultList().size());
		assertTrue(text1Result.equals(testText1()));
		assertTrue(text2Result.equals(testText2()));
		assertTrue(text3Result.equals(testText3()));
		assertTrue(text4Result.equals(testText4()));
		assertTrue(text5Result.equals(testText5()));
		assertTrue(text6Result.equals(testText6()));
		assertEquals(maxWord0Text1Result, testMaxWord0());
		assertTrue(maxWord1RText1ResultSize == testMaxWord1());
		assertTrue(maxWord2Text1ResultSize == testMaxWord2());

	}

	private String getString(List<String> wordFrequency) {
		  if(wordFrequency == null){
			  return null;
		  }
		  sb.delete(0, sb.length());
		  for(int i=0; i< wordFrequency.size(); i++){
	    	  sb.append(wordFrequency.get(i));
	    	  sb.append(" "); 
	    	 
	      }
		  String result = sb.toString().trim();
		  return result;
	}
	
	private List<String> testNullInput() {
		return wf.getWordFrequency(text0, maxWord2);

	}



	private List<String> testEmptyResultList() {
		return wf.getWordFrequency(text00, maxWord2);

	}

	private String testText1() {
		return getString(wf.getWordFrequency(text1, maxWord2));

	}

	private String testText2() {
		return getString( wf.getWordFrequency(text2, maxWord2));

	}

	private String testText3() {
		return getString(wf.getWordFrequency(text3, maxWord2));

	}

	private String testText4() {
		return getString(wf.getWordFrequency(text4, maxWord2));

	}

	private String testText5() {
		return getString(wf.getWordFrequency(text5, maxWord2));

	}

	private String testText6() {
		return getString(wf.getWordFrequency(text6, maxWord2));

	}

	private List<String> testMaxWord0() {
		return wf.getWordFrequency(text1, maxWord0);

	}

	private int testMaxWord1() {
		return wf.getWordFrequency(text1, maxWord1).size();

	}

	private int testMaxWord2() {
		return wf.getWordFrequency(text1, maxWord2).size();

	}
}
