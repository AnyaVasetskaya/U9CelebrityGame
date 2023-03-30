package part1startercode;

import java.util.ArrayList;

/**
 * The framework for the Celebrity Game project
 * 
 * @author cody.henrichsen
 * @version 2.3 25/09/2018 refactored the prepareGame and play methods
 */
public class CelebrityGame {
	/**
	 * A reference to a Celebrity or subclass instance.
	 */
	private Celebrity gameCelebrity;

	/**
	 * The GUI frame for the Celebrity game.
	 */
	private CelebrityFrame gameWindow;

	/**
	 * The ArrayList of Celebrity values that make up the game
	 */
	private ArrayList<Celebrity> celebGameList;

	/**
	 * Builds the game and starts the GUI
	 */
	public CelebrityGame() {
		celebGameList = new ArrayList<Celebrity>();
		gameWindow = new CelebrityFrame(this);
		gameCelebrity = null;
	}

	/**
	 * Determines if the supplied guess is correct.
	 * 
	 * @param guess
	 *            The supplied String
	 * @return Whether it matches regardless of case or extraneous external
	 *         spaces.
	 */
	public boolean processGuess(String guess) {
		if (guess.toLowerCase().trim().equals(gameCelebrity.getAnswer().toLowerCase().trim())) {
			celebGameList.remove(0);
			if (celebGameList.size() > 0)
				gameCelebrity = celebGameList.get(0);
			else
				gameCelebrity = new Celebrity("", "");
			return true;
		}
		return false;
	}

	/**
	 * Asserts that the list is initialized and contains at least one Celebrity.
	 * Sets the current celebrity as the first item in the list. Opens the game
	 * play screen.
	 */
	public void play() {
		if (celebGameList != null && celebGameList.size() > 0) {
			gameCelebrity = celebGameList.get(0);
			gameWindow.replaceScreen("GAME");
		}
	}

	/**
	 * Adds a Celebrity of specified type to the game list
	 * 
	 * @param name  The name of the celebrity
	 * @param clue  The clue for the celebrity
	 * @param type  What type of celebrity
	 */
	public void addCelebrity(String name, String clue, String type) {
		Celebrity c = new Celebrity(name, clue);
		celebGameList.add(c);
	}

	/**
	 * Validates the name of the celebrity. It must have at least 4 characters.
	 * @param name The name of the Celebrity
	 * @return If the supplied Celebrity is valid
	 */
	public boolean validateCelebrity(String name) {
		return name.trim().length() >= 3;
	}
	/**
	 * Checks that the supplied clue has at least 10 characters or is a series of clues
	 * @param clue The text of the clue(s)
	 * @param type Supports a subclass of Celebrity 
	 * @return If the clue is valid.
	 */
	public boolean validateClue(String clue, String type) {
		return clue.trim().length() >= 10;
	}

	/**
	 * Accessor method for the current size of the list of celebrities
	 * 
	 * @return Remaining number of celebrities
	 */
	public int getCelebrityGameSize() {
		return celebGameList.size();  // stub
	}

	/**
	 * Accessor method for the games clue to maintain low coupling between
	 * classes
	 * 
	 * @return The String clue from the current celebrity.
	 */
	public String sendClue() {
		return gameCelebrity.getClue();
	}

	public void reset() {
		gameWindow.dispose();
	}
}
