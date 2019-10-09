
public class AlgorithmWinBase implements IAlgorithm{
	/* Nell'Algoritmo base utilizziamo questa procedura per dire se a vinto:
	 * Controlliamo nelle 4 direzioni (2 per ogni verso es in verticale controlleremo sopra e sotto la cella appena inserita)
	 * partendo dalla cella appena inserita, se ci sono	pedine lo stesso tipo e le conteggiamo. 
	 */
	public  boolean CheckIfWin(Game game,ILog lg) {
		try
		{
			if (game.lastInserCel==null)
				return false;
			ICoord ultCoordinata = game.lastInserCel;
			PawnType simboloCorrenteDaControllare = game.Table[ultCoordinata.getRow()][ultCoordinata.getCol()].type;
			int count=1;
			//Sono 8 le direzioni partento dalla cella che stiamo controllando, 2 per ogni direzione completa da controllarer 
			for (int i = 1; i <= 8; i ++) {
				//ogni 2 direzioni diametralmente opposte fanno parte dello stesso conteggio per vedere se ha vinto
				if (i==1 || i==3 || i==5 || i==7)
					count =1;
				//ad ogni direzione ripartiamo dallo stesso punto originale
				int r = ultCoordinata.getRow();
				int c = ultCoordinata.getCol();
				while (true) {
					switch (i) {
					case 1: // Nord
						r--;
						break;
					case 2: // Sud
						r++;
						break;
					case 3: // Est
						c++;
						break;
					case 4: // Ovest
						c--;
						break;
					case 5: // Nord Ovest
						r--;
						c--;
						break;
					case 6: // Sud Est
						r++;
						c++;
						break;
					case 7: // Nord Est
						r--;
						c++;
						break;
					case 8: // Sud Ovest
						r++;
						c--;
						break;
					}
					if (game.CheckCoordinate(r, c)==false)
						break;
					//Se il simbolo della suiccessiva cella è diverso da quello che stiamo conteggiando ci fermiamo
					if (game.Table[r][c].type !=simboloCorrenteDaControllare )
						break;

					//se siamo arrivati fin qui significa che siamo su una cella valida con il simbolo che stiamo conteggiando
					count++;
					if (count>=4) //con 4 pedine in file si vince 
					{
						//Vittoria
						game.SetGameState(GameState.EndGameForWinner);
						return true;
					}
				}
			}
			//Se abbiamo controllato in tutte le 8 direzioni e non siamo uscit prima significa che non sono stati trovati
			//4 simboli uguali consecutivi per cui usciamo con false
			//ControlloSeMatricePiena();			
			return false;
		}
		catch (Exception e) {
			lg.severe(0, "Errore nel ControllaSeVinto", e);
			game.SetGameState(GameState.EndGameForError);
			return false;
		}
	}
}
