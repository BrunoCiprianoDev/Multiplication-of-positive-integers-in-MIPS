package objects;
public class Validador{
	public String valido(String binario){
		int tamanho = binario.length();
		if(tamanho!=8){
			return "ERRO1";	
		}
		for(int i=0; i<tamanho; i++){
			if(binario.charAt(i)!='1'){
				if(binario.charAt(i)!='0'){
					return "ERRO2";
				}
			}
		}	
		return binario; 		
	}
}
