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
    private String texto = "";
    private String valorString ="";
    private double valor1 = 0;
    private double valor2 = 0;
    private double resultado = 0;
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
        setLocation(800,100);
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
        jbBotoes[0].addActionListener(e-> { // AC/C

        }); // AC/C
        jbBotoes[1].addActionListener(e-> { // +/-

        }); // +/-
        jbBotoes[2].addActionListener(e-> { // %

        }); // %
        jbBotoes[3].addActionListener(e-> { // RAIZ Q

        }); // RAIZ Q
        jbBotoes[4].addActionListener(e-> { // /
            pressionaOperadorSimples("/");
        }); // /
        jbBotoes[5].addActionListener(e-> { // 7
            pressionaNumero("7");
        }); // 7
        jbBotoes[6].addActionListener(e-> { // 8
            pressionaNumero("8");
        }); // 8
        jbBotoes[7].addActionListener(e-> { // 9
            pressionaNumero("9");
        }); // 9
        jbBotoes[8].addActionListener(e-> { // x^Y

        }); // x^Y
        jbBotoes[9].addActionListener(e-> { // *
            pressionaOperadorSimples("*");
        }); // *
        jbBotoes[10].addActionListener(e->{ // 4
            pressionaNumero("4");
        }); // 4
        jbBotoes[11].addActionListener(e->{ // 5
            pressionaNumero("5");
        }); // 5
        jbBotoes[12].addActionListener(e->{ // 6
            pressionaNumero("6");
        }); // 6
        jbBotoes[13].addActionListener(e->{ // x^2

        }); // x^2
        jbBotoes[14].addActionListener(e->{ // -
            pressionaOperadorSimples("-");
        }); // -
        jbBotoes[15].addActionListener(e->{ // 1
            pressionaNumero("1");
        }); // 1
        jbBotoes[16].addActionListener(e->{ // 2
            pressionaNumero("2");
        }); // 2
        jbBotoes[17].addActionListener(e->{ // 3
            pressionaNumero("3");
        }); // 3
        jbBotoes[18].addActionListener(e->{ // x^3

        }); // x^3
        jbBotoes[19].addActionListener(e->{ // +
            pressionaOperadorSimples("+");
        }); // +
        jbBotoes[20].addActionListener(e->{ // 0
            pressionaNumero("0");
        }); // 0
        jbBotoes[21].addActionListener(e->{ // .
            pressionaNumero(".");
        }); // .
        jbBotoes[22].addActionListener(e->{ // X!

        }); // X!
        jbBotoes[23].addActionListener(e->{ // 10^X

        }); // 10^X
        jbBotoes[24].addActionListener(e->{ // =
            valor2 = Double.parseDouble(valorString); // Converte o segundo numero
            valor1 = calcular(valor1, valor2, operador); // Calcula a operação
            texto = String.valueOf(valor1); // Converte resultado em String
            valorString = texto; // copia para fazer proxima conta
            jtxVisor.setText(texto); // Exibe na tela
            valor2 = 0; // Zera o valor2
            operador = ""; // Limpa operador
            
            operadorDigitado = false;
        }); // =
       
        
    }
    private void pressionaNumero(String str){
        // se digitar numeros ou .
        texto += str; // Concatena str ao texto
        jtxVisor.setText(texto); // exibe o texto
        valorString += str; // Concatena str ao valorString
    }
    private void pressionaOperadorSimples(String str){
        // se digitar operador simples +,-,*,/
        operador = str;
        texto += str; // concatena o texto
        jtxVisor.setText(texto); // exibe o texto
        valor1 = Double.parseDouble(valorString);
        valorString = "";
        operadorDigitado = true;
        // Desativar botoes operadores
    }
    private double calcular(Double valor1, Double valor2,String str){
        double resultado = 0;
        switch(str){
            case "+":
                resultado = valor1 + valor2;
                break;
            case "-":
                resultado = valor1 - valor2;
                break;
            case "*":
                resultado = valor1 * valor2;
                break;
            case "/":
                resultado = valor1 / valor2;
                break;
        }
        return resultado;
    }
}
