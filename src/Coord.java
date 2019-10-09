public class Coord implements ICoord {
	private int col;
	private int row;

	public Coord(int r, int c) 
	{
		this.col = c;
		this.row = r;
	}

	public int getCol() {
		return col;
	}


	public int getRow() {
		return row;
	}


	public String toString() {
		return "(" + row + ", " + col + ")";
	}
}
