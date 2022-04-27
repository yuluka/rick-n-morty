package model;

public class Square {
	
	private int position; //Es le n�mero de la casilla.
	private String player; //La letra representaci�n del jugador que est� en la casilla.
	private Player player1;
	
	//private int portals;
	//private boolean seed;
	
	private Square next;
	private Square previous;
	
	public Square(int position) {
		this.position = position;
		player1 = null;
		player = "";
	}
	
	public void setPlayer(String player) {
		this.player = player;
	}
	
	public String getPlayer() {
		return player;
	}
	
	public Player getPlayer1() {
		return player1;
	}

	public void setPlayer1(Player player1) {
		this.player1 = player1;
	}

	public String squareToString() {
		if(player1 == null) {
			return "[ " + position + " ]";
		} else {
			return "[ " + player1.getName() + " ]";
		}		
	}

	public Square getNext() {
		return next;
	}

	public void setNext(Square next) {
		this.next = next;
	}
	
	public Square getXNext(int x) {
		return getXNext(x, next);
	}

	public Square getXNext(int x, Square current) {
		if(x == 0) {
			return current;
		}
		
		current = current.getNext();
		--x;
		return getXNext(x, current);
	}
	
	public Square getPrevious() {
		return previous;
	}

	public void setPrevious(Square previous) {
		this.previous = previous;
	}

	public int getPosition() {
		return position;
	}
	
	
}
