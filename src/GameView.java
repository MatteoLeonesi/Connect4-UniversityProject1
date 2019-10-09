import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GameView implements IView {
	private ILog lg;
	private Game gioco;	
	public GameView(ILog lg) {
		this.lg=lg;
	}

	@Override
	public void PrintMessage(String msg)
	{
		System.out.println(msg +"\n\n");
	}

	@Override
	public void SetGame(Game game)
	{
		this.gioco=game;
	}

	@Override
	public void PrintGameTable()
	{
		try
		{
			StringBuilder sb = new StringBuilder();
			sb.append("\n\t   1   2   3   4   5   6   7  ");
			for (int row = 0; row < gioco.Table.length; row++) {
				sb.append("\n\t |");
				for (int col = 0; col < gioco.Table[0].length; col++) {
					switch (gioco.Table[gioco.ROWS - 1 - row][col].type) {
					case Circle:
						sb.append(" " + Pawn.GetPawnChar(PawnType.Circle) + " ");
						break;
					case Cross :
						sb.append(" " +  Pawn.GetPawnChar(PawnType.Cross )+ " ");
						break;
					case Nothing:
						sb.append("   ");
						break;
					}
					sb.append("|");
				}
				sb.append("\n\t |---|---|---|---|---|---|---|");
			}
			PrintMessage(sb.toString());
		}
		catch (Exception e) {
			lg.severe(0, "Errore nel visualizzare la matrice", e);
			gioco.SetGameState(GameState.EndGameForError);

		} 
	}

	@Override
	public void PrintGameResult(GameState gamestate)
	{
		try
		{
			System.out.println("\n\n ================================================\n");
			PrintGameTable(); 
			switch(gamestate)
			{
			case EndGameForWinner:
				System.out.println("\n\n Gioco finito per vittoria dal giocarore " + gioco.players.get(gioco.GetCurrentIDPlayer()).GetName() + "\n");
				break;
			case EndGameforFullTable :
				System.out.println("\n\n Gioco finito per matruice piena  \n\n");
				break;
			case EndGameForOperatorChoice  :
				System.out.println("\n\n Gioco finito per scelta operatore  \n\n");
				break;
			case  EndGameForError:
				System.out.println("\n\n Gioco finito per errore imprevisto \n\n");
				break;
			default: //non ci dovrebbe mai entrare
				System.out.println("\n\n Gioco finito per stato non previsto\n\n");			
			}
			System.out.println("================================================\n");
		}
		catch (Exception e) {
			lg.severe(0, "Errore nel Menu", e);
		}
	}

	@Override
	public GameMode ChoicePlayerMode()
	{
		try
		{
			System.out.println("\n  Nuovo Gioco, quanti giocatori? \n" 
					+ "   1-Contro computer \n"
					+ "   2-Contro altro giocatore \n"
					+ "   3-Fine dei giochi \n"
					);
			return GameMode.fromInteger(Choice("Devi inserire un valore fra 1 e 3",1,3)); //Ritorna il numero di giocatori o 3 come fine gioco
		}
		catch (Exception e) {
			lg.severe(0, "Errore nel SceltaGiocatori", e);
			return GameMode.EndGame;
		}
	}

	@Override
	public int ChoiceColumn()
	{
		try
		{
			System.out.println (gioco.toString() + "\n"); //ERRORE DI STAMPA CHE HO CONSEGNATO
			System.out.println("\n Giocatore: " + gioco.players.get(gioco.idxCurrentPlayer).GetName() + " Simbolo: (" +gioco.players.get(gioco.idxCurrentPlayer).GetPawnChar()   
					+ ") selezione una colonna fra 1 e " +  gioco.COLUMNS + "\n\n");
			return Choice("Devi inserire un valore fra 1 e " + gioco.COLUMNS,1,gioco.COLUMNS); //Ritorna il numero di colonna dove inserire la pedina
		}
		catch (Exception e) {
			lg.severe(0, "Errore nel SceltaGiocatori", e);
			return -1;
		}
	}
	
	private int Choice(String msgSeErrore,int min, int max) {
		int numero;
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		//InputStreamReader in = new InputStreamReader(System.in);
		while (true) { 
			try { 
				Integer integer = Integer.parseInt(in.readLine());
				numero = integer.intValue(); //Se la conversione non va a buon fine genera un'eccezione
				if (numero<min || numero >max)
					throw new IOException(msgSeErrore);
				break;
			}
			catch (Exception e) {
				System.out.println(msgSeErrore);
			}
		}
		return numero;
	  }
	

}
