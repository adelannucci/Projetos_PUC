package br.com.robot.world;

public enum CardinalDirection {
	
	Nort(1),
	South(2),
	East(3),
	West(4);
	
	private int direction;
	
	CardinalDirection(int direction)
	{
		this.direction = direction;
	}
	
	public int getDirection()
	{
		return direction;
	}

}
