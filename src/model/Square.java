package model;

public class Square {
	
	private int number; //Es le n�mero de la casilla.
	private boolean seed; //Cada casilla tiene un boolean que dice si hay o no una semilla en ella
	private int portal; //Si hay un portal en la casilla, entonces la variable ser� distinta de 0.
	private String player; //La letra representaci�n del jugfador que est� en la casilla.
	
	public Square(int number) {
		this.number = number;
	}
	
	public String squareToString() {
		return "[ " + number + " ]";
	}
}
