package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

@SuppressWarnings("serial")
public class GameData implements Serializable{

	private static Board board;
	private static ArrayList<Player> scores = new ArrayList<Player>();
	
	public GameData() {
		
	}
	
	public static boolean createBoard(int col, int rows) {
		if ((col*rows) <= 1) {
			return false;
		} else {
			board = new Board(col, rows);
			return true;
		}
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
		
		saveScores();
	}
	
	public static int searchPlayer(Player goal) {
		if(scores.size() > 0) {
			sortScoresByName();
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
		
		if(mid >= scores.size()) {
			return -1;
		}
		
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
			return "Morty con " + board.getMorty().getSeeds() + " semillas.";
		} else {
			return "Rick con " + board.getRick().getSeeds() + " semillas.";
		}
	}
	
	private static void sortScoresByName() {
		Collections.sort(scores, new Comparator<Player>() {
			@Override
			public int compare(Player A, Player B) {
				return A.getUsername().compareTo(B.getUsername());
			}
		});
	}
	
	private static void sortScoresByScore() {
		for (int i = 1; i < scores.size(); i++) {
			for (int j = 0; j < i; j++) {
				if(scores.get(i).getScore() > scores.get(j).getScore()) {
					Player aux = scores.get(i);
					scores.remove(i);
					scores.add(j, aux);
					break;
				}
			}
		}
	}
	
	public static String getTop5() {
		String top5 = "";
		sortScoresByScore();
		
		for (int i = 0; i < scores.size() && i < 5; i++) {
			top5 += "\n" + scores.get(i).getUsername() + ": " + scores.get(i).getScore();
		}
		
		return top5;
	}
	
	public static String getScores() {
		String scoresStr = "";
		
		for (int i = 0; i < scores.size(); i++) {
			scoresStr += "\n" + scores.get(i).getUsername() + ": " + scores.get(i).getScore();
		}
		
		return scoresStr;
	}
	
	public static void saveScores() {
		try {
			File file = new File("data/scores.temp");
			FileOutputStream fos = new FileOutputStream(file);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(scores);
			
			oos.close();			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public static void loadScores() {
		try {
			File file = new File("data/scores.temp");
			FileInputStream fis = new FileInputStream(file);
			ObjectInputStream ois = new ObjectInputStream(fis);
			Object object = ois.readObject();
			scores = (ArrayList<Player>) object;
			
			ois.close();
		} catch (FileNotFoundException e) {
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
