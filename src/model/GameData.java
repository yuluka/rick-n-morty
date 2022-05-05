package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class GameData {

	private static Board board;
	private static ArrayList<Player> scores = new ArrayList<Player>();
	
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
	
	public static boolean isEndGame() {
		return board.isEndGame();
	}
	
	public static void registerScore() {
		Player winner = board.getWinner();
		board.calculateScore();
		
		int index = searchPlayer(winner);
		
		if(index < 0) {
			scores.add(winner);
		} else {
			scores.get(index).addScore(winner.getScore());
		}
	}
	
	public static int searchPlayer(Player goal) {
		if(scores.size() > 0) {
			sortScores();
			return searchPlayer(0, scores.size(), goal);
		} else {
			return -1;
		}
	}
	
	private static int searchPlayer(int beg, int end, Player goal) {
		if(end < beg) {
			return -1;	
		}
		
		int mid = (end+beg)/2;
		
		int comparation = goal.getUsername().compareTo(scores.get(mid).getUsername());
		
		if(comparation == 0) {
			return mid;
		} else if(comparation < 0) {
			end = mid-1;
			return searchPlayer(beg,end,goal);
		} else {
			beg = mid+1;
			return searchPlayer(beg,end,goal);
		}
	}
	
	public static String getWinner() {
		if(board.getWinner().getName().equals("M")) {
			return "Morty con " + board.getMorty().getSeeds() + " semillas";
		} else {
			return "Rick con " + board.getRick().getSeeds() + " semillas.";
		}
	}
	
	private static void sortScores() {
		Collections.sort(scores, new Comparator<Player>() {
			@Override
			public int compare(Player A, Player B) {
				return A.getUsername().compareTo(B.getUsername());
			}
		});
	}
	
	public static String getScores() {
		String scoresStr = "";
		
		for (int i = 0; i < scores.size(); i++) {
			scoresStr += "\n" + scores.get(i).getUsername() + ": " + scores.get(i).getScore();
		}
		
		return scoresStr;
	}
}
