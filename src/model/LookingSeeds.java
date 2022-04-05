package model;

public class LookingSeeds {

	private static Board board;

	public LookingSeeds() {
		
	}
	
	public static void createBoard(int col, int rows) {
		board = new Board(col, rows);
	}
	
	public static String printBoard() {
		return board.getBoard();
	}
}
