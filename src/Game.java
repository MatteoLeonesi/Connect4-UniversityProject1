import java.util.ArrayList;
import java.util.Arrays;

public class Game  {
	protected final int COLUMNS = 7;
	protected final int ROWS = 6;
	protected ILog lg;
	protected IAlgorithm algorithm;
	protected IView view;
	protected ArrayList<IPlayer> players = new ArrayList <IPlayer>(); //Array List contenente gli oggetti giocatori,2 
	protected int idxCurrentPlayer =1; //inizializiamo con il 2° giocatore in modo che al primo cambioGiocatore() in realtà inizia con il primo
	protected Pawn[][] Table = new Pawn[ROWS][COLUMNS]; //la matrice dove giocare 
	public ICoord lastInserCel=null;
	private int idPlayer=1;
	private int numPlayer;
	
	private GameState stateGame=GameState.playing; 
	


	//Nel costruttore iniziamo il Log, l'ggetto che gestisce i menù e l'algoritmo da utilizzare per la vittoria  
	public Game(IView view,IAlgorithm algorithm,ILog lg,GameMode modo) {
		this.lg=lg; //log che il gioco usa per il logging
		this.view= view; //Vista che il gioco usa per input\output
		this.view.SetGame(this); //passa all'oggetto vista l'oggetto gioco che dovrà gestire in termini di input/output
		this.algorithm=algorithm;
		switch (modo) {
		case Computer:
			//-- scelto un giocatore -- 
			players.add((new PlayerFactory()).getGiocatore("Giocatore", 1, PawnType.Cross));
			players.add((new PlayerFactory()).getGiocatore("BOT",2, PawnType.Circle));
			numPlayer=1;
			break;
		case HumanPlayer :
			//-- scelto due giocatori --
			players.add((new PlayerFactory()).getGiocatore("Giocatore", 1, PawnType.Cross));
			players.add((new PlayerFactory()).getGiocatore("Giocatore", 2, PawnType.Circle));
			numPlayer=2;
			break;
		default:
			SetGameState(GameState.EndGameForError);
			break;
				
		}
		initTable();
	}

	public boolean Run()
	{
		try
		{
			do {
				view.PrintGameTable();
				ChoiceColumnToInsertPawn();
				InsertPawn();
				algorithm.CheckIfWin(this,lg);
			} while (getGameState()==GameState.playing); //continuiamo il gioco se il giocatore corrente NON ha vinto
			view.PrintGameResult(getGameState());
			return true;
		}
		catch (Exception e) {
			lg.severe(0, "Errore in Avvia()", e);
			return false;
		}
	}

	//Inizializziamo la griglia con tutti oggetti Pedina con attributo 'Nessuna'
	private void initTable()
	{
		Arrays.stream(Table).forEach(a -> Arrays.fill(a, new Pawn(PawnType.Nothing)));
	}

	protected GameState getGameState() {
		return stateGame;
	}

	protected void SetGameState(GameState st) {
		stateGame=st;
	}

	//Per ora abbiamo solo 2 giocatori possibili
	private int swapPlayer(int idxGiocatoreCorrente) {
		idPlayer=(idxGiocatoreCorrente + 1) % 2;
		return idPlayer;
	}

	protected int GetCurrentIDPlayer() {
		return idPlayer;
	}

	/*Facciamo il cambio del giocatore e richiedimao la colonna dove inserire la pedina
	 * controlliamo se la matrice fosse piena
	 * o se è avvenuto un errore imprevisto
	 */
	private  boolean ChoiceColumnToInsertPawn() {
		try
		{
			int colonnaDoveInserirePedina=0;
			CheckFullTable();
			//cambierà stato di continuo tra 0 e 1 in quanto i giocatori si alternano  
			idxCurrentPlayer= swapPlayer(idxCurrentPlayer); //1°Giocatore==>indexGiocatoreCorrente=0  -  2°Giocatore==>indexGiocatoreCorrente=0
			//rimaniamo nel loop fino a che non abbiamo inserito una colonna valida e che abbia una cella libera
			do {
				if (numPlayer==2)
					//Modalità 2 giocatori
					//ad entrambe i giocatori viene richiesto la colonna su cui inserire la pedina
					colonnaDoveInserirePedina = view.ChoiceColumn()-1; 
				else
				{
					//Modalità un giocatore e un bot
					if (idxCurrentPlayer==1)  //se computer (2° giocatore:indexGiocatoreCorrente==1) se : nel caso di un solo giocatore, quando tocca giocare al secondo tiriamo fuori colonna random 
						colonnaDoveInserirePedina=  getRandomColumn();
					else //Se giocatore 
						colonnaDoveInserirePedina=  view.ChoiceColumn()-1;
				}
				//-- Cerchiamo la prima cella libera nella colonna selezionata dal giocatore --
				FindFirstFreeRowInColumn(colonnaDoveInserirePedina);
			} while (getGameState()!=GameState.EndGameForError &&  lastInserCel==null ); //continuiamo a chiedere solo se NON c'è un errore o se NON abbiamo trovato una cella libera
			return true;
		}
		catch (Exception e) {
			lg.severe(0, "Errore in RichiestaColonnaDoveInserirePedina()", e);
			SetGameState(GameState.EndGameForError);
			return false;
		}
	}

	/* Cerchiamo la prima cella libera su una data Colonna. (ricordiamoci che la mtrice è mriempita dalla riga 0 ma quando la visualizziamo
	 * la visualizziamo capovolta. 
	 * ritorniamo nell'oggetto Coordinata la cordinata della colonna libera o null in caso di errore:
	 */
	protected  ICoord FindFirstFreeRowInColumn(int col) {
		try
		{
			if (col < 0 || Table[0].length <= col) {
				//===> fuori range come colonna. 
				view.PrintMessage("Colonna non permessa \n\n");
				return null;   
			}

			//Partendo dalla riga 0 cerco la prima cella della matrice diove c'è un oggetto pedina che non ha un tipoPedina associato quindi vuota
			for (int row = 0; row < Table.length; row++) {
				if (Table[row][col].type  == PawnType.Nothing ) {
					lastInserCel=new Coord(row, col); //===> coordinata riga libera trovata nella colonna data
					return lastInserCel;
				}
			}
			//Se non abbiamo trovato una riga  libera su questa colonna...
			view.PrintMessage("Colonna piena provare con un'altra colonna\n\n");
			return null; 

		}
		catch (Exception e) {
			lg.severe(0, "Errore nel CercaPrimaRigaLiberaNellaColonna", e);
			SetGameState(GameState.EndGameForError);
			return null;
		}
	}


	protected  boolean InsertPawn() {
		try
		{
			if (lastInserCel!=null )
				Table[lastInserCel.getRow()][lastInserCel.getCol()] = new Pawn(players.get(idxCurrentPlayer).GetPawnType());
			return true;
		}
		catch (Exception e) {
			lg.severe(0, "Errore nel InserisciPedina", e);
			SetGameState(GameState.EndGameForError);
			return false;
		}
	}

	//ritorniamo un oggetto di tipo Coord purchè in un range valido di riga e colonna 
	protected boolean CheckCoordinate(int r,int c) {
		if (c < 0 || c >= COLUMNS) {
			return false;
		}

		if (r < 0 || r >= ROWS) {
			return false;
		}
		return true;

	}


	private boolean CheckFullTable() {
		//andiamo a controllare la riga più in basso se c'è almeno una pedina come 'Nessuna' allora significa che c'è almeno una cella libera
		for (int col = 0; col < COLUMNS; col ++) {
			if (Table[ROWS - 1][col].type == PawnType.Nothing) {
				return false;
			}
		}
		SetGameState(GameState.EndGameforFullTable);
		return true;
	}

	private int getRandomColumn() {
		return (int) (Math.random() * COLUMNS);
	}
}
