package model;

public class Square {
	
	private int position; //Es le número de la casilla.
	private Player player;
	
	//private int portals;
	//private boolean seed;
	
	private Square next;
	private Square previous;
	
	public Square(int position) {
		this.position = position;
		player = null;
	}
	
	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public String squareToString() {
		if(player == null) {
			return "[ " + position + " ]";
		} else {
			return "[ " + player.getName() + " ]";
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
