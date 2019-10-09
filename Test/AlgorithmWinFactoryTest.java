import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class AlgorithmWinFactoryTest {

	@Test
	void testGetAlgorithm() {
		ILog lg= new Log().InitLog("C:\\Temp");
		IView view= new GameView(lg);
		IAlgorithm algorithm = (new AlgorithmWinFactory()).getAlgorithm("Base");
		GameMode gameMode =GameMode.Computer; 
		Game gm = new Game(view,algorithm,lg, gameMode); 
		Boolean  win = algorithm.CheckIfWin(gm, lg);
		assertEquals(false,win);
	}
	
	@Test
	void testGetAlgorithmNotFound() {
		IAlgorithm algorithm = (new AlgorithmWinFactory()).getAlgorithm("NESSUNO");
		assertEquals(null,algorithm);
	}

}
