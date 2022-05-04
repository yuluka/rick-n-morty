package model;

public class Square {
	
	private int position; //Es le número de la casilla.
	private Player player;
	
	private String portalLetter;
	private Square portalPair;
	
	private boolean seed;
	
	private Square next;
	private Square previous;
	
	public Square(int position) {
		this.position = position;
		player = null;
		seed = false;
	}
	
	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public String squareToString() {
		if(portalPair == null && !seed && player == null) {
			return "[ " + position + " ]";
		} else if(player != null) {
			return "[ " + player.getName() + " ]";
		} else if(portalPair != null) {
			return "[ " + portalLetter + " ]";
		} else {
			return "[ * ]";
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

	public String getPortalLetter() {
		return portalLetter;
	}

	public void setPortalLetter(String portalLetter) {
		this.portalLetter = portalLetter;
	}

	public Square getPortalPair() {
		return portalPair;
	}

	public void setPortalPair(Square portalPair) {
		if(portalLetter.isEmpty()) {
			portalPair = null;
		}
		
		this.portalPair = portalPair;
	}

	public boolean isSeed() {
		return seed;
	}

	public void setSeed(boolean seed) {
		this.seed = seed;
	}
}
