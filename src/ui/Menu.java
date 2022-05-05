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
		
		if(!GameData.createBoard(columns, rows)) {
			System.out.println("\nNo es posible crear un tablero tan peque�o. Intenta Nuevamente.");
			startGame();
		}
		
		createSeeds();
	}
	
	public static void createSeeds() {
		System.out.println("\nDigita el n�mero de semillas (no deben ser m�s de las casillas totales):");
		int seeds = in.nextInt();
		
		if(!GameData.createSeeds(seeds)) {
			System.out.println("\nNo es posible crear tantas semillas o crear un juego sin semillas. Intenta Nuevamente.");
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
		
		isEndGame();
		
		playing();
	}
	
	public static void playing() {
		System.out.println("\n----- Opciones de jugador -----"
				+ "\n�Es el turno de " + GameData.getTurn() + "! �Qu� deseas hacer?"
				+ "\n1) Tirar dado."
				+ "\n2) Ver tablero."
				+ "\n3) Ver enlaces."
				+ "\n4) Ver semillas."
				+ "\n5) Ver marcador."
				+ "\n0) Salir.");
		
		int selection = in.nextInt();
		
		switch (selection) {
		case 1:
			launchDice();
			break;

		case 2:
			seeBoard();
			break;
			
		case 3:
			seePortals();
			break;
		
		case 4:
			seeBoardSeeds();
			break;
			
		case 5:
			showMarker();
			break;
			
		case 0:
			menu();
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
		
		isEndGame();
	}
	
	public static void isEndGame() {
		if(GameData.isEndGame()) {
			System.out.println("\n�Ya se han recolectado todas las semillas! El juego ha acabado."
					+ "\nEl ganador es: " + GameData.getWinner());
			
			GameData.registerScore();
			
			System.out.println("\n----- Top 5 -----"
					+ GameData.getTop5());
			
			menu();
		} else {
			playing();
		}
	}
	
	public static void seeBoard() {
		int boardVersion = 1; // Normal board
		
		System.out.println("\n" + GameData.printBoard(boardVersion));
		playing();
	} 
	
	public static void seePortals() {
		int boardVersion = 2; // Board with just portals
		
		System.out.println("\n" + GameData.printBoard(boardVersion));
		playing();
	}
	
	public static void seeBoardSeeds() {
		int boardVersion = 3; // Board with just seeds
		
		System.out.println("\n" + GameData.printBoard(boardVersion));
		playing();
	}
	
	public static void showMarker() {
		System.out.println("\nRick: " + GameData.getPlayerSeeds("R") + " semillas." +
				"\nMorty: " + GameData.getPlayerSeeds("M") + " semillas.");
		
		playing();
	}
	
	public static void seeScores() {
		System.out.println("\n----- Puntajes globales -----"
				+ GameData.getScores());
		
		menu();
	}
	
	public static void exit() {
		System.out.println("\n�Hasta luego!");
		
		System.exit(0);
	}
}
