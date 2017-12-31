/**
 *	A class to print out the SnakeGame board to the screen.
 *	It has utilities to print the board given a Snake object and
 *	a target Coordinate object.
 *	The height and width of the board must be provided to the constructor.
 *	The structure of the board will look like this:
 *
 *	+ - - - - - - - - - - - +
 *	|                       |
 *	|       @ * *           |
 *	|           *           |
 *	| +         *           |
 *	|           *           |
 *	|                       |
 *	+ - - - - - - - - - - - +
 *
 *	@author		Kevin Zhou
 *	@since		November 13th, 2017
 */
public class SnakeBoard
{
	private int widthOfBoard;		// The width of the board
	private int heightOfBoard;		// The height of the board
	
	private char[][] board;			// The 2D array to hold the board
	
	// Constructor
	public SnakeBoard(int height, int width)
	{
		heightOfBoard = height;
		widthOfBoard = width;
		// create the 2D array to hold the board, plus 2 for the borders
		board = new char[heightOfBoard + 2][widthOfBoard + 2];
		// fill in the border character
	}
	
	/**
	 *	Print the board to the screen. The board will contain the Snake's location
	 *	and the target's location. The snake's head will be an ampersand (@) and
	 *	its tail will be asterisks (*). The target will be a plus sign (+).
	 *	@param snake		a Snake object
	 *	@param target		a Coordinate object of the target
	 */
	public void printBoard(Snake snake, Coordinate target)
	{
		// clear previous board by setting all coordinates to ' '
		for (int row = 0; row < board.length; row++) 
			for (int col = 0; col < board[row].length; col++)
				board[row][col] = ' ';
		// print the snake by looping through the snake coordinates
		for(int i = 0; i < snake.size(); i++)
		{
			int row = snake.get(i).getRow();
			int col = snake.get(i).getCol();
			// print '@' for the head
			if(i == 0)
				board[row][col] = '@';
			// print '*' for the rest
			else
				board[row][col] = '*';
		}
		// print the target
		board[target.getRow()][target.getCol()] = '+';
		fillBorder();
		//print the spaces between the columns for additional spacing
		for (int row = 0; row < board.length; row++) 
		{
			for (int col = 0; col < board[row].length; col++)
				System.out.print(board[row][col] + " ");
			System.out.println();
		}
	}

	/**
	 * Fill the board array with the border characters.
	 */
	private void fillBorder()
	{
		// Fill in the corners with '+'
		board[0][0] = '+';
		board[0][widthOfBoard + 1] = '+';
		board[heightOfBoard + 1][0] = '+';
		board[heightOfBoard + 1][widthOfBoard + 1] = '+';
		// Fill in the top and bottom rows with the '-'
		for (int a = 1; a <= widthOfBoard; a++) {
			board[0][a] = '-';
			board[heightOfBoard + 1][a] = '-';
		}
		// Fill in the left and right columns with '|'
		for (int a = 1; a <= heightOfBoard; a++) {
			board[a][0] = '|';
			board[a][widthOfBoard + 1] = '|';
		}
	}
	
	
	/********************************************************/
	/********************* For Testing **********************/
	/********************************************************/
	
	public static void main(String[] args) {
		// Create the board
		int height = 10, width = 15;
		SnakeBoard sb = new SnakeBoard(height, width);
		// Place the snake
		Snake snake = new Snake(3, 3);
		// Place the target
		Coordinate target = new Coordinate(1, 7);
		// Print the board
		sb.printBoard(snake, target);
	}
}