package calculadora;

import java.awt.*;
import javax.swing.*;

public class TelaCalculadora extends JFrame {
    
    // Atributos
    private JPanel painel;
    private JPanel painelBotoes;
    private JPanel painelVisor;
    private JTextField jtxVisor;
    private String[] simbolos = {"AC/C","+/-","%","RAIZ Q","/","7","8","9","x^Y","*","4","5","6","x^2","-","1","2","3","x^3","+","0",".","X!","10^X","="};
    private JButton[] jbBotoes;
    private boolean clicavel = false;
    private boolean apagar = false;
    private String texto = "";
    private String valorString = "";
    private double valor1 = 0;
    private double valor2 = 0;
    private String operador = "";

    public TelaCalculadora(){
        painel = new JPanel(); 
        painelBotoes = new JPanel(); 
        painelVisor = new JPanel(); 
        jbBotoes = new JButton[25];
        for(int i=0; i<25; i++){
            jbBotoes[i] = new JButton(simbolos[i]);
        }
        jtxVisor = new JTextField();
    }

    public void configurarJanela(){
        setVisible(true);
        setTitle("Calculadora");
        setSize(520,350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocation(800,100);
        add(painel);
        configurarPainel();
    }

    public void configurarPainel(){
        painel.setLayout(new BorderLayout());
        painel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        painelVisor.setLayout(new BorderLayout());
        painelVisor.setBorder(BorderFactory.createEmptyBorder(0,0,10,0));
        jtxVisor.setFont(new Font("Arial", Font.PLAIN, 24));
        jtxVisor.setHorizontalAlignment(JTextField.RIGHT);
        jtxVisor.setEditable(false);
        painelVisor.add(jtxVisor, BorderLayout.CENTER);
        painel.add(painelVisor, BorderLayout.NORTH);

        painelBotoes.setLayout(new GridLayout(5,5,3,3));
        for(int i=0; i<25; i++){ 
            painelBotoes.add(jbBotoes[i]);
            jbBotoes[i].setFont(new Font("Arial", Font.PLAIN, 18));
        }
        painel.add(painelBotoes,BorderLayout.CENTER);

        // Números
        jbBotoes[20].addActionListener(e->{ // 0
            if (!(valorString.equals("0") && texto.endsWith("0"))){
                pressionaNumero("0");
            }
            
        });    
        jbBotoes[15].addActionListener(e->{ pressionaNumero("1"); });
        jbBotoes[16].addActionListener(e->{ pressionaNumero("2"); });
        jbBotoes[17].addActionListener(e->{ pressionaNumero("3"); });
        jbBotoes[10].addActionListener(e->{ pressionaNumero("4"); });
        jbBotoes[11].addActionListener(e->{ pressionaNumero("5"); });
        jbBotoes[12].addActionListener(e->{ pressionaNumero("6"); });
        jbBotoes[5].addActionListener(e->{ pressionaNumero("7"); });
        jbBotoes[6].addActionListener(e->{ pressionaNumero("8"); });
        jbBotoes[7].addActionListener(e->{ pressionaNumero("9"); });

        jbBotoes[21].addActionListener(e -> { // Ponto .
            if (!valorString.contains(".")) { // Verifica se já tem ponto
                pressionaNumero(".");
            }
        }); // Ponto .

        // Operadores Simples
        jbBotoes[19].addActionListener(e->{ pressionaOperador("+"); });
        jbBotoes[14].addActionListener(e->{ pressionaOperador("-"); });
        jbBotoes[9].addActionListener(e->{ pressionaOperador("*"); });
        jbBotoes[4].addActionListener(e->{ pressionaOperador("/"); });
        
        // Potências
        jbBotoes[8].addActionListener(e -> {
            pressionaOperador("^");
        });  // x^y
        jbBotoes[13].addActionListener(e -> { // x^2
            pressionaOperador("^2");
            valorString = "0";
        }); // x^2
        jbBotoes[18].addActionListener(e -> {
            pressionaOperador("^3");
            valorString = "0";
        }); // x^3
        jbBotoes[23].addActionListener(e -> { // 10^X
            pressionaOperadorEspecial("10^");
            jtxVisor.setText(texto); // Exibe o texto
            valorString = "0";
        }); // 10^X
        

        // Outros
        jbBotoes[0].addActionListener(e -> { // AC/C 
            if (texto.isEmpty()){ // Verifica se está vazia
                apagarTudo();
            }else if(apagar){ // Verifica se ja apagou ultimo
                apagarTudo();
            }else{
                if (operador.equals("RAIZ Q(")) {
                    texto = texto.substring(7, texto.length() - 1);
                }else if (operador.equals("10^")){
                    texto = texto.substring(3);
                } else if (operador.equals("^2")) {
                    texto = texto.substring(0, texto.length() - 2);
                } else if (operador.equals("^3")) {
                    texto = texto.substring(0, texto.length() - 2);
                } else if (operador.equals("^")) {
                    texto = texto.substring(0, texto.length() - 1);
                } else {
                    texto = texto.substring(0, texto.length() - 1);
                    if (!valorString.isEmpty()) {
                        valorString = valorString.substring(0, valorString.length() - 1);
                    }
                }
                operador = ""; // Reseta Operador
                valorString = String.valueOf(valor1);
                jtxVisor.setText(texto);
                apagar = true;
            }
            
        }); // AC/C 
        jbBotoes[1].addActionListener(e -> { // +/-
            if (!valorString.isEmpty()) {
                double numero = Double.parseDouble(valorString);
                numero *= -1;
                valorString = String.valueOf(numero);
                texto = valorString;
                jtxVisor.setText(texto);
            }
        }); // +/-
        jbBotoes[3].addActionListener(e -> { // RAIZ Q
            pressionaOperadorEspecial("RAIZ Q(");
            texto = texto.concat(")");
            jtxVisor.setText(texto); // Exibe o texto
            valorString = "0";
        }); // RAIZ Q
        jbBotoes[2].addActionListener(e -> { // %
            pressionaOperador("%");
        });  // %
        jbBotoes[22].addActionListener(e -> {// !
            pressionaOperador("!");
            valorString = "0";
        }); // Fatorial
        jbBotoes[24].addActionListener(e -> {// =
            valor2 = Double.parseDouble(valorString);
            valor1 = calcular(valor1, valor2, operador);
            texto = formatarNumero(valor1);
            valorString = texto;
            jtxVisor.setText(texto);
            valor2 = 0;
            operador = "";
            clicavel = false;
            apagar = false;
        }); // =
        
    }

    private void pressionaNumero(String str){
        if (valorString.equals("0") && texto.endsWith("0")){
                valorString = "";
                texto = texto.substring(0, texto.length() - 1);
            }
        texto = texto.concat(str); // Concatena texto 
        jtxVisor.setText(texto); // Exibe o texto
        valorString = valorString.concat(str); // Concatena valorString
        apagar = false; // reseta estado do apagar
    }
    private void pressionaOperador(String str){
        texto = texto.concat(str); // Concatena texto 
        jtxVisor.setText(texto); // Exibe o texto
        operador = str; // Atualizo operador
        valor1 = Double.parseDouble(valorString); // Converte para double
        valorString = "0"; // Reseta Valor String
        apagar = false;
    }
    private void pressionaOperadorEspecial(String str){
        operador = str; // Atualizo operador
        valor1 = Double.parseDouble(valorString);// Converte para double
        valorString = "0"; // Reseta Valor String
        texto = str.concat(texto);
        apagar = false;
    }
    private double calcular(Double valor1, Double valor2, String str){
        switch(str){
            case "+":
                valor1 = valor1 + valor2;
                break;
            case "-":valor1 = valor1 - valor2;
                break;
            case "*":valor1 = valor1 * valor2;
                break;
            case "/":
                if (valor2 == 0) {
                    JOptionPane.showMessageDialog(this, "Divisão por zero!");
                    valor1 = 0.0;
                }
                valor1 =  valor1 / valor2;
                break;
            case "^2": 
                valor1 = Math.pow(valor1, 2);
                break;
            case "^3": 
                valor1 = Math.pow(valor1, 3);
                break;
            case "^": 
                valor1 = Math.pow(valor1, valor2);
                break;
            case "RAIZ Q(":
                valor1 = Math.sqrt(valor1);
                break;
            case "!":
                valor1 = fatorial(valor1);
                break;
            case "10^":
                valor1 = Math.pow(10, valor1);
                break;
            case "%":
                valor1 = (valor1/100)*valor2;
                break;
        }
        return valor1;
    }
    private double fatorial(double n) {
        if (n < 0 || n != (int)n) {
            JOptionPane.showMessageDialog(this, "Fatorial só é válido para inteiros não negativos.");
            return 0;
        }
        double fat = 1;
        for (int i = 1; i <= n; i++) {
            fat *= i;
        }
        return fat;
    }
    private void travarOperador(boolean clicavel){
        jbBotoes[19].setEnabled(clicavel);
    }
    private void apagarTudo(){
        // Apaga tudo
        texto = "";
        valorString = "";
        valor1 = 0;
        valor2 = 0;
        operador = "";
        jtxVisor.setText("");
    }
    private String formatarNumero(double numero) {
    if (numero == (int) numero) {
        return String.valueOf((int) numero); // Converte para int se for número inteiro
    } else {
        return String.valueOf(numero); // Mantém como double se tiver decimal
    }
}

}
