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
	
	public static boolean createPortals(int portals) {
		if(portals >= (board.getColumns()*board.getRows())/2) {
			return false;
		} else {
			board.generatePortals(portals);
			
			return true;
		}
	}
	
	public static boolean createSeeds(int seeds) {
		if(seeds> (board.getColumns()*board.getRows())) {
			return false;
		}else {
			board.generateSeeds(seeds);
			
			return true;
		}
	}
	
	public static String printBoard() {
		return board.getBoard();
	}
	
	public static int launchDice() {
		int dice = board.launchDice();
		
		return dice;
	}
	
	public static int movePlayer(int wayToMove, int dice) {
		if(wayToMove == 1) {			
			board.movePlayerForward(dice);
		} else {
			board.movePlayerBackward(dice);
		}
		
		return 0;
	}
	
	public static void createPlayers(String usernameR, String usernameM) {
		board.positionPlayer(usernameR, usernameM);
	}
	
	public static String getTurn() {
		if(board.getTurn()) {
			return "Rick";
		} else {
			return "Morty";
		}
	}
}
