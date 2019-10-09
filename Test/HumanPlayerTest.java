import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class HumanPlayerTest {

	@Test
	void testHumanPlayer() {
		HumanPlayer hp = new HumanPlayer("Giocatore",1,PawnType.Circle);
		assertNotNull(hp);
	}

	@Test
	void testGetPawnType() {
		HumanPlayer hp = new HumanPlayer("Giocatore",1,PawnType.Circle);
		assertEquals(PawnType.Circle,hp.GetPawnType());
	}

	@Test
	void testGetIDPlayer() {
		HumanPlayer hp = new HumanPlayer("Giocatore",1,PawnType.Circle);
		assertEquals(1,hp.GetIDPlayer());
	}

	@Test
	void testGetName() {
		HumanPlayer hp = new HumanPlayer("Giocatore",1,PawnType.Circle);
		assertEquals("Giocatore",hp.GetName());
	}

	@Test
	void testGetPawnChar() {
		HumanPlayer hp = new HumanPlayer("Giocatore",1,PawnType.Cross);
		assertEquals(PawnType.Cross,hp.GetPawnType());
	}

}
