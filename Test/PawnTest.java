import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PawnTest {

	@Test
	void testPawn() {
		Pawn pw = new Pawn(PawnType.Cross);
		assertNotNull(pw);
	}

	@Test
	void testGetPawnChar() {
		assertEquals("O",Pawn.GetPawnChar(PawnType.Circle ));
	}

}
