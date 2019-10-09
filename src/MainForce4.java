public class MainForce4 {
	private IView view;
	private IAlgorithm algorithm;
	protected ILog lg= new Log().InitLog("C:\\Temp2"); 

	public MainForce4()
	{
		view= new GameView(lg); //creiamo l'oggetto che gestirà l'input\output
		
		algorithm = (new AlgorithmWinFactory()).getAlgorithm("Base"); //scelta algoritmo x declatare la vittori
		
		while (true)
		{
			//Scelta numero giocatori
			GameMode gameMode=view.ChoicePlayerMode();
			
			if (gameMode==GameMode.EndGame)
				break;
			
			//Avvio di un  nuovo gioco
			new Game(view,algorithm,lg, gameMode).Run(); //alla classe Gioco passeremo la vista che Gioco utilizzerà per gestire l'input\output
		}
		view.PrintMessage("Fine del gioco");
	}
}