import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class PlayerFactoryTest {

	@Test
	void testBotPlayer() {
		ArrayList<IPlayer> players = new ArrayList <IPlayer>(); //Array List contenente gli oggetti giocatori
		players.add((new PlayerFactory()).getGiocatore("BOT",2, PawnType.Circle));
		assertEquals("BOT",players.get(0).GetName());
		assertEquals(2,players.get(0).GetIDPlayer());
		assertEquals(PawnType.Circle,players.get(0).GetPawnType());
	}
	@Test
	void testHumanPlayer() {
		ArrayList<IPlayer> players = new ArrayList <IPlayer>(); //Array List contenente gli oggetti giocatori
		players.add((new PlayerFactory()).getGiocatore("Giocatore", 1, PawnType.Cross));
		assertEquals("Giocatore1",players.get(0).GetName());
		assertEquals(1,players.get(0).GetIDPlayer());
		assertEquals(PawnType.Cross,players.get(0).GetPawnType());

	}


}
