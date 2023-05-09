
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Calculator extends JFrame implements ActionListener {
    private JTextField display;
    private JButton[] buttons;
    private String[] buttonLabels = {
            "7", "8", "9", "+",
            "4", "5", "6", "-",
            "1", "2", "3", "*",
            "0", "C", "=", "/"
    };
    private String operand1 = "";
    private String operand2 = "";
    private String operator = "";
    private boolean reset = false;

    public Calculator() {
        setTitle("Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(200, 200);
        setLayout(new BorderLayout());

        display = new JTextField();
        display.setEditable(false);
        add(display, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridLayout(4, 4));
        buttons = new JButton[16];
        for (int i = 0; i < 16; i++) {
            buttons[i] = new JButton(buttonLabels[i]);
            buttons[i].addActionListener(this);
            buttonPanel.add(buttons[i]);
        }
        add(buttonPanel, BorderLayout.CENTER);
    }

    public void actionPerformed(ActionEvent event) {
        String label = event.getActionCommand();
        if (label.equals("C")) {
            operand1 = "";
            operand2 = "";
            operator = "";
            display.setText("");
            reset = false;
        } else if (label.equals("=")) {
            if (!operator.equals("") && !operand1.equals("") && !operand2.equals("")) {
                double result = 0.0;
                switch (operator) {
                    case "+":
                        result = Double.parseDouble(operand1) + Double.parseDouble(operand2);
                        break;
                    case "-":
                        result = Double.parseDouble(operand1) - Double.parseDouble(operand2);
                        break;
                    case "*":
                        result = Double.parseDouble(operand1) * Double.parseDouble(operand2);
                        break;
                    case "/":
                        result = Double.parseDouble(operand1) / Double.parseDouble(operand2);
                        break;
                }
                display.setText(Double.toString(result));
                operand1 = Double.toString(result);
                operand2 = "";
                operator = "";
                reset = true;
            }
        } else if (label.equals("+") || label.equals("-") || label.equals("*") || label.equals("/")) {
            if (!operand1.equals("") && !reset) {
                operator = label;
                display.setText(operand1 + " " + operator + " ");
            }
        } else if (label.equals("←")) {
            if (!reset) {
                if (operator.equals("")) {
                    operand1 = operand1.substring(0, operand1.length() - 1);
                    display.setText(operand1);
                } else if (operand2.equals("")) {
                    operator = "";
                    display.setText(operand1);
                } else {
                    operand2 = operand2.substring(0, operand2.length() - 1);
                    display.setText(operand1 + " " + operator + " " + operand2);
                }
            }
        } else {
            if (reset) {
                operand1 = "";
                reset = false;
            }
            if (operator.equals("")) {
                operand1 += label;
                display.setText(operand1);
            } else {
                operand2 += label;
                display.setText(operand1 + " " + operator + " " + operand2);
            }
        }
    }

    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        calculator.setVisible(true);
    }
}

