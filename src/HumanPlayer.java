
public class HumanPlayer implements IPlayer {
	private String name;
	private int IDPlayer;
	private PawnType pawnType;
	private String pawnChar ;
	public HumanPlayer(String name, int idPlayer, PawnType pawnType )
	{
		this.name = name;
		this.IDPlayer=idPlayer ;
		this.pawnType=pawnType;
		this.pawnChar= Pawn.GetPawnChar(pawnType);
	}


	public PawnType GetPawnType()
	{
		return pawnType;
	}


	public int GetIDPlayer()
	{
		return IDPlayer;
	}


	public String GetName()
	{
		return name;
	}


	public String GetPawnChar()
	{
		return pawnChar;
	}
}
