package part1startercode;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;

/**
 * CelebrityPanel for the game Celebrity
 * 
 * @author cody.henrichsen
 * @version 2.9 18/09/2018 Adjusted the listener functionality.
 */
public class CelebrityPanel extends JPanel implements ActionListener {
  
  /**
   * The button pressed when making a guess.
   */
  private JButton guessButton;
  
  /**
   * The button pressed to restart the game
   */
  private JButton resetButton;
  
  /**
   * The label used to identify what to type in the field
   */
  private JLabel guessLabel;
  
  /**
   * The label to hold the static text for the timer
   */
  private JLabel staticTimerLabel;
  
  /**
   * The label to hold the dynamic text of the timer.
   */
  private JLabel dynamicTimerLabel;
  
  /**
   * Timer for display
   */
  private Timer countdownTimer;
  
  /**
   * Listener for the countdownTimer
   */
  private ActionListener timerListener;
  
  /**
   * Holds the user and program input text area and allows the text to scroll.
   */
  private JScrollPane cluePane;
  
  /**
   * The text area for user and program information
   */
  private JTextArea clueArea;
  
  /**
   * The user interaction field.
   */
  private JTextField guessField;
  
  /**
   * Layout manager for the panel. Uses constraints between components to
   * align or spring from edges.
   */
  private SpringLayout panelLayout;
  
  /**
   * The String used when a user correctly guesses the Celebrity
   */
  private String success;
  
  /**
   * The String used when a user has not guessed correctly.
   */
  private String tryAgain;
  
  /**
   * The current value of the time in seconds.
   */
  private int seconds;
  
  /**
   * Reference to the game instance.
   */
  private CelebrityGame controller;
  
  /**
   * Builds the CelebrityPanel and initializes all data members.
   * 
   * @param controllerRef
   *            Reference to the Game passed when the CelebrityPanel is
   *            instantiated in the Frame.
   */
  public CelebrityPanel(CelebrityGame controllerRef) {
    super();
    controller = controllerRef;
    panelLayout = new SpringLayout();
    guessLabel = new JLabel("Guess:");
    staticTimerLabel = new JLabel("Time remaining: ");
    dynamicTimerLabel = new JLabel("30");
    guessButton = new JButton("Submit guess");
    resetButton = new JButton("Start again");
    clueArea = new JTextArea("", 30, 20);
    cluePane = new JScrollPane(clueArea);
    guessField = new JTextField("Enter guess here", 30);
    success = "You guessed correctly!!! \nNext Celebrity clue is: ";
    tryAgain = "You have chosen poorly, try again!\nThe clue is: ";
    seconds = 30;
    countdownTimer = new Timer(1000, null);

    setupPanel();
    setupLayout();
    setupListeners();
  }
  
  /**
   * Helper method to add all components to the panel and adjust GUI settings
   * including scroll bars, and line wrap.
   */
  private void setupPanel() {
    setLayout(panelLayout);
    add(guessLabel);
    add(cluePane);
    add(guessField);
    add(guessButton);
    add(resetButton);
    add(dynamicTimerLabel);
    add(staticTimerLabel);

    //Changes the font to be larger than default
    staticTimerLabel.setFont(new Font("Helvetica", Font.BOLD,20));
    dynamicTimerLabel.setFont(new Font("Helvetica", Font.BOLD,20));

    // These lines allow vertical scrolling but not horizontal.
    cluePane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    cluePane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

    // These lines allow word and line wrapping for the clue area.
    clueArea.setWrapStyleWord(true);
    clueArea.setLineWrap(true);

    // The clue area is set to not be editable by the user :D
    clueArea.setEditable(false);
  }
  
  /**
   * Using a helper method to hold all the constraints for the GUI components
   * in the panel
   */
  private void setupLayout() {
    panelLayout.putConstraint(SpringLayout.NORTH, cluePane, 15, SpringLayout.NORTH, this);
    panelLayout.putConstraint(SpringLayout.WEST, cluePane, 15, SpringLayout.WEST, this);
    panelLayout.putConstraint(SpringLayout.SOUTH, cluePane, -100, SpringLayout.SOUTH, this);
    panelLayout.putConstraint(SpringLayout.EAST, cluePane, -15, SpringLayout.EAST, this);
    panelLayout.putConstraint(SpringLayout.NORTH, guessButton, 10, SpringLayout.SOUTH, guessLabel);
    panelLayout.putConstraint(SpringLayout.SOUTH, guessButton, -15, SpringLayout.SOUTH, this);
    panelLayout.putConstraint(SpringLayout.NORTH, resetButton, 0, SpringLayout.NORTH, guessButton);
    panelLayout.putConstraint(SpringLayout.EAST, guessButton, 0, SpringLayout.EAST, cluePane);
    panelLayout.putConstraint(SpringLayout.WEST, resetButton, 0, SpringLayout.WEST, cluePane);
    panelLayout.putConstraint(SpringLayout.NORTH, guessLabel, 10, SpringLayout.SOUTH, cluePane);
    panelLayout.putConstraint(SpringLayout.WEST, guessLabel, 0, SpringLayout.WEST, cluePane);
    panelLayout.putConstraint(SpringLayout.SOUTH, resetButton, 0, SpringLayout.SOUTH, guessButton);
    panelLayout.putConstraint(SpringLayout.NORTH, guessField, 0, SpringLayout.NORTH, guessLabel);
    panelLayout.putConstraint(SpringLayout.WEST, guessField, 5, SpringLayout.EAST, guessLabel);
    panelLayout.putConstraint(SpringLayout.EAST, guessField, 0, SpringLayout.EAST, cluePane);
    panelLayout.putConstraint(SpringLayout.NORTH, staticTimerLabel, 15, SpringLayout.NORTH, resetButton);
    panelLayout.putConstraint(SpringLayout.WEST, staticTimerLabel, 10, SpringLayout.EAST, resetButton);
    panelLayout.putConstraint(SpringLayout.NORTH, dynamicTimerLabel, 0, SpringLayout.NORTH, staticTimerLabel);
    panelLayout.putConstraint(SpringLayout.WEST, dynamicTimerLabel, 5, SpringLayout.EAST, staticTimerLabel);
  }
  
  /*
   * Attaches listeners to the GUI components of the program
   */
  private void setupListeners() {
    guessButton.addActionListener(this);
    countdownTimer.addActionListener(this);
    countdownTimer.start();
    resetButton.addActionListener(this);
  }
  
  /**
   * Helper method for when the ActionListener attached to the timer fires.
   * Sets the text of the label to match the remaining time and a message at
   * the end.
   */
  private void timerFires() {
    seconds--;
    dynamicTimerLabel.setText("" + seconds);
    if (seconds == 0) {
      countdownTimer.stop();
      staticTimerLabel.setText("Time's up! YOU LOSE!");
      dynamicTimerLabel.setText("");
      guessButton.setEnabled(false);
      guessField.setEnabled(false);
    }
  }
  
  /**
   * Method to add a clue to the screen from the game instance
   * 
   * @param clue
   *            The clue to add to the screen.
   */
  public void addClue(String clue) {
    clueArea.setText("The clue is: " + clue);
    seconds = 30;
    dynamicTimerLabel.setText("" + seconds);
    countdownTimer.restart();
  }
  
  /**
   * Method to allow both button and enter press in the guessField
   * to provide the same functionality.
   */
  private void updateScreen() {
    clueArea.append("\nYou guessed: " + guessField.getText() + "\n");
    if (controller.processGuess(guessField.getText())) {
      clueArea.setBackground(Color.CYAN);
      clueArea.append("\n" + success + "\n");
      clueArea.append(controller.sendClue());
    } else {
      clueArea.setBackground(Color.WHITE);
      clueArea.append("\n" + tryAgain + "\n");
      clueArea.append(controller.sendClue());
    }

    if (controller.getCelebrityGameSize() == 0) {
      clueArea.append("\nNo more celebrities to guess.");
      guessButton.setEnabled(false);
      guessField.setEnabled(false);
      staticTimerLabel.setText("YOU WIN!");
      dynamicTimerLabel.setText("");
      countdownTimer.stop();
    }
  }

  public void actionPerformed(ActionEvent ae) {
    Object source = ae.getSource();
    if (source instanceof JButton) {
      JButton clickedButton = (JButton) source;
      String text = clickedButton.getText();

      if (text.equals("Submit guess")) {
        updateScreen();
      } else if (text.equals("Start again")) {
        controller.reset();
        CelebrityGame game = new CelebrityGame();
      }
    } else if (source instanceof Timer) {


      timerFires();
    }
  }
}
