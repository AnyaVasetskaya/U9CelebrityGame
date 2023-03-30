package part1startercode;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

/**
 * The start screen for the CelebrityGame app.
 * 
 * @author cody.henrichsen
 * @version 2.1 18/09/2018 Refactored away validation to controller.
 */
public class StartPanel extends JPanel implements ActionListener {
  /**
   * Reference to the Game to call methods.
   */
  private CelebrityGame controller;
  
  /**
   * The layout manager for the screen.
   */
  private SpringLayout panelLayout;
  
  /**
   * Logical container for the RadioButtons to guarantee only one is selected
   * at a time.
   */
  private ButtonGroup typeGroup;
  
  /**
   * RadioButton for the default type.
   */
  private JRadioButton celebrityRadio;
  
  /**
   * Customize the JRadioButton for the class created sub class
   */
  
  /**
   * Label to guide the user to what should be inputted.
   */
  private JLabel clueLabel;
  
  /**
   * Label for displaying the current number of celebrities added to the game
   */
  private JLabel celebrityCountLabel;
  
  /**
   * Textfield to type in the answer for the celebrity.
   */
  private JTextField answerField;
  
  /**
   * Textfield to type in the clue for the celebrity.
   */
  private JTextField clueField;
  
  /**
   * Button used to verify and add a Celebrity to the ArrayList of Celebrity for the game
   */
  private JButton addCelebrityButton;
  
  /**
   * Button used to start the game.
   */
  private JButton startButton;
  
  /**
   * String to populate the clueLabel if Celebrity is picked.
   */
  private String celebrityClue;
  
  /**
   * String to populate the clueLabel if Class Generated Celebrity is picked.
   */

  /**
   * String used for static text in label.
   */
  private String countLabelText;
  
  /**
   * The current number of celebrities added to the game
   */
  private int celebrityCount;

  /**
   * Constructs a StartPanel with a reference to the game passed as a
   * parameter to be used as a data member.
   * 
   * @param controllerRef  The reference to the game
   */
  public StartPanel(CelebrityGame controllerRef) {
    super();
    controller = controllerRef;
    panelLayout = new SpringLayout();
    typeGroup = new ButtonGroup();
    celebrityRadio = new JRadioButton("Celebrity");
    celebrityClue = "Enter the clue for the celebrity";
    clueLabel = new JLabel(celebrityClue);

    answerField = new JTextField("Type celebrity here (3  letters min)");
    clueField = new JTextField("Enter celebrity clue here (10 letters min)");
    addCelebrityButton = new JButton("Add current celebrity");
    startButton = new JButton("Start Celebrity game");
    celebrityCount = 0;
    countLabelText = "Current Celebrity Count: " + celebrityCount;
    celebrityCountLabel = new JLabel(countLabelText);

    // these setup methods are defined below
    setupPanel();
    setupLayout();
    setupListeners();
  }
  
  /**
   * Adds all components to the StartPanel and uses the SpringLayout variable,
   * panelLayout, as the layout manager.
   */
  private void setupPanel() {
    setLayout(panelLayout);
    add(clueLabel);
    add(celebrityRadio);
    add(answerField);
    add(clueField);
    add(startButton);
    add(celebrityCountLabel);
    add(addCelebrityButton);

    celebrityRadio.setSelected(true);
    startButton.setEnabled(false);
    typeGroup.add(celebrityRadio);
  }
  
  /**
   * Uses the Springlayout constraint system to place all GUI components on
   * screen. All constraints grouped together to keep code clean and
   * maintainable.
   */
  private void setupLayout() {
    panelLayout.putConstraint(SpringLayout.WEST, clueLabel, 0, SpringLayout.WEST, celebrityRadio);
    panelLayout.putConstraint(SpringLayout.NORTH, celebrityRadio, 15, SpringLayout.NORTH, this);
    panelLayout.putConstraint(SpringLayout.WEST, celebrityRadio, 15, SpringLayout.WEST, this);
    panelLayout.putConstraint(SpringLayout.EAST, addCelebrityButton, 0, SpringLayout.EAST, startButton);
    panelLayout.putConstraint(SpringLayout.NORTH, addCelebrityButton, 20, SpringLayout.SOUTH, clueField);
    panelLayout.putConstraint(SpringLayout.WEST, addCelebrityButton, 0, SpringLayout.WEST, celebrityRadio);
    
    panelLayout.putConstraint(SpringLayout.NORTH, startButton, 20, SpringLayout.SOUTH, addCelebrityButton);
    panelLayout.putConstraint(SpringLayout.NORTH, celebrityCountLabel, 0, SpringLayout.NORTH, celebrityRadio);
    panelLayout.putConstraint(SpringLayout.EAST, celebrityCountLabel, -45, SpringLayout.EAST, this);
    
    //Put your custom radio button info here
       
    panelLayout.putConstraint(SpringLayout.NORTH, clueLabel, 10, SpringLayout.SOUTH, answerField);
    panelLayout.putConstraint(SpringLayout.NORTH, answerField, 40, SpringLayout.SOUTH, celebrityRadio);
    panelLayout.putConstraint(SpringLayout.WEST, answerField, 0, SpringLayout.WEST, celebrityRadio);
    panelLayout.putConstraint(SpringLayout.EAST, answerField, -15, SpringLayout.EAST, this);
    
    panelLayout.putConstraint(SpringLayout.WEST, clueField, 0, SpringLayout.WEST, celebrityRadio);
    panelLayout.putConstraint(SpringLayout.SOUTH, clueField, 55, SpringLayout.SOUTH, answerField);
    panelLayout.putConstraint(SpringLayout.EAST, clueField, 0, SpringLayout.EAST, answerField);
    panelLayout.putConstraint(SpringLayout.WEST, startButton, 0, SpringLayout.WEST, celebrityRadio);
    panelLayout.putConstraint(SpringLayout.EAST, startButton, 0, SpringLayout.EAST, answerField);
  }
  
  /**
   * Used to link all Listeners to the associated GUI components.
   */
  private void setupListeners() {
    addCelebrityButton.addActionListener(this);
    startButton.addActionListener(this);
  }


  /**
   * Validation method for the text to create a Celebrity object.
   *
   * @param answerText
   *            The name of the Celebrity. Validation requires at least 4
   *            characters.
   * @param clueText
   *            The text for the clue. Validation depends on the selected
   *            Celebrity type, but at least 10 characters are required.
   * @return Whether the appropriate text amounts are filled and the correct
   *         type of clue is given.
   */
  private boolean validate(String answerText, String clueText) {
    boolean validClue = false;
    boolean validAnswer = false;
    validClue = controller.validateClue(clueText, "");
    validAnswer = controller.validateCelebrity(answerText);
    return (validClue && validAnswer);
  }

  private void invalidInput() {
    answerField.setText("Type in the celebrity!!");
    answerField.setBackground(Color.RED);
    clueField.setText("Type in the clue");
    clueField.setBackground(Color.RED);
  }
  
  private void addToGame() {
    String type = "Celebrity";
    String answer = answerField.getText().trim();
    String clue = clueField.getText().trim();
    answerField.setText("");
    clueField.setText("");
    controller.addCelebrity(answer, clue, type);
    startButton.setEnabled(true);
  }

  public void actionPerformed(ActionEvent ae) {
    Object source = ae.getSource();
    JButton clickedButton = (JButton) source;
    String text = clickedButton.getText();

    if (text.equals("Add current celebrity")) {
      // when "add celebrity" button gets clicked:
      answerField.setBackground(Color.WHITE);
      clueField.setBackground(Color.WHITE);
      if (validate(answerField.getText(), clueField.getText())) {
        addToGame();
      } else {
        invalidInput();
      }
      celebrityCount = controller.getCelebrityGameSize();
      celebrityCountLabel.setText(countLabelText + celebrityCount);
    } else if (text.equals("Start Celebrity game")) {
      controller.play();
    }
  }
}
