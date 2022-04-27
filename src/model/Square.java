package model;

public class Square {
	
	private int position; //Es le número de la casilla.
	private String player; //La letra representación del jugador que esté en la casilla.
	
	//private int portals;
	//private boolean seed;
	
	private Square next;
	private Square previous;
	
	public Square(int position) {
		this.position = position;
		player = "";
	}
	
	public void setPlayer(String player) {
		this.player = player;
	}
	
	public String getPlayer() {
		return player;
	}
	
	public String squareToString() {
		if(player.isEmpty()) {
			return "[ " + position + " ]";
		} else {
			return "[ " + player + " ]";
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
