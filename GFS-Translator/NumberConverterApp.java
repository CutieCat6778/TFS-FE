import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class NumberConverterApp extends JFrame {
    private JTextField inputField;
    private JTextField outputField;
    private JComboBox<String> inputBaseSelector;
    private JComboBox<String> outputBaseSelector;

    public NumberConverterApp() {
        setTitle("Number Converter");
        setSize(400, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 2));

        // Input
        inputField = new JTextField("0");
        add(inputField);

        // Input Base Selector
        inputBaseSelector = new JComboBox<>(new String[] { "Decimal", "Binary", "Hexadecimal" });
        add(inputBaseSelector);

        inputBaseSelector.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                updateInputFieldPrefix();
            }
        });

        // Output
        outputField = new JTextField("0");
        outputField.setEditable(false);
        add(outputField);

        // Output Base Selector
        outputBaseSelector = new JComboBox<>(new String[] { "Decimal", "Binary", "Hexadecimal" });
        add(outputBaseSelector);

        // Convert Button
        JButton convertButton = new JButton("Convert");
        add(convertButton);

        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                convertNumber();
            }
        });

        setVisible(true);
    }

    private void updateInputFieldPrefix() {
        String selectedInputBase = (String) inputBaseSelector.getSelectedItem();
        if (selectedInputBase.equals("Binary")) {
            inputField.setText("0b");
        } else if (selectedInputBase.equals("Hexadecimal")) {
            inputField.setText("0x");
        }
    }

    private String validateInput() {
        String inputNumber = inputField.getText();

        if (inputNumber.startsWith("0d")) {
            inputNumber = inputNumber.substring(2);
        } else if (inputNumber.startsWith("0b")) {
            inputNumber = inputNumber.substring(2);
        } else if (inputNumber.startsWith("0x")) {
            inputNumber = inputNumber.substring(2);
        }

        return inputNumber;
    }

    private void convertNumber() {
        String inputNumber = validateInput();

        String inputBase = (String) inputBaseSelector.getSelectedItem();
        String outputBase = (String) outputBaseSelector.getSelectedItem();

        try {
            if (inputBase.equals("Decimal") && inputNumber.startsWith("-") && !outputBase.equals("Decimal")) {
                throw new IllegalArgumentException("Number < 0");
            }
            if (inputBase.equals("Decimal")) {
                long decimalNumber = Long.parseLong(inputNumber);
                if (outputBase.equals("Binary")) {
                    outputField.setText(Long.toBinaryString(decimalNumber));
                } else if (outputBase.equals("Hexadecimal")) {
                    outputField.setText(Long.toHexString(decimalNumber).toUpperCase());
                } else {
                    outputField.setText(inputNumber); // Output in Decimal
                }
            } else if (inputBase.equals("Binary")) {
                long decimalNumber = Long.parseLong(inputNumber, 2);
                if (outputBase.equals("Decimal")) {

                    outputField.setText(Long.toString(decimalNumber).toUpperCase());
                } else if (outputBase.equals("Hexadecimal")) {
                    outputField.setText(Long.toHexString(decimalNumber));
                } else {
                    outputField.setText(inputNumber); // Output in Binary
                }
            } else if (inputBase.equals("Hexadecimal")) {
                long decimalNumber = Long.parseLong(inputNumber, 16);
                if (outputBase.equals("Decimal")) {
                    outputField.setText(Long.toString(decimalNumber));
                } else if (outputBase.equals("Binary")) {
                    outputField.setText(Long.toBinaryString(decimalNumber));
                } else {
                    outputField.setText(inputNumber); // Output in Hexadecimal
                }
            }
        } catch (NumberFormatException ex) {
            outputField.setText("Invalid input");
            return;
        } catch (IllegalArgumentException ex) {
            outputField.setText(ex.getMessage());
            return;
        }

        // Set the output
        String prefix = "";
        if (outputBaseSelector.getSelectedItem().equals("Binary")) {
            prefix = "0b";
        } else if (outputBaseSelector.getSelectedItem().equals("Hexadecimal")) {
            prefix = "0x";
        }

        // Set the output with the prefix
        outputField.setText(prefix + outputField.getText());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new NumberConverterApp());
    }
}
