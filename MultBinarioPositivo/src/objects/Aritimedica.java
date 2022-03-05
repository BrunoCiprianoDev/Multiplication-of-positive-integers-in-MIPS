package objects;
public class Aritimedica{
	public String somaBinarioSemSinal(String bin1, String bin2, int bits){
		Conversor conversor = new Conversor(bits);
		int binarioUm = conversor.converteBinarioEmInteiroSemSinal(bin1);
		int binarioDois = conversor.converteBinarioEmInteiroSemSinal(bin2);
		int binarioTres = binarioUm + binarioDois;
		return conversor.converteInteiroEmBinarioSemSinal(binarioTres);
	}
}
