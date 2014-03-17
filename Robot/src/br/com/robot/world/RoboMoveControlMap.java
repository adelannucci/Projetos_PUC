package br.com.robot.world;

import br.com.robot.robo.Robo;

public class RoboMoveControlMap {
	
	protected Robo robo;
	protected Map map;
	protected int roboRow;
	protected int roboColumn;
	protected int numberMaxInteraction;
	protected int numberInteraction;
	
	
	public RoboMoveControlMap(int rows, int columns, int roboStarRow, int roboStartColumn, int numberOfInteraction) throws Exception
	{
		this.map = new Map(rows, columns);
		this.map.mapGenerator();
		this.robo = new Robo();
		this.numberInteraction = 0;
		
		if((roboStarRow > 0 && roboStarRow < rows) && ( roboStartColumn > 0 && roboStartColumn < columns) && ( numberOfInteraction > 0) )
		{
			this.numberMaxInteraction = numberOfInteraction;
			this.roboRow = roboStarRow;
			this.roboColumn = roboStartColumn;
			map.printMap(roboRow, roboColumn);
		}
		else { throw new Exception("Error invalid set position start of robo"); }
	}
	
	public void interaction()
	{ 
		robo.readSensors(readMap());
		CardinalDirection direction = robo.move();
		
		if(direction == CardinalDirection.Nort)
		{
			this.roboRow--;
		}
		else if(direction == CardinalDirection.South)
		{
			this.roboRow++;
		}	
		else if(direction == CardinalDirection.East)
		{
			this.roboColumn++;
		}
		else if(direction == CardinalDirection.West)
		{
			this.roboColumn--;
		}
		
		numberInteraction++;
		System.out.println("Round: " + numberInteraction);
		map.printMap(roboRow, roboColumn);

	}
	
	private byte[][] readMap()
	{
		byte miniMap[][] = new byte[3][3];
		
		try {
			miniMap[0][0] = map.readMap(roboRow - 1, roboColumn - 1);
			miniMap[0][1] = map.readMap(roboRow - 1, roboColumn + 0);
			miniMap[0][2] = map.readMap(roboRow - 1, roboColumn + 1);
			miniMap[1][0] = map.readMap(roboRow + 0, roboColumn - 1);
			miniMap[1][2] = map.readMap(roboRow + 0, roboColumn + 1);
			miniMap[2][0] = map.readMap(roboRow + 1, roboColumn - 1);
			miniMap[2][1] = map.readMap(roboRow + 1, roboColumn + 0);
			miniMap[2][2] = map.readMap(roboRow + 1, roboColumn + 1);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return miniMap;
	}

	public Map getMap() {
		return map;
	}

	public int getRoboRow() {
		return roboRow;
	}

	public int getRoboColumn() {
		return roboColumn;
	}
	
	
}
