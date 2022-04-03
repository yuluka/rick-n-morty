package model;

public class Square {
	
	private int number; //Es le número de la casilla.
	private boolean seed; //Cada casilla tiene un boolean que dice si hay o no una semilla en ella
	private int portal; //Si hay un portal en la casilla, entonces la variable será distinta de 0.
	private String player; //La letra representación del jugfador que esté en la casilla.
	
	public Square(int number) {
		this.number = number;
	}
	
	public String squareToString() {
		return "[ " + number + " ]";
	}
}
