import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class GameTest {

	private String folderLog = "C:\\Temp";
	@Test
	void testVittoria() {
		ILog lg= new Log().InitLog(folderLog); 
		Game gm = new Game(new GameView(lg),(new AlgorithmWinFactory()).getAlgorithm("Base"),lg, GameMode.HumanPlayer);
		gm.FindFirstFreeRowInColumn(0);
		gm.InsertPawn();
		gm.FindFirstFreeRowInColumn(0);
		gm.InsertPawn();
		gm.FindFirstFreeRowInColumn(0);
		gm.InsertPawn();
		//Se togliamo le due righe successive ovviamente fallisce
		gm.FindFirstFreeRowInColumn(0); 
		gm.InsertPawn();
		boolean win= gm.algorithm.CheckIfWin(gm, lg);
		assertEquals(true,win);
	}


	@Test
	void testGame() {
		ILog lg= new Log().InitLog(folderLog); 
		Game gm = new Game(new GameView(lg),(new AlgorithmWinFactory()).getAlgorithm("Base"),lg, GameMode.HumanPlayer);
		assertNotNull(gm.algorithm);
		assertNotNull(gm.view);
		assertNotNull(gm.lg);
	}

	@Test
	void testGetGameState() {
		ILog lg= new Log().InitLog(folderLog); 
		Game gm = new Game(new GameView(lg),(new AlgorithmWinFactory()).getAlgorithm("Base"),lg, GameMode.HumanPlayer);
		assertEquals(GameState.playing ,gm.getGameState());
	}

	@Test
	void testSetGameState() {
		ILog lg= new Log().InitLog(folderLog); 
		Game gm = new Game(new GameView(lg),(new AlgorithmWinFactory()).getAlgorithm("Base"),lg, GameMode.HumanPlayer);
		gm.SetGameState(GameState.EndGameForWinner);
		assertEquals(GameState.EndGameForWinner,gm.getGameState());
		
	}

	@Test
	void testGetCurrentIDPlayer() {
		ILog lg= new Log().InitLog(folderLog); 
		Game gm = new Game(new GameView(lg),(new AlgorithmWinFactory()).getAlgorithm("Base"),lg, GameMode.HumanPlayer);
		assertEquals(1,gm.idxCurrentPlayer );
	}

	@Test
	void testCheckCoordinate() {
		ILog lg= new Log().InitLog(folderLog); 
		Game gm = new Game(new GameView(lg),(new AlgorithmWinFactory()).getAlgorithm("Base"),lg, GameMode.HumanPlayer);
		assertFalse(gm.CheckCoordinate(100, 100));
	}

}
