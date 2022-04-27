package model;

public class Board {

	private int rows;
	private int columns;
	private String completeBoard;
	
	private Square head;
	private Square tail;
	
	public Board(int columns, int rows) {
		this.rows = rows;
		this.columns = columns;
		
		completeBoard = "";
		
		createBoard();
		setPlayer("R");
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
	
	//THINGS WITH LINKED LISTS
	public void createBoard() {
		createBoard(new Square(1), 0);
	}
	
	private void createBoard(Square newSquare, int i) {
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
		createBoard(new Square(i+1), i);
	}
	
	public void createBoardStr() { //trigger
		completeBoard = "";
		createBoardStr(0, head);
	}
	
	private void createBoardStr(int i, Square current) {
		if(i >= columns*rows) {
			return;
		}
		
		if(i %columns == 0 && i != 0) {
			i += columns;
			completeBoard += "\n";
			
			current = current.getXNext(columns-1);
			inverseWayBoardStr(0, current);
			
			completeBoard += "\n";
		}
		
		if(i >= columns*rows) {
			return;
		}
		
		completeBoard += current.squareToString() + "	";
		createBoardStr(++i, current.getNext());
	}
	
	private void inverseWayBoardStr(int i, Square current) {
		if(i == columns) {
			return;
		}
		
		completeBoard += current.getPrevious().squareToString() + "	";
		inverseWayBoardStr(++i, current.getPrevious());
	}
	
	public String getBoard() {
		createBoardStr();
		return completeBoard;
	}
	
	public void movePlayerForward(int dice) {
		movePlayerForward(dice,head);
	}
	
	private void movePlayerForward(int dice, Square current) {
		if(dice == 0) {
			return;
		} else if(current.getPlayer().isEmpty()) {
			movePlayerForward(dice, current.getNext());
			return;
		}
		
		current.getNext().setPlayer(current.getPlayer());
		current.setPlayer("");
		movePlayerForward(--dice, current.getNext());
	}
	
	public void movePlayerBackward(int dice) {
		movePlayerBackward(dice,head);
	}
	
	private void movePlayerBackward(int dice, Square current) {
		if(dice == 0) {
			return;
		} else if(current.getPlayer().isEmpty()) {
			movePlayerBackward(dice, current.getNext());
			return;
		}
		
		current.getPrevious().setPlayer(current.getPlayer());
		current.setPlayer("");
		movePlayerBackward(--dice, current.getPrevious());
	}
	
	public void setPlayer(String player) {
		head.setPlayer(player);
	}
}
