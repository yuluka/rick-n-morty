package model;

import java.util.ArrayList;

public class Board {

	private int rows;
	private int columns;
	private ArrayList<Square> squares;
	private String completeBoard;
	
	public Board(int columns, int rows) {
		this.rows = rows;
		this.columns = columns;
		
		squares = new ArrayList<Square>();
		completeBoard = "";
		
		createSquares();
		createBoard();
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getColumns() {
		return columns;
	}

	public void setColumns(int columns) {
		this.columns = columns;
	}
	
	public ArrayList<Square> getSquares() {
		return squares;
	}

	public void setSquares(ArrayList<Square> squares) {
		this.squares = squares;
	}
	
	public void createSquares() {
		for (int i = 0; i < rows*columns; i++) {
			squares.add(new Square(i+1));
		}
	}
	
	public void createBoard() {
		createBoard(0);
	}
	
	public void createBoard(int i) {
		if(i >= columns*rows) {
			return;
		}
		
		if(i %columns == 0 && i != 0) {
			int j = i;
			i += columns;
			completeBoard += "\n";
			inverseWay(i, j);
			completeBoard += "\n";
		}
		
		completeBoard += squares.get(i).squareToString() + "	";
		createBoard(++i);
	}
	
	public void inverseWay(int i, int j) {
		if(i == j) {
			return;
		}
		
		completeBoard += squares.get(i-1).squareToString() + "	";
		inverseWay(--i, j);
	}
	
	public String getBoard() {
		return completeBoard;
	}
}
