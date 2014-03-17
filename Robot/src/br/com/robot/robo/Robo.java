package br.com.robot.robo;

import br.com.robot.world.CardinalDirection;

public class Robo {

	protected boolean x1;
	protected boolean x2;
	protected boolean x3;
	protected boolean x4;
	
	public Robo() 
	{
		
	}
	
	public void readSensors(byte world[][])
	{
		if(world[0][1] == 1 || world[0][2] == 1 ){ this.x1 = true; }
		else { this.x1 = false; }
		
		if(world[1][2] == 1 || world[2][2] == 1 ){ this.x2 = true; }
		else { this.x2 = false; }
		
		if(world[2][1] == 1 || world[2][0] == 1 ){ this.x3 = true; }
		else{ this.x3 = false; }
		
		if(world[1][0] == 1 || world[0][0] == 1 ){ this.x4 = true; }
		else{ this.x4 = false;}
	}
	
	public CardinalDirection move()
	{
		if(x4&&!x1){ return CardinalDirection.Nort; }
		if(x3&&!x4){ return CardinalDirection.West; }
		if(x2&&!x3){ return CardinalDirection.South; }
		if(x1&&!x2){ return CardinalDirection.East; }
		return CardinalDirection.Nort;
	}
	

}
