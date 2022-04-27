package model;

import java.util.ArrayList;

public class Board {

	private int rows;
	private int columns;
	private String completeBoard;
	
	private Square head;
	private Square tail;
	private Square rickSq;
	private Square mortySq;	//Pointers of the positions of Rick and Morty.
	
	private ArrayList<Player> players; //The players are saved in an arraylist because it is easier to add more players if I want that in some moment.
	
	public Board(int columns, int rows) {
		this.rows = rows;
		this.columns = columns;
		
		completeBoard = "";
		players = new ArrayList<Player>();
		
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
	
	private void movePlayerForward(int dice, Square current) { //No est� funcionando con dos players.
		if(dice == 0) {
			players.get(0).setTurn(false);
			players.get(1).setTurn(true);
			return;
		} else if(current.getPlayer1() == null) {
			movePlayerForward(dice, current.getNext());
			return;
		} else if(current.getPlayer1().isTurn()) {
			current.getNext().setPlayer1(current.getPlayer1());
			current.setPlayer1(null);
			movePlayerForward(--dice, current.getNext());
			return;
		} else {
			movePlayerForward(dice, current.getNext());
			return;
		}
		
		/*current.getNext().setPlayer(current.getPlayer());
		current.setPlayer("");
		
		current.getNext().setPlayer1(current.getPlayer1());
		current.setPlayer1(null);
		movePlayerForward(--dice, current.getNext());*/
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
	
	public void positionPlayer(String usernameR, String usernameM) {
		Player rick = new Player(usernameR, "R");
		Player morty = new Player(usernameM, "M"); //Creates the players.
		
		rick.setTurn(true); //Rick begins 'cause he's better. 
		morty.setTurn(false); //Buh. Morty's bullshit.
		
		players.add(rick);
		players.add(morty);
		
		rickSq = randomSquare();
		mortySq = randomSquare();
		rickSq.setPlayer1(rick);
		mortySq.setPlayer1(morty);
	}
	
	public Square randomSquare() {
		int randomPos = (int) (Math.random()*(columns*rows)+1);
		
		while(rickSq != null && randomPos == rickSq.getPosition()) {
			randomPos = (int) (Math.random()*(columns*rows)+1);
		}
		
		return searchSquare(head, randomPos);
	}
	
	private Square searchSquare(Square current, int pos) {
		if(current.getPosition() == pos) {
			return current;
		}
		
		return searchSquare(current.getNext(), pos);
	}
}
