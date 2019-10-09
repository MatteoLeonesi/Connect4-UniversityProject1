
public enum GameMode {
	Computer,
	HumanPlayer,
	EndGame;
	public static GameMode fromInteger(int x) {
		switch(x) {
		case 1:
			return Computer;
		case 2:
			return HumanPlayer;
		}
		return EndGame;
	}
}
