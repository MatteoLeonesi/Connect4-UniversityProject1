import java.io.File;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Log implements ILog {
	public int LastErrorCod=0; //questo campo lo possiamo leggere da fuori per fare magari delle scelte
	private Exception exception;
	private String msg;
	private Logger logger; 

	//Inizializza la classe Java per gestire i Log
	@Override
	public ILog InitLog(String pathLogFile) {
		try
		{
			//Se la directory non esiste la creiamo
			File directory = new File(pathLogFile);
			if (! directory.exists())
				directory.mkdir();

			logger = Logger.getLogger("LogForza4");  
			FileHandler fh;  
			fh = new FileHandler(pathLogFile+ "\\LogGiocoFroza4.log");  
			logger.addHandler(fh);
			SimpleFormatter formatter = new SimpleFormatter();  
			fh.setFormatter(formatter);
			//Se volessimo non visualizzare a anche video il log scommentare questa istruzione
			logger.setUseParentHandlers(false);
			return this;
		}
		catch (Exception e) {
			logger=null;
			System.out.println("Errore durante la creazione del Log:" + e.getMessage());
			return null;
		}
	}

	@Override
	public void severe(int  cod, String message , Exception e) {
		if (logger==null) return;
		LastErrorCod = cod;
		exception = e;
		msg=message + " " + e.toString();
		System.out.println(GetLastError());
		logger.severe(GetLastError());
	}

	@Override
	public void warning(int  cod, String message ) {
		if (logger==null) return;
		LastErrorCod = cod;
		msg=message; 
		System.out.println(GetLastError());
		logger.warning(GetMessage());
	}

	@Override
	public void info(int  cod, String message ) {
		if (logger==null) return;
		LastErrorCod = cod;
		msg=message; 
		System.out.println(GetMessage());
		logger.info(GetMessage()); //dipende se attivato
	}

	private String GetLastError()
	{
		return "COD:" + Integer.toString(LastErrorCod) + " LINEA: " + exception.getStackTrace()[0].getLineNumber() + " ERRORE: " + msg;
	}

	private String GetMessage()
	{
		return "COD:" + Integer.toString(LastErrorCod) +   msg;
	}

}
