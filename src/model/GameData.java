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
		if(seeds > (board.getColumns()*board.getRows())) { 
			return false; 
		} else if(seeds == 0) {
			return false;
		} else { 
			board.createSeeds(seeds); 
			 
			return true; 
		} 
	} 
	
	public static String printBoard(int boardVersion) {
		return board.getBoard(boardVersion);
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
	
	public static boolean isEndGame() {
		return board.isEndGame();
	}
	
	public static void calculateScore() {
		
	}
	
	public static int getPlayerSeeds(String player) {
		if(player == "R") {
			return board.getRick().getSeeds();
		} else {
			return board.getMorty().getSeeds();
		}
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
	
	public static String getWinner() {
		if(board.getWinner().getName().equals("M")) {
			return "Morty con " + board.getMorty().getSeeds();
		} else {
			return "Rick con " + board.getRick().getSeeds() + " semillas.";
		}
	}
}
