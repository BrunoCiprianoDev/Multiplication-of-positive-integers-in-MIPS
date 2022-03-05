package objects;
public class Shift{
	public Shift(){

	}
	public String shiftRight(String bin1){
		int tamanho = bin1.length();
		String bin2 = "0";
		for(int i=0; i<(tamanho-1); i++){
			if(bin1.charAt(i)=='0'){
				bin2 = bin2.concat("0");
			}else{
				bin2 = bin2.concat("1");
			}				
		}
		return bin2;
	}
	public String shiftLeft(String bin1){
		int tamanho = bin1.length();
		String bin2 = "";
		for(int i=1; i<(tamanho); i++){
			if(bin1.charAt(i)=='0'){
				bin2 = bin2.concat("0");
			}else{
				bin2 = bin2.concat("1");
			}				
		}
		bin2 = bin2.concat("0");
		return bin2;
	}

}
