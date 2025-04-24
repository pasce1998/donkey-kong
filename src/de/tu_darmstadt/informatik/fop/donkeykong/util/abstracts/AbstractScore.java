package de.tu_darmstadt.informatik.fop.donkeykong.util.abstracts;

import java.time.LocalDateTime;

/**
 * Abstract class with dummy methods for Score
 * @author Pascal Schikora
 * @author Egemen Ulutürk
 *
 */
public abstract class AbstractScore implements Comparable<AbstractScore> {

	/**
	 * Validates a score entry
	 * @param date the date when the score was achieved
	 * @param score the score which was achieved
	 * @throws throws an IllegalArgumentException if the entries are not valid entries
	 */
	public abstract void validate(LocalDateTime date, int score, String name);
	
	/**
	 * Returns the date as a string in the appropriate format
	 * @return the date formatted as a string
	 */
	public abstract String getDate();
	
	/**
	 * Returns the achieved score
	 * @return the score as an integer value
	 */
	public abstract int getScore();
	
	/**
	 * Returns the player name
	 * @return the player who achieved the score
	 */
	public abstract String getPlayerName();
	
}
