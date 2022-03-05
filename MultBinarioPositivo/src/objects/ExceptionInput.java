package objects;
import javax.swing.JOptionPane;
public class ExceptionInput{
	public boolean exception(String valor, String valido){	
		if(valido.equals("ERRO1")){
			JOptionPane.showMessageDialog(null, "O valor inserido no "+valor+" não tem 8bits","Número invalido!",JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if(valido.equals("ERRO2")){
			JOptionPane.showMessageDialog(null,"Foi inserido um valor invalido no "+valor,"Número invalido!", JOptionPane.ERROR_MESSAGE);
			return false;
		}
	return true;
	}
}
