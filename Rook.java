package Chess;

public class Rook extends Piece
{
	public Rook (int row, int col)
	{
		super(row, col);
	}
	
	public String getSymbol ()
	{
		return "R";
	}

	public boolean needsClearPath ()
	{
		return true;
	}
	
	public boolean getsPromoted ()
	{
		return false;
	}
	
	public boolean isValidMove (int startRow, int startCol, int endRow, int endCol)
	{
		if (startRow == endRow && startCol != endCol)
			return true;
		
		if (startRow != endRow && startCol == endCol)
			return true;
		
		return false;
	}
}