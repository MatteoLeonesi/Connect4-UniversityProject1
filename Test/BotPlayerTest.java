import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class BotPlayerTest {
 
	@Test
	void testGetPawnType() {
		BotPlayer bp = new BotPlayer(1,PawnType.Circle);
		assertEquals(PawnType.Circle,bp.GetPawnType());
	}

	@Test
	void testGetIDPlayer() {
		BotPlayer bp = new BotPlayer(1,PawnType.Circle);
		assertEquals(1,bp.GetIDPlayer());
	}

	@Test
	void testGetName() {
		BotPlayer bp = new BotPlayer(1,PawnType.Circle);
		bp.SetName("Giocatore di prova");
		assertEquals("Giocatore di prova",bp.GetName());
	}

	@Test
	void testGetPawnChar() {
		BotPlayer bp = new BotPlayer(1,PawnType.Cross);
		assertEquals("X",bp.GetPawnChar());
	}

}
