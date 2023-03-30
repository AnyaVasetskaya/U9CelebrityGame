package part1startercode;

/**
 * Celebrity base class for the Celebrity game.
 * @author cody.henrichsen
 * @version 1.4 17/09/2018
 */
public class Celebrity {
	/**
	 * The clue to determine the celebrity
	 */
	private String clue;

	/**
	 * The answer (the name of the celebrity)
	 */
	private String answer;

	/**
	 * Creates a Celebrity instance with the supplied answer and clue
	 * @param answer  the answer (name of celebrity)
	 * @param clue  the clue
	 */
	public Celebrity(String answer, String clue) {
		this.clue = clue;
		this.answer = answer;
	}

	/**
	 * Supplies the clue for the celebrity
	 * @return
	 */
	public String getClue() {
		return clue;
	}

	/**
	 * Supplies the answer for the celebrity.
	 * @return
	 */
	public String getAnswer() {
		return answer;
	}

	/**
	 * Updates the clue to the provided String.
	 * @param newClue The new clue.
	 */
	public void setClue(String newClue) {
		clue = newClue;
	}

	/**
	 * Updates the answer to the provided String.
	 * @param newAnswer The new answer.
	 */
	public void setAnswer(String newAnswer) {
		answer = newAnswer;
	}

	public String toString() { return "The Celebrity's name is " + answer + ". The clue for this celebrity is: " + clue + "."; }
}
