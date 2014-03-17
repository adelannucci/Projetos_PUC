package br.com.robot.gui;

import java.awt.Button;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Panel;

import javax.swing.JFrame;

import br.com.robot.world.RoboMoveControlMap;

public class WindowRobot {
	
	protected JFrame mainFrame;
	protected Panel painel;
	protected GridLayout layout;
	protected RoboMoveControlMap roboCotrolMap;	
	protected Button buttonMap[][];
	
	protected int x, y;
	
	public WindowRobot(int rows, int cols,int roboX, int roboY, int numberOfInteraction)
	{
		try {
			roboCotrolMap = new RoboMoveControlMap(rows, cols, roboX, roboY, numberOfInteraction);
			layout = new GridLayout(rows, cols);
			this.painel = new Panel(layout);
			
			this.buttonMapGenerator(rows, cols);
			buttonMap[roboX][roboY].setBackground(Color.RED);
			
			mainFrame = new JFrame("Robot");
			mainFrame.setSize(700, 700);
			mainFrame.add(painel);
			mainFrame.setVisible(true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	private void buttonMapGenerator(int rows, int cols) throws HeadlessException, Exception
	{
		this.buttonMap = new Button[rows][cols];
		for(int i = 0; i< rows; i++)
		{
			for(int j = 0; j< cols; j++)
			{
				if(roboCotrolMap.getMap().readMap(i, j) == 1)
				{
					buttonMap[i][j] = new Button();
					buttonMap[i][j].setBackground(Color.BLACK);
					painel.add(buttonMap[i][j]);
				}
				else
				{
					buttonMap[i][j] = new Button();
					buttonMap[i][j].setBackground(Color.WHITE);
					painel.add(buttonMap[i][j]);
				}
			}
		}
	}
	
	public void runner(int maxInteraction) throws InterruptedException
	{
		for(int acc = 0; acc<= maxInteraction; acc++)
		{
			buttonMap[roboCotrolMap.getRoboRow()][roboCotrolMap.getRoboColumn()].setBackground(Color.WHITE);
			roboCotrolMap.interaction();
			buttonMap[roboCotrolMap.getRoboRow()][roboCotrolMap.getRoboColumn()].setBackground(Color.RED);
			Thread.sleep(500);
			mainFrame.repaint();
		}
	}

}
