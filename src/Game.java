//Game.java
//Grant Wilkins
//May 2018
//This is the main driver class that will implement the hangman game and will
//run the game, so that the user can play it.
public class Game 
{
	public static void main(String[] args) 
	{
		//Driver Class, this initializes a Hangman class
		//and will run the game using our main method runGame
		Hangman game = new Hangman();
		game.runGame();
		
	}
}
