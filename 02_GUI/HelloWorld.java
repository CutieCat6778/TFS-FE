/*
 * 
 *  Hello World wird an der stelle 70, 50 ausgeben
 * 
 *  Author: Thinh Nguyen
 *  Datum: 25.10.2023
 * 
 */

import java.awt.*;
import java.awt.event.*;

public class HelloWorld {
  private Frame mainFrame;
  private Label headerLabel;
  private TextField nameTextField; // Input field
  private TextArea resultTextArea; // To display changes

  public HelloWorld() {
    prepareGUI();
    showTextInput();
  }

  public static void main(String[] args) {
    new HelloWorld();
  }

  private void prepareGUI() {
    mainFrame = new Frame("Aufgabe 02");
    mainFrame.setSize(400, 400);
    mainFrame.setLayout(new GridLayout(3, 1));
    mainFrame.addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent windowEvent) {
        System.exit(0);
        System.out.println("--- Window Closed ---");
      }
    });

    headerLabel = new Label("Geben Sie Ihre Name ein!");
    headerLabel.setAlignment(Label.CENTER);
    nameTextField = new TextField();
    resultTextArea = new TextArea();
  }

  private void showTextInput() {
    mainFrame.add(headerLabel);
    mainFrame.add(nameTextField);
    mainFrame.add(resultTextArea);
    mainFrame.setVisible(true);

    nameTextField.addTextListener(new TextListener() {
      public void textValueChanged(TextEvent e) {
        String text = nameTextField.getText();
        resultTextArea.setText("Ihre Name: " + text);
      }
    });
  }
}