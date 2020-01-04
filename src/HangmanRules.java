//Gallow.java
//Grant Wilkins
//May 2018
//This class helps create the word that will be guessed at and helps
//all of the other methods inside of the game itself. It is a rules
//class because lots of the rules inside of the game are housed and
//kept track of because of this method. 
import java.util.ArrayList;
import java.io.InputStream;


public class HangmanRules
{
	private ArrayList<Character> progress, word;
	private String answer;
	private Dictionary randWord;
	private int wordLength;
	
	//Locates the place where there is the text file in a system-independent
	//implementation.
	private InputStream getFilePath()
	{
		InputStream file = HangmanRules.class.getClassLoader().getResourceAsStream("1-1000.txt");
		return file;
	}
	
	public HangmanRules()
	{
		//Gets a random word length to read from the file.
		wordLength = 1 + (int) (Math.random()*10);
		
		//Creates a word generator.
		randWord = new WordFileReader();
		randWord.readFile(getFilePath());
		
		//Randomly selects the answer from the text file.
		answer = randWord.randRead(wordLength);
		
		
		//Initializes the ArrayList of characters that holds the words.
		word = new ArrayList<Character>();
		for(int i = 0; i < answer.length(); i++)
		{
			word.add(Character.toLowerCase(answer.charAt(i)));
		}
		
		//Creates the progress list where blanks hold the current place of the word.
		progress = new ArrayList<Character>();
		for(int i = 0; i < numChar(); i++)
		{
			progress.add('-');
		}
	}
	
	//Accessor method for the number of letters inside of the
	//key word being guessed against.
	public int numChar()
	{
		return word.size();
	}

	//These two methods are part of the current state and graphical implementation
	//of the game in the command line.
	public void printAnswer()
	{
		System.out.println("The word was: " + answer);
	}
	
	public void getProgress()
	{
		System.out.println("Your progress thus far is: ");
		for(Character currentString : progress)
			System.out.print(currentString);
		System.out.println( );
	}
	
	//This method will take the character guess by the player
	//and will return an ArrayList that has all of the indices
	//of an occurrence.
	public ArrayList<Integer> findOccurences(char guess)
	{
		ArrayList<Integer> occurence = new ArrayList<Integer>();
		
		for(int i = 0; i < word.size(); i++)
		{
			if(word.get(i) == guess)
				occurence.add(i);
		}
		
		return occurence;
	}
	
	//Takes in a guess and looks at the entire list of progress and sets each
	//character that matches the guess to that character.
	public void updateProgress(ArrayList<Integer> occ, char guess)
	{
		int j = 0;
		for(int i = 0; i < progress.size(); i++)
		{
			if( j < occ.size() && i == occ.get(j))
			{
				progress.set(i, guess);
				j++;
			}
		}
	}
	
	
	//Conditional for exit from the current round, checked at each point to see whether
	//the game should continue or not.
	public boolean checkWin()
	{
		for(int i = 0; i < word.size(); i++)
		{
			if(word.get(i) != progress.get(i))
				return false;
		}
		return true;
	}
	
}
