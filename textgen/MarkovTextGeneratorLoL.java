package textgen;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

/** 
 * An implementation of the MTG interface that uses a list of lists.
 * @author Srini 
 */
public class MarkovTextGeneratorLoL implements MarkovTextGenerator {

	// The list of words with their next words
	private List<ListNode> wordList; 
	
	// The starting "word"
	private String starter;
	
	// The random number generator
	private Random rnGenerator;
	
	public MarkovTextGeneratorLoL(Random generator)
	{
		wordList = new LinkedList<ListNode>();
		starter = "";
		rnGenerator = generator;
	}
	
	
	/** Train the generator by adding the sourceText */
	@Override
	public void train(String sourceText)
	{
		String[] trainText = sourceText.trim().split("[ ]+"); 
		
		/* Assuming first Idx exist, so no boundary case has been checked */
		this.starter = trainText[0];
				
		for(int i = 0; i < trainText.length; ++i)
		{
			ListNode newNode   = null;
			boolean  isPresent = false;
			int      prevIdx   = 0;
			
			for(int node = 0; node < wordList.size(); ++node)
			{
				if(wordList.get(node).getWord().equals(trainText[i]))
				{
					newNode = wordList.get(node);
					
					isPresent = true;
					
					prevIdx = node;
					
					break;
				}
			}
			
			if(newNode == null)
			{
				newNode = new ListNode(trainText[i]);
			}
			
			newNode.addNextWord(trainText[(i + 1) % trainText.length]);	
			
			if(isPresent)
			{
				wordList.set(prevIdx, newNode);
			}
			else
			{
				wordList.add(newNode);
			}					
		}
	}
	
	/** 
	 * Generate the number of words requested.
	 */
	@Override
	public String generateText(int numWords) 
	{
	    StringBuffer output = new StringBuffer();	
	    String curword = this.starter;
	    ListNode curNode = null;
	    output.append(curword);
	    
	    while(--numWords > 0)
	    {	    		    	
	    	for(int i = 0; i < wordList.size(); ++i)
	    	{
	    		if( wordList.get(i).getWord().equals(curword))
	    		{
	    			curNode = wordList.get(i);
	    			
	    			break;
	    		}	    		
	    	}
	    	
	    	/* curNode will always get initialized with correct node in the word list*/
	    	String randomWord = curNode.getRandomNextWord(rnGenerator);
	    	
	    	output.append(" " + randomWord);
	    	
	    	curword = randomWord;
	    }
	    			
		return output.toString();
	}
	
	
	// Can be helpful for debugging
	@Override
	public String toString()
	{
		String toReturn = "";
		for (ListNode n : wordList)
		{
			toReturn += n.toString();
		}
		return toReturn;
	}
	
	/** Retrain the generator from scratch on the source text */
	@Override
	public void retrain(String sourceText)
	{
		if(this.wordList != null)
		{
			this.wordList.clear();					
		}
		
		train(sourceText);
	}
		
	
	
	/**
	 * This is a minimal set of tests.  Note that it can be difficult
	 * to test methods/classes with randomized behavior.   
	 * @param args
	 */
	public static void main(String[] args)
	{
		// feed the generator a fixed random value for repeatable behavior
		MarkovTextGeneratorLoL gen = new MarkovTextGeneratorLoL(new Random(42));			
		String textString = "hi there hi Leo";
		//System.out.println(textString);
		gen.train(textString);
		//System.out.println(gen);
		System.out.println(gen.generateText(4));
//		String textString2 = "You say yes, I say no, "+
//				"You say stop, and I say go, go, go, "+
//				"Oh no. You say goodbye and I say hello, hello, hello, "+
//				"I don't know why you say goodbye, I say hello, hello, hello, "+
//				"I don't know why you say goodbye, I say hello. "+
//				"I say high, you say low, "+
//				"You say why, and I say I don't know. "+
//				"Oh no. "+
//				"You say goodbye and I say hello, hello, hello. "+
//				"I don't know why you say goodbye, I say hello, hello, hello, "+
//				"I don't know why you say goodbye, I say hello. "+
//				"Why, why, why, why, why, why, "+
//				"Do you say goodbye. "+
//				"Oh no. "+
//				"You say goodbye and I say hello, hello, hello. "+
//				"I don't know why you say goodbye, I say hello, hello, hello, "+
//				"I don't know why you say goodbye, I say hello. "+
//				"You say yes, I say no, "+
//				"You say stop and I say go, go, go. "+
//				"Oh, oh no. "+
//				"You say goodbye and I say hello, hello, hello. "+
//				"I don't know why you say goodbye, I say hello, hello, hello, "+
//				"I don't know why you say goodbye, I say hello, hello, hello, "+
//				"I don't know why you say goodbye, I say hello, hello, hello,";
//		System.out.println(textString2);
//		gen.retrain(textString2);
//		System.out.println(gen);
//		System.out.println(gen.generateText(20));
	}

}

/** Links a word to the next words in the list 
 * You should use this class in your implementation. */
class ListNode
{
    // The word that is linking to the next words
	private String word;
	
	// The next words that could follow it
	private List<String> nextWords;
	
	ListNode(String word)
	{
		this.word = word;
		nextWords = new LinkedList<String>();
	}
	
	public String getWord()
	{
		return word;
	}

	public void addNextWord(String nextWord)
	{
		nextWords.add(nextWord);
	}
	
	public String getRandomNextWord(Random generator)
	{
		int randomNumber = generator.nextInt(nextWords.size());
			
	    return nextWords.get(randomNumber);
	}

	public String toString()
	{
		String toReturn = word + ": ";
		for (String s : nextWords) {
			toReturn += s + "->";
		}
		toReturn += "\n";
		return toReturn;
	}
	
}


