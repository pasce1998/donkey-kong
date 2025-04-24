package de.tu_darmstadt.informatik.fop.donkeykong.util.interfaces;

import java.util.List;

import de.tu_darmstadt.informatik.fop.donkeykong.util.abstracts.AbstractScore;

/**
 * Interface with dummy methods for Highscores
 * @author Pascal Schikora
 * @author Egemen Ulutürk
 *
 */
public interface IHighscores {

	/**
	 * Loads the entries from a file
	 * @return returns true if they could be loaded successfully, false if not
	 */
	public boolean loadHighscores();
	
	/**
	 * Saves the highscores to a file
	 * @return true when the data was saved successfully and false if not
	 */
	public boolean saveToFile();
	
	/**
	 * Adds a new entry to the list and checks for max. displayable entries
	 * @param entry the entry to add
	 * @return true when the entry could be added, false if not
	 */
	public boolean addEntry(AbstractScore score);
	
	/**
	 * Getter for the HighscoreEntries
	 * @return a list of highscores
	 */
	public List<AbstractScore> getHighscores();
}
