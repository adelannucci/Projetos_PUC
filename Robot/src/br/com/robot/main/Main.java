package br.com.robot.main;

import br.com.robot.gui.WindowRobot;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		
		try {
//			RoboMoveControlMap roboCotrolMap = new RoboMoveControlMap(5, 50, 2, 2);
//			roboCotrolMap.runner(600);
			WindowRobot w = new WindowRobot(30, 40, 4, 4, 600);
			w.runner(600);
		} catch (Exception e) {
			e.printStackTrace();
		}
		

	}

}
