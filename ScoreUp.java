/**
 *  This program contains the rules of the game ScoreUp and runs the game.
 *	<Describe the game in brief here>
 *
 *	The Prompt, ScoreUpPlayer, DiceGroup, and Dice classes are required.
 *
 *  @author 
 *  @since	
 */

public class ScoreUp {
	private ScoreUpPlayer player1, player2;	// the two players
	private DiceGroup groupOfDice;		// the group of dice
	private boolean player1Turn;		// true = player1's turn, false = player2's turn
	private int round;					// current round in the game (1, 2, 3, ...)
	private TileBoard board;			// the board of tiles
	
	// Constants
	public final int NUM_DICE = 2;		// total number of dice
	public final int NUM_ROUNDS = 5;	// total number of rounds
	public final int NUM_TILES = 9; 	// total number of tiles in a round
	
	public static void main(String[] args) {
		ScoreUp su = new ScoreUp();
		su.run();
	}
	
	public ScoreUp() {
		player1 = new ScoreUpPlayer(5);
		player2 = new ScoreUpPlayer(5);
		round = 1;
		groupOfDice = new DiceGroup(NUM_DICE);
		board = new TileBoard(NUM_TILES);
	}
	
	public void run() {
		printIntroduction();
		createPlayers();
		chooseFirst();
		playGame();
	}
	
	/**
	 *	Prints the introduction screen
	 */
	public void printIntroduction() {
		System.out.println("\n");
		System.out.println("+------------------------------------------------------------------------------------+");
		System.out.println("|           ______   ______   ______   ______   ______   __  __   ______             |");
		System.out.println("|          /\\  ___\\ /\\  ___\\ /\\  __ \\ /\\  == \\ /\\  ___\\ /\\ \\/\\ \\ /\\  == \\            |");
		System.out.println("|          \\ \\___  \\\\ \\ \\____\\ \\ \\/\\ \\\\ \\  __< \\ \\  __\\ \\ \\ \\_\\ \\\\ \\  _-/            |");
		System.out.println("|           \\/\\_____\\\\ \\_____\\\\ \\_____\\\\ \\_\\ \\_\\\\ \\_____\\\\ \\_____\\\\ \\_\\              |");
		System.out.println("|            \\/_____/ \\/_____/ \\/_____/ \\/_/ /_/ \\/_____/ \\/_____/ \\/_/              |");
		System.out.println("|                                                                                    |");
		System.out.println("| WELCOME TO MONTA VISTA SCOREUP!                                                    |");
		System.out.println("|                                                                                    |");
		System.out.println("| ScoreUp is a dice game played between two players.  There are "
							+ NUM_ROUNDS + " rounds in a game   |");
		System.out.println("| of ScoreUp, and the players alternate turns.  In each turn, a player starts with   |");
		System.out.println("| the tiles 1, 2, 3, 4, 5, 6, 7, 8, and 9 showing.  The player then rolls a pair of  |");
		System.out.println("| dice.  After rolling the dice, the player adds up the dots on the dice, and then   |");
		System.out.println("| \"Scores Up\" any combination of numbers that equals the total number of dots        |");
		System.out.println("| showing on the dice. For example, if the total number of dots is 8, the player may |");
		System.out.println("| choose any of the following sets of numbers (as long as all of the numbers in the  |");
		System.out.println("| set have not yet been removed):                                                    |");
		System.out.println("|          8 or 7 & 1 or 6 & 2 or 5 & 3 or 5 & 2 & 1 or 4 & 3 & 1.                   |");
		System.out.println("|                                                                                    |");
		System.out.println("| The player then rolls the dice again, aiming to remove more numbers. The player    |");
		System.out.println("| continues throwing the dice and removing numbers until reaching a point at which,  |");
		System.out.println("| given the results produced by the dice, the player cannot remove any more numbers. |");
		System.out.println("| At that point, the player scores the sum of the numbers that have been removed.    |");
		System.out.println("| For example, if the numbers 2, 3, and 5 remain when the player rolls 6 & 3, the    |");
		System.out.println("| player's score is 35 (1 + 4 + 6 + 7 + 8 + 9 = 35). Play then passes to the next    |");
		System.out.println("| player.  After five rounds, the winner is the player with the highest total.       |");
		System.out.println("|                                                                                    |");
		System.out.println("| LET'S PLAY SOME SCOREUP!                                                           |");
		System.out.println("+------------------------------------------------------------------------------------+");
		System.out.println("\n");
	}

	/**
	 *	Prints the Round Scoreboard
	 */
	public void printRoundScore() {
		int num = 0;
		System.out.println("\n  NAME           Round 1    Round 2    Round 3    Round 4    Round 5     Total");
		System.out.println("+---------------------------------------------------------------------------------+");
		System.out.printf("| %-12s |", player1.getName());
		for (int i = 0; i < NUM_ROUNDS; i++) {
			num = player1.getRoundScore(i);
			if (num == 0) System.out.printf("          |", num);
			else System.out.printf("   %3d    |", num);
		}
		System.out.printf("   %4d    |\n", player1.getTotalScore());
		System.out.println("+---------------------------------------------------------------------------------+");
		System.out.printf("| %-12s |", player2.getName());
		for (int i = 0; i < NUM_ROUNDS; i++) {
			num = player2.getRoundScore(i);
			if (num == 0) System.out.printf("          |", num);
			else System.out.printf("   %3d    |", num);
		}
		System.out.printf("   %4d    |\n", player2.getTotalScore());
		System.out.println("+---------------------------------------------------------------------------------+");
	}
	
	public void createPlayers() {
		String name;
		name = Prompt.getString("Enter Player1 name: ").toUpperCase();
		player1.setName(name);
		System.out.print("\n");
		name = Prompt.getString("Enter Player2 name: ").toUpperCase();
		player2.setName(name);
		System.out.print("\n");
		System.out.println("Welcome " + player1.getName() + " and " + player2.getName() + "!");
		System.out.print("\n");
	}
	
	public void chooseFirst() {
		boolean tie = true;
		int startValue1;
		int startValue2;
		groupOfDice.initDice();
		do {
			System.out.print("Let's see who goes first. ");
			Prompt.getString(player1.getName() + ", please hit enter to roll the dice");
			groupOfDice.rollDice();
			startValue1 = groupOfDice.getTotal();
			groupOfDice.printDice();
			Prompt.getString(player2.getName() + ", it's your turn. Please hit enter to roll the dice");
			groupOfDice.rollDice();
			startValue2 = groupOfDice.getTotal();
			groupOfDice.printDice();
			System.out.println(player1.getName() + ", you rolled a sum of " + startValue1
					+ ", and " + player2.getName() + ", you rolled a sum of " + startValue2
					+ ".");
			if (startValue1 == startValue2) {
				System.out.println("\nDO OVER. Both of you got the same value!\n");
			}
			else {
				if (startValue1 > startValue2) {
					System.out.println("\nCONGRATULATIONS. " + player1.getName()
						+ ", you rolled a higher number so you get to go first.");
					player1Turn = true;
				}	
				else {
					System.out.println("\nCONGRATULATIONS. " + player2.getName()
						+ ", you rolled a higher number so you get to go first.");
					player1Turn = false;
				}
				tie = false;
			}
		} while(tie);
	}
	
	public void playGame() {
		while(round <= NUM_ROUNDS) {
			System.out.println("\nRound " + round + " of " + NUM_ROUNDS + " rounds\n");
			playTurn();
			System.out.println("\n");
			board = new TileBoard(NUM_TILES);
			if (player1Turn)
				player1Turn = false;
			else
				player1Turn = true;
			playTurn();
			System.out.println("\n");
			board = new TileBoard(NUM_TILES);
			if (player1Turn)
				player1Turn = false;
			else
				player1Turn = true;
			round++;
		}
		printRoundScore();
		if (player1.getTotalScore() > player2.getTotalScore()) {
			System.out.println("\nCONGRATULATIONS >>> " + player1.getName()
				+ " for being the HIGH SCORER.\n\n\n");
		}	
		else {
			System.out.println("\nCONGRATULATIONS >>> " + player2.getName()
				+ " for being the HIGH SCORER\n\n\n");
		}
	}
	
	public void playTurn() {
		int sum = 0;
		boolean badInput = false;
		String input;
		if(player1Turn) {
			Prompt.getString(player1.getName() + ", it's your turn to play. "
								+ "Please hit enter to roll the dice");
		}
		else {
			Prompt.getString(player2.getName() + ", it's your turn to play. "
								+ "Please hit enter to roll the dice");
		}
		groupOfDice.rollDice();
		groupOfDice.printDice();
		board.printTiles();
		sum = groupOfDice.getTotal();
		do {
			do {
				input = Prompt.getString("Enter the tiles to remove. For example, "
									+ "if you'd like to remove tiles 1, 2, and 5, enter 125");
				if (isValid(input) && sumsMatch(input, sum) && digitsMatchTiles(input)) {
					badInput = false;
					makeNewBoard(input);
				}
				else
					badInput = true;
			} while(badInput);
			groupOfDice.rollDice();
			groupOfDice.printDice();
			board.printTiles();
			sum = groupOfDice.getTotal();
		} while(hasPlay(sum) && boardNotClear());
		System.out.println("\nUh-oh, looks like there are no valid choices left.");
		if(player1Turn) {
			player1.scoreUpRound(round, board.computeRound());
			printRoundScore();
			Prompt.getString(player1.getName() + ", your turn has ended. "
								+ "Please hit enter to finish your turn");
		}
		else {
			player2.scoreUpRound(round, board.computeRound());
			printRoundScore();
			Prompt.getString(player2.getName() + ", your turn has ended. "
								+ "Please hit enter to finish your turn");
		}
	}
	
	public boolean isValid(String str) {
		int[] holdChar = new int[str.length()];
		for(int i = 0; i < str.length(); i++) {
			if(str.charAt(i) < '1' || str.charAt(i) > '9')
				return false;
			else
				holdChar[i] = Integer.parseInt(str.substring(i, i+1));
		}
		for(int i = 0; i < str.length(); i++) {
			for(int j = i+1; j < str.length(); j++) {
				if(holdChar[i] == holdChar[j])
					return false;
			}
		}
		return true;
	}
	
	public boolean sumsMatch(String str, int diceSum) {
		int temp = 0;
		for(int i = 0; i < str.length(); i++) {
			temp += Integer.parseInt(str.substring(i,i+1));
		}
		if (temp == diceSum)
			return true;
		return false;
	}
	
	public boolean digitsMatchTiles(String str) {
		int temp = 0;
		for(int i = 0; i < str.length(); i++) {
			temp = Integer.parseInt(str.substring(i,i+1));
			if (board.isTileScored(temp-1))
				return false;
		}
		return true;
	}
	public boolean hasPlay(int sum) {
		if (hasWithOne(sum) || hasWithTwo(sum) || hasWithThree(sum) || hasWithFour(sum))
			return true;
		else
			return false;
	}
	
	public boolean hasWithOne(int sum) {
		if (sum <= 9 && !board.isTileScored(sum-1))
			return true;
		return false;
	}
	
	public boolean hasWithTwo(int sum) {
		int max = sum/2 + 1;
		for (int i = 1; i < max; i++) {
			if (!board.isTileScored(i - 1))
				if((sum - i) <= 9 && i != (sum - i) &&!board.isTileScored(sum - i - 1))
					return true;
		}
		return false;
	}
			
	public boolean hasWithThree(int sum) {
		int max = sum/2;
		if (sum >= 6 & sum <= 8) {
			if (board.isTileScored(0))
				return false;
			else {
				for (int i = 2; i < max; i++) {
					if (!board.isTileScored(i - 1)) {
						if(!board.isTileScored(sum - i - 2))
							return true;
					}
				}
				return false;
			}
		}
		else if (sum >= 9 && sum <= 12) {
			if (sum == 12 && !board.isTileScored(2) && !board.isTileScored(3) && !board.isTileScored(4)) {
					return true;
			}
			else if (board.isTileScored(0) && board.isTileScored(1)) 
				return false;
			else if (!board.isTileScored(1) && board.isTileScored(0)) {
				for (int i = 3; i < max; i++) {
					if (!board.isTileScored(i - 1)) {
						if(i != (sum - i - 3) && !board.isTileScored(sum - i - 3))
							return true;
					}
				}
				return false;
			}
			else if (!board.isTileScored(0)) {
				for (int i = 2; i < max; i++) {
					if (!board.isTileScored(i - 1)) {
						if(!board.isTileScored(sum - i - 2))
							return true;
					}
				}
				return false;
			}
			else
				return false;
		}
		else
			return false;
	}

	public boolean hasWithFour(int sum) {
		if (sum >= 10) {
			if (board.isTileScored(0) || board.isTileScored(1))
				return false;
			else if (board.isTileScored(2)) {
				if (sum == 12 && !board.isTileScored(3) && !board.isTileScored(4))
					return true;
				return false;
			}
			else if (sum == 10 && !board.isTileScored(3))
				return true;
			else if (sum == 11 && !board.isTileScored(4))
				return true;
			else if (sum == 12 && !board.isTileScored(5))
				return true;
			else
				return false;
		}
		else
			return false;
	}

	public boolean boardNotClear() {
		for (int i = 0; i < NUM_TILES; i++) {
			if (!board.isTileScored(i))
				return true;
		}
		return false;
	}

	public void makeNewBoard(String str) {
		int temp = 0;
		for(int i = 0; i < str.length(); i++) {
			temp = Integer.parseInt(str.substring(i,i+1));
			board.clearTile(temp-1);
		}
	}
}