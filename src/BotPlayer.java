
public class BotPlayer implements IPlayer {
	private String name;
	private int IDPlayer;
	private PawnType pawnType;
	private String pawnChar ;
	public  BotPlayer(int idPlayer, PawnType pawnType )
	{
		this.name = "BOT";
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

	public void SetName(String name)
	{
		this.name=name;
	}

	public String GetPawnChar()
	{
		return pawnChar;
	}
}
