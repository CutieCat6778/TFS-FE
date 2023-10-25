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
  private Label statusLabel;
  private Panel controlPanel;
  private Label msgLabel;

  public HelloWorld() {
    prepareGUI();
  }

  public static void main(String[] args) {
    HelloWorld awtContainerDemo = new HelloWorld();
    awtContainerDemo.ShowDemoFrame();
  }

  private void prepareGUI() {
    mainFrame = new Frame("Hello World!");
    mainFrame.setSize(400, 400);
    mainFrame.setLayout(new GridLayout(3, 1));
    mainFrame.addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent windowEvent) {
        System.exit(0);
      }
    });

    headerLabel = new Label("Hello world!");
    headerLabel.setAlignment(Label.CENTER);
    statusLabel = new Label("From Thinh Nguyen");
    statusLabel.setAlignment(Label.CENTER);
    statusLabel.setSize(350, 100);

    msgLabel = new Label("Wellcome to TFS");
    msgLabel.setAlignment(Label.CENTER);

    controlPanel = new Panel();
    controlPanel.setLayout(new FlowLayout());

    mainFrame.add(headerLabel);
    mainFrame.add(controlPanel);
    mainFrame.add(statusLabel);
  }

  private void ShowDemoFrame() {
    headerLabel.setText("Container in action: Frame");

    final Frame frame = new Frame();
    frame.setSize(300, 300);
    frame.setLayout(new FlowLayout());
    frame.add(msgLabel);
    frame.addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent windowEvent) {
        frame.dispose();
        statusLabel.setText("From Thinh Nguyen");
      }
    });

    Button okButton = new Button("Open new frame!");

    okButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        statusLabel.setText("A Frame shown to the user!");
        frame.setVisible(true);
      }
    });

    controlPanel.add(okButton);

    mainFrame.setVisible(true);
  }
}