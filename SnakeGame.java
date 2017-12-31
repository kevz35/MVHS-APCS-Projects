import java.io.PrintWriter;
import java.util.Scanner;
/**
 *	SnakeGame is a one player game in which the user controls a snake
 * 	with the w, a, s, d keys. The snake is an array list and moves on a
 * 	coordinate board. It eats targets to gain points. The snake gets longer
 * 	by one coordinate for each target it eats, making the game progressively
 * 	harder. The snake cannot cross over itself or run into the border.
 * 	The game ends when the user chooses to quit, by entering q
 * 	as the input. The user can also choose to save a game with f, load
 * 	a game at the start with y, or ask for help commands with h.
 *	
 *	@author	    Kevin Zhou
 *	@since	    November 13th, 2017
 */
public class SnakeGame {
	
	private Snake snake;		// the snake in the game
	private SnakeBoard board;	// the game board
	private Coordinate target;	// the target for the snake
	private int score;			// the score of the game

	// declare the number of rows and columns as a constant int
	private final int NUM_ROWS = 20;
	private final int NUM_COLS = 25;

	/**
	 * 	Constructor for the SnakeGame class
	 * 	Generates a random location for the snake and target to spawn
	 * 	Initializes the snake, board, target, and score
	 */
	public SnakeGame()
	{
		// initialize the board with the constant values for rows and columns
		board = new SnakeBoard(NUM_ROWS, NUM_COLS);

		// use Math.random() to generate the spawn location for the snake
		// make sure to subtract the snake's length for the row spawn
		int row = 1 + (int)(Math.random() * (NUM_ROWS - 4));
		int col = 1 + (int)(Math.random() * NUM_COLS);
		// initialize the snake at the generated spawn location
		snake = new Snake(row, col);

		// use Math.random() to generate the spawn location for the target
		int r = 1 + (int)(Math.random() * NUM_ROWS);
		int c = 1 + (int)(Math.random() * NUM_COLS);
		// this boolean is used determine if a new target location is needed
		boolean findNew = false;
		// this do while loop is used to check if the target is on the snake
		do
		{
			// set findNew to false so the loop will exit if the target
			// is not found on the snake
			findNew = false;
			// use a for each loop to loop through the snake's coordinates
			for(Coordinate temp: snake)
				// if the target spawn location is found
				if(r == temp.getRow() && c == temp.getCol())
				{
					// generate a new spawn location for the target
					r = 1 + (int)(Math.random() * NUM_ROWS);
					c = 1 + (int)(Math.random() * NUM_COLS);
					// set findNew to true to check if the newly
					// generated spawn location is on the snake
					findNew = true;
				}
		}while(findNew);
		// initialize the target at the generated spawn location
		target = new Coordinate(r, c);
		// initialize the score to zero
		score = 0;
	}

	public static void main(String[] args)
	{
		SnakeGame sg = new SnakeGame();
		sg.run();
	}	

	/**
         * This run method plays the SnakeGame. It takes in user input in a switch
         * statement and appropriately moves the snake, prints help commands, or quits/saves
	 * the game depending on the char the user enters.
         * Precondition: in order for a game to be loaded, a game must have been
         * 		previously saved
         */
	public void run()
	{
		// print the starting messages
		System.out.println("Welcome to the SnakeGame!\n");
		System.out.println("Let's get started.\n");
		// use Prompt to ask the user if they want to load their last save
		char input = Prompt.getChar("Would you like to load the last save? (y or n)");
		// if the user says yes
		if(input == 'y')
		{
			// remove the current snake
			snake.clear();
			// use OpenFile to read in save.txt, where the saved game data is stored
			Scanner infile = OpenFile.openToRead("save.txt");
			String str = infile.nextLine();
			// this for loop reads the text file and replaces everything that is not
			// a digit with a space
			for(int i = 0; i < str.length(); i++)
				if(!Character.isDigit(str.charAt(i)))
					str = str.substring(0, i) + " " + str.substring(i + 1);
			// add the singular coordinate values to a string array
			String[] parts = str.split(" ");
			// convert the singular coordinate values to coordinate pairs
			for (int i = 0; i < parts.length - 1; i ++)
			{
				if(parts[i].length() > 0 && parts[i + 1].length() > 0)
				{
    				int x = Integer.parseInt(parts[i].trim());
    				int y = Integer.parseInt(parts[i + 1].trim());
				// add the coordinate to the snake
   				snake.add(new Coordinate(x, y));
   				}
			}
			// the next line contains the target coordinate values
			str = infile.nextLine();
			int x = Integer.parseInt(str.substring(1, str.indexOf(",")));
			int y = Integer.parseInt(str.substring(str.indexOf(",") + 1, str.indexOf(")")));
			// initialize the saved target location
			target = new Coordinate(x, y);
			// the last line contains the score
			score = Integer.parseInt(infile.nextLine());
		}
		System.out.println(snake);
		System.out.println(target);
		System.out.println(score);

		// print the new version of the board with the saved snake and target
		board.printBoard(snake, target);
		input = Prompt.getChar("Score: " + score + " (w - North, s - South, d - East, a - West, h - Help)");
		do
		{
			// use the first coordinate of the snake, aka the snake's head
			int row = snake.get(0).getRow();
			int col = snake.get(0).getCol();

			Coordinate last = new Coordinate(1, 1);
			switch(input)
			{
				// if the user enters 'w', move the snake up 
				case 'w':
					// check if the coordinate one row up is valid
					if(isValid(new Coordinate(row - 1, col)))
					{
						// add the new coordinate to the snake
						snake.add(0, new Coordinate(row - 1, col));
						// remove the tail coordinate of the snake
						last = snake.remove(snake.size() - 1);
					}
					break;
				// if the user enters 's', move the snake down 
				case 's':
					// check if the coordinate one row down is valid
					if(isValid(new Coordinate(row + 1, col)))
					{
						// add the new coordinate to the snake
						snake.add(0, new Coordinate(row + 1, col));
						// remove the tail coordinate of the snake
						last = snake.remove(snake.size() - 1);
					}
					break;
				// if the user enters 'd', move the snake right
				case 'd':
					// check if the coordinate one column right is valid
					if(isValid(new Coordinate(row, col + 1)))
					{
						// add the new coordinate to the snake
						snake.add(0, new Coordinate(row, col + 1));
						// remove the tail coordinate of the snake
						last = snake.remove(snake.size() - 1);
					}
					break;
				// if the user enters 'a', move the snake left
				case 'a':
					// check if the coordinate one column left is valid
					if(isValid(new Coordinate(row, col - 1)))
					{
						// add the new coordinate to the snake
						snake.add(0, new Coordinate(row, col - 1));
						// remove the tail coordinate of the snake
						last = snake.remove(snake.size() - 1);
					}
					break;
				// if the user enters 'h', print the commands
				case 'h':
					System.out.println("\nCommands:");
					System.out.println(" w - move north");
					System.out.println(" s - move south ");
					System.out.println(" d - move east ");
					System.out.println(" a - move west ");
					System.out.println(" h - help");
					System.out.println(" f - save game to file");
					System.out.println(" q - quit ");
					Prompt.getString("Press enter to continue");
					break;
				// if the user enters 'f', create a txt file and print the
				// snake, target, and score into the file
				case 'f':
					PrintWriter outfile = OpenFile.openToWrite("save.txt");
					outfile.println(snake);
					outfile.println(target);
					outfile.println(score);
					outfile.close();
					System.out.println("Score Saved");
					break;
			}
			// if the snake's head or first coordinate is now on the target
			if(snake.get(0).equals(target))
			{
				// increment the score
				score++;
				//
				snake.add(last);
				// generate a new spawn location for the target
				int r = 1 + (int)(Math.random() * NUM_ROWS);
				int c = 1 + (int)(Math.random() * NUM_COLS);
				// this boolean is used determine if a new target location is needed
				boolean findNew = false;
				do
				{
					// set findNew to false so the loop will exit if the target
					// is not found on the snake
					findNew = false;
					// use a for each loop to loop through the snake's coordinates
					for(Coordinate temp: snake)
						// if the target spawn location is found
						if(r == temp.getRow() && c == temp.getCol())
						{
							// generate a new spawn location for the target
							r = 1 + (int)(Math.random() * NUM_ROWS);
							c = 1 + (int)(Math.random() * NUM_COLS);
							// set findNew to true to check if the newly
							// generated spawn location is on the snake
							findNew = true;
						}
				}while(findNew);
				// initialize the target at the generated spawn location
				target = new Coordinate(r, c);
			}
			// print the board with the new snake and target locations
			board.printBoard(snake, target);
			// prompt the user with commands
			input = Prompt.getChar("Score: " + score + " (w - North, s - South, d - East, a - West, h - Help)");
			if(input == 'q')
			{
				// ask the user if they want to quit
				char choice = Prompt.getChar("Do you want to quit? (y or n)");
				// if the user answers no
				if(choice == 'n')
					// prompt the user with commands again
					input = Prompt.getChar("Score: " + score + " (w - North, s - South, d - East, a - West, h - Help)");
			}
		// continue running the game if the last user input was not ‘q’
		}while(input != 'q');
		// print the user's final score and an ending message
		System.out.println("Final score = " + score);
		System.out.println("\nThanks for playing the SnakeGame!!!");
	}

	/**
         * Checks if the coordinate that the snake wants to move to is valid
         *                                        of tokens found
         * @param c            the coordinate the snake wants to move to
         * @return              true if the coordinate is not part of the snake
	 *			or part of the border, false if otherwise
         */
	public boolean isValid(Coordinate c)
	{
		// use a for each loop to loop through the snake’s coordinates
		for(Coordinate temp: snake)
			// if the coordinate the snake wants to move to is found
			if(temp.equals(c))
				// the coordinate is not valid
				return false;
		// if the coordinate is within the boundaries of the board, return true
		if (c.getRow() >= 1 && c.getRow() <= NUM_ROWS && c.getCol() >= 1 && c.getCol() <= NUM_COLS)
			return true;
		// if not, return false
		return false;
	}
}