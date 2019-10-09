
public interface IView {

	void PrintMessage(String msg);

	void SetGame(Game game);

	void PrintGameTable();

	void PrintGameResult(GameState gamestate);

	GameMode ChoicePlayerMode();

	int ChoiceColumn();

}