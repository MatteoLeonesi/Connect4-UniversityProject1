public class PlayerFactory {
	public IPlayer getGiocatore(String playerType,int idPlayer ,PawnType pawnType)
	{
		switch(playerType) {
		case "BOT" : 
			return new BotPlayer(idPlayer, pawnType);
		default : 
			return new HumanPlayer(playerType + idPlayer, idPlayer, pawnType);
		}
	}
}
