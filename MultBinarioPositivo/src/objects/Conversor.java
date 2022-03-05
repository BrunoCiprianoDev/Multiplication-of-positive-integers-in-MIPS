package objects;
public class Conversor{
	private int bits;
	public Conversor(int numeroDeBits){
		this.bits=numeroDeBits;
	}	
	
	public int converteBinarioEmInteiroSemSinal(String binario){
		int numeroDeCaracter = binario.length();
		int valorInteiro = 0;
		int contador = (numeroDeCaracter - 1);
		for(int expoente = 0; expoente < numeroDeCaracter; expoente++){
			if(binario.charAt(expoente) == '1'){
				valorInteiro += (Math.pow(2,contador));
			}
			contador--;
		}
		return valorInteiro;
	}

	public String converteInteiroEmBinarioSemSinal(int inteiro){
		int resto, contador = 1;
		String resultadoInvertido = "";	
		while(inteiro != 0){
			resto = inteiro % 2;
			inteiro = inteiro / 2;
			if(resto == 1){
				resultadoInvertido = resultadoInvertido.concat("1");
			}else{
			
				resultadoInvertido = resultadoInvertido.concat("0");
			}
		}
		int tamanho = resultadoInvertido.length();
		String resultado = "";
		if(tamanho<bits){
			int bitsParaCompletar=(bits-tamanho);
			for(int i=0; i<bitsParaCompletar; i++){
				resultado = resultado.concat("0");
			}
		}
		for(int i=(tamanho-1); i>=0 ; i--){
			if((resultadoInvertido.charAt(i)) == '1'){
				resultado = resultado.concat("1");
			}else{
				resultado = resultado.concat("0");
			}
		}
		return resultado;
	}
	public String converteInteiroEmBinarioComSinal(int inteiro){

		if(inteiro<2147483647 && inteiro>(-2147483648)){
			if(inteiro>=0){
				String resultado = converteInteiroEmBinarioSemSinal(inteiro);
				return resultado;
			}else{
				inteiro = inteiro*(-1);
				String resultado = converteInteiroEmBinarioSemSinal(inteiro);
				String resultado2="";


				//Realiza complemento de dois//
				for(int i=0; i<bits; i++){
					if(resultado.charAt(i)=='0'){
						resultado2=resultado2.concat("1");
					}else{
						resultado2=resultado2.concat("0");
					}	
				}
				
				//Faz a soma do 1//
				boolean sair = false;
				int posicaoDeZero = 0;
				for(int i=(bits-1); i >=0 || sair==false; i--){
					if(resultado2.charAt(i)=='0' && sair!=true){
						posicaoDeZero = i;
						sair = true;
					}
				}

				String resultado3="";
				
				for(int i=0; i<posicaoDeZero; i++){
					if(resultado2.charAt(i)=='1'){
						resultado3 = resultado3.concat("1");
					}else{
						resultado3 = resultado3.concat("0");
					}
				}
				resultado3 = resultado3.concat("1");
				
				if(posicaoDeZero<(bits-1)){
					for(int i=(posicaoDeZero+1); i<bits; i++){
						resultado3 = resultado3.concat("0");		
					}
				}
				return resultado3;
			}
		}else{
			throw new IllegalArgumentException("Overflow error!");
			
		}
	}
	public int converteBinarioComSinalEmInteiro(String binario){
		if(binario.charAt(0)=='0'){
			return converteBinarioEmInteiroSemSinal(binario);
		}else{
			int valor = 0;	
			valor += (Math.pow(2,(bits-1)));
			valor = valor*(-1);
			int contador = (bits-2);
			for(int i=1; i<bits; i++){
				if(binario.charAt(i)=='1'){
					valor += Math.pow(2,contador);		
				}	
				contador--;
			}	
			return valor-1;				
		}		
	}
}


