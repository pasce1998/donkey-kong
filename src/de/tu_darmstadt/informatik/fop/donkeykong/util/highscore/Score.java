package de.tu_darmstadt.informatik.fop.donkeykong.util.highscore;

import java.time.LocalDateTime;

import de.tu_darmstadt.informatik.fop.donkeykong.util.Consts;
import de.tu_darmstadt.informatik.fop.donkeykong.util.abstracts.AbstractScore;

/**
 * Class for tracking a score
 * @author Pascal Schikora
 * @author Egemen Ulutürk
 *
 */
public class Score extends AbstractScore {

	private LocalDateTime date;
	private int score;
	private String playerName;
	
	/**
	 * Constructor
	 * @param date the date when the score was achieved
	 * @param score the achieved score
	 * @param playerName the player who achieved the score
	 */
	public Score(final LocalDateTime date, final int score, final String playerName) {
		this.validate(date, score, playerName);
		this.date = date;
		this.score = score;
		this.playerName = playerName;
	}
	
	/**
	 * Constructor for generating a score out of a string
	 * @param data the string to decode
	 */
	public Score(final String data) {
		if(data == null || data.isEmpty()) throw new IllegalArgumentException();
		final String[] fields = data.split(Consts.SCORE_SEPERATOR);
		if(fields.length != 3) throw new IllegalArgumentException();
		try{
			LocalDateTime date = LocalDateTime.parse(fields[0], Consts.SCORE_FORMATTER);
			int score = Integer.valueOf(fields[1]);
			validate(date, score, fields[2]);
			this.score = score;
			this.date = date;
			this.playerName = fields[2];
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void validate(LocalDateTime date, int score, String playerName) {
		if(date == null) {
			throw new IllegalArgumentException();
		}
		if(score < 0) {
			throw new IllegalArgumentException();
		}
		if(playerName == null || playerName.isEmpty()) {
			throw new IllegalArgumentException();
		}
	}
	
	/**
	 * Checks if an object is equal to the current score
	 * @return returns true if the values are equal, returns false if the values are not equal or the object is no score
	 */
	@Override
	public boolean equals(Object obj) {
		if(obj == null || !(obj instanceof Score)) return false;
		final Score other = (Score) obj;
		return this.date.equals(other.date) && this.score == other.score && this.playerName.equals(other.playerName);
	}
	
	/**
	 * Compares one score to another and determines if it is less, equal or more than the given other score
	 * @return returns a negative number, zero or a positive number depending on the cases listed above
	 */
	@Override
	public int compareTo(AbstractScore other) {
		if(other == null) throw new IllegalArgumentException();
		int comparison =  other.getScore() - this.getScore();
		if(comparison == 0) {
			return this.getDate().compareTo(other.getDate());
		}
		return comparison;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public String getDate() {
		return this.date.format(Consts.SCORE_FORMATTER);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public int getScore() {
		return score;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public String getPlayerName() {
		return playerName;
	}
	
	/**
	 * Returns the score as a string with the date and score seperated by the score seperator
	 */
	@Override
	public String toString() {
		return this.date.format(Consts.SCORE_FORMATTER) + Consts.SCORE_SEPERATOR + this.score + Consts.SCORE_SEPERATOR + this.playerName;
	}
}
