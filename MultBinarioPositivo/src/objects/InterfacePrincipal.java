package objects;
import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class InterfacePrincipal extends JFrame implements ActionListener{

	Validador validador = new Validador();
	ExceptionInput exceptionInput = new ExceptionInput();
	boolean editorArea = true; 	
	ArrayList<String> passos = new ArrayList<>();
	Operador operador = new Operador();	
	Conversor conversor = new Conversor(8);
	int contador = 0;
	
	Font fonte = new Font("Serif", Font.BOLD,20);
	ImageIcon imagem = new ImageIcon(getClass().getResource("../Imagens//ImagemMultSemSinal.png"));
	ImageIcon imagemB = new ImageIcon(getClass().getResource("../Imagens//BackgroundWhite.png"));
	JLabel label = new JLabel(imagem);
	JLabel labelB = new JLabel(imagemB);
	JLabel txtMultiplicador = new JLabel("Multiplicador:");
	JLabel txtMultiplicando = new JLabel("Multiplicando:");
	JLabel txtExplicativo = new JLabel("Insira os valores binarios(com 8bits) para o multiplicador, multiplicando e clique em iniciar:");
	JLabel txtOperacao = new JLabel();

	JTextField areaMultiplicador = new JTextField("");
	JTextField areaMultiplicando = new JTextField("");

	JTextArea multiplicador = new JTextArea("00000000");
	JTextArea multiplicando = new JTextArea("0000000000000000");
	JTextArea ultimoBit = new JTextArea("0");
	JTextArea resultado = new JTextArea("0000000000000000"); 
	JProgressBar barra = new JProgressBar();

	JButton btnIniciar = new JButton("Iniciar");
	JButton btnProximo = new JButton("Proximo");
	JButton btnAnterior = new JButton("Anterior");
	JButton btnReiniciar = new JButton("Reiniciar");

	@Override
	public void actionPerformed(ActionEvent e){
		if(e.getSource()==btnIniciar){
			if(editorArea==true){
				String multiplicadorValidado = validador.valido(areaMultiplicador.getText());
				String multiplicandoValidado = validador.valido(areaMultiplicando.getText());
				if((exceptionInput.exception("multiplicador", multiplicadorValidado)==true) && (exceptionInput.exception("multiplicando",multiplicandoValidado))==true){
					editorArea = false;
					String zeros = "00000000";
					multiplicandoValidado = zeros.concat(multiplicandoValidado);
					passos = operador.multiplicador(multiplicandoValidado,multiplicadorValidado,8);
					areaMultiplicador.setEnabled(editorArea);
					areaMultiplicando.setEnabled(editorArea);		
					atualizaValores(contador);
					int multiplicadorInteiro = conversor.converteBinarioEmInteiroSemSinal(multiplicadorValidado);
					int multiplicandoInteiro = conversor.converteBinarioEmInteiroSemSinal(multiplicandoValidado);
					txtOperacao.setText("Operação realizada: "+multiplicadorInteiro+" x "+multiplicandoInteiro+" = "+multiplicadorInteiro*multiplicandoInteiro);	
				}
			}
		}
		if(e.getSource()==btnProximo){
			if(!editorArea && contador<8){
					++contador;
					atualizaValores(contador);
					barra.setValue(contador*125);
			}					
		}
		if(e.getSource()==btnAnterior){
			if(!editorArea && 0<contador){
					--contador;
					atualizaValores(contador);
					barra.setValue(contador*125);
			}					
		}
		if(e.getSource()==btnReiniciar){
			passos.clear();
			multiplicador.setText("00000000");
			multiplicando.setText("0000000000000000");	
			resultado.setText("0000000000000000");	
			ultimoBit.setText("0");
			editorArea = true;
			areaMultiplicador.setEnabled(editorArea);
			areaMultiplicando.setEnabled(editorArea);	
			contador=0;
			barra.setValue(0);
			areaMultiplicando.setText("");
			areaMultiplicador.setText("");

		}
	}

	public void atualizaValores(int indice){
		String linha = passos.get(indice);	
		String[] partes = linha.split(" ");
		resultado.setText(partes[0]);			
		multiplicador.setText(partes[1]);
		multiplicando.setText(partes[2]);
		ultimoBit.setText(String.valueOf(partes[1].charAt(7)));		

	}	 
	
	public InterfacePrincipal(){
		//Buttons
		btnIniciar.setBounds(220,41,100,50);
		btnIniciar.setBackground(Color.WHITE);
		btnIniciar.addActionListener(this);
		add(btnIniciar);
		btnAnterior.setBounds(320,41,100,50);
		btnAnterior.setBackground(Color.WHITE);
		btnAnterior.addActionListener(this);
		add(btnAnterior);
		btnProximo.setBounds(420,41,100,50);
		btnProximo.setBackground(Color.WHITE);
		btnProximo.addActionListener(this);
		add(btnProximo);
		btnReiniciar.setBounds(520,41,100,50);
		btnReiniciar.setBackground(Color.WHITE);
		btnReiniciar.addActionListener(this);
		add(btnReiniciar);
	
	
		//TextField para input
		areaMultiplicador.setBounds(130,41,70,20);
		add(areaMultiplicador);
		areaMultiplicando.setBounds(130,71,70,20);
		add(areaMultiplicando);
		
		//Labels:
		txtExplicativo.setBounds(20,10,700,20);
		add(txtExplicativo);
		txtMultiplicador.setBounds(20,40,100,20);
		add(txtMultiplicador);	
		txtMultiplicando.setBounds(20,70,100,20);
		add(txtMultiplicando);	
		txtOperacao.setBounds(20,440,700,20);
		add(txtOperacao);

		//Barra de Progresso
		barra.setBounds(515,415,150,20);
		barra.setForeground(Color.RED);
		barra.setMaximum(1000);
		barra.setValue(0);
		add(barra);

		//Multiplicando
		multiplicando.setBounds(111,151,225,20);
		multiplicando.setFont(fonte);
		multiplicando.setEditable(false);
		add(multiplicando);

		//Multiplicador		
		multiplicador.setBounds(396,151,97,20);
		multiplicador.setFont(fonte);
		multiplicador.setEditable(false);
		add(multiplicador);

		//Ultimo Bit
		ultimoBit.setBounds(498,151,18,20);
		ultimoBit.setFont(fonte);
		ultimoBit.setForeground(Color.RED);
		ultimoBit.setEditable(false);
		add(ultimoBit);

		//Area resultado
		resultado.setBounds(63,352,225,20);
		resultado.setFont(fonte);
		resultado.setEditable(false);
		add(resultado);

		//Background imagem
		label.setBounds(2,20,700,500);
		add(label);
		labelB.setBounds(0,0,700,500);
		add(labelB);


		//JFrame 
		setLayout(null);
		setTitle("Multiplicação de inteiros sem sinal");
		setSize(700,500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
		setResizable(false);

	}



}
