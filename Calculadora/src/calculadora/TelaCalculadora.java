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
    private boolean operadorDigitado = false;
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
        jbBotoes[20].addActionListener(e->{ pressionaNumero("0"); });
        jbBotoes[15].addActionListener(e->{ pressionaNumero("1"); });
        jbBotoes[16].addActionListener(e->{ pressionaNumero("2"); });
        jbBotoes[17].addActionListener(e->{ pressionaNumero("3"); });
        jbBotoes[10].addActionListener(e->{ pressionaNumero("4"); });
        jbBotoes[11].addActionListener(e->{ pressionaNumero("5"); });
        jbBotoes[12].addActionListener(e->{ pressionaNumero("6"); });
        jbBotoes[5].addActionListener(e->{ pressionaNumero("7"); });
        jbBotoes[6].addActionListener(e->{ pressionaNumero("8"); });
        jbBotoes[7].addActionListener(e->{ pressionaNumero("9"); });

        // "." com verificação
        jbBotoes[21].addActionListener(e -> {
            if (!valorString.contains(".")) {
                pressionaNumero(".");
            }
        });

        // Operadores simples
        jbBotoes[19].addActionListener(e->{ pressionaOperadorSimples("+"); });
        jbBotoes[14].addActionListener(e->{ pressionaOperadorSimples("-"); });
        jbBotoes[9].addActionListener(e->{ pressionaOperadorSimples("*"); });
        jbBotoes[4].addActionListener(e->{ pressionaOperadorSimples("/"); });

        // AC/C 
        jbBotoes[0].addActionListener(e -> {
            if (texto.isEmpty()) {
                // Reseta tudo
                texto = "";
                valorString = "";
                valor1 = 0;
                valor2 = 0;
                operador = "";
                jtxVisor.setText("");
            } else {
                if (texto.endsWith(" RAIZ Q")) {
                    texto = texto.substring(0, texto.length() - 6);
                    operador = "";
                } else if (texto.endsWith("^2")) {
                    texto = texto.substring(0, texto.length() - 2);
                    operador = "";
                } else if (texto.endsWith("^3")) {
                    texto = texto.substring(0, texto.length() - 2);
                    operador = "";
                } else if (texto.endsWith("^")) {
                    texto = texto.substring(0, texto.length() - 1);
                    operador = "";
                } else {
                    texto = texto.substring(0, texto.length() - 1);
                    if (!valorString.isEmpty()) {
                        valorString = valorString.substring(0, valorString.length() - 1);
                    }
                }
                jtxVisor.setText(texto);
            }
});

        // +/-
        jbBotoes[1].addActionListener(e -> {
            if (!valorString.isEmpty()) {
                double numero = Double.parseDouble(valorString);
                numero *= -1;
                valorString = String.valueOf(numero);
                texto = valorString;
                jtxVisor.setText(texto);
            }
        });

        // Porcentagem
        jbBotoes[2].addActionListener(e -> {
            if (!valorString.isEmpty()) {
                double numero = Double.parseDouble(valorString);
                numero /= 100;
                valorString = String.valueOf(numero);
                texto = valorString;
                jtxVisor.setText(texto);
            }
        });

        // Raiz quadrada
        jbBotoes[3].addActionListener(e -> {
            pressionaOperadorSimples(" RAIZ Q");
            valorString = "0";
        });

        // Potência de 2
        jbBotoes[13].addActionListener(e -> {
            pressionaOperadorSimples("^2");
            valorString = "0";
        });

        // Potência de 3
        jbBotoes[18].addActionListener(e -> {
            pressionaOperadorSimples("^3");
            valorString = "0";
        });

        // Potência x^y
        jbBotoes[8].addActionListener(e -> {
            pressionaOperadorSimples("^");
        });

        // Fatorial
        jbBotoes[22].addActionListener(e -> {
            if (!valorString.isEmpty()) {
                valor1 = Double.parseDouble(valorString);
                valor1 = fatorial(valor1);
                texto = String.valueOf(valor1);
                valorString = texto;
                jtxVisor.setText(texto);
            }
        });

        // 10^X
        jbBotoes[23].addActionListener(e -> {
            if (!valorString.isEmpty()) {
                valor1 = Double.parseDouble(valorString);
                valor1 = Math.pow(10, valor1);
                texto = String.valueOf(valor1);
                valorString = texto;
                jtxVisor.setText(texto);
            }
        });

        // Botão =
        jbBotoes[24].addActionListener(e -> {
            try {
                valor2 = Double.parseDouble(valorString);
                valor1 = calcular(valor1, valor2, operador);
                texto = String.valueOf(valor1);
                valorString = texto;
                jtxVisor.setText(texto);
                valor2 = 0;
                operador = "";
                operadorDigitado = false;
            } catch (Exception ex) {
                jtxVisor.setText("Erro");
            }
        });
    }

    private void pressionaNumero(String str){
        if(operador.equals("C")){
            operador = "";
        } 
        texto += str;
        jtxVisor.setText(texto);
        valorString += str;
    }

    private void pressionaOperadorSimples(String str){
        operador = str;
        texto += str;
        jtxVisor.setText(texto);
        valor1 = Double.parseDouble(valorString);
        valorString = "";
        operadorDigitado = true;
    }

    private double calcular(Double valor1, Double valor2, String str){
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
                if (valor2 == 0) {
                    JOptionPane.showMessageDialog(this, "Divisão por zero!");
                    return 0;
                }
                resultado = valor1 / valor2;
                break;
            case "^2":
                resultado = Math.pow(valor1, 2);
                break;
            case "^3":
                resultado = Math.pow(valor1, 3);
                break;
            case "^":
                resultado = Math.pow(valor1, valor2);
                break;
            case " RAIZ Q":
                resultado = Math.sqrt(valor1);
                break;
        }
        return resultado;
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
}
