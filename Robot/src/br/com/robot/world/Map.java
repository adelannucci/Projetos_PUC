package br.com.robot.world;

public class Map {
	
	protected byte map[][];
	protected int rows;
	protected int columns;

	public Map(int rows, int columns) throws Exception
	{
		if(rows > 0 && columns > 0)
		{
			this.rows = rows;
			this.columns = columns;
			
			if(this.rows > 0 && this.columns > 0)
			{
				map = new byte[this.rows][this.columns];
			}	
		}
		else
		{
			throw new Exception("Error invalid number of columns or rows");
		}
		
		
	}
	
	public void mapGenerator()
	{
		
		for(int i = 0; i< this.rows; i++)
		{
			for(int j = 0; j< this.columns; j++)
			{
				if(i == 0 || j == 0 || i == this.rows-1 || j == this.columns-1)
				{
					this.map[i][j]=1;
				}
			}
		}
	}
	
	public void printMap(int row, int column)
	{
		for(int i = 0; i< this.rows; i++)
		{
			for(int j = 0; j< this.columns; j++)
			{
				if(i == row && j == column)
					System.out.print("R");
				else
					System.out.print(map[i][j]);
			}
			System.out.println("");
		}
	}
	
	public byte readMap(int row, int column) throws Exception
	{
		if(row >= 0 && column >= 0)
			return map[row][column];
		else { throw new Exception("Error invalid number of columns or rows"); }
	}
	
}
