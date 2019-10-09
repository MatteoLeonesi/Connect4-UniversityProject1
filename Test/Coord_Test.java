import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Coord_Test {

	@Test
	void testGetCol() {
		ICoord coordinata = new Coord(5,6);
		assertEquals(coordinata.getRow(),5);
	}

	@Test
	void testGetRig() {
		ICoord coordinata = new Coord(5,6);
		assertEquals(coordinata.getCol(),6);
	}

	@Test
	void testToString() {
		ICoord coordinata = new Coord(5,6);
		String ris = coordinata.toString(); 
		assertEquals("(5, 6)", ris); 
	}

}
