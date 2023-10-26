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

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH; // Fill both horizontally and vertically

        // Input
        inputField = new JTextField("0");
        setPlaceholder(inputField, "Input");
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(inputField, gbc);

        // Input Base Selector
        inputBaseSelector = new JComboBox<>(new String[] { "Decimal", "Binary", "Hexadecimal" });
        gbc.weightx = 0.5;
        gbc.weighty = 0.0;
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        add(inputBaseSelector, gbc);

        inputBaseSelector.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                updateInputFieldPrefix();
            }
        });

        // Output
        outputField = new JTextField("");
        setPlaceholder(inputField, "Output");
        outputField.setEditable(false);
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        add(outputField, gbc);

        // Output Base Selector
        outputBaseSelector = new JComboBox<>(new String[] { "Decimal", "Binary", "Hexadecimal" });
        gbc.weightx = 0.5;
        gbc.weighty = 0.0;
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        add(outputBaseSelector, gbc);

        // Convert Button
        JButton convertButton = new JButton("Convert");
        gbc.weightx = 1.0;
        gbc.weighty = 0.0;
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 3; // Span 2 columns
        add(convertButton, gbc);

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

    private void setPlaceholder(JTextField textField, String placeholder) {
        textField.setText(placeholder);
        textField.setForeground(Color.GRAY);
        textField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (textField.getText().equals(placeholder)) {
                    textField.setText("");
                    textField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (textField.getText().isEmpty()) {
                    textField.setText(placeholder);
                    textField.setForeground(Color.GRAY);
                }
            }
        });
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
