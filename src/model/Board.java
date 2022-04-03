package model;

import java.util.ArrayList;

public class Board {

	private int rows;
	private int columns;
	private ArrayList<Square> squares;
	
	public Board(int columns, int rows) {
		this.rows = rows;
		this.columns = columns;
		squares = new ArrayList<Square>();
		createSquares();
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
	
	public String printSquares(int i, String board) {
		if(i == columns*rows) {
			return "";
		}
		
		board = squares.get(i).squareToString() + "	";
		
		if((i+1) %columns == 0 && i != 0) {
			board += "\n";
		}
		
		return board + printSquares(++i, board);
	}
	
	public String printSquares() {
		return printSquares(0, "");
	}
}
