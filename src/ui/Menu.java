package ui;

import java.util.Scanner;

import model.GameData;

public class Menu {
	
	private static Scanner in = new Scanner(System.in);
	
	public static void menu() {
		System.out.println("\n----- Selecciona lo que quieres hacer -----"
				+ "\n1) Iniciar juego."
				+ "\n2) Ver puntajes globales."
				+ "\n0) Salir");
		
		int selection = in.nextInt();
		
		switch (selection) {
		case 1:
			startGame();
			break;

		case 2:
			seeScores();
			break;
			
		case 0:
			exit();
			break;
			
		default:
			System.out.println("\nLa opci�n seleccionada es inv�lida. Vuelve a intentar.");
			menu();
			break;
		}
	}
	
	public static void startGame() {
		System.out.println("\n----- Iniciar juego -----"
				+ "\nDigita las medidas del tablero (columnas  filas):");
		
		int columns = in.nextInt();
		int rows = in.nextInt();
		
		GameData.createBoard(columns, rows);
		
		createSeeds();
	}
	
	public static void createSeeds() {
		System.out.println("\nDigita el n�mero de semillas (no deben ser m�s de las casillas totales):");
		int seeds = in.nextInt();
		
		if(!GameData.createSeeds(seeds)) {
			System.out.println("\nNo es posible crear tantos enlaces. Intenta Nuevamente.");
			createSeeds();
		}
		
		createPortals();
	}
	
	public static void createPortals() {
		System.out.println("\nDigita el n�mero de enlaces de portales (deben ser menos de la mitad de las casillas digitadas):");
		int portals = in.nextInt();
		
		if(!GameData.createPortals(portals)) {
			System.out.println("\nNo es posible crear tantos enlaces. Intenta Nuevamente.");
			createPortals();
		}
		
		createPlayers();
	}
	
	public static void createPlayers() {
		System.out.println("\nDigita el username del primer jugador (usar� a Rick):");
		in.nextLine();
		String rick = in.nextLine();
		
		System.out.println("\nDigita el username del segundo jugador (usar� a Morty):");
		String morty = in.nextLine();
		
		GameData.createPlayers(rick, morty);
		
		playing();
	}
	
	public static void playing() {
		System.out.println("\n----- Opciones de jugador -----"
				+ "\n�Es el turno de " + GameData.getTurn() + "! �Qu� deseas hacer?"
				+ "\n1) Tirar dado."
				+ "\n2) Ver tablero."
				+ "\n3) Ver enlaces."
				+ "\n4) Ver marcador."
				+ "\n0) Salir.");
		
		int selection = in.nextInt();
		
		switch (selection) {
		case 1:
			launchDice();
			break;

		case 2:
			seeBoard();
			break;
			
		case 0:
			exit();
			break;
			
		default:
			System.out.println("\nLa opci�n seleccionada es inv�lida. Vuelve a intentar.");
			startGame();
			break;
		}
	}
	
	public static void launchDice() {
		int diceResult = GameData.launchDice();
		System.out.println("\nResultado del dado: " + diceResult);		
		forwardOrBackward(diceResult);
	}
	
	public static void forwardOrBackward(int diceResult) {
		System.out.println("\n�Hacia d�nde deseas moverte?"
				+ "\n1) Adelante."
				+ "\n2) Atr�s.");
		
		int selection = in.nextInt();
		
		switch (selection) {
		case 1:
			System.out.println("\n*** Te mover�s hacia adelante ***");
			break;
			
		case 2:
			System.out.println("\n*** Te mover�s hacia atr�s ***");
			break;
			
		default:
			System.out.println("\nTu elecci�n es inv�lida. Intenta nuevamente.");
			forwardOrBackward(diceResult);
			break;
		}
		
		GameData.movePlayer(selection, diceResult);
		playing();
	}
	
	public static void seeBoard() {
		System.out.println("\n" + GameData.printBoard());
		playing();
	} 
	
	public static void seeScores() {
		
	}
	
	public static void exit() {
		System.exit(0);
	}
}
