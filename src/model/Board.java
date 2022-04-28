package model;

import java.util.ArrayList;

public class Board {

	private final int RICK_INDEX = 0;
	private final int MORTY_INDEX = 1;
	
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
	
	public Square getRickSq() {
		return rickSq;
	}

	public Square getMortySq() {
		return mortySq;
	}
	
	public String getBoard() {
		createBoardStr();
		return completeBoard;
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

	//PLAYERS MOVEMENT
	public int launchDice() {
		int dice = (int) (Math.random()*6+1);
		
		return dice;
	}
	
	//Si un jugador cae encima del otro, el otro es movido a una posición random en el tablero.
	//Falta prever un caso en el que un jugador pasa encima del otro, porque se borra uno de ellos cuando eso sucede
	
	public void movePlayerForward(int dice) {	
		if(dice == 0) {
			if(players.get(RICK_INDEX).isTurn()) {
				players.get(RICK_INDEX).setTurn(false);
				players.get(MORTY_INDEX).setTurn(true);
			} else {
				players.get(MORTY_INDEX).setTurn(false);
				players.get(RICK_INDEX).setTurn(true);
			}
			
			return;			
		} else if(players.get(RICK_INDEX).isTurn()) {
			if(rickSq.getPosition() + dice == mortySq.getPosition()) {
				mortySq.setPlayer(null);
				
				mortySq = randomSquare();
				mortySq.setPlayer(players.get(MORTY_INDEX));
			} else if(rickSq.getNext().getPosition() == mortySq.getPosition()) {
				rickSq.getNext().getNext().setPlayer(players.get(RICK_INDEX));
				
				rickSq.setPlayer(null);
				
				rickSq = rickSq.getNext().getNext();
				movePlayerForward(dice-2);
				return;
			}
			
			rickSq.getNext().setPlayer(players.get(RICK_INDEX));
			
			rickSq.setPlayer(null);
			
			rickSq = rickSq.getNext();
			movePlayerForward(--dice);
			return;
		} else {
			if(mortySq.getPosition() + dice == rickSq.getPosition()) {
				rickSq.setPlayer(null);
				
				rickSq = randomSquare();
				rickSq.setPlayer(players.get(RICK_INDEX));
			} else if(mortySq.getNext().getPosition() == rickSq.getPosition()) {
				mortySq.getNext().getNext().setPlayer(players.get(MORTY_INDEX));
				
				mortySq.setPlayer(null);
				
				mortySq = mortySq.getNext().getNext();
				movePlayerForward(dice-2);
				return;
			}
			
			mortySq.getNext().setPlayer(players.get(MORTY_INDEX));
			
			mortySq.setPlayer(null);
			
			mortySq = mortySq.getNext();
			movePlayerForward(--dice);
			return;
		}
	}
	
	public void movePlayerBackward(int dice) {
		if(dice == 0) {
			if(players.get(RICK_INDEX).isTurn()) {
				players.get(RICK_INDEX).setTurn(false);
				players.get(MORTY_INDEX).setTurn(true);
			} else {
				players.get(MORTY_INDEX).setTurn(false);
				players.get(RICK_INDEX).setTurn(true);
			}
			
			return;			
		} else if(players.get(RICK_INDEX).isTurn()) {			
			if(rickSq.getPosition() - dice == mortySq.getPosition()) {
				mortySq.setPlayer(null);
				
				mortySq = randomSquare();
				mortySq.setPlayer(players.get(MORTY_INDEX));
			} else if(rickSq.getPrevious().getPosition() == mortySq.getPosition()) {
				rickSq.getPrevious().getPrevious().setPlayer(players.get(RICK_INDEX));
				
				rickSq.setPlayer(null);
				
				rickSq = rickSq.getPrevious().getPrevious();
				movePlayerBackward(dice-2);
				return;
			}
			
			rickSq.getPrevious().setPlayer(players.get(RICK_INDEX));
			
			rickSq.setPlayer(null);
			
			rickSq = rickSq.getPrevious();
			movePlayerBackward(--dice);
			return;
		} else {
			if(mortySq.getPosition() - dice == rickSq.getPosition()) {
				rickSq.setPlayer(null);
				
				rickSq = randomSquare();
				rickSq.setPlayer(players.get(RICK_INDEX));
			} else if(mortySq.getPrevious().getPosition() == rickSq.getPosition()) {
				mortySq.getPrevious().getPrevious().setPlayer(players.get(MORTY_INDEX));
				
				mortySq.setPlayer(null);
				
				mortySq = mortySq.getPrevious().getPrevious();
				movePlayerBackward(dice-2);
				return;
			}
			
			mortySq.getPrevious().setPlayer(players.get(MORTY_INDEX));
			
			mortySq.setPlayer(null);
			
			mortySq = mortySq.getPrevious();
			movePlayerBackward(--dice);
			return;
		}
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
		rickSq.setPlayer(rick);
		mortySq.setPlayer(morty);
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
