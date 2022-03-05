package objects;
import java.util.ArrayList;
public class Operador{

	public ArrayList<String> multiplicador(String multiplicando, String multiplicador, int bits){
		Aritimedica aritimedica = new Aritimedica();
		multiplicando = completaBits(multiplicando,bits);	
		multiplicador = completaBits(multiplicador,(bits/2));
		String resultado = inicializaResultado(bits);
		Shift shift = new Shift();
		ArrayList<String> passos = new ArrayList<>();
		
		String linha ="0000000000000000 "+multiplicador+" "+multiplicando;
		passos.add(linha);

		for(int i=0; i<bits; i++){
			if(multiplicador.charAt((bits-1))=='1'){
				resultado = aritimedica.somaBinarioSemSinal(resultado,multiplicando,bits);
				linha = completaBits(resultado, 8);
			}else{
				linha = completaBits(resultado, 8);
			
			}	
			multiplicador = shift.shiftRight(multiplicador);	
			multiplicando = shift.shiftLeft(multiplicando);
			linha = linha.concat(" ");
			linha = linha.concat(multiplicador);
			linha = linha.concat(" ");
			linha = linha.concat(multiplicando);
			passos.add(linha);
		}	
	return passos; 
	}

	private String inicializaResultado(int bits){
		String resultado = "";
		for(int i=0; i<(bits*2); i++){
			resultado = resultado.concat("0");
		}
	return resultado;
	}	
	public String completaBits(String multiplicando,int bits){
		if(multiplicando.length() < (bits*2)){
			int tamanho = multiplicando.length();                        
                        int bitsACompletar = (2*bits) - tamanho;
                        String novoMultiplicando = "";
			for(int i=0;i<bitsACompletar;i++){
				novoMultiplicando = novoMultiplicando.concat("0");
			}
			return novoMultiplicando.concat(multiplicando);
		}
	return multiplicando;
	}

}

