//Gallow.java
//Grant Wilkins
//May 2018
//This class pulls together all of the other classes by creating the
//runGame() method to be used in the driver class. All of the other
//methods merely help this class function with extra effects like 
//keeping a track of the number of games you win.
import java.util.ArrayList;
import java.util.Scanner;
public class Hangman 
{
	private ArrayList<Character> guess;
	private HangmanRules hang;
	private Gallow gallow;
	private int numGames, numWins, numLosses, welcomeCount, winEntry;
	
	//Constructor built to initialize all of the other class' 
	//objects.
	public Hangman()
	{
		hang = new HangmanRules();
		guess = new ArrayList<Character>();
		gallow = new Gallow();
		numGames = 0;
		numWins = 0;
		numLosses = 0;
		welcomeCount = 0;
		winEntry = 0;
		//new Music();
	}
	
	//Prints out the guessed chars
	public void getGuess()
	{
		System.out.println("The characters you have guessed are: ");
		for(Character guessedChars : guess)
			System.out.print(guessedChars);
		System.out.println();
	}
	
	//Helps reset the game to the original setting so that people
	//can play the game again.
	public void reset()
	{
		gallow = new Gallow();
		guess = new ArrayList<Character>();
		hang = new HangmanRules();
		
	}
	
	//This is the main method behind the game that unites all of the overarching pieces.
	//This is where most of the game comes together. Each comment in the below method 
	//will explain the purpose of loops and of certain implementations.
	public void runGame()
	{
		@SuppressWarnings("resource")
		Scanner guesser = new Scanner(System.in);
		//Checks to see if this is the first game and if the player
		//wants to start playing.
		while(numGames == 0)
		{
			
			if(welcomeCount == 0)
				welcome();
			welcomeCount++;
			System.out.println("Are you ready to play? [Y/n]");
			char initializationOfGame = guesser.next().charAt(0);
			if(yes(initializationOfGame))
				break;
			else if (no(initializationOfGame))
			{
				System.out.println("We hope you come back to play again!");
				gallow.setStateInt(10);
				break;
			}
			else
			{
				System.out.println("I am sorry that is not a valid response, please enter your answer again!");
			}
		}
		
		//After a user exits the above loop for first play they will begin 
		//their game. At this point the game begins and the conditionals
		//make sure that the player has not exceeded the number of guesses and that
		//they have not already won their game.
		while(gallow.getStateInt() < 6 && !hang.checkWin())
		{
			//At each iteration the player will see their hangman's state
			//and their guesses and their progress.
			gallow.makeState();
			hang.getProgress();
			getGuess();
			System.out.println("You have " + (6-gallow.getStateInt()) + " guesses left in this round!");
			
			//Prompts for a next guess
			System.out.println("What is your next guess? ");
			char c = guesser.next().charAt(0);
			
			//Ensures the user has not already guessed, if not enters the possibility of in
			//the word or out of the word by using the findOccurrences
			//in the hangmanRules class
			if(guess.indexOf(c)  == -1)
			{
				guess.add(c);
				ArrayList<Integer> occurences = hang.findOccurences(c);
				if(occurences.size() == 0)
				{
					gallow.incrementState();
					System.out.println("***Your guess is not in the word!***");
				}
				else
				{
					hang.updateProgress(occurences, c);
					System.out.println("***Your guess was found!***");
				}
			}
			else
			{
				System.out.println("***You already guessed that!***");
			}	
		}
		
		//After the loop is exited in some way we check to see
		//if the player won or if the player lost. The first
		//conditional checks loss and the else is the winning clause.
		numGames++;
		if(gallow.getStateInt() == 6 || gallow.getStateInt() == 7)
		{
			//gallow.printFall();
			if(gallow.getStateInt() == 6)
			{
				numLosses++;
				gallow.makeState();
				gallow.getLoss();
				gallow.makeState();
				hang.printAnswer();
			}
			
			System.out.println("Would you like to continue playing [Y/n]?");
			char answer = guesser.next().charAt(0);
			if(yes(answer))
			{
				reset();
				runGame();
			}
			else if(no(answer))
			{
				finishPlaying();
			}
			else
			{
				System.out.println("I am sorry that is an invalid response, please try again.");
				runGame();
			}
		}
		//This makes sure that the game will cut off when the player responds no,
		//seeing as the state is set to ten when the player types no.
		else if(gallow.getStateInt() == 10)
		{
			
		}
		else
		{
			//Makes sure wins are not counted if someone doesn't respond correctly to the prompting
			if(winEntry == 0)
			{
				winEntry++;
				numWins++;
				gallow.getWin();
				gallow.makeState();
				hang.printAnswer();
			}
			System.out.println("Would you like to continue playing [Y/n]?");
			char answer = guesser.next().charAt(0);
			if(yes(answer))
			{
				winEntry = 0;
				reset();
				runGame();
			}
			else if(no(answer))
			{
				winEntry = 0;
				finishPlaying();
			}
			else
			{
				System.out.println("I am sorry that is not a valid response, please try again.");
				runGame();
			}
		}
	}
	
	//The yes and no methods process a one character
	//response by a user to see if they give yes or no to 
	//the prompting from the system.
	public boolean yes(char response)
	{
		return (response == 'y' || response == 'Y');
	}
	
	
	public boolean no(char response)
	{
		return (response == 'n' || response == 'N');
	}
	
	//Welcomes, provides authors, allows controls to be displayed before playing
	public void welcome()
	{
		ArrayList<String> welcome = new ArrayList<String>();
		welcome.add("===================================");
		welcome.add("=        WELCOME TO HANGMAN       =");
		welcome.add("=         BY GRANT WILKINS        =");
		welcome.add("=  -----------------------------  =");
		welcome.add("=            CONTROLS:            =");
		welcome.add("=   -As the state is printed out  =");
		welcome.add("=   type in a character for your  =");
		welcome.add("=   guess. If you type more than  =");
		welcome.add("=   a character only the first    =");
		welcome.add("=   character will be your guess! =");
		welcome.add("=                                 =");
		welcome.add("=   -The blanks under progress    =");
		welcome.add("=   show the remaining character  =");
		welcome.add("=       you must guess to win!    =");
		welcome.add("=                                 =");
		welcome.add("=   You have 6 incorrect guesses  =");
		welcome.add("=            per word!            =");
		welcome.add("=                                 =");
		welcome.add("=     GOOD LUCK! AND HAVE FUN!    =");
		welcome.add("===================================");
		
		for(String s: welcome)
			System.out.println(s);
		System.out.println();
	}
	
	//Prints the exit message and helps with the ending to the game
	//makes sure there is a conclusion with the number of games
	//and the ability to end.
	public void finishPlaying()
	{
		ArrayList<String> done = new ArrayList<String>();
		done.add("===================================");
		done.add("=       THANKS FOR PLAYING!       =");
		done.add("=       YOU WON "  + numWins + " GAME(S)         =");
		done.add("=       YOU LOST "  + numLosses + " GAME(S)        =");
		done.add("=       CONGRATULATIONS!          =");
		done.add("===================================");
		
		//Personalizes the statements of the exit credits to the performance of the user
		double ratio = 0;
		if(numLosses != 0)
			ratio = ((double) numWins)/((double) numLosses);
		else
			ratio = 10;
		
		if(ratio < 1)
			done.set(4, "=      BETTER LUCK NEXT TIME      =" );
		else if(ratio == 1.0)
			done.set(4, "=          RIGHT ON IT!           =");
		
		for(String s : done)
			System.out.println(s);
		
	}
	
	
}
