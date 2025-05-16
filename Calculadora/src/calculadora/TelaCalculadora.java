package calculadora;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class TelaCalculadora extends JFrame{
    
    // Atributos
    private JPanel painel;
    private JPanel painelBotoes;
    private JPanel painelVisor;
    private JTextField jtxVisor;
    private String[] simbolos = {"AC/C","+/-","%","RAIZ Q","/","7","8","9","x^Y","*","4","5","6","x^2","-","1","2","3","x^3","+","0",".","X!","10^X","="};
    private JButton[] jbBotoes;
   
    
    public TelaCalculadora(){
        // Paineis
        painel = new JPanel(); // Painel
        painelBotoes = new JPanel(); // Painel dos Botões
        painelVisor = new JPanel(); 
        // Botões
        jbBotoes = new JButton[25];
        for(int i=0; i<25; i++){
            jbBotoes[i] = new JButton(simbolos[i]);
        }
        // Visor
        jtxVisor = new JTextField();
    }
    
    // Métodos públicos
    public void configurarJanela(){
        setVisible(true);
        setTitle("Calculadora");
        setSize(500,350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocation(1200,100);
        add(painel);
        configurarPainel();
    }
    public void configurarPainel(){
        
        // ------ Painel Principal ------ 
        painel.setLayout(new BorderLayout());
        painel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10)); // Bordas
        
        
        // ------ Painel Visor ------ 
        painelVisor.setLayout(new BorderLayout());
        painelVisor.setBorder(BorderFactory.createEmptyBorder(0,0,10,0)); // Borda inferior
        jtxVisor.setFont(new Font("Arial", Font.PLAIN, 24));
        jtxVisor.setHorizontalAlignment(JTextField.RIGHT); // Alinha os numeros a direita
        jtxVisor.setEditable(false);
        painelVisor.add(jtxVisor, BorderLayout.CENTER);
        painel.add(painelVisor, BorderLayout.NORTH);
        
        
        // ------ Painel Botões ------ 
        painelBotoes.setLayout(new GridLayout(5,5,3,3));
        for(int i=0; i<25; i++){ 
            painelBotoes.add(jbBotoes[i]); // Adiciona jbBotoes ao painelBotoes
            jbBotoes[i].setFont(new Font("Arial", Font.PLAIN, 18)); // Define a fonte
        }
        painel.add(painelBotoes,BorderLayout.CENTER);
       
        
    }
}
