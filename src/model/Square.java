package model;

public class Square {
	
	private int number; //Es le n�mero de la casilla.
	private boolean seed; //Cada casilla tiene un boolean que dice si hay o no una semilla en ella
	private int portal; //Si hay un portal en la casilla, entonces la variable ser� distinta de 0.
	private String player; //La letra representaci�n del jugfador que est� en la casilla.
	
	private Square next; //Conexi�n con la casilla siguiente. Si la casilla es la �ltima, 
	//el next es la primera casilla existente.
	private Square previous; //Conexi�n con la casilla anterior. Si la casilla es la 
	//primera, el previous es la �ltima casilla existente
	
	public Square(int number) {
		this.number = number;
	}
	
	public String squareToString() {
		return "[ " + number + " ]";
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
}
