//Gallow.java
//Grant Wilkins
//May 2018
//This file includes the main graphical component of the program by 
//constructing the 'gallows' for the hangman. Largely this works by a double
//ArrayList the holds all of the possible states. At each point this will
//keep a hold of the number of guesses the user has left.
import java.util.ArrayList;
public class Gallow 
{
	//Current state of the gallow
	private ArrayList<String> currentState;
	//Counter to keep up with the state
	private int s;
	
	//This is the constructor that initializes the fields and the objects
	//that are implemented for the Gallow Class
	public Gallow()
	{
		s = 0;
		currentState = getState();
	}
	
	//Prints out the current state of the hang man
	//to the player at each turn
	public void makeState()
	{
		for(String str : currentState)
		{
			System.out.println(str);
		}
	}
	
	//Sets a certain state to its position in the main state array.
	//Keeps up with the number the state is at. This is a basic modifier
	//method.
	public void setStateInt(int state)
	{
		s = state;
	}
	
	//This is an accessor method for the state integer.
	public int getStateInt()
	{
		return s;
	}
	
	//Increments at each turn so that the counter can move up
	//And so non-explicit call can be made to this to increment the state
	public void incrementState()
	{
		s++;
		currentState = getState();
	}
	
	//This will evoke the winning clause within the game class.
	public void getWin()
	{
		s = 8;
		currentState = getState();
	}
	
	//Ensures an easy way to retrieve and set the loss to 
	//the state int and state function to that state.
	public void getLoss()
	{
		s = 7;
		currentState = getState();
	}
	
	
	public ArrayList<String> getState()
	{
		//Here we have all of the possible states in the game
		//They will have their assigned positions and the currentState
		//will update based on the point in the game.
		ArrayList<ArrayList<String>> states = new ArrayList<ArrayList<String>>();
		ArrayList<String> j = new ArrayList<String>();
		j.add("==========");
		j.add("  |     | ");
		j.add("        | ");
		j.add("        | ");
		j.add("        | ");
		j.add("        | ");
		j.add("        | ");
		j.add("----------");
		
		states.add(j);
		
		ArrayList<String> head = new ArrayList<String>();
		head.add("==========");
		head.add("  |     | ");
		head.add("  O     | ");
		head.add("        | ");
		head.add("        | ");
		head.add("        | ");
		head.add("        | ");
		head.add("----------");
		
		states.add(head);
		
		ArrayList<String> body = new ArrayList<String>();
		body.add("==========");
		body.add("  |     | ");
		body.add("  O     | ");
		body.add("  |     | ");
		body.add("        | ");
		body.add("        | ");
		body.add("        | ");
		body.add("----------");
		
		states.add(body);
		
		ArrayList<String> arm1 = new ArrayList<String>();
		arm1.add("==========");
		arm1.add("  |     | ");
		arm1.add("  O     | ");
		arm1.add(" \\|     | ");
		arm1.add("        | ");
		arm1.add("        | ");
		arm1.add("        | ");
		arm1.add("----------");
		
		states.add(arm1);
		
		ArrayList<String> arm2 = new ArrayList<String>();
		arm2.add("==========");
		arm2.add("  |     | ");
		arm2.add("  O     | ");
		arm2.add(" \\|/    | ");
		arm2.add("        | ");
		arm2.add("        | ");
		arm2.add("        | ");
		arm2.add("----------");
		
		states.add(arm2);
		
		ArrayList<String> leg1 = new ArrayList<String>();
		leg1.add("==========");
		leg1.add("  |     | ");
		leg1.add("  O     | ");
		leg1.add(" \\|/    | ");
		leg1.add("  |     | ");
		leg1.add(" /      | ");
		leg1.add("        | ");
		leg1.add("----------");
		
		states.add(leg1);
		
		ArrayList<String> leg2 = new ArrayList<String>();
		leg2.add("==========");
		leg2.add("  |     | ");
		leg2.add("  O     | ");
		leg2.add(" \\|/    | ");
		leg2.add("  |     | ");
		leg2.add(" / \\    | ");
		leg2.add("        | ");
		leg2.add("----------");
		
		states.add(leg2);
		
		ArrayList<String> gameOver = new ArrayList<String>();
		
		gameOver.add("==========");
		gameOver.add("  |     | ");
		gameOver.add("        | ");
		gameOver.add("  GAME  | ");
		gameOver.add("  OVER  | ");
		gameOver.add("        | ");
		gameOver.add("        | ");
		gameOver.add("----------");
		
		states.add(gameOver);
		
		ArrayList<String> win = new ArrayList<String>();
		
		win.add("==========");
		win.add("  |     | ");
		win.add("        | ");
		win.add("   YOU  | ");
		win.add("   WIN  | ");
		win.add("        | ");
		win.add("        | ");
		win.add("----------");
		
		states.add(win);
		
		return states.get(s);
		
	}
	
}
