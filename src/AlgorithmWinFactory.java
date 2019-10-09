
public class AlgorithmWinFactory {
	public  IAlgorithm getAlgorithm(String algorithmType)
	{
		switch(algorithmType) {
		case "Base" : 
			return new AlgorithmWinBase();
		default : 
			return null;
		}
	}
}
