package calculadora;

import java.awt.*;
import javax.swing.*;

public class TelaCalculadora extends JFrame{
    
    // Atributos
    private JPanel painel;
    private JPanel painelBotoes;
    private JPanel painelVisor;
    private JTextField jtxVisor;
    private String[] simbolos = {"AC/C","+/-","%","RAIZ Q","/","7","8","9","x^Y","*","4","5","6","x^2","-","1","2","3","x^3","+","0",".","X!","10^X","="};
    private JButton[] jbBotoes;
    // Atributos para calcular
    private boolean operadorDigitado = false;
    private String valorString ="";
    private double valor1 = 0;
    private double valor2 = 0;
    private String operador = "";

   
    
    public TelaCalculadora(){

        // ----- Paineis ----- 
        painel = new JPanel(); // Painel
        painelBotoes = new JPanel(); // Painel dos Botões
        painelVisor = new JPanel(); 

        // ----- Botões ----- 
        jbBotoes = new JButton[25];
        for(int i=0; i<25; i++){
            jbBotoes[i] = new JButton(simbolos[i]);
        }

        // ----- Visor ----- 
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

        
        // Ações dos botoes
        jbBotoes[0].addActionListener(e->{ // AC/C

        });
        jbBotoes[1].addActionListener(e->{ // +/-

        });
        jbBotoes[2].addActionListener(e->{ // %

        });
        jbBotoes[3].addActionListener(e->{ // RAIZ Q

        });
        jbBotoes[4].addActionListener(e-> { // /
            operador = "/";
            valor1 = Double.parseDouble(valorString)
            adicionarDigito("/")
        });
        jbBotoes[5].addActionListener(e-> adicionarDigito("7"));
        jbBotoes[6].addActionListener(e-> adicionarDigito("8"));
        jbBotoes[7].addActionListener(e-> adicionarDigito("9"));
        jbBotoes[8].addActionListener(e->{ // x^Y

        });
        jbBotoes[9].addActionListener(e-> adicionarDigito("*"));
        jbBotoes[10].addActionListener(e-> adicionarDigito("4"));
        jbBotoes[11].addActionListener(e-> adicionarDigito("5"));
        jbBotoes[12].addActionListener(e-> adicionarDigito("6"));
        jbBotoes[13].addActionListener(e->{ // x^2

        });
        jbBotoes[14].addActionListener(e-> adicionarDigito("-"));
        jbBotoes[15].addActionListener(e-> adicionarDigito("1"));
        jbBotoes[16].addActionListener(e-> adicionarDigito("2"));
        jbBotoes[17].addActionListener(e-> adicionarDigito("3"));
        jbBotoes[18].addActionListener(e->{ // x^3

        });
        jbBotoes[19].addActionListener(e-> adicionarDigito("+"));
        jbBotoes[20].addActionListener(e-> adicionarDigito("0"));
        jbBotoes[21].addActionListener(e-> adicionarDigito("."));
        jbBotoes[22].addActionListener(e->{ // X!

        });
        jbBotoes[23].addActionListener(e->{ // 10^X

        });
        jbBotoes[24].addActionListener(e->{ // =

        });
       
        
    }
    
    public void adicionarDigito(String digito){
        jtxVisor.setText(jtxVisor.getText() + digito);
    }


}
