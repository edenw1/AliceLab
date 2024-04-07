package Chess;

public class Knight extends Piece
{
	public Knight (int row, int col)
	{
		super(row, col);
	}
	
	public String getSymbol ()
	{
		return "N";
	}

	public boolean needsClearPath ()
	{
		return false;
	}
	
	public boolean getsPromoted ()
	{
		return false;
	}
	
	public boolean isValidMove (int startRow, int startCol, int endRow, int endCol)
	{
		if (Math.abs(startRow-endRow) == 2 && Math.abs(startCol-endCol) == 1)
			return true;
		
		if (Math.abs(startRow-endRow) == 1 && Math.abs(startCol-endCol) == 2)
			return true;
		
		return false;
	}
}