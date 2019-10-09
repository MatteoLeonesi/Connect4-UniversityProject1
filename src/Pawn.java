public class Pawn {
	public PawnType type;
	public String symbol="";
	public Pawn(PawnType pawnType) {
		type=pawnType;
		symbol=GetPawnChar(pawnType);
	}
	
	//Funzione utilizzata in vari punti del programma definita Static per praticità
	static String GetPawnChar(PawnType pawnType)
	{
		if (pawnType==PawnType.Cross) 
			return "X";
		else if (pawnType==PawnType.Circle )
			return "O";
		else 
			return " ";
	}
}
