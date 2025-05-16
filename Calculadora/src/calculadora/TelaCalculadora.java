package calculadora;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.*;

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
        painelVisor = new JPanel(); // Painel do Visor
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
        setSize(400,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocation(400,100);
        add(painel);
        configurarPainel();
    }
    public void configurarPainel(){
        
        // Painel Visor
        painelVisor.setLayout(new FlowLayout(FlowLayout.RIGHT));
        jtxVisor.setColumns(25);
        jtxVisor.setEditable(false);
        painelVisor.add(jtxVisor);
        
        // Painel Botões
        painelBotoes.setLayout(new GridLayout(5,5,1,1));
        for(int i=0; i<25; i++){
            painelBotoes.add(jbBotoes[i]);
        }
            
        
        
        painel.setLayout(new FlowLayout(FlowLayout.LEADING));
        painel.add(painelVisor);
        painel.add(painelBotoes);
    }
}
