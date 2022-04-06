package model;

import java.util.ArrayList;

public class Board { //Falta crear el board en la estructura enlazada.

	private int rows;
	private int columns;
	private String completeBoard;
	
	private ArrayList<Square> squares;
	private Square head;
	private Square tail;//Estos dos van a reemplazar el arraylist	
	
	public Board(int columns, int rows) {
		this.rows = rows;
		this.columns = columns;
		
		squares = new ArrayList<Square>();
		completeBoard = "";
		
		//createSquares();
		//createBoard();
		createSquaresLinkedList();
		createBoardLinkedList();
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
		createSquares(0);
	}
	
	public void createSquares(int i) {
		if(i == columns*rows) {
			return;
		}
		
		squares.add(new Square(i+1));
		createSquares(++i);
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
		
		if(i >= columns*rows) {
			return;
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
	
	
	//THINGS WITH LINKED LISTS
	public void createSquaresLinkedList() {
		createSquaresLinkedList(new Square(1), 0);
	}
	
	public void createSquaresLinkedList(Square newSquare, int i) {
		if(i == columns*rows) {
			return;
		}
		
		if(tail == null) {
			tail = newSquare;
			tail.setNext(newSquare);
			tail.setPrevious(newSquare);
			head = newSquare;
			head.setNext(newSquare);
			head.setPrevious(newSquare);
		} else {
			tail.setNext(newSquare);
			newSquare.setNext(head);
			newSquare.setPrevious(tail);
			tail = newSquare;	
			head.setPrevious(tail);
		}
		
		++i;		
		createSquaresLinkedList(new Square(i+1), i);
	}
	
	public void createBoardLinkedList() {
		createBoardLinkedList(0, head);
	}
	
	public void createBoardLinkedList(int i, Square current) {
		if(i >= columns*rows) {
			return;
		}
		
		if(i %columns == 0 && i != 0) {
			int j = i;
			i += columns;
			completeBoard += "\n";
			current = current.getXNext(columns-1);
			inverseWayLinkedList(i, j, current);
			completeBoard += "\n";
		}
		
		if(i >= columns*rows) {
			return;
		}
		
		completeBoard += current.squareToString() + "	";
		createBoardLinkedList(++i, current.getNext());
	}
	
	public void inverseWayLinkedList(int i, int j, Square current) {
		if(i == j) {
			return;
		}
		
		completeBoard += current.getPrevious().squareToString() + "	";
		inverseWayLinkedList(--i, j, current.getPrevious());
	}
	
	public String getBoard() {
		return completeBoard;
	}
}
