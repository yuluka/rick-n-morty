package model;

//import java.util.ArrayList;

public class GameData {

	private static Board board;
	//private static ArrayList<String> scores;

	public GameData() {
		
	}
	
	public static void createBoard(int col, int rows) {
		board = new Board(col, rows);
	}
	
	public static String printBoard() {
		return board.getBoard();
	}
	
	public static int launchDice() {
		int dice = (int) (Math.random()*6+1);
		
		return dice;
	}
	
	public static void movePlayer(int wayToMove, int dice) {
		if(wayToMove == 1) {
			board.movePlayerForward(dice);
		} else {
			board.movePlayerBackward(dice);
		}
	}
}
