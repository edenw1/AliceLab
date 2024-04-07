package Chess;

public class Queen extends Piece
{
	public Queen (int row, int col)
	{
		super(row, col);
	}
	
	public String getSymbol ()
	{
		return "Q";
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
		// Bishop movement
		if (Math.abs(startRow - endRow) == Math.abs(startCol - endCol))
			return true;
		
		// Rook movement
		if (startRow == endRow && startCol != endCol)
			return true;
		
		if (startRow != endRow && startCol == endCol)
			return true;
		
		return false;
	}
}