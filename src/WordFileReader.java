//WordFileReader.java
//Grant Wilkins
//May 2018
//This randomized file reader is inspired by a very similar engine provided by
//Duke University CS201. The main differences are the implementation of the
//random string method because of the dictionary interface and the InputStream framework.
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class WordFileReader implements Dictionary 
{
	
	Map<Integer,ArrayList<String>> myWordMap;
	Random myRandom;
	
	public WordFileReader() {
		myWordMap = new HashMap<Integer,ArrayList<String>>();
		myRandom = new Random();
	}
	
	//Reads file and takes the input file of possible words
	//This uses an InputStream to find the file path
	//relatively.
	public boolean readFile(InputStream filename) {
		try {
			//FileReader dataFile = new FileReader(s);
			//BufferedReader bufferedReader = new BufferedReader(dataFile);
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(filename, "UTF-8"));
			String currentLine = bufferedReader.readLine();

			while(currentLine != null) {
				String trimmedWord = currentLine.trim();
				@SuppressWarnings("deprecation")
				Integer length = new Integer(trimmedWord.length());
				if(!myWordMap.containsKey(length)) {
					myWordMap.put(length, new ArrayList<String>());
				}
				myWordMap.get(length).add(trimmedWord);
				currentLine = bufferedReader.readLine();
			}
			bufferedReader.close();
		} 
		catch (IOException e) {
			System.err.println("A error occured reading file: " + e);
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	//This randRead will use a random word length and 
	//then will select a random word file from the
	//text file
	public String randRead(int wordLength) {
		ArrayList<String> wordList =  myWordMap.get(wordLength);
		
		if (wordList == null || wordList.size() == 0) {
			return null; //there are no words of this length
		}
		
		int selection = myRandom.nextInt(wordList.size());
		return wordList.get(selection);
	}
}

