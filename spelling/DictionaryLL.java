package spelling;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * A class that implements the Dictionary interface using a LinkedList
 *
 */
public class DictionaryLL implements Dictionary 
{

	private LinkedList<String> dict;
	
    public DictionaryLL ()
    {
    	dict = new LinkedList<String>();
    }

    /** Add this word to the dictionary.  Convert it to lowercase first
     * for the assignment requirements.
     * @param word The word to add
     * @return true if the word was added to the dictionary 
     * (it wasn't already there). */
    public boolean addWord(String word)
    {
    	String newWord = word.toLowerCase();
    	
    	if(isWord(newWord))
    	{
    		return false;
    	}
    	else
    	{
    		dict.add(newWord);
    		
    		return true;
    	}    	        
    }


    /** Return the number of words in the dictionary */
    public int size()
    {        
        return dict.size();
    }

    /** Is this a word according to this dictionary? */
    public boolean isWord(String s) 
    {
        String lowerWord = s.toLowerCase();
        
        return dict.contains(lowerWord);
    }

    
}
