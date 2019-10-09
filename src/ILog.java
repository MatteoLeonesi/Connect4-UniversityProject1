
public interface ILog {

	//Inizializza la classe Java per gestire i Log
	ILog InitLog(String pathLogFile);

	void severe(int cod, String message, Exception e);

	void warning(int cod, String message);

	void info(int cod, String message);

}