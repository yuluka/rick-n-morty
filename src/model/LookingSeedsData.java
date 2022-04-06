package model;

import java.util.ArrayList;

public class LookingSeedsData {

	private static Board board;
	private static ArrayList<String> scores;

	public LookingSeedsData() {
		
	}
	
	public static void createBoard(int col, int rows) {
		board = new Board(col, rows);
	}
	
	public static String printBoard() {
		return board.getBoard();
	}
}
