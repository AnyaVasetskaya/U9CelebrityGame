package part1startercode;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.*;

/**
 * GUI Frame class for the Celebrity Game
 * @author cody.henrichsen
 * @version 2.1 17/09/2018
 */
public class CelebrityFrame extends JFrame {
	/**
	 * The instance of the panel used for playing the game.
	 */
	private CelebrityPanel gamePanel;
	
	/**
	 * Blank panel used for the CardLayout switching screens feature.
	 */
	private JPanel panelCards;
	
	/**
	 * The instance variable used to add a celebrity to the game.
	 */
	private StartPanel startPanel;
	
	/**
	 * A reference to the CelebrityGame instance to allow for minimized coupling in the object structure.
	 */
	private CelebrityGame controller;
	
	
	/**
	 * Builds an instance of the CelebFrame with a reference to the CelebrityGame instance.
	 * @param controllerRef A reference to a CelebrityGame to share with the CelebPanel instance.
	 */
	public CelebrityFrame(CelebrityGame controllerRef) {
		super();
		controller = controllerRef;
		panelCards = new JPanel(new CardLayout());
		gamePanel = new CelebrityPanel(controller);
		startPanel = new StartPanel(controller);
		setupFrame();
	}
	
	/**
	 * Configures the JFrame window subclass to add the panel and set size based information.
	 */
	private void setupFrame() {
		// the following are methods INHERITED from JFrame that we can call since
		// CelebrityFrame is a subclass of JFrame
		setSize(1000, 600);  // (window width, window height)
		setTitle("Guess Who!");
		add(panelCards);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// the following calls the add method on the instance variable panelCards,
		// which is a JPanel object (the JPanel class has an add method)
		panelCards.add(gamePanel, "GAME");
		panelCards.add(startPanel, "START");

		// helper method defined below
		replaceScreen("START");

		// must be the last line of the configuration to allow the GUI to be displayed.
		// If not set as true the window will not display and the app will terminate.
		setVisible(true);
	}
	
	/**
	 * Swaps the screen between the existing panels based on the String parameter.
	 * @param screen The name of the screen to open.
	 */
	public void replaceScreen(String screen) {
		if (screen.equals("GAME")) {
			// send the first clue to the screen
			gamePanel.addClue(controller.sendClue());
		}

		// present the appropriate panel based on the name provided in "screen"
		// (either "START" or "GAME")
		LayoutManager layout = panelCards.getLayout();
		CardLayout cardLayout = (CardLayout) layout;
		cardLayout.show(panelCards, screen);
	}
}
