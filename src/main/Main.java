package main;

import model.GameData;
import ui.Menu;

public class Main {

	public static void main(String[] args) {
		System.out.println("ˇBienvenido a Buscando las Semillas!");
		GameData.loadScores();
		Menu.menu();
		
	}

}
